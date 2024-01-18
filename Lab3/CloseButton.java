import java.awt.event.*;
import java.io.PrintWriter;

public class CloseButton extends MyButton implements ActionListener{
    PrintWriter out;

    CloseButton(String text, PrintWriter out){
        this.out = out;
        setText(text);
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        out.println("");
        out.flush();
        System.exit(0);
    }
}