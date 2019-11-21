package main.java.gameoflife.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Position {
    private int row;
    private int col;
     //return the position of the obj
    public Position toPosition(int row, int col) {
        return this
                .setRow(row)
                .setCol(col);
    }

    public boolean areEqual(Position p1 , Position p2) { //checks if two objects are in the same row
        if (p1.row != p2.row ) {
            return false;
        }
        if (p1.col != p2.col ) {
                return false;
        }
        return true;
    }

    public boolean isValid(Position p) { // checks validity of the obj within the bounds of the grid
        return (p.row >= 0 && p.row < 20 && p.col >= 0 && p.col < 60);
    }

    public Double calculateDistance(Position p2) // calculate the distance b/w two objects
    {
        int row_difference = this.row - p2.row;
        int col_difference = this.col - p2.col;
        return(Math.sqrt((row_difference * row_difference) + (col_difference * col_difference)));
    }
}
