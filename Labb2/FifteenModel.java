import java.util.ArrayList;
import java.util.stream.IntStream;
import java.util.Random;

public class FifteenModel implements Boardgame{
    int[][] board;
    int gridSize;
    /**Row index of square previously containing the number zero and which is assigned the number of the pressed square*/
    int replacedRow;
    /**Column index of square previously containing the number zero and which is assigned the number of the pressed square*/
    int replacedCol;
    private Random rand = new Random();
    private String message = "Game to be started";
    
    /**Fills the board[][] with numbers 0 to gridSize^2 - 1*/
    FifteenModel(int gridSize){
        this.gridSize = gridSize;
        board = new int[gridSize][gridSize];
        ArrayList<Integer> intList = new ArrayList<>(IntStream.range(0, gridSize*gridSize).boxed().toList());
        int numElementsLeft = intList.size();
        for(int r = 0; r < gridSize; r++){
            for(int c = 0; c < gridSize; c++){
                int randIdx = rand.nextInt(numElementsLeft);
                board[r][c] = intList.remove(randIdx);
                numElementsLeft--;
            }
        }
    }

    @Override
    public boolean move(int r, int c) {
        int[][] neighbors = {{r,c-1}, {r,c+1}, {r-1 ,c}, {r+1,c}}; // The row and column coordinates of all squares neighboring the pressed square
        for(int[] rc : neighbors){
            int new_r = rc[0];
            int new_c = rc[1];
            if(new_r >= 0 && new_r < gridSize && new_c >= 0 && new_c < gridSize){ // Checking so that the neighboring squares are within the limits of the grid
                if(board[new_r][new_c] == 0){ // board[new_r][new_c] == 0 represents the empty square
                    board[new_r][new_c] = board[r][c]; // The value of the empty square is set to the value of the pressed square
                    replacedRow = new_r; //
                    replacedCol = new_c;                    
                    board[r][c] = 0;
                    
                    if(hasWon()){
                        message = "Congratulations, you won!";
                    }
                    else{
                        message = "Move made successfully!";
                    }
                    
                    return true;
                }
            }
        }
        message = "Could not make the requested move";
        return false;
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
}