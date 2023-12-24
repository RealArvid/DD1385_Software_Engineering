import java.awt.Color;
import java.util.*;

public class TetrisBlock {
    ArrayList<Coordinate> coordinates;
    Color color;
    private Color[][] board;
    private int rotationState;
    private Random random = new Random();
    /**A an array of array, where each subarray consists of the relative coordinates of a rotation state.
    That the coordinates are relative means that the first coordinate is (0, 0) and that the indicies of the other coordinates are relative to this.*/
    private Coordinate[][] relativeCoordinates;
    private final int numRows;
    private final int numCols;
    private static ArrayList<TetrisTemplate> tetrisTemplates = new ArrayList<>(Arrays.asList(
        new TetrisTemplate("I", Color.CYAN,                 new String[] {"0, 0, 0,-2, 0,-1, 0, 1", "0, 0, 1, 0,-1, 0,-2, 0"}),
        new TetrisTemplate("J", Color.BLUE,                 new String[] {"0, 0, 0,-1, 0, 1, 1, 1", "0, 0,-1, 0, 1, 0, 1,-1", "0, 0, 0,-1, 0, 1, -1, -1", "0, 0,-1, 0, 1, 0,-1, 1"}),
        new TetrisTemplate("L", Color.decode("#F0A000"), new String[] {"0, 0, 0,-1, 0, 1, 1,-1", "0, 0,-1, 0, 1, 0,-1,-1", "0, 0, 0,-1, 0, 1,-1, 1", "0, 0,-1, 0, 1, 0, 1, 1"}),
        new TetrisTemplate("O", Color.YELLOW,               new String[] {"0, 0, 0,-1, 1,-1, 1, 0"}),
        new TetrisTemplate("S", Color.decode("#00F000"), new String[] {"0, 0, 1,-1, 1, 0, 0, 1", "0, 0,-1,-1, 1, 0, 0,-1"}),
        new TetrisTemplate("T", Color.decode("#A000F0"), new String[] {"0, 0, 0,-1, 0, 1, 1, 0", "0, 0, 0,-1,-1, 0, 1, 0", "0, 0, 0,-1, 0, 1, -1, 0", "0, 0, 0, 1,-1, 0, 1, 0"}),
        new TetrisTemplate("Z", Color.RED,                  new String[] {"0, 0, 0,-1, 1, 0, 1, 1", "0, 0, 0,-1,-1, 0, 1,-1"})
    ));

    TetrisBlock(Coordinate startCoordinate, Color[][] board){
        this.board = board;
        this.numRows = board.length;
        this.numCols = board[0].length;
        TetrisTemplate tetrisTemplate = tetrisTemplates.get(random.nextInt(tetrisTemplates.size()));
        this.color = tetrisTemplate.color;
        this.rotationState = 0;
        this.relativeCoordinates = tetrisTemplate.relativeCoordinates;
        this.coordinates = new ArrayList<>();
        for(Coordinate c: tetrisTemplate.relativeCoordinates[0]){
            coordinates.add(startCoordinate.add(c));
        }
    }

    public void reset(Coordinate startCoordinate, Color[][] board){
        TetrisTemplate tetrisTemplate = tetrisTemplates.get(random.nextInt(tetrisTemplates.size()));
        color = tetrisTemplate.color;
        rotationState = 0;
        relativeCoordinates = tetrisTemplate.relativeCoordinates;
        coordinates.clear();
        for(Coordinate c: tetrisTemplate.relativeCoordinates[0]){
            coordinates.add(startCoordinate.add(c));
        }
    }

    public boolean move(int delta_row, int delta_column){
        for(Coordinate c : coordinates){
            int newRow = c.row + delta_row;
            int newCol = c.column + delta_column;
            if(!checkAvailability(newRow, newCol)){
                return false;
            }
        }
        for(Coordinate c: coordinates){
            c.row += delta_row;
            c.column += delta_column;
            }
        return true;
    }

    public boolean rotate(){
        int newRotationState = (rotationState+1) % relativeCoordinates.length; // rotationState is incremented by 1 or set to 0 the the old rotationState refers to the last element in relativeCoordinates
        ArrayList<Coordinate> oldCoordinates = Coordinate.deepCopy(coordinates);
        for(int i = 0; i < relativeCoordinates[newRotationState].length; i++){
            coordinates.set(i, oldCoordinates.get(0).add(relativeCoordinates[newRotationState][i]));
        }
        for(Coordinate c: coordinates){
            if(!checkAvailability(c.row, c.column)){
                coordinates.clear();
                for(Coordinate oldCoordinate: oldCoordinates){
                    coordinates.add(oldCoordinate);
                }
                return false;
            }
        }
        rotationState = newRotationState;
        return true;
    }

    private boolean checkAvailability(int row, int col){
        if(row < 0 || row >= numRows){
            return false;
        }
        if(col < 0 || col >= numCols){
            return false;
        }
        if(board[row][col] != Color.BLACK){
            return false;
        }
        return true;
    }
}