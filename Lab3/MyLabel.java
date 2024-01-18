import java.awt.*;
import javax.swing.JLabel;

public class MyLabel extends JLabel{
    MyLabel(String labelText){
        super(labelText, JLabel.CENTER);
        setFocusable(false);
        setFont(new Font("Comic Sans", Font.BOLD, 25));
        setForeground(Color.WHITE); // Text color
    }
}
