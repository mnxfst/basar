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

import java.util.concurrent.CountDownLatch;

import akka.actor.UntypedActor;

import com.mnxfst.basar.message.switchboard.PingTestMsg;
import com.mnxfst.basar.message.switchboard.PongTestMsg;

/**
 * Test actor for sending {@link PingTestMsg ping messages}
 * @author mnxfst
 * @since 12.06.2013
 *
 * Revision Control Info $Id$
 */
public class PingSendingTestActor extends UntypedActor {
	
	private int pongCounter = 0;
	private final CountDownLatch pongLatch;
	
	public PingSendingTestActor(final CountDownLatch pongLatch) {
		this.pongLatch = pongLatch;
	}
	
	/**
	 * @see akka.actor.UntypedActor#onReceive(java.lang.Object)
	 */
	public void onReceive(Object message) throws Exception {

		if(message instanceof PongTestMsg && pongLatch.getCount() > 0) {			
			PongTestMsg pong = (PongTestMsg)message;			
			getSender().tell(new PingTestMsg(pong.getSourceRef(), pong.getSequenceId(), System.currentTimeMillis()), getSelf());
			
			pongCounter = pongCounter + 1;
			pongLatch.countDown();
			
//			if(pongCounter % 1000 == 0)
//				System.out.println("Pongs received: " + pongCounter);
		}
		
	}

}
