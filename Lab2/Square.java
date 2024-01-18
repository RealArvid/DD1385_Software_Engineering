import java.awt.*;
import java.awt.event.*;

import javax.swing.JButton;

public class Square extends JButton{
    int r; //Row position in the grid
    int c; //Column position in the grid
    
    Square(ActionListener listener, String buttonText, int r, int c){
        this.r = r;
        this.c = c;
        setText(buttonText);
        setForeground(Color.WHITE);
        setBackground(Color.BLACK);
        setFont(new Font("Comic Sans", Font.BOLD, 25));
        setFocusable(false);
        addActionListener(listener);
    }

    @Override // Not used in final implementation
    public String toString(){
        return getText();
    }
}
