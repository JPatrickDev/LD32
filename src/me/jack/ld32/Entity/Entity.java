package me.jack.ld32.Entity;

import me.jack.ld32.Entity.Projectile.Projectile;
import me.jack.ld32.Level.Level;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 * Created by Jack on 18/04/2015.
 */
public abstract class Entity {

    protected float x, y;
    protected float health = 0f;

    public static SpriteSheet sprites;
    public Entity(float x, float y) {
        this.x = x;
        this.y = y;
        if(sprites == null){
            try {
                sprites = new SpriteSheet("res/sprites.png",32,32);
            } catch (SlickException e) {
                e.printStackTrace();
            }
        }
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
