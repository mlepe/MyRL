import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.terminal.Terminal;
import org.jetbrains.annotations.Contract;

import java.io.IOException;

/**
 * Singleton class for handling input.
 */
public class InputHandler {
    KeyStroke keyStroke;
    Player player;
    Terminal terminal;
    Map map;

    /**
     * Initializes the input handler with the player object and the terminal from which to read input.
     * @param player    Player object to be controlled by the user's input
     * @param terminal  Terminal object from which input is to be received
     */
    @Contract(pure = true)
    InputHandler(Map map, Player player, Terminal terminal)
    {
        this.map = map;
        this.player = player;
        this.terminal = terminal;
    }

    /**
     * Read user input. Is blocking.
     * @throws IOException  can throw a IOException
     */
    public void read_key() throws IOException {
        keyStroke = terminal.readInput();

        // TODO: Rework with keyStroke.getHashCode() to handle numpad keys and other characters
        switch(keyStroke.getKeyType())
        {
            case ArrowUp:
                player.move(map,0, -1);
                break;

            case ArrowDown:
                player.move(map,0, 1);
                break;

            case ArrowLeft:
                player.move(map,-1, 0);
                break;

            case ArrowRight:
                player.move(map,1, 0);
                break;

            case Escape:
                RLTerminal.exit();
                //terminal.close();
                break;

        }

        // Resetting
        keyStroke = null;
    }
}
