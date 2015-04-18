package me.jack.ld32.Entity;

import me.jack.ld32.Level.Level;
import org.newdawn.slick.Graphics;

/**
 * Created by Jack on 18/04/2015.
 */
public abstract class Entity {

    private int x, y;

    public Entity(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public abstract void render(Graphics g);
    public abstract void update(Level level);
}
