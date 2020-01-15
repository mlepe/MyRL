import com.googlecode.lanterna.TextColor;

/**
 * Represents the controllable entity and creature of the game, a.k.a the player character.
 * Acts like any other creature, except it doesn't have an AI, and is controlled by user input.
 * Inherits from Creature.
 */
public class Player extends Creature {
    Player() {
        super(new Glyph('@', TextColor.ANSI.YELLOW, TextColor.ANSI.BLACK), 10, 10);
    }

    Player(int x, int y) {
        super(new Glyph('@', TextColor.ANSI.YELLOW, TextColor.ANSI.BLACK), x, y);
    }

    Player(Glyph glyph, int x, int y) {
        super(glyph, x, y);
    }
}
