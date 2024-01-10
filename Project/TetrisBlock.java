import java.awt.Color;
import java.util.*;


public class TetrisBlock {
    Color color;
    ArrayList<Coordinate> coordinates;
    private ArrayList<Coordinate> oldCoordinates;
    private int rotationState, oldRotationState;
    private static Random random = new Random();

    /**An array of arrays, where each subarray consists of the relative coordinates of a rotation state.
    The coordinates are relative to first coordinate "0, 0" which stays the same for all rotation states*/
    Coordinate[][] relativeCoordinates;

    /**Lists the available templates with all necessary information fo creating new tetrises*/
    private static ArrayList<TetrisTemplate> tetrisTemplates = new ArrayList<>(Arrays.asList(
        new TetrisTemplate("I", Color.CYAN,                 new String[] {"0, 0, 0,-2, 0,-1, 0, 1", "0, 0, 1, 0,-1, 0,-2, 0"}),
        new TetrisTemplate("J", Color.BLUE,                 new String[] {"0, 0, 0,-1, 0, 1, 1, 1", "0, 0,-1, 0, 1, 0, 1,-1", "0, 0, 0,-1, 0, 1, -1,-1", "0, 0,-1, 0, 1, 0,-1, 1"}),
        new TetrisTemplate("L", Color.decode("#F0A000"), new String[] {"0, 0, 0,-1, 0, 1, 1,-1", "0, 0,-1, 0, 1, 0,-1,-1", "0, 0, 0,-1, 0, 1, -1, 1", "0, 0,-1, 0, 1, 0, 1, 1"}),
        new TetrisTemplate("O", Color.YELLOW,               new String[] {"0, 0, 0,-1, 1,-1, 1, 0"}),
        new TetrisTemplate("S", Color.decode("#00F000"), new String[] {"0, 0, 1,-1, 1, 0, 0, 1", "0, 0,-1,-1, 1, 0, 0,-1"}),
        new TetrisTemplate("T", Color.decode("#A000F0"), new String[] {"0, 0, 0,-1, 0, 1, 1, 0", "0, 0, 0,-1,-1, 0, 1, 0", "0, 0, 0,-1, 0, 1, -1, 0", "0, 0, 0, 1,-1, 0, 1, 0"}),
        new TetrisTemplate("Z", Color.RED,                  new String[] {"0, 0, 0,-1, 1, 0, 1, 1", "0, 0, 0,-1,-1, 0, 1,-1"})
    ));


    TetrisBlock(Coordinate startCoordinate){
        TetrisTemplate tetrisTemplate = tetrisTemplates.get(random.nextInt(tetrisTemplates.size()));
        this.color = tetrisTemplate.color;
        this.rotationState = 0;
        this.relativeCoordinates = tetrisTemplate.relativeCoordinates;
        this.coordinates = new ArrayList<>();
        for(Coordinate c: tetrisTemplate.relativeCoordinates[0]){
            coordinates.add(startCoordinate.add(c));
        }
    }


    public ArrayList<Coordinate> move(int delta_row, int delta_column){
        oldCoordinates = Coordinate.deepCopy(coordinates);
        for(Coordinate c: coordinates){
            c.row += delta_row;
            c.column += delta_column;
        }
        return coordinates;
    }


    public ArrayList<Coordinate> rotate(){
        oldCoordinates = Coordinate.deepCopy(coordinates);
        oldRotationState = rotationState;
        rotationState = (rotationState+1) % relativeCoordinates.length; // rotationState is incremented by 1 or set to 0 the the old rotationState refers to the last element in relativeCoordinates
        coordinates.clear();
        for(int i = 0; i < relativeCoordinates[rotationState].length; i++){
            coordinates.add(oldCoordinates.get(0).add(relativeCoordinates[rotationState][i]));
        }
        return coordinates;
    }


    public void undo(){
        coordinates.clear();
        for(Coordinate c: oldCoordinates){
            coordinates.add(c.deepCopy());
        }
        rotationState = oldRotationState;
    }


    public int width(){
        if(coordinates.size()==0){
            return 0;
        }
        int min = coordinates.get(0).column;
        int max = min;
        for(Coordinate c: coordinates){
            if(c.column > max){
                max = c.column;
            }
            else if(c.column < min){
                min = c.column;
            }
        }
        return max-min+1;
    }
}