public class TicTacToe implements Boardgame{
    char[][] board;
    int gridSize;

    TicTacToe(int gridSize){
        this.gridSize = gridSize;
        board = new char[gridSize][gridSize];
        for(int r = 0; r < gridSize; r++){
            for(int c = 0; c < gridSize; c++){
                board[r][c] = ' ';
            }
        }
    }

    public boolean move(int x, int y){
        return true;
    }

    public String getStatus(int x, int y){
        return "x";
    }

    public String getMessage(){
        return "Mock message";
    }

}