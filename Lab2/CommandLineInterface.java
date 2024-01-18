import java.util.*;
class CommandLineInterface {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int gridSize = 4;
        Boardgame game = new FifteenModel(gridSize);
        //Boardgame game = new TicTacToe(gridSize);
        System.out.println("\nWelcome to fifteen puzzle\n");
        while (true) {
            // Print the current board
            for (int i=0; i<gridSize; i++) {
                for (int j=0; j<gridSize; j++){
                    System.out.print("  ");
                    String status = game.getStatus(i,j); // getStatus
                    if(status.length() == 0)
                        System.out.print(status + "   ");
                    else if(status.length() == 1)
                        System.out.print(status + "  ");
                    else
                        System.out.print(status + " ");           
                }
                System.out.println();
            }
            System.out.println();
            System.out.print("Your move: ");
            int i = scanner.nextInt();  // get an int number from terminal window
            int j = scanner.nextInt();
            game.move(i,j);	                             // move
            System.out.println(game.getMessage());	     // getMessage
        }
    }
}