import Actors.ActorAdd;
import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.pattern.Patterns;
import akka.util.Timeout;
import scala.concurrent.Await;
import scala.concurrent.Future;

public class parentActor extends AbstractActor {
    public static Props prop()
    {
        System.out.println("Creating Actor Object ");
        return Props.create(DemoActor1.class, DemoActor1::new);
    }

    @Override
    public void preStart(){
        System.out.println("DemoActor prestart()");
    }


    @Override
    public void postStop(){
        System.out.println("DemoActor postStop()");
    }

    public void add(int add){
        System.out.println("Hello add"+add+" added");
    }

    public void makeQuestion(){
        try {
            Timeout timeout = Timeout.create(java.time.Duration.ofSeconds(10));
            ActorRef chRef = getContext().actorOf(ActorAdd.props(), "ChildActor");
            Future<Object> future =  Patterns.ask(chRef, "who are you?", 2000);
            int add = (int) Await.result(future, timeout.duration());
            System.out.println("Got Response from child : "+add);
        }
        catch (Exception ex){
            System.out.println(ex);
        }

    }
    public Receive createReceive(){
        return receiveBuilder()
                .matchEquals("ask",mkq->{
                    makeQuestion();
                })
                .matchEquals("ask",mkq->{
                    makeQuestion();
                })
                .matchEquals("ask",mkq->{
                    makeQuestion();
                })
                .build();
    }
}
