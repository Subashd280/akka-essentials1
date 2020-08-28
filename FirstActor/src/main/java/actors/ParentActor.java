package actors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import core.DC;

import java.util.ArrayList;

public class ParentActor extends AbstractActor {

    public static Props prop()
    {
        System.out.println("Creating Actor Object ");
        return Props.create(ParentActor.class, ParentActor::new);
    }
    ArrayList<ActorRef> childActors=new ArrayList<>();

    @Override
    public void preStart(){
        System.out.println("DemoActor prestart()");
        ActorRef actorRef=context().actorOf(AddActor.prop(),"Addition");
        childActors.add(actorRef);
         actorRef=context().actorOf(SubActor.prop(),"Subtraction");
        childActors.add(actorRef);
         actorRef=context().actorOf(MulActor.prop(),"Multiplication");
        childActors.add(actorRef);
    }

    public void sendToChild(DC dc)
    {
        for(ActorRef actorRef:childActors){
            actorRef.tell(dc,ActorRef.noSender());
        }
    }

    @Override
    public void postStop(){
        System.out.println("DemoActor postStop()");
    }


    public Receive createReceive(){
        return receiveBuilder()
                .match(DC.class,dcobj->{sendToChild(dcobj);})
                .build();
    }
}
