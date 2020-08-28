import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class MyActor extends AbstractActor {
   private final LoggingAdapter log= Logging.getLogger(getContext().getSystem(),this);


    public static Props Props() {
        System.out.println("Creating an Actor");
        return Props.create(MyActor.class,()->new MyActor());
    }


    @Override
   public void preStart(){
       System.out.println("MyActor preStarted......");
   }
@Override
public void postStop(){
    System.out.println("MyActor PostStopped.....");
}
    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(String.class, s -> {
                    log.info("Received String message: {}", s);
                })
                .matchAny(o -> log.info("received unknown message"))
                .build();

    }
}
