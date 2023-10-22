import java.util.*;

public class FifteenModel implements Boardgame{
    private Random rand = new Random();
    private String message;
    private ArrayList<int[]> availableMoves = new ArrayList<int[]>();
    /**Keeps track of the number of moves made during a game*/
    private int numMoves;
    int[][] board;
    int gridSize;
    /**Row index of the zero square*/
    int zero_r; 
    /**Column index of the zero square*/
    int zero_c;

    
    /**Fills the board[][] with numbers 0 to gridSize^2 - 1*/
    FifteenModel(int gridSize){
        this.gridSize = gridSize;
        board = new int[gridSize][gridSize];
        int i = 1;
        for(int r = 0; r < gridSize; r++){ // Numbers are initially placed in order 1 to gridSize^2
            for(int c = 0; c < gridSize; c++){
                board[r][c] = i;
                i++;
            }
        }
        board[gridSize - 1][gridSize - 1] = 0; // The last number, gridSize^2, is converted to a zero, so that there are gridSize^2 - 1 positive numbers
        zero_r = gridSize - 1;
        zero_c = gridSize - 1;
        updateAvailableMoves(zero_r, zero_c); // This initializes the availableMoves ArrayList<int[]>

        for(int x = 0; x < 100*gridSize*gridSize; x++){ // The numbers are shuffled around
            int randIdx = rand.nextInt(availableMoves.size());
            int[] rc = availableMoves.get(randIdx);
            move(rc[0], rc[1]);            
        }
        numMoves = 0;
        message = "Game to be started";
    }

    @Override
    public boolean move(int r, int c) {
        if(message == "Congratulations, you won!"){ // Ensures no moves can be made after the game has been won
            return false; 
        }
        else{
            numMoves++;
            for(int[] rc : availableMoves){
                if(r == rc[0] && c == rc[1]){ // Checking so that the pressed square is among the available moves
                    board[zero_r][zero_c] = board[r][c]; // The value of the empty square is set to the value of the pressed square
                    board[r][c] = 0;
                    zero_r = r;
                    zero_c = c;                
                    updateAvailableMoves(zero_r, zero_c);
                    if(hasWon()){
                        message = "Congratulations, you won! (total moves = " + numMoves + ")";
                    }
                    else{
                        message = "Move made successfully! (total moves = " + numMoves + ")";
                    }
                    return true;
                }
            }
            message = "Could not make the requested move (total moves = " + numMoves + ")";
            return false;            
        }
    }


    @Override
    public String getStatus(int r, int c) {
        String value = "" + board[r][c];
        
        if(value.equals("0")){
            return "";
        }
        else
            return value;
    }

    @Override
    public String getMessage() {
        return message;
    }

    /**Returns true is all squares are in order. If not, it returns false*/
    private boolean hasWon(){
        int correctValue = 1; // The value of each square if in order
        for(int r = 0; r < gridSize; r++){
            for(int c = 0; c < gridSize; c++){
                if(board[r][c] != correctValue && board[r][c] != 0){
                    return false;
                }
                correctValue++;
            }
        }
        return true;
    }

    /**Private method for updating the availableMoves ArrayList<int[]>
    @param r Row coordinate for the zero square
    @param c Column coordinate for the zero square*/
    private void updateAvailableMoves(int r, int c){
        availableMoves.clear();
        int[][] possibleNeighbors = {{r,c-1}, {r,c+1}, {r-1,c}, {r+1,c}}; // Some of these coordinates may fall outside the range of the grid
        for(int[] rc : possibleNeighbors){
            if(rc[0] >= 0 && rc[0] < gridSize && rc[1] >= 0 && rc[1] < gridSize){ // Checks which squares are within the limits of the grid
                    int[] availableMove = {rc[0], rc[1]};
                    availableMoves.add(availableMove);
            }
        }
    }
}