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
    private static Color darkDray = new Color(25, 25, 25);
    final int numCols;
    final int numRows;

    GameArea(Color[][] board, int squareSize, ArrayList<Coordinate> tetrisCoordinates, Color tetrisColor){
        this.board = board;
        this.squareSize = squareSize;
        this.tetrisCoordinates = tetrisCoordinates;
        this.tetrisColor = tetrisColor;
        this.numCols = board[0].length;
        this.numRows = board.length;
        setPreferredSize(new Dimension(squareSize*numCols, squareSize*numRows));
        setVisible(true);
    }

    public void repaintAll(ArrayList<Coordinate> tetrisCoordinates, Color tetrisColor){
        this.tetrisColor = tetrisColor;
        this.tetrisCoordinates = tetrisCoordinates;
        repaintBoard = true;
        repaintTetris = true;
        oldTetrisCoordinates.clear(); // Necessary , otherwise solidified tetris blocks will not be drawn until thismthod is called again;
        repaint();
    }

    public void repaintTetris(ArrayList<Coordinate> tetrisCoordinates, Color tetrisColor){
        this.tetrisColor = tetrisColor;
        this.tetrisCoordinates = tetrisCoordinates;
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
                    g.setColor(darkDray);
                    g.drawRect(squareSize*col, squareSize*row, squareSize, squareSize);
                    if(board[row][col] != Color.BLACK){
                        g.setColor(board[row][col]);
                        g.fillRect(squareSize*col, squareSize*row, squareSize, squareSize);
                        g.setColor(Color.GRAY);
                        g.drawRect(squareSize*col, squareSize*row, squareSize, squareSize);
                    }
                }
            }
        }
        if(repaintTetris){
            g.setColor(Color.BLACK);
            for(Coordinate c: oldTetrisCoordinates){ // Erasing the old tetris
                g.setColor(Color.BLACK);
                g.fillRect(squareSize*c.column, squareSize*c.row, squareSize, squareSize);
                g.setColor(darkDray);
                g.drawRect(squareSize*c.column, squareSize*c.row, squareSize, squareSize);
            }
            for(Coordinate c: tetrisCoordinates){ // Painting the new tetris
                g.setColor(tetrisColor);
                g.fillRect(squareSize*c.column, squareSize*c.row, squareSize, squareSize);
                g.setColor(Color.GRAY); // Border color
                g.drawRect(squareSize*c.column, squareSize*c.row, squareSize, squareSize); // Painting the borders of the squares of the tetris block
            }
            oldTetrisCoordinates = Coordinate.deepCopy(tetrisCoordinates);
        }
    }
}