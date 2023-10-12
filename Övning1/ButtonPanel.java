import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ButtonPanel extends JPanel{
    public ButtonPanel(ActionListener masterListener){
        setLayout(new GridLayout(4,4));
        String order = "798+456-123*0./=";
        for(int i = 0; i < order.length(); i++){
            String buttonText = "" + order.charAt(i);
            Button button = new Button(masterListener, buttonText);
            add(button);
        }
    }
}