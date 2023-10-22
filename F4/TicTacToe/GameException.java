import java.net.*;

public class GameException extends Exception{
    private String errorMsg;
    private Socket causingSckt;

    public GameException(String msg, Socket socket){
        errorMsg=msg;
        causingSckt=socket;
    }

    public Socket getCausingSocket(){
	    return causingSckt;
    } 
}