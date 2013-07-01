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

import java.util.Set;
import java.util.concurrent.Callable;

import akka.actor.ActorRef;

import com.mnxfst.basar.message.BasarMessage;

/**
 * Propagates a provided message towards the given receivers
 * TODO check if this can be enhanced by providing just one listener and have the job instantiated as much as there are listeners: one instance per listener
 * @author mnxfst
 * @since 12.06.2013
 *
 * Revision Control Info $Id$
 */
public class MessagePropagationJob implements Callable<Boolean> {

	private final BasarMessage basarMessage;
	private final Set<ActorRef> listeners;
	private final ActorRef senderRef;
	
	/**
	 * Initialize the job using the provided information
	 * @param basarMessage
	 * @param listeners
	 */
	public MessagePropagationJob(final BasarMessage basarMessage, Set<ActorRef> listeners, final ActorRef senderRef) {		
		this.basarMessage = basarMessage;
		this.listeners = listeners;	
		this.senderRef = senderRef;
	}
	
	/**
	 * @see java.util.concurrent.Callable#call()
	 */
	public Boolean call() throws Exception {		
		
		if(listeners != null && !listeners.isEmpty()) {
			for(ActorRef actorRef : listeners) {
				actorRef.tell(basarMessage, senderRef);
			}
		
			return Boolean.TRUE;
		}
		
		return Boolean.FALSE;
	}

}
