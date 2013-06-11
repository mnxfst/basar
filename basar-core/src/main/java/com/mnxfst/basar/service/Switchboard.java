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

import com.mnxfst.basar.message.BasarMessage;
import com.mnxfst.basar.message.flow.RegisterMessageReceiverMsg;

import akka.actor.UntypedActor;

/**
 * Receives <b>all</b> inbound messages and forwards them to all registered/interested components. Components
 * within a sub-domain use the switchboard as entry point for forwarding messages towards other sub-domains.
 * @author mnxfst
 * @since 11.06.2013
 *
 * Revision Control Info $Id$
 */
public class Switchboard extends UntypedActor {

	
	
	/**
	 * @see akka.actor.UntypedActor#onReceive(java.lang.Object)
	 */
	public void onReceive(Object message) throws Exception {

		// ensure that the received message belongs to the application space ...
		if(message instanceof BasarMessage) {
			
			
			
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
		
	}

}
