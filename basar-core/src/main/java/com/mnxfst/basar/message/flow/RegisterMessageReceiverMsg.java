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
package com.mnxfst.basar.message.flow;

import com.mnxfst.basar.message.BasarMessage;
import com.mnxfst.basar.service.Switchboard;

/**
 * Message to notify the {@link Switchboard switchboard} about a component being interested in
 * messages of a certain type. 
 * @author mnxfst
 * @since 11.06.2013
 *
 * Revision Control Info $Id$
 */
public class RegisterMessageReceiverMsg extends BasarMessage {

	private static final long serialVersionUID = 2248591190982783515L;

	/** reference of component being interested in messages of certain type */
	private String receiverRef = null;
	
	/** names the message type the component is interested in */
	private Class<? extends BasarMessage> messageType = null;
	
	/**
	 * Default constructor
	 */
	public RegisterMessageReceiverMsg() {		
	}
	
	/**
	 * Initializes a message instance using the provided information 
	 * @param sourceRef
	 * @param sequenceId
	 * @param created
	 * @param receiverRef
	 * @param messageType
	 */
	public RegisterMessageReceiverMsg(String sourceRef, String sequenceId, long created, String receiverRef, Class<? extends BasarMessage> messageType) {
		super(sourceRef, sequenceId, created);
		this.receiverRef = receiverRef;
		this.messageType = messageType;
	}

	public String getReceiverRef() {
		return receiverRef;
	}

	public void setReceiverRef(String receiverRef) {
		this.receiverRef = receiverRef;
	}

	public Class<? extends BasarMessage> getMessageType() {
		return messageType;
	}

	public void setMessageType(Class<? extends BasarMessage> messageType) {
		this.messageType = messageType;
	}
	
}
