import akka.actor.ActorRef;
import akka.actor.ActorSystem;

import java.io.IOException;

public class DemoActorSystem {

    public static void main(String[] args) throws IOException {
        ActorSystem actorSystem=ActorSystem.create("MyActor");

        try{
            ActorRef MyActorRef=actorSystem.actorOf(MyActor.Props(),"MyActor");
            MyActorRef.tell("Hello Child ",ActorRef.noSender());
        }catch (Exception ex ){
            System.out.println("Error:"+ex);
        }

    }
}
