package Actor;

import Core.AddressCarrier;
import Core.CommandCarrier;
import Core.MessageCarrier;
import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;

import java.util.HashMap;

public class MessageChildActor extends AbstractActor {
    HashMap <String, ActorRef> actorAddress=new HashMap<>();
    public void preStart(){

    }
    public static Props prop(){
        return Props.create(MessageChildActor.class,MessageChildActor::new);
    }
    public void initializeAddress(AddressCarrier addressCarrier){
        this.actorAddress=addressCarrier.actorAddress;
    }

    public void sendMessage(CommandCarrier commandCarrier){

        if(actorAddress.containsKey(commandCarrier.to)){
            ActorRef actorRef=actorAddress.get(commandCarrier.to);
            MessageCarrier messageCarrier=new MessageCarrier();
            messageCarrier.to=commandCarrier.to;
            messageCarrier.from=commandCarrier.from;
            messageCarrier.message=commandCarrier.message;
            System.out.println(messageCarrier.from+"Sending message.............to"+messageCarrier.to);
            actorRef.tell(messageCarrier,ActorRef.noSender());
        }
    }

    public void receiveMessage(MessageCarrier messageCarrier){
        System.out.println(messageCarrier.to+"Message Received >>>\tfrom"+messageCarrier.from+"\n\t\t"+messageCarrier.message);
    }

    @Override
    public void postStop(){
        System.out.println("MessageChildActor PostStopped.....");
    }
    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(AddressCarrier.class,addressCarrier->{initializeAddress(addressCarrier);})
                .match(CommandCarrier.class,commandCarrier -> {sendMessage(commandCarrier);})
                .match(MessageCarrier.class,messageCarrier ->{receiveMessage(messageCarrier);} )
                .build();
    }
}
