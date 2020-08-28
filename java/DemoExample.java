import akka.actor.AbstractActor;
import akka.actor.Props;
public class DemoExample  extends AbstractActor {
//    public static void props(){
//
//    }

    public static Props Props() {
        System.out.println("creating an Actor......");
        return Props.create(DemoExample.class,()->new DemoExample());
    }

    @Override
    public void preStart(){
        System.out.println("DemoExample prestarted......");
    }

    @Override
    public void postStop(){
        System.out.println("DemoExample poststopped......");
    }

    public Receive createReceive() {
        return receiveBuilder()
                .match(String.class,strMsg->{
                    System.out.println("DemoExample for the Actor is started.....");
                })
                .build();
    }
}
