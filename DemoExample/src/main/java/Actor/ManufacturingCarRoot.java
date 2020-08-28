package Actor;

import Core.CarDetails;
import Core.carrier;
import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;

import java.util.Random;


public class ManufacturingCarRoot extends AbstractActor {
    public static Props prop()
    {
        return Props.create(ManufacturingCarRoot.class,ManufacturingCarRoot::new);
    }
    public void preStart(){
        System.out.println("Manufacturing Car Prestarted........");
    }

    public void frameModel(CarDetails carDetails){
        ActorRef actorRef=getContext().actorOf(ManufacturingChildCar.prop(),carDetails.carName);
        actorRef.tell(carDetails,ActorRef.noSender());
        System.out.println("CarDetail"+carDetails.carName+"is Received");

    }
    public void engineModel(carrier carrier){
        ActorRef actorRef=getContext().actorOf(ManufacturingChildCar.prop(),carrier.engineno);
        actorRef.tell(carrier,ActorRef.noSender());
        System.out.println("Engine Model"+carrier.engineno+"is received");
    }
     public void  colorModel(Random color){
         // colours = new ArrayList<>();
        ActorRef actorRef=getContext().actorOf(ManufacturingChildCar.prop(), "M");


    }
    public void postStop(){
        System.out.println("Manufacturing Car PostStopped.......");
    }
    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(CarDetails.class,carDetails->frameModel(carDetails))
                .match(carrier.class,carrier -> engineModel(carrier))
               // .match()
                .build();
    }
}
