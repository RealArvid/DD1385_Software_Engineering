public class Main {
    public static void main(String[] args){
        Tetris game = new Tetris(25, 10);
        new ViewControl(game);
        game.start();
    }
}