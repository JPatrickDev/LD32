package me.jack.ld32.Entity;

import me.jack.ld32.Level.Level;
import me.jack.ld32.Level.Path;

import java.awt.*;

/**
 * Created by Jack on 18/04/2015.
 */
public abstract class PathFollowingEntity extends Entity {

    private int tilePosition = -1;
    private Path path;

    public PathFollowingEntity(Path path) {
        super(path.getPath()[0].x, path.getPath()[0].y);
        this.path = path;
    }


    public void follow(Level level) {
     //   System.out.println("Follow");
        tilePosition++;
        if(tilePosition == path.getPath().length){
            level.entities.remove(this);
            return;
        }
        Point target = path.getPath()[tilePosition];
        this.x = target.x;
        this.y = target.y;
    }


}
