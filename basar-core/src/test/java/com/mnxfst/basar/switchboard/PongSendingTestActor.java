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

import akka.actor.UntypedActor;

import com.mnxfst.basar.message.switchboard.PingTestMsg;
import com.mnxfst.basar.message.switchboard.PongTestMsg;

/**
 * Test actor for sending {@link PongTestMsg pong messages}
 * @author mnxfst
 * @since 12.06.2013
 *
 * Revision Control Info $Id$
 */
public class PongSendingTestActor extends UntypedActor {

	private boolean respond = true;
	private int pingCounter = 0;	
	
	public PongSendingTestActor(boolean respond) {
		this.respond = respond;
	}
	
	/**
	 * @see akka.actor.UntypedActor#onReceive(java.lang.Object)
	 */
	public void onReceive(Object message) throws Exception {
		
		if(message instanceof PingTestMsg) {
			PingTestMsg ping = (PingTestMsg)message;
			getSender().tell(new PongTestMsg(ping.getSourceRef(), ping.getSequenceId(), System.currentTimeMillis()), getSelf());
			pingCounter = pingCounter + 1;
			if(!respond && pingCounter % 1000 == 0)
				System.out.println("Pings received: " + pingCounter + " by " + getSelf().path());
		}		
	}

}
