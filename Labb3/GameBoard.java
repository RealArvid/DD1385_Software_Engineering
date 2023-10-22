import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

import javax.swing.*;

class GameBoard extends JPanel{
    private Icon[] icons = {
		new ImageIcon("rock.png"), 
		new ImageIcon("paper.png"),
		new ImageIcon("scissors.png")}; // From flaticon.com
    private MyButton[] buttons = new MyButton[3];
    private MyButton selectedButton; // Remembers last chosen button/gesture
    private String[] texts = {"ROCK", "PAPER", "SCISSORS"};
    private MyLabel upperMessage, lowerMessage, scoreLabel;
    private int score;
    private Color bgColor = Color.BLACK;
	private Color markedColor = new Color(180, 180, 0);
	private HashMap<String,MyButton> map = new HashMap<String, MyButton>();


    /**Constructor that builds the board. Used for computer board,
	as it does not add listeners to buttons*/
    GameBoard(String name) {
		setLayout(new GridLayout(5,1));

		// Upper JPanel holds player's name and last gesture played
		JPanel upper = new JPanel();
		upper.setBackground(bgColor);
		upper.setLayout(new GridLayout(2,1));
		upper.add(new MyLabel(name)); 
		upperMessage = new MyLabel("–");
		upper.add(upperMessage);
		add(upper);

		// Lower JPanel has messages about the game and score
		JPanel lower = new JPanel();
		lower.setBackground(bgColor);
		lower.setLayout(new GridLayout(2,1));
		lowerMessage = new MyLabel("win/lose/draw");
		scoreLabel = new MyLabel("Score: 0");
		lower.add(lowerMessage); lower.add(scoreLabel);

		for (int i = 0; i<3; i++){
			buttons[i] = new MyButton();
			buttons[i].setIcon(icons[i]);
			buttons[i].setActionCommand(texts[i]);
			add(buttons[i]);
			// Store each button in a map with its text as key.
			// Enables us to retrieve the button from a textvalue.
			map.put(texts[i], buttons[i]);
		}
		
		selectedButton = buttons[0]; // Arbitrary value at start
		add(lower);
    }


    /**Contructor for players board. Adds listener to buttons*/
    GameBoard(String name, ActionListener listener) {
		this(name); // Calls other constructor to build the board
		for(int i = 0; i < 3; i++)
			buttons[i].addActionListener(listener);
    }

    /**Resets the color of the previously selected button*/
    void resetColor() {
		selectedButton.setBackground(bgColor);
    }

	/**Sets the lower message*/
    void setUpperMessage(String text) {
		upperMessage.setText(text);
    }

	/**Sets the upper message*/
    void setLowerMessage(String text) {
		lowerMessage.setText(text);
    }

	/**remember last chosen MyButton and mark it yellow*/
	void markPlayed(String gesture) {
	selectedButton = map.get(gesture);
	selectedButton.setBackground(markedColor);
	}

    /**Adds one point and display new score*/
    void wins() {
		score++;
		scoreLabel.setText("Score: " + score);
    }
}