import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class Button extends JButton{
    Button(ActionListener masterListener, String buttonText){
        this.setForeground(Color.WHITE);
        this.setBackground(Color.BLACK);
        this.setText(buttonText);
        this.setFont(new Font("Comic Sans", Font.BOLD, 25));
        this.setFocusable(false);
        this.addActionListener(masterListener);
    }
}
