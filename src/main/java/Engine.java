import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;

public class Engine {
    ArrayList<Entity> entities;
    TileFactory tileFactory;
    Player player;
    Terminal terminal;
    Map map;
    boolean running;
    InputHandler inputHandler;

    public void init() throws IOException {
        // Init
        running = true; // Determines if the game is running, and therefore if it should keep running
        DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory();

        //Terminal terminal = null; // Define the main Terminal object
        TerminalSize terminalSize = new TerminalSize(80, 40); // Define Terminal size

        // Create the terminal from its factory with the appropriate size
        terminal = defaultTerminalFactory.setInitialTerminalSize(terminalSize).createTerminal();

        terminal.setCursorVisible(false); // Disable cursor visibility

        // Create map
        //Map map = new Map(terminalSize.getColumns(), terminalSize.getRows());
        map = new Map(80, 40, 30, 6, 10);
        map.makeMap();
        terminal.flush();

        map.draw(terminal); // Initial map draw
        //ap.makeMap();

        player = new Player(map.playerX, map.playerY); // Initialize player
        player.draw(terminal); // Initial player draw

        terminal.flush();

        inputHandler = new InputHandler(map, player, terminal); // Initialize input handler
    }

    public void run() throws IOException {
        // Main loop
        while (running) {
            // Input phase
            inputHandler.read_key();
            if (!RLTerminal.isRunning) // Checks for exit cue
            {
                terminal.close();
            }

            // Update phase
            RLTerminal.update(terminal);

            // Drawing phase
            map.draw(terminal);
            player.draw(terminal);

            // Flushing before reiterating
            terminal.flush();
        }
    }

    public void putGlyph(@NotNull Glyph glyph, int x, int y) throws IOException {
        terminal.setCursorPosition(x, y);
        terminal.setForegroundColor(glyph.foregroundColor);
        terminal.setBackgroundColor(glyph.backgroundColor);
        terminal.putCharacter(glyph.character); // Puts the character at the defined cursor position, with the defined colors
        terminal.resetColorAndSGR(); // Resets color values to default values to not influence the rendering in further calls
        terminal.setCursorPosition(0, 0);
    }

    public void update() throws IOException {
        terminal.clearScreen();
    }

    /**
     * Set isRunning attribute to false. (For handling game exit events, etc.)
     */
    public void exit()
    {
        running = false;
    }
}
