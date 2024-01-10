import java.awt.Color;
import java.util.regex.*;

public class TetrisTemplate {
    String name;
    Color color;
    /**An array of array, where each subarray consists of the relative coordinates of a rotation state.
    That the coordinates are relative means that the first coordinate is (0, 0) and that the indicies of the other coordinates are relative to this.*/
    Coordinate[][] relativeCoordinates;


    TetrisTemplate(String name, Color color, Coordinate[][] relativeCoordinates){
        this.name = name;
        this.color = color;
        this.relativeCoordinates = relativeCoordinates;
    }

    
    TetrisTemplate(String name, Color color, String[] relativeCoordinates){
        this.name = name;
        this.color = color;
        int numRotations = relativeCoordinates.length;
        this.relativeCoordinates = new Coordinate[numRotations][];
        Pattern pattern = Pattern.compile("[-0-9]+");
        for(int i = 0; i < numRotations; i++){
            Matcher matcher = pattern.matcher(relativeCoordinates[i]);
            int count = 0; // Equals 2 times the number of coordinates, as each coordinate consists of two numbers. Should be an even number
            while(matcher.find()){
                count++;
            }
            Coordinate[] nestedList = new Coordinate[count/2];
            matcher.reset();
            for(int j = 0; j < count/2; j++){
                matcher.find();
                int match1 = Integer.parseInt(matcher.group());
                matcher.find();
                int match2 = Integer.parseInt(matcher.group());
                nestedList[j] = new Coordinate(match1, match2);
            }
            this.relativeCoordinates[i] = nestedList;
        }
    }
}