public class Main {
    public static void main(String[] args){
        int gridSize = 4;
        //FifteenModel game = new FifteenModel(gridSize);
        TicTacToe game = new TicTacToe(gridSize);
        new ViewControl(game, gridSize);
    }
}