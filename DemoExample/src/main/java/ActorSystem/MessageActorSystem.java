package ActorSystem;

import Actor.MessageRootActor;
import Core.ActorDetails;
import Core.CommandCarrier;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class MessageActorSystem  {
    public static void main(String[] args)throws IOException {

        ActorSystem actorSystem=ActorSystem.create("MessageActorSystem");

        try{
            ActorRef rootActor=actorSystem.actorOf(MessageRootActor.prop(),"messageRootActor");
            while (true){

                System.out.println("Chose the action..\n\t\t 1.register\n\t\t 2.makecommands \n\t\t 3.contacts ");
                Scanner scan=new Scanner(System.in);
                int action=scan.nextInt();
                switch (action){
                    case 1:{

                        System.out.println("provide name for person ");
                        String name=scan.next();
                        ActorDetails actorDetails=new ActorDetails(name);
                        rootActor.tell(actorDetails,ActorRef.noSender());
                    }
                    break;
                    case 2:{

                        System.out.println(" provide To :");
                        String toadd=scan.next();
                        System.out.println(" provide From :");
                        String fromAdd=scan.next();
                        BufferedReader obj = new BufferedReader(new InputStreamReader(System.in));
                        String message=obj.readLine();
                        CommandCarrier commandCarrier=new CommandCarrier(toadd,fromAdd,message);
                        rootActor.tell(commandCarrier,ActorRef.noSender());
                    }
                    break;
                    case 3:{

                        rootActor.tell("contacts",ActorRef.noSender());

                    }
                    break;
                    default:
                        System.out.println("U hv choosed invalid action please try again...");
                }

            }

        }catch (Exception ex){
            ex.printStackTrace();
            System.out.println(ex);
        }

    }
}
