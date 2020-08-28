import akka.actor.AbstractActor;
import akka.actor.Props;

public class ChildActorSub extends AbstractActor {
    public static Props props(){
        System.out.println("creating an actor............");
        return  Props.create(ChildActorSub.class,()->new ChildActorSub());
    }




    @Override
    public void preStart(){
        System.out.println("Demo Actor Prestarted");
    }


    @Override
    public void postStop(){
        System.out.println("Demo Actor Post Stop");
    }
    public void Sub(Dc dc) { System.out.println("Subtraction:"+dc.num1+"and"+dc.num2+"is"+(dc.num1-dc.num2)); }
    @Override
    public Receive createReceive(){
        return  receiveBuilder()
                .match(String.class,strMsg->{
                    System.out.println("Demo Actor got message "+strMsg);
                })
                .match(Dc.class, dcobj->{Sub(dcobj);})
                .build();
    }
}
