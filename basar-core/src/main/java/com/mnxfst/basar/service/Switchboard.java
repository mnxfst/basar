/**
 * basar - enhanced electronic marketplace
 * Copyright (C) 2013 Christian Kreutzfeldt
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.mnxfst.basar.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.routing.Broadcast;

import com.mnxfst.basar.message.BasarMessage;
import com.mnxfst.basar.message.switchboard.ReceiverRegistrationSuccessMsg;
import com.mnxfst.basar.message.switchboard.RegisterMessageReceiverMsg;
import com.mnxfst.basar.message.switchboard.SwitchboardStatsRequestMsg;
import com.mnxfst.basar.message.switchboard.SwitchboardStatsResponseMsg;

/**
 * Receives <b>all</b> inbound messages and forwards them to all registered/interested components. Components
 * within a sub-domain use the switchboard as entry point for forwarding messages towards other sub-domains.
 * 
 * TODO:
 * 	- step through message listeners periodically and remove invalid references
 *  - reason to notify the listeners in case their registration failed for any reason
 *  - implement a message counter that forwards its value through a switchboard to all registered listeners (eg. logging facilities or accounting components)
 *    (be aware that the counter needs to be reset after forwarding its current value to ensure that the communicated values are valid and
 *     the counter never stalls)
 *  - decide whether to send messages directly to the sender or retrieve it from the message by source reference lookup
 * @author mnxfst
 * @since 11.06.2013
 *
 * Revision Control Info $Id$
 */
public class Switchboard extends UntypedActor {

	private final Map<Class<? extends BasarMessage>, Set<ActorRef>> messageListeners = new HashMap<>();
	
	/**
	 * @see akka.actor.UntypedActor#onReceive(java.lang.Object)
	 */
	public void onReceive(Object message) throws Exception {

		// ensure that the received message belongs to the application space ...
		if(message instanceof BasarMessage) {

			//////////////////////////////////////////////////////////////////////////////////////////////
			// at first: check if the message contains any switchboard relevant content
			if(message instanceof RegisterMessageReceiverMsg) {
				handleMessageReceiverRegistration((RegisterMessageReceiverMsg)message);
			} // ... here might follow more relevant message types
			//////////////////////////////////////////////////////////////////////////////////////////////

			//////////////////////////////////////////////////////////////////////////////////////////////
			// now: fetch the list of listeners that registered themselves for a specific message type 
			//      that might even be listeners for those message types that are declard to be switchboard 
			//      relevant - eg. logging services or alike
			Set<ActorRef> messageTypeListeningActorRefs = messageListeners.get(message.getClass());
			if(messageTypeListeningActorRefs != null && !messageTypeListeningActorRefs.isEmpty()) {
				for(ActorRef receivingActorRef : messageTypeListeningActorRefs) {
					receivingActorRef.forward(message, getContext());
				}
			}
			//////////////////////////////////////////////////////////////////////////////////////////////			
			
		} else if (message instanceof String) {
			System.out.println("Received string: " + message);
		} else {			
			// ... otherwise forward to "unhandled" handler 
			unhandled(message);
		}		
	}
	
	/**
	 * Handles messages of tpye {@link RegisterMessageReceiverMsg}. The component referenced through 
	 * contained {@link RegisterMessageReceiverMsg#getReceiverRef()} will be registered for all messages
	 * of selected {@link RegisterMessageReceiverMsg#getMessageType() type}. 
	 * @param msg
	 */
	protected void handleMessageReceiverRegistration(RegisterMessageReceiverMsg msg) {

		// fetch actor as well the message type the contained receiver wishes to register itself for
		Class<? extends BasarMessage> messageType = msg.getMessageType();
		ActorRef receivingActorRef = getContext().system().actorFor(msg.getReceiverRef());
		
		// retrieve the list of message receivers for a specific type, add the actor and put back everything into the map
		Set<ActorRef> receivers = messageListeners.get(messageType);
		if(receivers == null)
			receivers = new HashSet<>();
		receivers.add(receivingActorRef);
		messageListeners.put(messageType, receivers);
		
		// finally: notify the actor which send the request about its successful registration - keep the sequence identifier to allow
		// the sender a valid association between request and response ... if he does in any way
		receivingActorRef.tell(new ReceiverRegistrationSuccessMsg(getSelf().path().toString(), msg.getSequenceId(), System.currentTimeMillis()), getSelf());
	}
	
	/**
	 * Handles messages of type {@link SwitchboardStatsRequestMsg}. The method builds a {@link SwitchboardStatsResponseMsg response}
	 * and {@link ActorRef#tell(Object, ActorRef) tells} the referenced source component about it. 
	 * @param msg
	 */
	protected void handleSwitchboardStatsRequest(SwitchboardStatsRequestMsg msg) {
		
		SwitchboardStatsResponseMsg response = new SwitchboardStatsResponseMsg(getSelf().path().toString(), msg.getSequenceId(), System.currentTimeMillis());
		ActorRef requestingActorRef = getContext().system().actorFor(msg.getSourceRef());
		if(!this.messageListeners.isEmpty()) {
			for(Class<? extends BasarMessage> msgType : this.messageListeners.keySet()) {
				Set<ActorRef> listeners = this.messageListeners.get(msgType);
				response.addMessageTypeListenerCount(msgType.getName(), (listeners != null ? listeners.size() : 0));
			}
		}
		requestingActorRef.tell(response, getSelf());
	}
	
	/**
	 * Convenience method for requesting {@link SwitchboardStatsRequestMsg statistics} from all {@link Switchboard switchboard} instances 
	 * @param switchboardActorRef
	 * @param requestingActorRef
	 * @param sequenceId
	 */
	public static void requestSwitchboardStats(final ActorRef switchboardActorRef, final ActorRef requestingActorRef, final String sequenceId) {		
		switchboardActorRef.tell(new Broadcast(new SwitchboardStatsRequestMsg(requestingActorRef.path().toString(), sequenceId, System.currentTimeMillis())), requestingActorRef);		
	}

	/**
	 * Convenience method for registering a new message listener at all {@link Switchboard switchboard} instances
	 * @param switchboardActorRef
	 * @param requestingActorRef
	 * @param messageTypeListenerRef
	 * @param messageType
	 * @param sequenceId
	 */
	public static void registerMessageTypeListener(final ActorRef switchboardActorRef, final ActorRef requestingActorRef, final ActorRef messageTypeListenerRef, final Class<? extends BasarMessage> messageType, final String sequenceId) {
		switchboardActorRef.tell(new Broadcast(new RegisterMessageReceiverMsg(requestingActorRef.path().toString(), sequenceId, System.currentTimeMillis(), messageTypeListenerRef.path().toString(), messageType)), requestingActorRef);
	}
}
