import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.pattern.Patterns;
import akka.util.Timeout;
import scala.concurrent.Await;
import scala.concurrent.Future;

public class DemoActor1 extends AbstractActor {
    public static Props prop(){
        System.out.println("Creating actor Object.....");
        return Props.create(DemoActor1.class,DemoActor1::new);
    }
    @Override
    public void preStart(){
        System.out.println("DemoActor PreStarted.....");
    }

    @Override
    public void postStop(){
        System.out.println("DemoActor PreStarted.....");
    }



    public void greet(String stringobj){
        System.out.println("Hello "+stringobj+" Good Morning");
    }

    public void makeQuestion(){
        try {
            Timeout timeout = Timeout.create(java.time.Duration.ofSeconds(10));
            ActorRef chRef = getContext().actorOf(ChildActor1.prop(), "ChildActor");
            Future<Object> future = Patterns.ask(chRef, "who are you?", 2000);
            String result = Await.result(future, timeout.duration()).toString();
            System.out.println("Got Response from child : "+result);
        }
        catch (Exception ex){
            System.out.println(ex);
        }

    }


    public Receive createReceive(){
        return receiveBuilder()
                .matchEquals("ask",mqk->{
                    makeQuestion();
                })
                .build();
    }


}
