import java.awt.*;
import javax.swing.JLabel;

public class MyLabel extends JLabel{
    /**The width of the panel where the label is placed. Used to center the label*/
    private int panelWidth;
    private int yPos;

    MyLabel(String text, int panelWidth, int yPos){
        super.setText(text);
        this.panelWidth = panelWidth;
        this.yPos = yPos;
        setForeground(Color.WHITE);
        setFont(new Font("Comic Sans", Font.BOLD, 25));
        Dimension size = getPreferredSize();
        setBounds((panelWidth-size.width)/2, yPos, 200, size.height);
    }

    public void setText(String text){
        super.setText(text);
        Dimension size = getPreferredSize();
        setBounds((panelWidth-size.width)/2, yPos, 200, size.height); // Label is centered horizontally
    }
}
