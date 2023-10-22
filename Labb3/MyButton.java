import java.awt.*;
import javax.swing.*;

public class MyButton extends JButton{
    MyButton(){
        setFocusable(false);
        setFont(new Font("Comic Sans", Font.BOLD, 25));
        setForeground(Color.WHITE); // Text color (Note that the buttons have no text)
        setBackground(Color.BLACK); 
    }

    MyButton(String buttonText){
        this();
        setText(buttonText);
    }
}