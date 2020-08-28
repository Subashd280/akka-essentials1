import akka.actor.AbstractActor;
import akka.actor.Props;

public class ChildActorAdd extends AbstractActor {
    public static Props props(){
        System.out.println("creating an actor............");
        return  Props.create(ChildActorAdd.class,()->new ChildActorAdd());
    }


    @Override
    public void preStart(){
        System.out.println("Child Actor Prestarted");
    }


    @Override
    public void postStop(){
        System.out.println("Child Actor Post Stop");
    }
    public void Add(Dc dc)
    {
        System.out.println("Addition:"+dc.num1+"and"+dc.num2+"is"+(dc.num1+dc.num2));
    }
    @Override
    public Receive createReceive(){
        return  receiveBuilder()
                .match(String.class,strMsg->{
                    System.out.println("Child Actor got message "+strMsg);
                })
                .match(Dc.class, dcobj->{Add(dcobj);})
                .build();
    }
}
