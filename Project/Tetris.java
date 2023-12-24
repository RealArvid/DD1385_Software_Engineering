import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Tetris extends Thread{
    Color[][] board;
    final int numRows;
    final int numColumns;
    private TetrisBlock block;
    private boolean gameOn = true;
    private Coordinate startCoordinate;
    private final Object lock = new Object();
    private boolean pause = false;
    private long sleepEndTime = 0;
    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    private int clearedLines = 0;
    private int score = 0;
    private int sleepDuration = 1000;
    private int level = 1;
    Map<Integer, Integer> sleepDurationMap = new HashMap<>(Map.ofEntries(
        Map.entry(2, 900), Map.entry(4, 800), Map.entry(6, 700), Map.entry(8, 600),
        Map.entry(10, 500), Map.entry(12, 400), Map.entry(14, 300), Map.entry(16, 200),
        Map.entry(18, 150), Map.entry(20, 100)));

    Tetris(int numRows, int numColumns){
        this.numRows = numRows;
        this.numColumns = numColumns;
        board = new Color[numRows][numColumns];
        for(int row = 0; row < numRows; row++){
            for(int column = 0; column < numColumns; column++){
                board[row][column] = Color.BLACK;
            }
        }
        startCoordinate = new Coordinate(0, numColumns/2);
        block = new TetrisBlock(startCoordinate, board);
    }

    public void run(){
        while(gameOn){
            try{
                updateSleepEndTime();
                while(System.currentTimeMillis() < sleepEndTime){
                    long sleepTime = sleepEndTime - System.currentTimeMillis();
                    sleep((sleepTime < 0) ? 0 : sleepTime);
                }
                synchronized(lock){
                    if(pause){
                        lock.wait();
                    }
                }
                moveDown();
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public ArrayList<Coordinate> getTetrisCoordinates(){
        return block.coordinates;
    }

    public Color getTetrisColor(){
        return block.color;
    }

    public boolean rotate(){
        boolean rotated = block.rotate();
        if(rotated){
            propertyChangeSupport.firePropertyChange("moveEvent", null, block.color);
        }
        return rotated;
    }

    public boolean moveLeft(){
        boolean moved = block.move(0, -1);
        if(moved){
            propertyChangeSupport.firePropertyChange("moveEvent", null, block.color);
        }
        return moved;
    }

    public boolean moveRight(){
        boolean moved = block.move(0, 1);
        if(moved){
            propertyChangeSupport.firePropertyChange("moveEvent", null, block.color);
        }
        return moved;
    }

    public boolean moveDown(){
        boolean moved = block.move(1, 0);
        if(moved){
            propertyChangeSupport.firePropertyChange("moveEvent", null, block.color);
        }
        else if(!moved && gameOn){ // gameOn condition is necessary because otherwise a new block would be spawned when called from the ViewControl class
            solidifyBlock();
            checkRows();
            spawnNewBlock();
            checkGame();
            propertyChangeSupport.firePropertyChange("combinedEvent", null, block.color);
        }
        return moved;
    }

    private void solidifyBlock(){
        Color color = block.color;
        for(Coordinate c: block.coordinates){
            board[c.row][c.column] = color;
        }
    }  

    private void checkRows() {
        for(int row = 0; row < numRows; row++){
            boolean completeRow = true; // Used to keep track of rows with no empty spaces
            for(int col = 0; col < numColumns; col++){
                if(board[row][col]==Color.BLACK){
                    completeRow=false;
                    break;
                }
            }
            if(completeRow){
                deleteRow(row);
                clearedLines += 1;
                propertyChangeSupport.firePropertyChange("deleteRow", null, clearedLines);
                if(sleepDurationMap.containsKey(clearedLines)){
                    sleepDuration = sleepDurationMap.get(clearedLines);
                    level += 1;
                    propertyChangeSupport.firePropertyChange("LevelUp", null, level);
                }
                row--; // If a row is deleted, meaning that the row above is shifted down, row-- ensures that the row that's shifted down is also checked
            }
        }
    }

    private void deleteRow(int rowToBeDeleted) {
        for(int col = 0; col < numColumns; col++){
            for(int row = rowToBeDeleted; row > 0; row--){
                board[row][col] = board[row-1][col];
            }
        }
        for(int col = 0; col < numColumns; col++){
            board[0][col] = Color.BLACK;
        }
    }

    private void spawnNewBlock(){
        score += block.coordinates.size()*level; // += 4*level
        propertyChangeSupport.firePropertyChange("newBlock", null, score);
        block.reset(startCoordinate, board);
    }

    private void checkGame() {
        for(Coordinate c: block.coordinates){
            if(board[c.row+1][c.column] != Color.BLACK){
                System.out.println("Game Over!");
                gameOn=false;
                break;
            }
        }
    }

    public void updateSleepEndTime(){
        sleepEndTime = System.currentTimeMillis() + sleepDuration;
    }

    public void pause() {
        synchronized (lock) {
            if(pause){
                lock.notify();
            }
            pause = !pause;
        }
    }

    public void addPropertyChangeListener(PropertyChangeListener listener){
        propertyChangeSupport.addPropertyChangeListener(listener);
    }
}