import java.io.*;
import java.net.*;

public class GameServer{
    public static void main(String[] args) throws Exception{
		ServerSocket serverSocket = new ServerSocket(1234);
		System.out.println("Server socket created"); 
		while(true){
			TicTocToe game = new TicTocToe();
			Socket socket1 = serverSocket.accept();
			System.out.println("socket1 accepted"); 
			Socket socket2 = serverSocket.accept();
			System.out.println("socket2 accepted"); 
			//medan spelet kan fortsätta
			while(game.isOn()){
				try{
					//skicka game-objektet till första klienten och ta emot modifierade objektet
					game = sendRecieve(socket1, game);
					//skicka modifierade game-objektet till den andra klienten och ta emot modifierade objektet
					game = sendRecieve(socket2, game);
					//om det inte går att spela längre
					//skicka game-objektet till första klienten
					if(!game.isOn()) // If the player of socket2 wins, the player of socket1 is informed
						send(socket1,game);
				}
				catch(GameException ge){
					game.shutdown();
					Socket causingSocket = ge.getCausingSocket();
					if(causingSocket == socket1)
						send(socket2, game);
					else
						send(socket1, game);
					break;
				}
			}
			socket1.close();
			socket2.close();
		}
    }
    
    private static void send(Socket socket,TicTocToe game){
		try{
			ObjectOutputStream objOut = new ObjectOutputStream(socket.getOutputStream());
			objOut.writeObject(game);
		}
		catch(IOException ioe){
			System.out.println("något fel inträffade! " + ioe);
		}
    }
    
    private static TicTocToe sendRecieve(Socket socket, TicTocToe game)throws GameException{
		try{
			send(socket,game);
			if(game.isOn()){
				ObjectInputStream objIn=new ObjectInputStream(socket.getInputStream());
				game = (TicTocToe)objIn.readObject();
			}
		}
		catch(IOException ioe){
			System.out.println("något IO fel inträffade! "+ ioe);
			throw new GameException("klient lämnade", socket);
		}
		catch(ClassNotFoundException cnfe){
			System.out.println("hittar ingen class! " + cnfe);
		}
		return game;
    }
}