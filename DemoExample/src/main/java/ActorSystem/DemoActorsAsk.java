package ActorSystem;


import Actors.DemoActorAskParent2;
import Core.Dc;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;

import java.io.IOException;
import java.util.Scanner;

public class DemoActorsAsk {
    public static void main(String[] args)throws IOException {

        ActorSystem actorSystem=ActorSystem.create("DemoAS2");

        System.out.println(actorSystem);

        try{
            ActorRef demoActorRef=actorSystem.actorOf(DemoActorAskParent2.prop(),"demoActorParenTask");
            System.out.println("Actor Ref "+demoActorRef);

            while (true){

                Scanner scan=new Scanner(System.in);
                // String s1=scan.next();
                System.out.println("Enter first num ");
                int n1=scan.nextInt();
                System.out.println("Enter second num ");
                int n2=scan.nextInt();
                System.out.println("operation");
                String oper=scan.next();

                Dc dc=new Dc(n1,n2,oper);
                demoActorRef.tell(dc,ActorRef.noSender());

            }

        }catch (Exception ex){
            System.out.println(ex);
        }

    }
}
