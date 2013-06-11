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
package com.mnxfst.basar.message;

import java.io.Serializable;

/**
 * Common base to all messages floating around in the application space
 * @author mnxfst
 * @since 11.06.2013
 *
 * Revision Control Info $Id$
 */
public abstract class BasarMessage implements Serializable {

	private static final long serialVersionUID = 698576120660153646L;

	/** unique identifier which helps to track the message within the application */
	private String sequenceId = null;
	
	/** actor reference to source component which created this message */
	private String sourceRef = null;
	
	/** timestamp set on message creation */
	private long created = 0;
	
	/**
	 * Default constructor - obviously 
	 */
	public BasarMessage() {		
	}
	
	/**
	 * Initializes the message using the provided information - obviously .. again :-)
	 * @param sourceRef
	 * @param sequenceId
	 * @param created
	 */
	public BasarMessage(String sourceRef, String sequenceId, long created) {
		this.sourceRef = sourceRef;
		this.sequenceId = sequenceId;
		this.created = created;
	}

	public String getSequenceId() {
		return sequenceId;
	}

	public void setSequenceId(String sequenceId) {
		this.sequenceId = sequenceId;
	}

	public String getSourceRef() {
		return sourceRef;
	}

	public void setSourceRef(String sourceRef) {
		this.sourceRef = sourceRef;
	}

	public long getCreated() {
		return created;
	}

	public void setCreated(long created) {
		this.created = created;
	}
	
	
	
	
}
