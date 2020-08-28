package Actors;

import Core.Dc;
import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;

public class ActorAdd extends AbstractActor {
    public static Props props() {
        System.out.println("creating an actor............");
        return Props.create(ActorAdd.class, () -> new ActorAdd());
    }


    @Override
    public void preStart() {
        System.out.println("Demo Actor Prestarted" + getContext());
    }


    @Override
    public void postStop() {
        System.out.println("Demo Actor Post Stop");
    }

    public void Add(Dc dc) {
        ActorRef actorRef = getContext().actorOf(ActorAdd.props(), "ActorAdd");
        System.out.println("Addition:" + dc.num1 + "and" + dc.num2 + "is" + ( dc.num1 + dc.num2 ));
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(String.class, strMsg -> {
                    System.out.println("Demo Actor got message " + strMsg);
                })
                .match(Dc.class, dcobj -> {
                    Add(dcobj);
                })
                .build();
    }
}
