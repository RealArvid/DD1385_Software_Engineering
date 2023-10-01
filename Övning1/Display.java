import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;

public class Display extends JLabel{
    public Display(String startText){
        super("0",null,RIGHT);
        setForeground(Color.WHITE); // text color
        setBackground(Color.BLACK);
        setOpaque(true);
        setFont(new Font("Arial", 0, 35));
        setText(startText);
        setBorder(new EmptyBorder(5,5,0,5)); // Adding some padding
    }
}