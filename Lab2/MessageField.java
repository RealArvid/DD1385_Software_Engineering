import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class MessageField extends JLabel{
    MessageField(String text){
        setText(text);
        setFont(new Font("Apple Casual", Font.BOLD, 20));
        setForeground(Color.WHITE);
        setHorizontalAlignment(SwingConstants.CENTER);
        setBorder(new EmptyBorder(0,0,7,0)); // Adding some padding
    }
}
