package VersionBonus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class MyButton extends JButton{
    private Random rand = new Random();
    boolean pressed;
    String text0;
    String text1;

    MyButton(ActionListener masterListener, String[] buttonText){
        this.pressed = false;
        this.text0 = buttonText[0];
        this.text1 = buttonText[1];
        this.setText(text0);
        this.setFocusable(false);
        this.setFont(new Font("Comic Sans", Font.BOLD, 25));
        this.setForeground(Color.BLACK); // Text color
        this.setBackground(new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)));
        this.addActionListener(masterListener);
    }

    public void toggleState(){
        if(this.pressed){
            this.setText(this.text0);
            this.setBackground(invertColor(this.getBackground()));        
            this.pressed = false;
        }
        else{
            this.setText(this.text1);
            this.setBackground(invertColor(this.getBackground()));
            this.pressed = true;
        }
    }

    public Color invertColor(Color color){
        return new Color(255 - color.getRed(), 255 - color.getGreen(), 255 - color.getBlue());
    }
}