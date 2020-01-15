import org.jetbrains.annotations.Contract;

/**
 * Represents a corridor with its start and end x,y coordinates.
 * Differs from a room in purpose.
 */
public class Corridor {
    int x1;
    int y1;
    int x2;
    int y2;

    /**
     * Constructs a corridor with a start and end point.
     * @param x1    start point x
     * @param y1    start point y
     * @param x2    end point x
     * @param y2    end point y
     */
    @Contract(pure = true)
    Corridor(int x1, int y1, int x2, int y2)
    {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }
}
