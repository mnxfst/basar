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

import com.mnxfst.basar.message.BasarMessage;
import com.mnxfst.basar.service.Switchboard;

/**
 * Send by the {@link Switchboard switchboard} in respond to a successful {@link RegisterMessageReceiverMsg message type receiver registration} 
 * @author mnxfst
 * @since 12.06.2013
 *
 * Revision Control Info $Id$
 */
public class ReceiverRegistrationSuccessMsg extends BasarMessage {

	private static final long serialVersionUID = -9062074420513052020L;

	public ReceiverRegistrationSuccessMsg() {
		super();
	}
	
	public ReceiverRegistrationSuccessMsg(String sourceRef, String sequenceId, long created) {
		super(sourceRef, sequenceId, created);
	}
	
}
