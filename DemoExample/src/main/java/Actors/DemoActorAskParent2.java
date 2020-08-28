package Actors;

import Core.Dc;
import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.pattern.Patterns;
import akka.util.Timeout;
import scala.concurrent.Await;
import scala.concurrent.Future;

import java.util.HashMap;

public class DemoActorAskParent2 extends AbstractActor {
    public static Props prop()
    {
        System.out.println("Creating Actor Object ");
        return Props.create(DemoActorAskParent2.class, DemoActorAskParent2::new);
    }
    HashMap<String, ActorRef> actorMap=new HashMap<>();

    @Override
    public void preStart(){
        System.out.println("DemoActorAsk prestart()");
        ActorRef chRef = getContext().actorOf(ActorAdd.props(), "AddActor1");
        actorMap.put("add",chRef);
        chRef = getContext().actorOf(ActorSub.props(), "SubActor1");
        actorMap.put("sub",chRef);
    }


    @Override
    public void postStop(){
        System.out.println("DemoActorAsk postStop()");
    }



    public void passToAppA(Dc dc)throws Exception{

        String operation=dc.operation;
        if(actorMap.containsKey(operation)){
            Timeout timeout = Timeout.create(java.time.Duration.ofSeconds(10));
            ActorRef opActor=actorMap.get(operation);
            Future<Object> future = Patterns.ask(opActor,dc, 2000);
            // String result = Await.result(future, timeout.duration()).toString();
            String result =  Await.result(future, timeout.duration()).toString();
            System.out.println("Got Response from child : "+result);

        }
        else {

        }
    }
    public Receive createReceive(){
        return receiveBuilder()
                .match(Dc.class, dc1 -> {passToAppA(dc1);})

                .build();
    }
}
