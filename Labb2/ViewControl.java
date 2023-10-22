import javax.swing.JFrame;
import java.awt.event.*;
import java.awt.*;

public class ViewControl extends JFrame implements ActionListener, Runnable {
    private FifteenModel game;
    private SquarePanel squarePanel;
    private MessageField messageField;
    private int gridSize;
    Thread activity;

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
        int oldZeroRow = game.zero_r;
        int oldZeroCol = game.zero_c;
        boolean moveSuccess = game.move(pressedSquare.r, pressedSquare.c);
        if(moveSuccess){
            squarePanel.board[oldZeroRow][oldZeroCol].setText(old_text); // The text of the neighboring, previously empty square, is set to the text of pressed square
            pressedSquare.setText(""); // The text of the pressed square is set to ""
        }
        messageField.setText("");
        activity = new Thread(this);
		activity.start();
    }

    @Override
    public void run() {
        try {
            Thread.sleep(50);
        }
        catch(InterruptedException e) {
            ;
        }
        finally{
            messageField.setText(game.getMessage());
        }
    }
}