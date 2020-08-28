package actors;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import core.DC;

import java.io.IOException;
import java.util.Scanner;

public class DemoAS1 {
    public static void main(String[] args)throws IOException {

        ActorSystem actorSystem= ActorSystem.create("DemoAS1");

        System.out.println(actorSystem);

        try{
            ActorRef demoActorRef=actorSystem.actorOf(ParentActor.prop(),"demoActor1");
            System.out.println("Actor Ref "+demoActorRef);

            while (true){

               Scanner scan=new Scanner(System.in);
//                String s1=scan.next();
//                demoActorRef.tell(s1,ActorRef.noSender());
                System.out.println("Enter the num 1:");
                float num1=scan.nextFloat();
                System.out.println("Enter the num 2:");
                float num2=scan.nextFloat();

                DC dc=new DC(num1,num2);
                demoActorRef.tell(dc, ActorRef.noSender());

            }

}catch (Exception ex){
            System.out.println(ex);
        }

    }
}
