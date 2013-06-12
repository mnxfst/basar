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

import org.junit.Test;

import com.mnxfst.basar.message.switchboard.RegisterMessageReceiverMsg;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.routing.Broadcast;
import akka.routing.RoundRobinRouter;

/**
 * Test case for {@link Switchboard}
 * @author mnxfst
 * @since 12.06.2013
 *
 * Revision Control Info $Id$
 */
public class SwitchboardTest {

	@Test
	public void testSendMulticast() throws Exception {
		
		ActorSystem system = ActorSystem.create("junit");
		ActorRef sbRef = system.actorOf(new Props(Switchboard.class).withRouter(new RoundRobinRouter(5)), "switchboard");
		
		sbRef.tell(new RegisterMessageReceiverMsg(sbRef.path().name(), "test-seq1", System.currentTimeMillis(), "wtf", RegisterMessageReceiverMsg.class), sbRef);
		sbRef.tell(new RegisterMessageReceiverMsg(sbRef.path().name(), "test-seq2", System.currentTimeMillis(), sbRef.path().toString(), RegisterMessageReceiverMsg.class), sbRef);
		sbRef.tell(new RegisterMessageReceiverMsg(sbRef.path().name(), "test-seq3", System.currentTimeMillis(), sbRef.path().toString(), RegisterMessageReceiverMsg.class), sbRef);
		sbRef.tell(new RegisterMessageReceiverMsg(sbRef.path().name(), "test-seq4", System.currentTimeMillis(), sbRef.path().toString(), RegisterMessageReceiverMsg.class), sbRef);
		sbRef.tell(new RegisterMessageReceiverMsg(sbRef.path().name(), "test-seq5", System.currentTimeMillis(), sbRef.path().toString(), RegisterMessageReceiverMsg.class), sbRef);
		sbRef.tell(new RegisterMessageReceiverMsg(sbRef.path().name(), "test-seq6", System.currentTimeMillis(), sbRef.path().toString(), RegisterMessageReceiverMsg.class), sbRef);
		sbRef.tell(new Broadcast("Broadcast"), sbRef);
		
		
		
		Thread.sleep(1000);
		
		system.shutdown();
		
	}
	
}
