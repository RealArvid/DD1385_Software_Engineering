public class TicTacToe implements Boardgame{
    char[][] board;
    int gridSize;
    private String message = "Game to be started";

    /**Keeps track of the total number of moves during a game*/
    private int numMoves;

    TicTacToe(int gridSize){
        this.gridSize = gridSize;
        board = new char[gridSize][gridSize];
        for(int r = 0; r < gridSize; r++){
            for(int c = 0; c < gridSize; c++){
                board[r][c] = ' ';
            }
        }
    }

    public boolean move(int r, int c){
        numMoves++;
        board[r][c] = 'x';
        message = "" + numMoves;
        return true;
    }

    public String getStatus(int r, int c){
        return "" + board[r][c];
    }

    public String getMessage(){
        return message;
    }

}