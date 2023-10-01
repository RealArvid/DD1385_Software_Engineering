import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener{
    Display disp;
    ButtonPanel buttons;
    Double[] memmory = {null, null};
    char operator = '=';
    String dispText = "0";
    
    private Calculator(){
        setSize(300,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Calculator");
        disp = new Display(dispText);
        buttons = new ButtonPanel(this);
        setLayout(new GridBagLayout()); // Unnecessary since BorderLayout is the default layout
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(disp, gbc);

        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(buttons, gbc);

        setVisible(true);
    }
        // a + b +
        // a + b =    
    public static void main(String[] args){
        new Calculator(); // Raknare mainWindow = new Raknare();
    }
    public void actionPerformed(ActionEvent ae){
        String buttonText = ae.getActionCommand();
        char buttonSymbol = buttonText.charAt(0);
        int ascii = (int) buttonSymbol;
        
        if ((ascii >= 48 && ascii <= 57) || ascii == 46){ // 0-9 or .
            if(buttonSymbol == '.'){
                if(!dispText.matches("[0-9]*\\.")){ // Matches .
                    dispText += buttonText;
                }
            }            
            else{
                if(dispText.matches("^0$|[\\+\\-\\*\\/]")){ // Matches "0" (only) and all strings containing +, -, /, *
                    dispText = "" + buttonText;
                }
                else{
                    dispText += buttonText;           
                }                
            }
            
            if(operator == '='){// Updating the memmory
                memmory[0] = Double.parseDouble(dispText);            
            }
            else{
                memmory[1] = Double.parseDouble(dispText);
            }
        }
        
        else{// If anything but a number has been entered
            if(memmory[1] != null){
                memmory[0] = calculate(memmory[0], memmory[1], operator);
            }

            if(buttonSymbol == '='){
                memmory[1] = null;
                dispText = String.valueOf(memmory[0]).replaceAll(".0$", ""); // Implemented with regex to remove .0 at the end
                if(operator == '='){
                    memmory[0] = null;
                    dispText = "0";
                }
            }
            else{
                dispText = "" + buttonSymbol; 
            }
            operator = buttonSymbol;
        }
        disp.setText(dispText);
        System.out.println(buttonSymbol + ", " + memmory[0] + ", " + memmory[1]);
    }

    public double calculate(double a, double b, char operator){
        double answer = 0;
        if(operator == '+'){
            answer = a + b;
        }
        else if(operator == '-'){
            answer = a - b;
        }
        else if(operator == '*'){
            answer = a * b;
        }
        else if(operator == '/'){
            answer = a / b;
        }
        return answer;
    }
}