package Core;

public class CommandCarrier {
    public String to;
    public String from;
    public String message;
    public CommandCarrier(String toAdd,String fromAdd,String data){
        to=toAdd;
        from=fromAdd;
        message=data;
    }
}
