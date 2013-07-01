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
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import akka.actor.Actor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActorFactory;
import akka.routing.RoundRobinRouter;

import com.mnxfst.basar.message.switchboard.PingTestMsg;
import com.mnxfst.basar.message.switchboard.PongTestMsg;

/**
 * Test case for {@link Switchboard}
 * @author mnxfst
 * @since 12.06.2013
 *
 * Revision Control Info $Id$
 */
public class SwitchboardTest {

	// actor system used throughout the test class but gets initialized prior each test execution
	private ActorSystem actorSystem = null;

	/**
	 * Initialize the test actor system
	 * @throws Exception
	 */
	@Before
	public void initialize() throws Exception {
		actorSystem = ActorSystem.create("junit-basar");
	}
	
	@Test
	public void testSendMulticast() throws Exception {
		
		final CountDownLatch pongLatch = new CountDownLatch(2000000);
		final Props pingActorProps = new Props(new UntypedActorFactory() {
			private static final long serialVersionUID = 1L;
			public Actor create() throws Exception {
				return new PingSendingTestActor(pongLatch);
			}
		});
		final Props pongRespondingActorProps = new Props(new UntypedActorFactory() {
			private static final long serialVersionUID = 1L;
			public Actor create() throws Exception {
				return new PongSendingTestActor(true);
			}
		});
		final Props pongNoRespondingActorProps = new Props(new UntypedActorFactory() {
			private static final long serialVersionUID = 1L;
			public Actor create() throws Exception {
				return new PongSendingTestActor(false);
			}
		});

		
//		actorSystem.
		final ActorRef pingActorRef = actorSystem.actorOf(pingActorProps, "pingActor");
		final ActorRef pongActorRef = actorSystem.actorOf(pongRespondingActorProps, "pongActor");
		final ActorRef pongNoRespondingActorRef = actorSystem.actorOf(pongNoRespondingActorProps, "pongNoResponseActor");
		final ActorRef pongRespondingActorRef = actorSystem.actorOf(pongRespondingActorProps, "pongResponseActor");
		final ActorRef pongRespondingActorRef2 = actorSystem.actorOf(pongRespondingActorProps, "pongResponseActor2");
		final ActorRef switchboard = actorSystem.actorOf(new Props(Switchboard.class).withRouter(new RoundRobinRouter(15)), "switchboard");
		
		Switchboard.registerMessageTypeListener(switchboard, pingActorRef, pingActorRef, PongTestMsg.class, "seq-1");
		Switchboard.registerMessageTypeListener(switchboard, pongActorRef, pongActorRef, PingTestMsg.class, "seq-2");
//		Switchboard.registerMessageTypeListener(switchboard, pongNoRespondingActorRef, pongNoRespondingActorRef, PingTestMsg.class, "seq-3");
		Switchboard.registerMessageTypeListener(switchboard, pongRespondingActorRef, pongRespondingActorRef, PingTestMsg.class, "seq-4");
		Switchboard.registerMessageTypeListener(switchboard, pongRespondingActorRef2, pongRespondingActorRef2, PingTestMsg.class, "seq-5");
		
		Thread.sleep(500);
		
		long start = System.currentTimeMillis();
		switchboard.tell(new PingTestMsg(pingActorRef.path().toString(), "seq-3", System.currentTimeMillis()), pingActorRef);
		switchboard.tell(new PingTestMsg(pingActorRef.path().toString(), "seq-4", System.currentTimeMillis()), pingActorRef);
		
		pongLatch.await(8, TimeUnit.SECONDS);
		long end = System.currentTimeMillis();
		System.out.println("Duration: " + (end-start) + "ms");
		Assert.assertEquals("The latch must have counted down to 0", 0, pongLatch.getCount());		
		
//		
//		final CountDownLatch expectedStatsMessagesCounter1 = new CountDownLatch(5);
//		final CountDownLatch expectedStatsMessagesCounter2 = new CountDownLatch(5);
//		
//		final Props statsTestActor1Props = new Props(new UntypedActorFactory() {
//			private static final long serialVersionUID = 1L;
//			public Actor create() throws Exception {				
//				return new SwitchboardStatsTestActor(expectedStatsMessagesCounter1);
//			}
//		});
//		final TestActorRef<SwitchboardStatsTestActor> statsTestRef1 = TestActorRef.create(actorSystem, statsTestActor1Props, "switchbordStatReceiver1");
//		final SwitchboardStatsTestActor statsTestActor1 = statsTestRef1.underlyingActor();
//		
//		final Props statsTestActor2Props = new Props(new UntypedActorFactory() {
//			private static final long serialVersionUID = 1L;
//			public Actor create() throws Exception {				
//				return new SwitchboardStatsTestActor(expectedStatsMessagesCounter2);
//			}
//		});
//		final TestActorRef<SwitchboardStatsTestActor> statsTestRef2 = TestActorRef.create(actorSystem, statsTestActor2Props, "switchbordStatReceiver2");
//		final SwitchboardStatsTestActor statsTestActor2 = statsTestRef2.underlyingActor();
//		
//		ActorRef sbRef = actorSystem.actorOf(new Props(Switchboard.class).withRouter(new RoundRobinRouter(5)), "switchboard");
//		
//		Switchboard.registerMessageTypeListener(sbRef, sbRef, sbRef, RegisterMessageReceiverMsg.class, "test-seq-1");
//		Switchboard.registerMessageTypeListener(sbRef, sbRef, actorSystem.actorFor("wtf"), RegisterMessageReceiverMsg.class, "test-seq2");
//		Switchboard.registerMessageTypeListener(sbRef, statsTestRef1, statsTestRef1, RegisterMessageReceiverMsg.class, "test-seq3");
//		Switchboard.registerMessageTypeListener(sbRef, statsTestRef1, statsTestRef1, SwitchboardStatsRequestMsg.class, "test-seq4");
//		Switchboard.registerMessageTypeListener(sbRef, statsTestRef2, statsTestRef2, SwitchboardStatsRequestMsg.class, "test-seq5");
//		Switchboard.registerMessageTypeListener(sbRef, statsTestRef2, statsTestRef2, SwitchboardStatsRequestMsg.class, "test-seq5");
//		
//		Switchboard.requestSwitchboardStats(sbRef, statsTestRef1, "test-seq7");
//		
//		expectedStatsMessagesCounter1.await(1000, TimeUnit.MILLISECONDS);
//		Assert.assertEquals("The count down latch must show a value of 0", 0, expectedStatsMessagesCounter1.getCount());
//		Assert.assertEquals("The number of registered message types must be 2", 2, statsTestActor1.getReportedStats().size());
//		Assert.assertEquals("The number of registered listeners for type '"+RegisterMessageReceiverMsg.class.getName()+"'", 2, statsTestActor1.getReportedStats().get(RegisterMessageReceiverMsg.class.getName()).intValue());
//		Assert.assertEquals("The number of registered listeners for type '"+SwitchboardStatsRequestMsg.class.getName()+"'", 2, statsTestActor1.getReportedStats().get(SwitchboardStatsRequestMsg.class.getName()).intValue());
//
//		Switchboard.registerMessageTypeListener(sbRef, sbRef, actorSystem.actorFor("wtf-again"), RegisterMessageReceiverMsg.class, "test-seq6");
//		Switchboard.requestSwitchboardStats(sbRef, statsTestRef2, "test-seq8");
//		expectedStatsMessagesCounter2.await(1000, TimeUnit.MILLISECONDS);
//		Assert.assertEquals("The count down latch must show a value of 0", 0, expectedStatsMessagesCounter2.getCount());
//		Assert.assertEquals("The number of registered message types must be 2", 2, statsTestActor2.getReportedStats().size());
//		Assert.assertEquals("The number of registered listeners for type '"+RegisterMessageReceiverMsg.class.getName()+"'", 3, statsTestActor2.getReportedStats().get(RegisterMessageReceiverMsg.class.getName()).intValue());
//		Assert.assertEquals("The number of registered listeners for type '"+SwitchboardStatsRequestMsg.class.getName()+"'", 2, statsTestActor2.getReportedStats().get(SwitchboardStatsRequestMsg.class.getName()).intValue());
//
//		actorSystem.shutdown();
		
	}
	
}
