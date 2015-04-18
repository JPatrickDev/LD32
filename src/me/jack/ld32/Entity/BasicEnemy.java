package me.jack.ld32.Entity;

import me.jack.ld32.Entity.Projectile.Projectile;
import me.jack.ld32.Level.Level;
import me.jack.ld32.Level.Path;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

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
        g.fillRect(x ,y,Level.tileSize,Level.tileSize);
        g.setColor(Color.white);
    }

    int i = 0;
boolean die = false;
    @Override
    public void update(Level level) {
       // System.out.println("Update");
        if(die || health <=0){
            level.removeEntity(this);
            return;
        }
        i++;
        if (i >= 5) {
            i = 0;
            follow(level);
        }
    }

    @Override
    public void hitByProjectile(Projectile projectile) {
        health-=projectile.getDamage();
    }
}
