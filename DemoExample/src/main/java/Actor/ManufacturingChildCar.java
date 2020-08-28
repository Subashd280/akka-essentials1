package Actor;

import akka.actor.AbstractActor;
import akka.actor.Props;

public class ManufacturingChildCar extends AbstractActor {

    public static Props prop(){
        return Props.create(ManufacturingChildCar.class,ManufacturingChildCar::new);
    }
    @Override
    public void preStart(){

    }
    @Override
    public void postStop(){

    }
    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .build();
    }
}
