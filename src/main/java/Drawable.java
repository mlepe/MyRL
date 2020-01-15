import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

/**
 * Public interface for drawable objects.
 */
public interface Drawable {
    void draw(Terminal terminal) throws IOException;
}
