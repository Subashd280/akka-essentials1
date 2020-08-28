package ActorSystem;

import Actor.ManufacturingCarRoot;
import Core.Frame;
import Core.carrier;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManufacturingCar {
    public static void main(String[] args) {

        ActorSystem actorSystem=ActorSystem.create("ManufacturingCar");

        try
        {
            ActorRef actorRef=actorSystem.actorOf(ManufacturingCarRoot.prop(),"ManufacturingCarRoot");
            while(true){

                System.out.println("Choose the action........\n\t\t1.frame Type....\n\t\t2.EngineShop.....\n\t\t3.Coloring The Frame....\n\t\t 4.Exit");
                Scanner scan=new Scanner(System.in);
                int action=scan.nextInt();
                switch(action) {
                    case 1: {
                        System.out.println("Enter the FrameType:");
                        int frameType = scan.nextInt();
                        Frame frame = new Frame(frameType);
                        actorRef.tell(frame, ActorRef.noSender());
                        System.out.println("car frame is ready");
                    }
                    break;
                    case 2: {
                        System.out.println("Enter the TCF:");
                        String engine = scan.next();
                        carrier carrier = new carrier(engine);
                        actorRef.tell(carrier, ActorRef.noSender());
                        System.out.println("ENGINE MODEL is ready");
                    }
                    break;
                    case 3: {
                        List<String> colours = new ArrayList<>();

                        colours.add("blue");
                        colours.add("orange");
                        colours.add("red");
                        colours.add("green");

                        String col = colours.get(1);
                        System.out.println(col);

                        int size = colours.size();

                        System.out.printf("The size of the ArrayList is: %d%n", size);
                    }
                        break;
                    case 4:
                        actorRef.isTerminated();
                        break;
                    default:
                        throw new Exception("Invalid Option value: " + action);
                }
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}
