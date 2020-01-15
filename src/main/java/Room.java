import org.jetbrains.annotations.Contract;

import java.util.HashMap;

/**
 * Represents a room with its coordinates and size attributes.
 */
public class Room {
    int width;
    int height;
    int x1;
    int y1;
    int x2;
    int y2;
    int centerX;
    int centerY;

    /**
     * Constructs a room with the given top-left coordinates, width, and height.
     *
     * @param x      top-left x coordinate of the room
     * @param y      top-left y coordinate of the room
     * @param width  width of the room
     * @param height height of the room
     */
    @Contract(pure = true)
    Room(int x, int y, int width, int height) {
        x1 = x;
        y1 = y;
        x2 = x + width;
        y2 = y + height;
        this.width = width;
        this.height = height;
        updateCenter();
    }

    public void updateCenter() {
        centerX = (x1 + x2) / 2;
        centerY = (y1 + y2) / 2;
    }

    public HashMap<String, Integer> getCenterCoordinates() {
        HashMap<String, Integer> coordinates = new HashMap<String, Integer>();

        centerX = (x1 + x2) / 2;
        centerY = (y1 + y2) / 2;

        coordinates.put("x", centerX);
        coordinates.put("y", centerY);

        return coordinates;
    }

    public boolean intersects(Room other)
    {
        return ((this.x1 <= other.x2) && (this.x2 >= other.x1) && (this.y1 <= other.y2) && (this.y2 >= other.y1));
    }
}
