import akka.actor.ActorRef;
import akka.actor.ActorSystem;

import java.util.Scanner;

public class DemoAS1 {
    public static void main(String[] args) {

        ActorSystem actorSystem=ActorSystem.create("DemoAS1");
        System.out.println("actorSystem");

        try{
            ActorRef demoActorRef=actorSystem.actorOf(DemoActor1.prop(),"demoActor1");
            System.out.println("Actor Ref "+demoActorRef);

            while (true) {

                Scanner scan = new Scanner(System.in);
                String s1 = scan.next();
                demoActorRef.tell(s1, ActorRef.noSender());
            }
        }catch (Exception e){
            System.out.println("e");
        }
    }
}
