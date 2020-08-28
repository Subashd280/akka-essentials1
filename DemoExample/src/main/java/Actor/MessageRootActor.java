package Actor;

import Core.ActorDetails;
import Core.AddressCarrier;
import Core.CommandCarrier;
import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;

import java.util.HashMap;

public class MessageRootActor extends AbstractActor {
    public static Props prop(){
        return Props.create(MessageRootActor.class,MessageRootActor::new);
    }

    HashMap<String, ActorRef> actorAddress=new HashMap<>();

    @Override
   public void preStart(){

   }
    public void registerActor(ActorDetails actorDetails){
        ActorRef actorRef=getContext().actorOf(MessageChildActor.prop(),actorDetails.actorName);
        actorAddress.put(actorDetails.actorName,actorRef);
        AddressCarrier addressCarrier=new AddressCarrier();
        addressCarrier.actorAddress=actorAddress;
        for (String actorName : actorAddress.keySet()) {
            ActorRef actorRef1=actorAddress.get(actorName);
            actorRef1.tell(addressCarrier,ActorRef.noSender());
        }

        System.out.println("successfully "+actorDetails.actorName+" person contact added ");

    }
    public void sendCommand(CommandCarrier commandCarrier) {

        if (actorAddress.containsKey(commandCarrier.from)) {
            ActorRef actorRef = actorAddress.get(commandCarrier.from);
            actorRef.tell(commandCarrier, ActorRef.noSender());
        }
    }
    public void displayContacts(){
        if(actorAddress.size()<1){
            System.out.println("Sorry not contacts ");
            return;
        }
        for (String actorName : actorAddress.keySet()) {
            System.out.println("\t\t"+actorName);
        }

    }

    @Override
   public void postStop(){

   }
    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(ActorDetails.class,actorDetails -> {
                    registerActor(actorDetails);
                })
                .match(CommandCarrier.class,commandCarrier -> {
                    sendCommand(commandCarrier);
                })
                .matchEquals("contacts",contacts->{
                    displayContacts();
                })
                .build();
    }
}
