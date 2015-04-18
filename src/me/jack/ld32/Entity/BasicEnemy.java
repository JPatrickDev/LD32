package me.jack.ld32.Entity;

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
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.blue);
        g.fillRect(x * Level.tileSize,y*Level.tileSize,Level.tileSize,Level.tileSize);
        g.setColor(Color.white);
    }

    int i = 0;

    @Override
    public void update(Level level) {
       // System.out.println("Update");
        i++;
        if (i >= 2) {
            i = 0;
            follow(level);
        }
    }
}
