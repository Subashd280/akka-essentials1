import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;

public class DemoActorAdd extends AbstractActor {
    public static Props props(){
        System.out.println("creating an actor............");
        return  Props.create(DemoActorAdd.class,()->new DemoActorAdd());
    }


    @Override
    public void preStart(){
        System.out.println("Demo Actor Prestarted");
    }


    @Override
    public void postStop(){
        System.out.println("Demo Actor Post Stop");
    }
    public void Add(Dc dc)
    {
        ActorRef actorRef=getContext().actorOf(ChildActorAdd.props(),"ChildActorAdd");
        actorRef.tell(dc,ActorRef.noSender());
//        System.out.println("Addition:"+dc.num1+"and"+dc.num2+"is"+(dc.num1+dc.num2));
    }
    @Override
    public Receive createReceive(){
        return  receiveBuilder()
                .match(String.class,strMsg->{
                    System.out.println("Demo Actor got message "+strMsg);
                })
                .match(Dc.class, dcobj->{Add(dcobj);})
                .build();
    }
}
