package carActor;

import akka.actor.AbstractActor;
import akka.actor.Props;

public class FrameSet extends AbstractActor {
    public static Props prop(){
        System.out.println("Creating an Frameset actor....");
        return Props.create(FrameSet.class, ()-> new FrameSet() );
    }
    @Override
    public void preStart(){
        System.out.println("Frameset Prestarted......");
    }
    public void frameset(){

    }
    @Override
    public void postStop(){
        System.out.println("Frameset postStopped....");
    }
    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .build();
    }
}
