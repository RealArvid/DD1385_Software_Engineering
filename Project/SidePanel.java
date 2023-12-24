import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

public class SidePanel extends JPanel{
    MyLabel levelLabel;
    MyLabel linesLabel;
    MyLabel scoreLabel;

    SidePanel(int width, int height){
        setBackground(Color.decode("#101010"));
        setSize(width, height);
        setPreferredSize(new Dimension(width, height));
        setVisible(true);
        setLayout(null);
        
        levelLabel = new MyLabel("Level: 1", width, 300);
        linesLabel = new MyLabel("Lines: 0", width, 400);
        scoreLabel = new MyLabel("Score: 0", width, 500);
        add(levelLabel);
        add(linesLabel);
        add(scoreLabel);
    }
}
