import com.googlecode.lanterna.TextColor;
import org.jetbrains.annotations.Contract;

/**
 * Represents the graphical element of an object (ie. Entity or Tile).
 * Composed of a character, a background color, and a foreground color.
 */
public class Glyph {
    TextColor foregroundColor;
    TextColor backgroundColor;
    char character;

    /**
     * Constructs a glyph with the following attributes.
     * @param character         character element the glyph
     * @param foregroundColor   color of the character
     * @param backgroundColor   background color of the character
     */
    @Contract(pure = true)
    Glyph(char character, TextColor foregroundColor, TextColor backgroundColor)
    {
        this.character = character;
        this.foregroundColor = foregroundColor;
        this.backgroundColor = backgroundColor;
    }
}
