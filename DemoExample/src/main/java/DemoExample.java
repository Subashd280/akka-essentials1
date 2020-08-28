import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;

import java.util.Optional;


public class DemoExample extends AbstractActor {

    public static Props props(){
        System.out.println("Creating an Actor......");
        return Props.create(DemoExample.class,()->new DemoExample());
    }



    @Override
    public void preStart(){
        System.out.println("DemoExample prestarted.........");
        postStop();
    }
@Override
    public void preRestart(Throwable reason, Optional<Object> message) {
    System.out.println("DemoExample preReStarted..........");
        for (ActorRef each : getContext().getChildren()) {
            getContext().unwatch(each);
            getContext().stop(each);
        }
        postStop();
    }
    @Override
     public void postStop(){
        System.out.println("DemoExample PostStopped.........");
    }

    public Receive createReceive(){
        return receiveBuilder()
                .match(String.class,strMsg->{
                    System.out.println("DemoExample for the Actor is Started...........");
                })
                .build();


    }
}
