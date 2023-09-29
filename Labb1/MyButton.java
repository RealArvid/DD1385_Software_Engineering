import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MyButton extends JButton{
    private Color myRed = new Color(150, 0, 0);
    private Color myGreen = new Color(0, 150, 0);

    MyButton(ActionListener masterListener, String buttonText){
        this.setText(buttonText);
        this.setFocusable(false);
        this.setFont(new Font("Comic Sans", Font.BOLD, 25));
        this.setForeground(Color.BLACK);
        this.setBackground(myRed);
        this.addActionListener(masterListener);
    }

    public void toggleState(){
        String oldText = this.getText();
        System.out.println(oldText);
        if(this.getBackground() == myRed){
            this.setText(oldText.replaceAll("Off", "On"));
            this.setBackground(myGreen);
        }
        else{
            this.setText(oldText.replaceAll("On", "Off"));
            this.setBackground(myRed);
        }
        
    }
}
