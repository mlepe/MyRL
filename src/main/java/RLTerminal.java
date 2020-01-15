import com.googlecode.lanterna.terminal.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * Represents a rogue-like oriented terminal. Could be interpreted as being the engine of the game.
 */
public class RLTerminal {
    static boolean isRunning = true;

    /**
     * Puts a glyph on x,y coordinates on given terminal.
     * Basic function for putting characters representing objects on screen with specified position and colors.
     * Called in all draw functions.
     * @param terminal      Terminal object on which to put the glyph
     * @param glyph         Glyph object to put on the terminal
     * @param x             x position of the glyph
     * @param y             y position of the glyph
     * @throws IOException  can throw an IOException
     */
    public static void putGlyph(@NotNull Terminal terminal, @NotNull Glyph glyph, int x, int y) throws IOException {
        terminal.setCursorPosition(x, y);
        terminal.setForegroundColor(glyph.foregroundColor);
        terminal.setBackgroundColor(glyph.backgroundColor);
        terminal.putCharacter(glyph.character); // Puts the character at the defined cursor position, with the defined colors
        terminal.resetColorAndSGR(); // Resets color values to default values to not influence the rendering in further calls
        terminal.setCursorPosition(0, 0);
    }

    /**
     * Updates the terminal - performs necessary updating functions, ie. clearing the screen.
     * @param terminal      terminal to update
     * @throws IOException  can throw an IOException
     */
    public static void update(@NotNull Terminal terminal) throws IOException {
        terminal.clearScreen();
    }

    /**
     * Set isRunning attribute to false. (For handling game exit events, etc.)
     */
    public static void exit()
    {
        isRunning = false;
    }
}
