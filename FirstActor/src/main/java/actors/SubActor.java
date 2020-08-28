package actors;

import akka.actor.AbstractActor;
import akka.actor.Props;
import core.DC;

public class SubActor extends AbstractActor {

    public static Props prop()
    {
        System.out.println("Creating Actor Object ");
        return Props.create(SubActor.class, SubActor::new);
    }

    @Override
    public void preStart(){
        System.out.println("DemoActor prestart()"+getContext());
    }


    @Override
    public void postStop(){
        System.out.println("DemoActor postStop()");
    }

public void greet(String stringobj){
    System.out.println("Hello "+stringobj+" Good Morning");
}
    public void Sub(DC dc)
    {
        System.out.println("Subtraction:"+dc.num1+"and"+dc.num2+"is"+(dc.num1-dc.num2));
    }

    public Receive createReceive(){
        return receiveBuilder()
                .match(String.class,strMsg->{
                    greet(strMsg);

                }).match(DC.class,dcobj->{Sub(dcobj);})
                .build();
    }
}
