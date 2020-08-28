import akka.actor.AbstractActor;
import akka.actor.Props;

public class ChildActor1 extends AbstractActor {
    public static Props prop() {
        return Props.create(ChildActor1.class, ChildActor1::new);
    }
    public void preStart(){
        System.out.println("ChildActor preStarted.......");
    }
    public void postStop(){
        System.out.println("ChildActor postStopped.......");
    }
    public void answerQuestion(String strQ)throws Exception{
        System.out.println("Child got message << "+strQ);

        switch (strQ){
            case "who are you?":{
                String result="I'm your child";
                getSender().tell(result, getSelf());
            }
            break;
            default: {
                String ds = "I'm unable to understand your question ";
                getSender().tell(ds, getSelf());
            }
        }

    }

    public void greet(String stringobj){
        System.out.println("Hello "+stringobj+" Good Morning");
    }
    public Receive createReceive(){
        return receiveBuilder()
                .match(String.class,strQ->{
                    answerQuestion(strQ);

                })
                .build();
    }


}
