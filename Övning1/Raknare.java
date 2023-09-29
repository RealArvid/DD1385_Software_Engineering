import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;

public class Raknare extends JFrame implements ActionListener{
    Display disp;
    ButtonSet buttons;
    String shown = "0";
    double dispValue, memmory;
    char operator=0;
    
    private Raknare(){
        setSize(300,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        disp = new Display();
        buttons = new ButtonSet(this);
        setLayout(new BorderLayout());
        
        add(disp, BorderLayout.NORTH);
        add(buttons, BorderLayout.CENTER);
        setVisible(true);
    }
    
    public static void main(String[] args){
        Raknare mainWindow = new Raknare();
    }
    public void actionPerformed(ActionEvent ae){
        String button = ae.getActionCommand();
        char sign = button.charAt(0);
        int num = button.charAt(0) - '0';
        System.out.println(button);
        if (num < 10 && num > -1){
            if(!shown.equals("0")){
                shown += button;
                disp.setText(shown);
            }
            else{
                shown = button;
            }
        }
        else if(sign=='+'){
            if(memmory == 0){
                memmory=Integer.parseInt(shown);
                operator = sign;
                shown = "0";
            }
            else{
                memmory += Integer.parseInt(shown);
                operator = sign;
                disp.setText(""+memmory);
            }
        }
    }
}
