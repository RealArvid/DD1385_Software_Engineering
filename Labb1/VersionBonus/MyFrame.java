package VersionBonus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.Math;

public class MyFrame extends JFrame implements ActionListener{
    MyButton[] buttons;
    int numButtons;

    MyFrame(String[][] buttonTexts){
        this.numButtons = buttonTexts.length;
        int gridSize = (int) Math.ceil(Math.sqrt(numButtons)); // Example: If numButtons = 8, gridSize = 3 (representing a 3x3 grid) 
        this.setLayout(new GridLayout(gridSize, gridSize));
        this.setSize(600,350);
        this.setTitle("My program");  
        this.getContentPane().setBackground(Color.BLACK);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.buttons = new MyButton[numButtons];

        for(int i = 0; i < numButtons; i++){
            MyButton button = new MyButton(this, buttonTexts[i]);
            buttons[i] = button;
            this.add(button);
        }

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MyButton pressedButton = (MyButton) e.getSource();
        for(MyButton button : this.buttons){
            if(button != pressedButton){
                button.toggleState();
            }
        }
    }
}