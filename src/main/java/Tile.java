import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.terminal.Terminal;
import org.jetbrains.annotations.Contract;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents a tile element of the map. Represented by a glyph, positioned at x,y coordinates, and is immobile.
 * Implements the Drawable interface, as a tile has a graphical representation.
 * NOTE: May be reworked as a subclass of Entity if ever convenient.
 */
public class Tile implements Drawable{
    Glyph glyph;
    int x;
    int y;
    boolean isBlocking; // determines if the tile blocks movement (= is solid)
    boolean isOpaque; // determines if the tile blocks view (= is full height, and not see-through)

    ArrayList<Entity> onTile; // To keep track of entities that are on a tile of the map

    // TODO: Implement tile health (invincible for floor, variable for walls)
    int health;
    int maxHealth;
    boolean isDestructible;

    /**
     * Initialize a tile with default values at the given position.
     * @param x x position of the tile
     * @param y y position of the tile
     */
    @Contract(pure = true)
    Tile(int x, int y)
    {
        glyph = new Glyph('.', TextColor.ANSI.WHITE, TextColor.ANSI.BLACK);
        isBlocking = false;
        isOpaque = false;
        this.x = x;
        this.y = y;
    }

    /**
     * Initialize a tile with given values at the given position.
     * @param glyph glyph of the tile
     * @param x     x position of the tile
     * @param y     y position of the tile
     */
    @Contract(pure = true)
    Tile(Glyph glyph, int x, int y)
    {
        this.glyph = glyph;
        isBlocking = false;
        isOpaque = false;
        this.x = x;
        this.y = y;
    }

    /**
     * Initialize a tile with given values at the given position.
     * @param glyph         glyph of the tile
     * @param x             x position of the tile
     * @param y             y position of the tile
     * @param isBlocking    to determine if tile is blocking
     * @param isOpaque      to determine if tile is opaque
     */
    @Contract(pure = true)
    Tile(Glyph glyph, int x, int y, boolean isBlocking, boolean isOpaque)
    {
        this.glyph = glyph;
        this.isBlocking = isBlocking;
        this.isOpaque = isOpaque;
        this.x = x;
        this.y = y;
    }

    /**
     * Draws the tile on the given terminal.
     * @param terminal      Terminal object on which to draw the tile
     * @throws IOException  Can throw a IOException
     */
    public void draw(Terminal terminal) throws IOException {
        RLTerminal.putGlyph(terminal, glyph, x, y);
        //terminal.flush();
    }
}
