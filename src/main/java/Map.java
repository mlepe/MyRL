import com.googlecode.lanterna.terminal.Terminal;
import sun.awt.OSInfo;
import sun.misc.OSEnvironment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Represents the environment of the game, with a variable size.
 * Composed of individual tiles arranged at x,y coordinates.
 * Implements Drawable interface as the map is drawable.
 */
public class Map implements Drawable {
    int height; // maximum vertical number of tiles
    int width; // maximum horizontal number ot tiles
    Tile[][] map; // tiles composing the map
    TileFactory tileFactory;
    ArrayList<Room> rooms;
    ArrayList<Corridor> corridors;

    int roomMinSize = 6;
    int roomMaxSize = 10;
    int maxRooms = 30;

    Boolean[][] fovMap;
    int fovRadius = 30;

    int playerX;
    int playerY;

    /**
     * Initializes the map with default values.
     */
    Map() {
        tileFactory = new TileFactory(); // Initialize tile factory for tile creation

        height = 40;
        width = 40;
        map = new Tile[width][height];
        rooms = new ArrayList<Room>();
        corridors = new ArrayList<Corridor>();

        roomMinSize = 6;
        roomMaxSize = 10;
        maxRooms = 30;


        // Initialize each tile with default tiles
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                //map[y][x] = new Tile(x, y);
                map[x][y] = tileFactory.newTile("basic_floor", x, y);
            }
        }
    }

    /**
     * Initialize the map with the given width and height.
     *
     * @param width  width of the map
     * @param height height of the map
     */
    Map(int width, int height) {
        tileFactory = new TileFactory(); // Initialize tile factory for tile creation

        this.height = height;
        this.width = width;
        map = new Tile[width][height];
        rooms = new ArrayList<Room>();
        corridors = new ArrayList<Corridor>();

        roomMinSize = 6;
        roomMaxSize = 10;
        maxRooms = 30;

        // Initialize each tile with default tiles
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                //map[y][x] = new Tile(x, y);
                map[x][y] = tileFactory.newTile("basic_wall", x, y);
            }
        }
    }

    /**
     * Initialize the map with the given width and height.
     *
     * @param width  width of the map
     * @param height height of the map
     */
    Map(int width, int height, int maxRooms, int roomMinSize, int roomMaxSize) {
        tileFactory = new TileFactory(); // Initialize tile factory for tile creation

        this.height = height;
        this.width = width;
        map = new Tile[width][height];
        rooms = new ArrayList<Room>();
        corridors = new ArrayList<Corridor>();

        roomMinSize = 6;
        roomMaxSize = 10;
        maxRooms = 30;

        // Initialize each tile with default tiles
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                //map[y][x] = new Tile(x, y);
                map[x][y] = tileFactory.newTile("basic_wall", x, y);
            }
        }
    }

    /**
     * Draws each individual tile of the map by calling their draw function.
     *
     * @param terminal Terminal object on which to draw the map
     * @throws IOException can throw an IOException
     */
    public void draw(Terminal terminal) throws IOException {
        // Loops through each tile and calls its draw function
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                map[x][y].draw(terminal); // Draw tile
            }
        }
        terminal.flush(); // flushes the terminal once every tile has been drawn
    }

    public void createRoom(Room room) {
        System.out.println("in createRoom()");
        for (int x = room.x1 + 1; x < room.x2; x++) {
            for (int y = room.y1 + 1; y < room.y2; y++) {
                map[x][y] = tileFactory.newTile("basic_floor", x, y);
            }
        }
        //rooms.add(room);
    }

    public void makeMap() {
        System.out.println("In makeMap()");

        int roomsCount = 0;
        Random random = new Random(System.currentTimeMillis());

        for (int r = 0; r < maxRooms; r++) {
            int width = roomMinSize + random.nextInt(roomMaxSize - roomMinSize);
            System.out.println(width);
            int height = roomMinSize + random.nextInt(roomMaxSize - roomMinSize);
            System.out.println(height);

            int x = random.nextInt(this.width - width - 1);
            System.out.println(x);
            int y = random.nextInt(this.height - height - 1);
            System.out.println(y);

            Room room = new Room(x, y, width, height);

            for (int others = 0; others < rooms.size(); others++) {
                if (room.intersects(rooms.get(others))) {
                    System.out.println("out");
                    return;
                }
            }
            createRoom(room);

            HashMap<String, Integer> newCoords = room.getCenterCoordinates();

            if (roomsCount == 0) {
                playerX = newCoords.get("x");
                playerY = newCoords.get("y");
            } else {
                HashMap<String, Integer> prevCoords = rooms.get(roomsCount - 1).getCenterCoordinates();

                if (random.nextBoolean()) {
                    createHTunnel(prevCoords.get("x"), newCoords.get("x"), prevCoords.get("y"));
                    createVTunnel(prevCoords.get("y"), newCoords.get("y"), prevCoords.get("x"));
                } else {
                    createVTunnel(prevCoords.get("y"), newCoords.get("y"), prevCoords.get("x"));
                    createHTunnel(prevCoords.get("x"), newCoords.get("x"), prevCoords.get("y"));
                }
            }
            rooms.add(room);
            roomsCount += 1;
        }
    }

    public void createHTunnel(int x1, int x2, int y) {
        for (int x = Math.min(x1, x2); x < Math.max(x1, x2) + 1; x++) {
            map[x][y] = tileFactory.newTile("basic_floor", x, y);
        }
    }

    public void createVTunnel(int y1, int y2, int x) {
        for (int y = Math.min(y1, y2); y < Math.max(y1, y2) + 1; y++) {
            map[x][y] = tileFactory.newTile("basic_floor", x, y);
        }
    }

    public void makeFovMap(Player player)
    {
    }
}
