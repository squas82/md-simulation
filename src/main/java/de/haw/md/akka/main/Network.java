package de.haw.md.akka.main;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.cluster.pubsub.DistributedPubSub;
import akka.cluster.pubsub.DistributedPubSubMediator;
import de.haw.md.akka.msg.ActorController;
import de.haw.md.akka.msg.NetworkMsgModel;
import de.haw.md.helper.StaticValues;


/**
 * Network Class implementing package creation and onReceive methods
 * reacts to "Tick" Message
 * 
 * @author Sascha Waltz
 *
 */
public class Network extends UntypedActor {

	private ActorRef mediator = DistributedPubSub.get(getContext().system()).mediator();

	private String channel;

	public Network(String channel) {
		this.channel = channel;
		NetworkContainer.getInstance().setNetwork(this);
	}

	@Override
	public void onReceive(Object msg) throws Exception {
		if (msg instanceof String) {
			if (((String) msg).contains("Tick")) {
				NetworkMsgModel createPackage = createPackage();
				mediator.tell(new DistributedPubSubMediator.Publish(channel, createPackage), getSelf());
			}
		} else
			unhandled(msg);
	}
	
	public void sendActorController(String id, boolean active){
		ActorController actorController = new ActorController(id, active);
		mediator.tell(new DistributedPubSubMediator.Publish(channel, actorController), getSelf());
	}

	public NetworkMsgModel createPackage() {
		String src;
		String dst;
		int countOfRoutes = StaticValues.ROUTES.length - 1;
		do {
			src = StaticValues.ROUTES[StaticValues.randInt(0, countOfRoutes)].split("-")[StaticValues.randInt(0, 1)];
			dst = StaticValues.ROUTES[StaticValues.randInt(0, countOfRoutes)].split("-")[StaticValues.randInt(0, 1)];
		} while (src.compareToIgnoreCase(dst) == 0);
		return new NetworkMsgModel(StaticValues.generatePackageID(), src, dst, StaticValues.generatePackageID().getBytes());
	}

}
