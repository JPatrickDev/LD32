package me.jack.ld32.Entity;

import me.jack.ld32.Entity.Projectile.Projectile;
import me.jack.ld32.Level.Level;
import me.jack.ld32.Level.Path;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import java.awt.*;

/**
 * Created by Jack on 18/04/2015.
 */
public class BasicEnemy extends PathFollowingEntity {

    public BasicEnemy(Path path) {
        super(path);
        health = 1f;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.blue);
        g.fillRect(x, y, Level.tileSize, Level.tileSize);
        g.setColor(Color.white);
    }


    boolean die = false;

    @Override
    public void update(Level level) {
        // System.out.println("Update");
        if (die || health <= 0) {
            level.removeEntity(this);
            if(health <= 0){
                level.money+=25;
                level.updateExp(20);
            }
            return;
        }

        follow(level);
    }

    @Override
    public void hitByProjectile(Projectile projectile) {
        health -= projectile.getDamage();
    }
}
