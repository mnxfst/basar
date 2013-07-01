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
package com.mnxfst.basar.switchboard;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.dispatch.Futures;
import akka.routing.Broadcast;

import com.mnxfst.basar.message.BasarMessage;
import com.mnxfst.basar.message.switchboard.ListenerRegistrationSuccessMsg;
import com.mnxfst.basar.message.switchboard.RegisterMessageListenerMsg;

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

	/** map of all available message types being associated with a set of listener that wish to be notified about these messages */
	private final Map<Class<? extends BasarMessage>, Set<ActorRef>> messageListeners = new HashMap<>();
	
	/**
	 * @see akka.actor.UntypedActor#onReceive(java.lang.Object)
	 */
	public void onReceive(Object message) throws Exception {

		// ensure that the received message belongs to the application space ...
		if(message instanceof BasarMessage) {

			//////////////////////////////////////////////////////////////////////////////////////////////
			// at first: check if the message contains any switchboard relevant content
			// switchboard relevant messages are:
			// - message type receiver registrations
			if(message instanceof RegisterMessageListenerMsg) {
				handleMessageReceiverRegistration((RegisterMessageListenerMsg)message);
			} else {
				propagateMessageTowardsListeners((BasarMessage)message);
			}
			//////////////////////////////////////////////////////////////////////////////////////////////			
			
		} else {			
			// ... otherwise forward to "unhandled" handler
			System.out.println("unhandled: " + message);
			unhandled(message);
		}		
	}
	
	/**
	 * Propagates the provided message to listeners being registered for the message type
	 * @param basarMessage
	 * TODO check what happens on heavy load, maybe we need a specialized dispatcher which queues inbound jobs
	 */
	protected void propagateMessageTowardsListeners(final BasarMessage basarMessage) {
		Futures.future(new MessagePropagationJob(basarMessage, this.messageListeners.get(basarMessage.getClass()), getSelf()), getContext().system().dispatcher());
	}
	
	/**
	 * Handles messages of type {@link RegisterMessageListenerMsg}. The component referenced through 
	 * contained {@link RegisterMessageListenerMsg#getReceiverRef()} will be registered for all messages
	 * of selected {@link RegisterMessageListenerMsg#getMessageType() type}. 
	 * @param msg
	 */
	protected void handleMessageReceiverRegistration(RegisterMessageListenerMsg msg) {

		if(StringUtils.startsWithIgnoreCase(getSelf().path().toString(), msg.getReceiverRef())) {
			System.out.println("Switchboard must not be able to register itself as message receiver");
			return;
		}
		
		// fetch actor as well the message type the contained receiver wishes to register itself for
		Class<? extends BasarMessage> messageType = msg.getMessageType();
		ActorRef receivingActorRef = getContext().system().actorFor(msg.getReceiverRef());
		
		// retrieve the list of message receivers for a specific type, add the actor and put back everything into the map
		Set<ActorRef> receivers = messageListeners.get(messageType);
		if(receivers == null)
			receivers = new HashSet<>();
		receivers.add(receivingActorRef);
		this.messageListeners.put(messageType, receivers);
		
		// finally: notify the actor which send the request about its successful registration - keep the sequence identifier to allow
		// the sender a valid association between request and response ... if he does in any way
		receivingActorRef.tell(new ListenerRegistrationSuccessMsg(getSelf().path().toString(), msg.getSequenceId(), System.currentTimeMillis()), getSelf());
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
		switchboardActorRef.tell(new Broadcast(new RegisterMessageListenerMsg(requestingActorRef.path().toString(), sequenceId, System.currentTimeMillis(), messageTypeListenerRef.path().toString(), messageType)), requestingActorRef);
	}
}
