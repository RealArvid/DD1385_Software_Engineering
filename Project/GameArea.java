import java.awt.*;
import java.util.ArrayList;
import javax.swing.JPanel;

public class GameArea extends JPanel{
    private Color[][] board;
    private int squareSize;
    private ArrayList<Coordinate> tetrisCoordinates = new ArrayList<>();
    private Color tetrisColor;
    private static Color darkGray = new Color(25, 25, 25);
    final int numRows, numCols;

    GameArea(Color[][] board, int squareSize, TetrisBlock tetrisBlock){
        this.board = board;
        this.squareSize = squareSize;
        this.tetrisCoordinates = tetrisBlock.coordinates;
        this.tetrisColor = tetrisBlock.color;
        this.numCols = board[0].length;
        this.numRows = board.length;
        setPreferredSize(new Dimension(squareSize*numCols, squareSize*numRows));
        setVisible(true);
    }

    public void repaint(TetrisBlock tetrisBlock){
        this.tetrisCoordinates = tetrisBlock.coordinates;
        this.tetrisColor = tetrisBlock.color;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g){
        // Painting board
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, squareSize*numCols, squareSize*numRows); // Making everything black
        for(int row = 0; row < numRows; row++){
            for(int col = 0; col < numCols; col++){
                if(board[row][col] == Color.BLACK){
                    g.setColor(darkGray);
                    g.drawRect(squareSize*col, squareSize*row, squareSize, squareSize);
                }
                else{
                    g.setColor(board[row][col]);
                    g.fillRect(squareSize*col, squareSize*row, squareSize, squareSize);
                    g.setColor(Color.GRAY);
                    g.drawRect(squareSize*col, squareSize*row, squareSize, squareSize);
                }
            }
        }

        // Overlaying tetris block
        for(Coordinate c: tetrisCoordinates){
            g.setColor(tetrisColor);
            g.fillRect(squareSize*c.column, squareSize*c.row, squareSize, squareSize);
            g.setColor(Color.GRAY); // Border color
            g.drawRect(squareSize*c.column, squareSize*c.row, squareSize, squareSize);
        }
    }
}