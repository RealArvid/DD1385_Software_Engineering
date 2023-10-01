package VersionC;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ear implements ActionListener{
    private MyButton button;

    Ear(MyButton button){
        this.button = button;
        button.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        this.button.toggleState();
    }
    
}
