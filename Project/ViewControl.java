import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;

public class ViewControl extends JFrame implements PropertyChangeListener{
    Tetris game;
    GameArea gameArea;
    SidePanel sidePanel;
    int squareSize = 40;

    ViewControl(Tetris game){      
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        setTitle("Tetris");
        
        this.game = game;
        game.addPropertyChangeListener(this);
        gameArea = new GameArea(game.board, squareSize, game.getTetrisCoordinates(), game.getTetrisColor());
        initControls();

        sidePanel = new SidePanel(250, squareSize*game.numRows);
        add(gameArea);
        add(sidePanel);
        
        pack();
        setLocationRelativeTo(null); // Centers window
        setVisible(true);
    }

    private void initControls(){
        InputMap inputMap = getRootPane().getInputMap();
        inputMap.put(KeyStroke.getKeyStroke("RIGHT"), "right");
        inputMap.put(KeyStroke.getKeyStroke("LEFT"), "left");
        inputMap.put(KeyStroke.getKeyStroke("UP"), "up");
        inputMap.put(KeyStroke.getKeyStroke("DOWN"), "down");
        inputMap.put(KeyStroke.getKeyStroke("p"), "pause");
        inputMap.put(KeyStroke.getKeyStroke("SPACE"), "pause");

        ActionMap actionMap = getRootPane().getActionMap();
        actionMap.put("right", new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e) {
                game.moveRight();
            }
        });

        actionMap.put("left", new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e) {
                game.moveLeft();
            }
        });

        actionMap.put("up", new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e) {
                game.rotate();
            }
        });

        actionMap.put("down", new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(game.moveDown()){
                    game.updateSleepEndTime();
                }
            }
        });

        actionMap.put("pause", new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e) {
                game.pause();
            }
        });
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if("moveEvent".equals(evt.getPropertyName())){
            gameArea.repaintTetris((Color) evt.getNewValue());
        }
        else if("combinedEvent".equals(evt.getPropertyName())){
            gameArea.repaintAll((Color) evt.getNewValue());
        }
        else if("deleteRow".equals(evt.getPropertyName())){
            sidePanel.linesLabel.resetText("Lines: " + (int) evt.getNewValue());
        }
        else if("newBlock".equals(evt.getPropertyName())){
            sidePanel.scoreLabel.resetText("Score: " + (int) evt.getNewValue());
        }
        else if("LevelUp".equals(evt.getPropertyName())){
            sidePanel.levelLabel.resetText("Level: " + (int) evt.getNewValue());
        }
    }

    public static void main(String[] args){
        Tetris game = new Tetris(20, 10);
        new ViewControl(game);
        game.start();
    }
}