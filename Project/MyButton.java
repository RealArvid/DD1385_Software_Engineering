import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MyButton extends JButton{
    private Color myRed = new Color(150, 0, 0);

    MyButton(String text, int width, int height, int panelWidth, int yPos, ActionListener listener){
        setText(text);
        setForeground(Color.WHITE);
        setFont(new Font("Comic Sans", Font.BOLD, 25));
        this.setFocusable(false);
        this.setBackground(myRed);
        this.addActionListener(listener);
        setBounds((panelWidth-width)/2, yPos, width, height);
    }
}