import javax.swing.*;
import java.awt.*;

public class Display extends JLabel{
    public Display(){
        super("0",null,RIGHT);
        setSize(300,100);
        setFont(new Font("Arial", 0, 30));
        //setText("0");
    }
}