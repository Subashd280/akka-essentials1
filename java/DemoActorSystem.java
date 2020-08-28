import akka.actor.ActorRef;
import akka.actor.ActorSystem;

public class DemoActorSystem {
    public static void main(String[] args) {
        ActorSystem actorSystem=ActorSystem.create("demoAS");
        try{
            ActorRef demoExampleRef = actorSystem.actorOf(DemoExample.Props(),"DemoExample");
            demoExampleRef.tell("HelloChild",ActorRef.noSender());
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}
