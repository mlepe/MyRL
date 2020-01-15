import com.googlecode.lanterna.TextColor;

import org.jetbrains.annotations.NotNull;

/**
 * Represents an entity that is alive, ie. has stats like health, and is mobile.
 * Implements Mobile interface, as a creature can move.
 * Inherits from Entity.
 */
public class Creature extends Entity implements Mobile {
    // STATS
    // Primary
    int strength;
    int dexterity;
    int constitution;
    int perception;
    int agility;
    int willpower;
    int intelligence;
    int charisma;
    int beauty;
    int luck;

    // Secondary
    int speed;
    int maxCarryWeight;
    int protection; // = PV
    int evasion; // = DV
    // Needed stats
    int health;
    int maxHealth;
    int mana;
    int maxMana;
    int stamina;
    int maxStamina;
    // Other stats
    int fatigue;
    int maxFatigue;
    int sanity;
    int maxSanity;

    // Other
    int carryWeight;


    /**
     * Constructs an entity with default values.
     */
    Creature()
    {
        super();
        this.glyph = new Glyph('c', TextColor.ANSI.WHITE, TextColor.ANSI.BLACK);
    }

    /**
     * Constructs an entity.
     * @param glyph the physical representation of the entity
     * @param x     the x position of the entity
     * @param y     the y position of the entity
     */
    Creature(Glyph glyph, int x, int y)
    {
        super(glyph, x, y);
    }

    /**
     * Move the creature by an increment of x and y if no collision. x and y may be negative.
     * @param map   map (necessary for tile informations)
     * @param x     x increment
     * @param y     y increment
     */
    public void move(@NotNull Map map, int x, int y)
    {
        if (map.map[this.x + x][this.y + y].isBlocking)
        {
            x = 0;
            y = 0;
        }

        this.x += x;
        this.y += y;
    }
}
