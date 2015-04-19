package me.jack.ld32.Entity.Enemy;

import me.jack.ld32.Entity.PathFollowingEntity;
import me.jack.ld32.Entity.Projectile.Projectile;
import me.jack.ld32.Level.Level;
import me.jack.ld32.Level.Path;
import org.newdawn.slick.Graphics;

/**
 * Created by Jack on 19/04/2015.
 */
public class PurpleEnemy extends PathFollowingEntity

{

    public static org.newdawn.slick.Image img;
    int rot = 0;

    public PurpleEnemy(Path path) {
        super(path);
        health = 40f;
        moveSpeed = 8;
        if (img == null) {
            img = sprites.getSprite(1, 1);
        }
    }

    @Override
    public void render(Graphics g) {
        img.setRotation(rot);
        g.drawImage(img, x, y);
        img.setRotation(0);
    }


    boolean die = false;

    @Override
    public void update(Level level) {
        // System.out.println("Update");
        if (die || health <= 0) {
            level.removeEntity(this);
            if (health <= 0) {
                level.money += 150;
                level.updateExp(100);
            }
            return;
        }

        float oX = x;
        float oY = y;
        follow(level);
        if (oX < x) {
            rot = 0;
        }
        if (oX > x) {
            rot = 180;
        }

        if (oY < y) {
            rot = 90;
        }
        if (oY > y) {
            rot = 270;
        }
    }

    @Override
    public void hitByProjectile(Projectile projectile) {
        health -= projectile.getDamage();
    }
}