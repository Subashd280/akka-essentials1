package ActorSystem;

import Actors.ActorAdd;
import Actors.ActorMul;
import Actors.ActorSub;
import Core.Dc;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;

import java.util.Scanner;

public class DemoAS {


    public static void main(String[] args) throws Exception{
        ActorSystem actorSystem=ActorSystem.create("demo");
        try {
            ActorRef actorRef = actorSystem.actorOf(ActorAdd.props(), "Actors.ActorAdd");
            ActorRef actorRef1 = actorSystem.actorOf(ActorSub.props(), "Actors.ActorSub");
            ActorRef actorRef2 = actorSystem.actorOf(ActorMul.props(), "Actors.ActorMul");
//            DemoactorRef.tell("HelloChild 1:", ActorRef.noSender());
//            DemoactorRef.tell("HelloChild 2:", ActorRef.noSender());


            while (true) {
                Scanner sc = new Scanner(System.in);
                System.out.println("Enter the num1:");
                    int num1 = sc.nextInt();
                System.out.println("Enter the num2:");
                int num2=sc.nextInt();
                System.out.println("Enter the Operation name:");
                String operation=sc.next();
                Dc dc=new Dc(num1,num2,operation);
                actorRef.tell(dc,ActorRef.noSender());
                actorRef1.tell(dc,ActorRef.noSender());
                actorRef2.tell(dc,ActorRef.noSender());
            }
        }catch(Exception e){
            System.out.println("e");
        }

    }
}
