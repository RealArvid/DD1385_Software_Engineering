import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.*;

public class SquarePanel extends JPanel{
    Square[][] board;

    SquarePanel(ActionListener masterListener, int gridSize, Boardgame game){
        setLayout(new GridLayout(gridSize,gridSize));
        board = new Square[gridSize][gridSize];
        for(int r = 0; r < gridSize; r++){
            for(int c = 0; c < gridSize; c++){
                String buttonText = game.getStatus(r, c);
                Square square = new Square(masterListener, "" + buttonText, r, c);
                board[r][c] = square;
                add(square);
            }
        }
    }
}