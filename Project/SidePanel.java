import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class SidePanel extends JPanel{
    MyLabel nextShapeLabel, levelLabel, linesLabel, scoreLabel;
    private MyButton restartButton;
    final int height, width, squareSize;
    private Coordinate[] tetrisCoordinates;
    private int tetrisWidth;
    private Color tetrisColor;


    SidePanel(int width, int height, int squareSize, TetrisBlock tetrisBlock, ActionListener listener){
        this.width = width;
        this.height = height;
        this.squareSize = squareSize;
        tetrisCoordinates = tetrisBlock.relativeCoordinates[0];
        tetrisWidth = tetrisBlock.width();
        tetrisColor = tetrisBlock.color;
        setBackground(Color.BLACK);
        setSize(width, height);
        setPreferredSize(new Dimension(width, height));
        setVisible(true);
        setLayout(null);

        nextShapeLabel = new MyLabel("Next shape:", width, (int) Math.round(0.75*squareSize));
        levelLabel     = new MyLabel("Level: 1", width, squareSize*6);
        linesLabel     = new MyLabel("Lines: 0", width, squareSize*8);
        scoreLabel     = new MyLabel("Score: 0", width, squareSize*10);
        restartButton  = new MyButton("Restart", 3*squareSize, squareSize, width, height-2*squareSize, listener);
        add(nextShapeLabel);
        add(levelLabel);
        add(linesLabel);
        add(scoreLabel);
        add(restartButton);
    }


    public void repaintTetris(TetrisBlock tetrisBlock){
        tetrisCoordinates = tetrisBlock.relativeCoordinates[0];
        tetrisWidth = tetrisBlock.width();
        tetrisColor = tetrisBlock.color;
        repaint();
    }


    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        // Painting the preview of the next tetris block
        int horizontalAdjustment = (width - squareSize*tetrisWidth)/2 - squareSize * leftmost_column(tetrisCoordinates);
        for(Coordinate c: tetrisCoordinates){
            int row = squareSize * (c.row + 2);
            int col = squareSize * c.column + horizontalAdjustment;
            g.setColor(tetrisColor);
            g.fillRect(col, row, squareSize, squareSize);
            g.setColor(Color.GRAY); // Border color
            g.drawRect(col, row, squareSize, squareSize);
        }
        // Painting a white line of width 1 at the leftmost edge of the panel
        g.setColor(Color.WHITE);
        g.fillRect(0,0,1,height);
    }


    private static int leftmost_column(Coordinate[] coordinates){
        if(coordinates.length==0){
            return 0;
        }
        int min = coordinates[0].column;
        for(Coordinate c: coordinates){
            if(c.column < min){
                min = c.column;
            }
        }
        return min;
    }
}