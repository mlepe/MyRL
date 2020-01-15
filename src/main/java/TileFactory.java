import com.googlecode.lanterna.TextColor;

import java.util.HashMap;

/**
 * Singleton class for handling the instantiation of tiles.
 * Defines different types of predefined tiles that are then easily instantiated.
 */
public class TileFactory {
    HashMap<String, Tile> tileTypes; // Represents predefined tiles

    /**
     * Constructs the tile factory and initializes its default tile types.
     */
    TileFactory()
    {
        tileTypes = new HashMap<String, Tile>();

        // Define the glyph of tiles to be added to tile types
        Glyph basicWallGlyph = new Glyph('#', TextColor.ANSI.WHITE, TextColor.ANSI.BLACK);
        Glyph basicFloorGlyph = new Glyph('.', TextColor.ANSI.WHITE, TextColor.ANSI.BLACK);
        Glyph errorGlyph = new Glyph('!', TextColor.ANSI.RED, TextColor.ANSI.BLACK);

        // Create the tile objects
        Tile basicWallTile = new Tile(basicWallGlyph, 0, 0, true, true);
        Tile basicFloorTile = new Tile(basicFloorGlyph, 0, 0, false, false);
        Tile errorTile = new Tile(errorGlyph, 0, 0, false, false);

        // Add created tiles to the hash map of tile types, identified by a simple String key.
        tileTypes.put("basic_floor", basicFloorTile);
        tileTypes.put("basic_wall", basicWallTile);
        tileTypes.put("error", errorTile); // Tile type to represent erroneous tiles
    }

    /**
     * Instantiate a predefined tile at a given position.
     * @param tileType  type of the tile
     * @param x         x position of the tile
     * @param y         y position of the tile
     * @return          a Tile object
     */
    public Tile newTile(String tileType, int x, int y)
    {
        Tile tile;

        // Check if tile type exists
        if (tileTypes.containsKey(tileType))
        {
            // Instantiate tile with the attribute of the tile type
            Glyph glyph = tileTypes.get(tileType).glyph;
            boolean isBlocking = tileTypes.get(tileType).isBlocking;
            boolean isOpaque = tileTypes.get(tileType).isOpaque;

            tile = new Tile(glyph, x, y, isBlocking, isOpaque);
        }
        else
        {
            // Instantiate an error tile
            Glyph glyph = tileTypes.get("error").glyph;
            boolean isBlocking = tileTypes.get("error").isBlocking;
            boolean isOpaque = tileTypes.get("error").isOpaque;

            tile = new Tile(glyph, x, y, isBlocking, isOpaque);
        }

        return tile; // Returns the instantiated tile object
    }
}
