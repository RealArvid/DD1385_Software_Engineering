import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;

public class ViewControl extends JFrame implements ActionListener, PropertyChangeListener{
    Tetris game;
    GameArea gameArea;
    SidePanel sidePanel;
    int squareSize = 40;
    private InputMap inputMap;
    private ActionMap actionMap;


    ViewControl(Tetris game){      
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        setTitle("Tetris");
        
        this.game = game;
        this.gameArea = new GameArea(game.board, squareSize, game.block);
        this.sidePanel = new SidePanel(squareSize*5, squareSize*game.numRows, squareSize, game.nextBlock, this);
        game.addPropertyChangeListener(this);
        add(gameArea);
        add(sidePanel);
        pack();

        initControls();
        setLocationRelativeTo(null); // Centers window on screen
        setResizable(false);
        setVisible(true);
    }


    private void initControls(){
        inputMap = getRootPane().getInputMap();
        inputMap.put(KeyStroke.getKeyStroke("RIGHT"), "right");
        inputMap.put(KeyStroke.getKeyStroke("LEFT"), "left");
        inputMap.put(KeyStroke.getKeyStroke("UP"), "up");
        inputMap.put(KeyStroke.getKeyStroke("DOWN"), "down");
        inputMap.put(KeyStroke.getKeyStroke("p"), "pause");
        inputMap.put(KeyStroke.getKeyStroke("SPACE"), "pause");

        actionMap = getRootPane().getActionMap();
        actionMap.put("right", new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e){
                game.moveRight();
            }
        });

        actionMap.put("left", new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e){
                game.moveLeft();
            }
        });

        actionMap.put("up", new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e){
                game.rotate();
            }
        });

        actionMap.put("down", new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e){
                if(game.moveDown()){
                    game.updateSleepEndTime();
                }
            }
        });

        actionMap.put("pause", new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e){
                game.pause();
            }
        });
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt){
        if("Repaint".equals(evt.getPropertyName())){
            gameArea.repaint(game.block);
        }
        else if("Row deleted".equals(evt.getPropertyName())){
            sidePanel.linesLabel.setText("Lines: " + (int) evt.getNewValue());
        }
        else if("Update score and block preview".equals(evt.getPropertyName())){
            sidePanel.scoreLabel.setText("Score: " + (int) evt.getNewValue());
            sidePanel.repaintTetris(game.nextBlock);
        }
        else if("Update level".equals(evt.getPropertyName())){
            sidePanel.levelLabel.setText("Level: " + (int) evt.getNewValue());
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        game.restart();
        sidePanel.linesLabel.setText("Lines: 0");
        sidePanel.scoreLabel.setText("Score: 0");
        sidePanel.levelLabel.setText("Level: 1");
        sidePanel.repaintTetris(game.nextBlock);
        gameArea.repaint(game.block);
    }


    public static void main(String[] args){
        Tetris game = new Tetris(20, 10);
        new ViewControl(game);
        game.start();
    }
}