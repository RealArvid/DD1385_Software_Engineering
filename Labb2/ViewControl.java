import javax.swing.JFrame;

import java.awt.*;
import java.awt.event.*;

public class ViewControl extends JFrame implements ActionListener {
    private FifteenModel game;
    private int gridSize;
    private SquarePanel squarePanel;
    private MessageField messageField;

    ViewControl(FifteenModel game){
        this.game = game;
        this.gridSize = game.gridSize;
        setSize(600,650);
        setTitle(gridSize*gridSize - 1 + " Puzzle"); // 15 Puzzle if gridSize = 4
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
        String old_text = pressedSquare.getText();
        game.move(pressedSquare.r, pressedSquare.c);
        if(game.getMessage() != "Could not make the requested move"){
            squarePanel.board[game.replacedRow][game.replacedCol].setText(old_text); // The text of the neighboring, previously empty square, is set to the text of pressed square
            pressedSquare.setText(""); // The text of the pressed square is set to ""
        }
        messageField.setText(game.getMessage());
    }

    public static void main(String[] args){
        int gridSize = 2;
        FifteenModel game = new FifteenModel(gridSize);
        new ViewControl(game);
    }
}