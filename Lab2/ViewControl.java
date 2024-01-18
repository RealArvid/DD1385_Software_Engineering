import javax.swing.JFrame;
import java.awt.event.*;
import java.awt.*;

public class ViewControl extends JFrame implements ActionListener{
    private Boardgame game;
    private SquarePanel squarePanel;
    private MessageField messageField;
    private int gridSize;

    ViewControl(Boardgame game, int gridSize){
        this.game = game;
        this.gridSize = gridSize;
        setSize(600,650);
        getContentPane().setBackground(Color.BLACK);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(gridSize,gridSize));
        squarePanel = new SquarePanel(this, gridSize, game);
        messageField = new MessageField(game.getMessage());
        add(squarePanel, BorderLayout.CENTER);
        add(messageField, BorderLayout.SOUTH);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Square pressedSquare = (Square) e.getSource();
        if(game.move(pressedSquare.r, pressedSquare.c)){
            for(int r = 0; r < gridSize; r++){
                for(int c = 0; c < gridSize; c++){
                    squarePanel.board[r][c].setText(game.getStatus(r,c));
                }
            }
        }
        messageField.setText(game.getMessage());

        // Square pressedSquare = (Square) e.getSource();
        // String old_text = pressedSquare.getText();
        // int oldZeroRow = game.zero_r;
        // int oldZeroCol = game.zero_c;
        // if(game.move(pressedSquare.r, pressedSquare.c)){
        //     squarePanel.board[oldZeroRow][oldZeroCol].setText(old_text); // The text of the neighboring, previously empty square, is set to the text of the pressed square
        //     pressedSquare.setText(""); // The text of the pressed square is set to ""
        // }
        // messageField.setText(game.getMessage());
    }
}