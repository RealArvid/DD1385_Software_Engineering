import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Tetris extends Thread{
    Color[][] board;
    final int numRows, numColumns;
    private Coordinate startCoordinate;
    public TetrisBlock block, nextBlock;   
    private int level, clearedLines, score;

    /**Can be initialized to anything that satisfies System.currentTimeMillis() < sleepEndTime*/
    private long sleepEndTime;

    /**The duration in miliseconds that sleep() is called before a tetris block falls down another step*/
    private int sleepDuration;

    /**Mapping from clearedLines to sleeping duration*/
    Map<Integer, Integer> sleepDurationMap = new HashMap<>(Map.ofEntries(
        Map.entry(0, 1000), Map.entry(2, 900), Map.entry(4, 800), Map.entry(6, 700),
        Map.entry(8, 600), Map.entry(10, 500), Map.entry(12, 400), Map.entry(14, 300),
        Map.entry(16, 200), Map.entry(18, 150)));
    private boolean pause, gameOn;
    private final Object pauseLock = new Object();
    private final Object gameOnLock = new Object();
    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);


    Tetris(int numRows, int numColumns){
        this.numRows = numRows;
        this.numColumns = numColumns;
        board = new Color[numRows][numColumns];
        prepareGame();
    }


    public void addPropertyChangeListener(PropertyChangeListener listener){
        propertyChangeSupport.addPropertyChangeListener(listener);
    }


    public void run(){
        while(true){
            try{
                while(gameOn){
                    updateSleepEndTime();
                    while(System.currentTimeMillis() < sleepEndTime){
                        long sleepTime = sleepEndTime - System.currentTimeMillis();
                        sleep((sleepTime < 0) ? 0 : sleepTime);
                    }
                    synchronized(pauseLock){
                        if(pause){
                            pauseLock.wait();
                        }
                    }
                    moveDown();
                }
                synchronized(gameOnLock){
                    gameOnLock.wait();
                    gameOn = true;
                }
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }


    public boolean rotate(){
        return blockAction(block.rotate());
    }


    public boolean moveLeft(){
        return blockAction(block.move(0, -1));
    }


    public boolean moveRight(){
        return blockAction(block.move(0, 1));
    }


    public boolean moveDown(){
        boolean moved = blockAction(block.move(1, 0));
        if(!moved && gameOn){ // gameOn condition is necessary because otherwise a new block would always be spawned
            solidifyBlock();
            checkRows();
            spawnNewBlock();
            checkGame();
            propertyChangeSupport.firePropertyChange("Repaint", null, null);
        }
        return moved;
    }


    /**Updates the sleep end time so that the effective sleep duration is always 1000 miliseconds for level 1. 
     * This method is necessary, because when the user has forced a block to drop by one or more steps, we want to reset the sleep duration*/
    public void updateSleepEndTime(){
        sleepEndTime = System.currentTimeMillis() + sleepDuration;
    }


    public void pause(){
        synchronized(pauseLock){
            if(pause){
                pauseLock.notify();
            }
            pause = !pause;
        }
    }


    public void restart(){
        prepareGame();
        synchronized(gameOnLock){
            gameOnLock.notify();
        }
    }


    /**Private wrapper method for the public methods rotate(), moveLeft(), moveRight() and moveDown()
    @param method One of the methods block.rotate() or block.move(), which return an ArrayList of Coordinates*/
    private boolean blockAction(ArrayList<Coordinate> method){
        if(gameOn){  // Makes sure user cannot invoke methods from ViewControl after game has finished
            if(checkAvailability(method)){
                propertyChangeSupport.firePropertyChange("Repaint", null, null);
                return true;
            }
            block.undo();    
        }
        return false;
    }


    private void solidifyBlock(){
        for(Coordinate c: block.coordinates){
            board[c.row][c.column] = block.color;
        }
    }  


    /**Identifies complete rows that should be deleted and calls deleteRow() to delete these rows.
    Also reports to ViewControl so that graphics are updated*/
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
                propertyChangeSupport.firePropertyChange("Row deleted", null, clearedLines);
                if(sleepDurationMap.containsKey(clearedLines)){
                    sleepDuration = sleepDurationMap.get(clearedLines);
                    level += 1;
                    propertyChangeSupport.firePropertyChange("Update level", null, level);
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
        score += block.coordinates.size()*level; // Same as: score += 4*level
        block = nextBlock;
        nextBlock = new TetrisBlock(startCoordinate);
        propertyChangeSupport.firePropertyChange("Update score and block preview", null, score);
    }


    /**Changes the variable gameOn to false if the board coordinates immediately below the tetris block are not empty (Color.BLACK).
    To be called whenever a new block appears. */
    private void checkGame(){
        for(Coordinate c: block.coordinates){
            if(board[c.row+1][c.column] != Color.BLACK){
                System.out.println("Game Over!");
                gameOn=false;
                break;
            }
        }
    }


    private boolean checkAvailability(ArrayList<Coordinate> coordinates){
        for(Coordinate c: coordinates){
            int row = c.row;
            int col = c.column;
            if(row < 0 || row >= numRows){
                return false;
            }
            if(col < 0 || col >= numColumns){
                return false;
            }
            if(board[row][col] != Color.BLACK){
                return false;
            }
        }
        return true;
    }


    private void prepareGame(){
        for(int row = 0; row < numRows; row++){
            for(int column = 0; column < numColumns; column++){
                board[row][column] = Color.BLACK;
            }
        }
        startCoordinate = new Coordinate(0, numColumns/2);
        block = new TetrisBlock(startCoordinate);
        nextBlock = new TetrisBlock(startCoordinate);        
        level = 1;
        clearedLines = 0;
        score = 0;
        sleepEndTime = 0;
        sleepDuration = sleepDurationMap.get(clearedLines);
        pause = false;
        gameOn = true;
    }
}