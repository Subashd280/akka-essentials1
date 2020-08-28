package actors;

import akka.actor.AbstractActor;
import akka.actor.Props;
import core.DC;

public class AddActor extends AbstractActor {

    public AddActor() {

    }

    public static Props prop(String ddd)
    {
        System.out.println("Creating Actor Object ");
 return Props.create(AddActor.class, AddActor::new);


    }

String dd;
    public AddActor(String d){
        dd=d;
    }

    public static Props prop() {
        return null;
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
    public void Add(DC dc)
    {
        System.out.println("Addition:"+dc.num1+"and"+dc.num2+"is"+(dc.num1+dc.num2));
    }

    public Receive createReceive(){
        return receiveBuilder()
                .match(String.class,strMsg->{
                    greet(strMsg);

                }).match(DC.class,dcobj->{Add(dcobj);})
                .build();
    }
}
