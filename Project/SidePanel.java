import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class SidePanel extends JPanel{
    MyLabel nextShapeLabel;
    MyLabel levelLabel;
    MyLabel linesLabel;
    MyLabel scoreLabel;
    final int height;
    final int width;
    final int squareSize;
    private Coordinate[] tetrisCoordinates;
    private Color tetrisColor;


    SidePanel(int width, int height, int squareSize, TetrisBlock tetrisBlock){
        this.width = width;
        this.height = height;
        this.squareSize = squareSize;
        tetrisCoordinates = tetrisBlock.getRelativeCoordinates(0);
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
        add(nextShapeLabel);
        add(levelLabel);
        add(linesLabel);
        add(scoreLabel);
    }

    public void repaintTetris(TetrisBlock tetrisBlock){
        tetrisCoordinates = tetrisBlock.getRelativeCoordinates(0);
        tetrisColor = tetrisBlock.color;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        int horizontalAdjustment = (width - squareSize*TetrisBlock.width(tetrisCoordinates))/2 - squareSize * TetrisBlock.min_column(tetrisCoordinates);
        for(Coordinate c: tetrisCoordinates){
            int row = squareSize * (c.row + 2);
            int col = squareSize * c.column + horizontalAdjustment;
            g.setColor(tetrisColor);
            g.fillRect(col, row, squareSize, squareSize);
            g.setColor(Color.GRAY); // Border color
            g.drawRect(col, row, squareSize, squareSize);
        }
        g.setColor(Color.WHITE);
        g.fillRect(0,0,1,height);
    }
}