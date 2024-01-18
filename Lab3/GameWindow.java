import java.awt.*;
import java.awt.event.*;
import java.io.PrintWriter;
import javax.swing.*;

class GameWindow extends JFrame implements WindowListener{
    GameBoard myBoard, computerBoard;
	PrintWriter out;

    GameWindow(String playerName, PrintWriter out, ActionListener listener) {
		this.out = out;

		computerBoard = new GameBoard("Computer");
		myBoard = new GameBoard(playerName, listener);
		JPanel boards = new JPanel();
		boards.setLayout(new GridLayout(1,2));
		boards.add(computerBoard);
		boards.add(myBoard);
		add(boards, BorderLayout.CENTER);

		// CloseButton closeButton = new CloseButton("Close", out);
		// add(closeButton, BorderLayout.SOUTH);

		setTitle("Rock Paper Scissors");
		addWindowListener(this);
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(350, 650);
		setVisible(true);
    }

	@Override
	public void windowOpened(WindowEvent e) {
		;
	}

	@Override
	public void windowClosing(WindowEvent e) {
		out.println("");
        out.flush();
		System.exit(0);
	}

	@Override
	public void windowClosed(WindowEvent e) {
		;	
	}

	@Override
	public void windowIconified(WindowEvent e) {
		;
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		;
	}

	@Override
	public void windowActivated(WindowEvent e) {
		;
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		;
	}
}