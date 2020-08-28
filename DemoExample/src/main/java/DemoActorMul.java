import akka.actor.AbstractActor;
import akka.actor.Props;

public class DemoActorMul extends AbstractActor {
    public static Props props(){
        System.out.println("creating an actor............");
        return  Props.create(DemoActorMul.class,()->new DemoActorMul());
    }




    @Override
    public void preStart(){
        System.out.println("Demo Actor Prestarted");
    }


    @Override
    public void postStop(){
        System.out.println("Demo Actor Post Stop");
    }
    public void Mul(Dc dc) { System.out.println("Multiplication:"+dc.num1+"and"+dc.num2+"is"+(dc.num1*dc.num2)); }
    @Override
    public Receive createReceive(){
        return  receiveBuilder()
                .match(String.class,strMsg->{
                    System.out.println("Demo Actor got message "+strMsg);
                })
                .match(Dc.class, dcobj->{Mul(dcobj);})
                .build();
    }
}
