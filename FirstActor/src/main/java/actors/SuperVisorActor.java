package actors;

import akka.actor.AbstractActor;
import akka.actor.Cancellable;
import akka.actor.Props;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;

public class SuperVisorActor extends AbstractActor {
    public static Props prop(){
        return Props.create(SuperVisorActor.class, SuperVisorActor::new);
    }

    @Override
    public void preStart(){
        System.out.println("SuperVisor started");
    }

    @Override
    public void postStop(){
        System.out.println("SuperVisor stopped");
    }

    public void createChild(){
        System.out.println("Going to create Child ");
    }
    Cancellable cancellable;

    static int x=1;

public void start(){
    this.cancellable =  context().system().scheduler().schedule(Duration.create(0, TimeUnit.MILLISECONDS),
            Duration.create(3000, TimeUnit.MILLISECONDS),
            new Runnable() {

                @Override
                public void run() {
                    try {
                        x=x+1;
                        System.out.println("started "+x);
                    }
                    catch (Exception ex){

                    }



                }
            }, context().system().dispatcher());
}


public void stop(){
    System.out.println("Stopped");
    x=0;
    if(this.cancellable!=null) {
        this.cancellable.cancel();
        this.cancellable = null;
    }
}
    public void stay(){
        System.out.println("Paused");
        if(this.cancellable!=null) {
            this.cancellable.cancel();
            this.cancellable = null;
        }
    }


//    @Override
//    public Receive createReceive(){
//        return receiveBuilder().
//                match(String.class,msg->{
//                    System.out.println(msg);
//                }).
//        matchEquals("start",obj->{
////    start();
//}).
//        matchEquals("stop",obj->{
//            stop();
//        }).
//        matchEquals("stay",obj->{
//stay();
//        })
//                .build();
//    }

    @Override
    public Receive createReceive(){
    return receiveBuilder().build();
    }


}
