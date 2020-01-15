import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.terminal.Terminal;
import org.jetbrains.annotations.Contract;

import java.io.IOException;

/**
 * Represents an object that is physically represented by a glyph, and has a x,y position.
 * Implements the Drawable interface, as an entity has a graphical representation.
 */
public class Entity implements Drawable {
    Glyph glyph; // glyph representing the object graphically
    int x; // x coordinate of the entity
    int y; // y coordinate of the entity

    // Stats


    /**
     * Constructs an entity with default values.
     */
    @Contract(pure = true)
    Entity()
    {
        this.glyph = new Glyph('e', TextColor.ANSI.WHITE, TextColor.ANSI.BLACK);
        this.x = 0;
        this.y = 0;
    }

    /**
     * Constructs an entity.
     * @param glyph the physical representation of the entity
     * @param x     the x position of the entity
     * @param y     the y position of the entity
     */
    @Contract(pure = true)
    Entity(Glyph glyph, int x, int y)
    {
        this.glyph = glyph;
        this.x = x;
        this.y = y;
    }

    /**
     * Draws the entity on the terminal provided as argument.
     * @param terminal      Terminal object on which to draw
     * @throws IOException  can throw an IOException
     */
    public void draw(Terminal terminal) throws IOException {
        RLTerminal.putGlyph(terminal, glyph, x, y);
        terminal.flush();
    }

    /**
     * Set the position of the entity to new x,y coordinates.
     * @param x x integer to replace current x position
     * @param y y integer to replace current y position
     */
    public void setPosition(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
}
