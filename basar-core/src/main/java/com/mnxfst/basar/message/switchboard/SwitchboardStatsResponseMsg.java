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
package com.mnxfst.basar.message.switchboard;

import java.util.HashMap;
import java.util.Map;

import com.mnxfst.basar.message.BasarMessage;

/**
 * Holds the response to a {@link SwitchboardStatsRequestMsg switchboard stats request}
 * @author mnxfst
 * @since 12.06.2013
 *
 * Revision Control Info $Id$
 */
public class SwitchboardStatsResponseMsg extends BasarMessage {

	private static final long serialVersionUID = 2631343409983012855L;

	private final Map<String, Integer> registeredMessageTypeListeners = new HashMap<String, Integer>();

	/**
	 * Default constructor
	 */
	public SwitchboardStatsResponseMsg() {		
		super();
	}

	/**
	 * Initializes the message using the provided input
	 * @param sourceRef
	 * @param sequenceId
	 * @param created
	 */
	public SwitchboardStatsResponseMsg(String sourceRef, String sequenceId, long created) {
		super(sourceRef, sequenceId, created);
	}
	
	/**
	 * Sets the number of message listeners for a given message type
	 * @param messageType
	 * @param numOfListenersForType
	 */
	public void addMessageTypeListenerCount(String messageType, int numOfListenersForType) {
		this.registeredMessageTypeListeners.put(messageType, Integer.valueOf(numOfListenersForType));
	}
	
	/**
	 * Returns the number of listeners for a given message type. In case no such listener
	 * exists in the map, the result is null
	 * @param messageType
	 * @return
	 */
	public Integer getMessageTypeListenerCounts(String messageType) {
		return this.registeredMessageTypeListeners.get(messageType);
	}

	public Map<String, Integer> getRegisteredMessageTypeListeners() {
		return registeredMessageTypeListeners;
	}
	

}
