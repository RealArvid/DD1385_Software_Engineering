import javax.swing.*;
import java.awt.*;

public class JLabelAbsolutePositionExample {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("JLabel Absolute Position Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel panel = new JPanel(null); // Set layout manager to null for absolute positioning

            // Create a JLabel
            JLabel label = new JLabel("Hello, World!");

            // Set the position of the JLabel
            label.setBounds(0, 50, 200, 30); // x, y, width, height

            // Add the JLabel to the JPanel
            panel.add(label);

            frame.getContentPane().add(panel);
            frame.setSize(300, 200); // Set frame size explicitly
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}