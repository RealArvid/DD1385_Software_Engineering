package VersionA;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame implements ActionListener{
    MyButton button;
    MyButton button2;
    
    MyFrame(){
        this.setSize(600,350);
        this.setTitle("My program");  
        this.getContentPane().setBackground(new Color(30, 0, 30));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        
        
        button = new MyButton(this, "Off");
        button.setBounds(100, 100, 100, 100);
        this.add(button);

        button2 = new MyButton(this, "Off (BIG)");
        button2.setBounds(300, 50, 200, 200);
        this.add(button2);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MyButton pressedButton = (MyButton) e.getSource();
        pressedButton.toggleState();
    }
}