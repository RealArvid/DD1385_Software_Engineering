package Labb1;
import javax.swing.JFrame;

public class MyFrame extends JFrame {
    JFrame frame;
    
    MyFrame(){
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420,420);
        //frame.setVisible(true);
    }

    public void show(){
        frame.setVisible(true);
    }
}