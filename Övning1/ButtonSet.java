import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ButtonSet extends JPanel{
    public ButtonSet(ActionListener master){
        setSize(300,300);
        setLayout(new GridLayout(5,4));
        String order = "798+456-123*0.=/";
        for(int i = 0; i < order.length(); i++){
            String buttonText = "" + order.charAt(i);
            Button temp = new Button();
            temp.setText(buttonText);
            temp.addActionListener(master);
            add(temp);
        }
    }
}