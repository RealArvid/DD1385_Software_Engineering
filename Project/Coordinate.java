import java.util.ArrayList;

public class Coordinate implements Cloneable{
    int row;
    int column;

    Coordinate(int row, int column){
        this.row = row;
        this.column = column;
    }

    public Coordinate add(Coordinate anotherCoordinate){
        return new Coordinate(this.row + anotherCoordinate.row, this.column + anotherCoordinate.column);
    }

    public Coordinate subtract(Coordinate anotherCoordinate){
        return new Coordinate(this.row - anotherCoordinate.row, this.column - anotherCoordinate.column);
    }

    public Coordinate deepCopy(){
        return new Coordinate(this.row, this.column);
    }

    public static ArrayList<Coordinate> deepCopy(ArrayList<Coordinate> coordinateList){
        ArrayList<Coordinate> newList = new ArrayList<Coordinate>();
        for(Coordinate c: coordinateList){
            newList.add(c.deepCopy());
        }
        return newList;
    }

    @Override // Unused method
    public boolean equals(Object otherObject) {
        if(otherObject instanceof Coordinate){
            Coordinate otherCoordinate = (Coordinate) otherObject;
            if(this.row == otherCoordinate.row && this.column == otherCoordinate.column){
                return true;
            }
        }
        return false;
    }

    @Override // Unused method
    public String toString() {
        return "Row: " + row + ", Column: " + column;
    }
}