import java.awt.*;
import javax.swing.JLabel;

public class MyLabel extends JLabel{
    private String text;
    private int panelWidth;
    private int yPos;

    MyLabel(String text, int panelWidth, int yPos){
        this.text = text;
        this.panelWidth = panelWidth;
        this.yPos = yPos;
        setText(text);
        setForeground(Color.WHITE);
        setFont(new Font("Comic Sans", Font.BOLD, 25));
        Dimension size = getPreferredSize();
        setBounds((panelWidth-size.width)/2, yPos, 200, size.height);
    }

    public void resetText(String text){
        this.text = text;
        setText(text);
        Dimension size = getPreferredSize();
        setBounds((panelWidth-size.width)/2, yPos, 200, size.height);
    }
}
