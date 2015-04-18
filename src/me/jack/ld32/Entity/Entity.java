package me.jack.ld32.Entity;

import me.jack.ld32.Entity.Projectile.Projectile;
import me.jack.ld32.Level.Level;
import org.newdawn.slick.Graphics;

/**
 * Created by Jack on 18/04/2015.
 */
public abstract class Entity {

    protected float x, y;
    protected float health = 0f;

    public Entity(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public abstract void render(Graphics g);
    public abstract void update(Level level);

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public abstract void hitByProjectile(Projectile projectile);
}
