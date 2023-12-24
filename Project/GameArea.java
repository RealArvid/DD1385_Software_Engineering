import java.awt.*;
import java.util.ArrayList;
import javax.swing.JPanel;

public class GameArea extends JPanel{
    private Color[][] board;
    private int squareSize;
    private ArrayList<Coordinate> tetrisCoordinates = new ArrayList<>();
    private ArrayList<Coordinate> oldTetrisCoordinates = new ArrayList<>();
    private Color tetrisColor;
    private boolean repaintBoard = true;
    private boolean repaintTetris = true;
    final int numCols;
    final int numRows;

    GameArea(Color[][] board, int squareSize, ArrayList<Coordinate> tetrisCoordinates, Color tetrisColor){
        this.board = board;
        this.squareSize = squareSize;
        this.tetrisCoordinates = tetrisCoordinates; // Only passed in once (since the tetris object only reset and never replaced by another)
        this.tetrisColor = tetrisColor;
        this.numCols = board[0].length;
        this.numRows = board.length;
        setPreferredSize(new Dimension(squareSize*numCols, squareSize*numRows));
        setVisible(true);
    }

    public void repaintAll(Color tetrisColor){
        this.tetrisColor = tetrisColor;
        oldTetrisCoordinates.clear();  // After a block has been soldified, we don't want to make its old coordinates black
        repaintBoard = true;
        repaintTetris = true;
        repaint();
    }

    public void repaintTetris(Color tetrisColor){
        this.tetrisColor = tetrisColor;
        repaintBoard = false;
        repaintTetris = true;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        if(repaintBoard){
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, squareSize*numCols, squareSize*numRows);
            for(int row = 0; row < numRows; row++){
                for(int col = 0; col < numCols; col++){
                    if(board[row][col] != Color.BLACK){
                        g.setColor(Color.GRAY);
                        g.drawRect(squareSize*col, squareSize*row, squareSize, squareSize);
                        g.setColor(board[row][col]);
                        g.fillRect(squareSize*col, squareSize*row, squareSize, squareSize);
                    }
                }
            }
        }
        if(repaintTetris){
            g.setColor(Color.BLACK);
            for(Coordinate c: oldTetrisCoordinates){ // Erasing the old tetris
                g.fillRect(squareSize*c.column, squareSize*c.row, squareSize, squareSize);
                g.drawRect(squareSize*c.column, squareSize*c.row, squareSize, squareSize);
            }
            for(Coordinate c: tetrisCoordinates){ // Painting the new tetris
                g.setColor(Color.GRAY); // Border color
                g.drawRect(squareSize*c.column, squareSize*c.row, squareSize, squareSize); // Painting the borders of the squares of the tetris block
                g.setColor(tetrisColor);
                g.fillRect(squareSize*c.column, squareSize*c.row, squareSize, squareSize);
            }
            oldTetrisCoordinates = Coordinate.deepCopy(tetrisCoordinates);
        }
    }
}