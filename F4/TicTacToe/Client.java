import java.net.*; 
import java.io.*;

public class Client{
    public static void main(String[] args){
		GameInterface game = null;
		ObjectOutputStream objOut = null;
		ObjectInputStream objIn = null;
		try{
			System.out.println("Väntar på annan spelare!");
			Socket socket = new Socket("localhost", 1234);
			System.out.println("Socket created");
			do{
				objIn = new ObjectInputStream(socket.getInputStream());
				objOut = new ObjectOutputStream(socket.getOutputStream());
				game = (GameInterface) objIn.readObject();
				if(game.isOn()){
					game.play();
					objOut.writeObject(game);
				}
			} while(game.isOn());
			System.out.println("Spelet är slut\n"+game+"\n"+game.getWinner()+ " Vann!");
		}
		catch(IOException ioe){
			System.out.println("något IO fel inträffade! " + ioe);
		}
		catch(ClassNotFoundException cnfe){
			System.out.println("hittar ingen class! "+cnfe);
		}
    }
}