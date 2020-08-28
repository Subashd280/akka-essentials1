package actors;

import akka.actor.AbstractActor;
import akka.actor.Props;

public class DemoActor1 extends AbstractActor {

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

public void greet(String stringobj){
    System.out.println("Hello "+stringobj+" Good Morning");
}

    public Receive createReceive(){
        return receiveBuilder()
                .match(String.class,strMsg->{
                    greet(strMsg);

                })
                .build();
    }
}
