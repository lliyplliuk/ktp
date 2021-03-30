/**
 * This class represents a specific location in a 2D map.  Coordinates are
 * integer values.
 **/
public class Location {
    /**
     * X coordinate of this location.
     **/
    public int xCoord;

    /**
     * Y coordinate of this location.
     **/
    public int yCoord;


    /**
     * Creates a new location with the specified integer coordinates.
     **/
    public Location(int x, int y) {
        xCoord = x;
        yCoord = y;
    }

    /**
     * Creates a new location with coordinates (0, 0).
     **/
    public Location() {
        this(0, 0);
    }

    /**
     * Equals this Location to another
     **/
    public boolean equals(Object var1) {
        if (var1 instanceof Location) {
            Location other = (Location) var1;
            return (xCoord == other.xCoord && yCoord == other.yCoord);
        }
        return false;
    }

    /**
     * Provides a hashCode for each Location.
     **/
    public int hashCode() {
        int result = 11;
        return result * xCoord + result * yCoord;
    }
}
