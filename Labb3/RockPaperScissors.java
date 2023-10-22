import java.awt.event.*;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
// import java.util.concurrent.TimeUnit;

public class RockPaperScissors implements ActionListener{
    private int counter = 0;
    private GameBoard computerBoard;
    private GameBoard myBoard;
    private BufferedReader in;
    private PrintWriter out;
    private static ArrayList<String> hand =
        new ArrayList<>(Arrays.asList("ROCK", "PAPER", "SCISSORS"));

    RockPaperScissors(String playerName){
        try{
            Socket socket = new Socket("localhost",4713);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());

            out.println(playerName);
            out.flush();
            System.out.println(in.readLine()); // Retrieves "Hello, "+name

            GameWindow gameWindow = new GameWindow(playerName, out, this);
            computerBoard = gameWindow.computerBoard;
            myBoard = gameWindow.myBoard;
        }
        catch(IOException ioe){
            System.out.println(ioe);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new ButtonAction(e).start();
        counter++;
        if(counter == 1){
            computerBoard.resetColor();
            myBoard.resetColor();
            
            computerBoard.setUpperMessage("–");
            myBoard.setUpperMessage("–");

            computerBoard.setLowerMessage("One");
            myBoard.setLowerMessage("One");
        }
        else if(counter == 2){
            computerBoard.setLowerMessage("Two");
            myBoard.setLowerMessage("Two");
        }
        else{ // counter == 3
            try{
                String myGesture = e.getActionCommand();
                myBoard.markPlayed(myGesture);
                myBoard.setUpperMessage(myGesture);
                out.println(myGesture);
                out.flush();
                
                String computerGesture = in.readLine();
                computerBoard.markPlayed(computerGesture);
                computerBoard.setUpperMessage(computerGesture);

                int myIdx = hand.indexOf(myGesture);
                int computerIdx = hand.indexOf(computerGesture);
                if(myIdx == computerIdx){ // Draw
                    computerBoard.setLowerMessage("Draw");
                    myBoard.setLowerMessage("Draw");
                }
                else if((myIdx + 1) % 3 == computerIdx){ // Computer wins
                    computerBoard.setLowerMessage("Win");
                    myBoard.setLowerMessage("Loss");
                    computerBoard.wins();
                }
                else{ // Player wins
                    computerBoard.setLowerMessage("Loss");
                    myBoard.setLowerMessage("Win");     
                    myBoard.wins();
                }
                
                // TimeUnit.MILLISECONDS.sleep(1000);
                counter = 0;
            }
            catch(IOException ioe){
                System.out.println(ioe);
            }
            // catch(InterruptedException ie){
            //     System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXX");
            //     System.out.println(ie);
            // }
        }
    }

    public static void main(String[] args){
        new RockPaperScissors("Arvid");
    }
}