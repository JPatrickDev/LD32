package me.jack.ld32.Entity;

import me.jack.ld32.Level.Level;
import me.jack.ld32.Level.Path;

import java.awt.*;

/**
 * Created by Jack on 18/04/2015.
 */
public abstract class PathFollowingEntity extends Entity {

    private int tilePosition = 0;
    private Path path;

    public PathFollowingEntity(Path path) {
        super(path.getPath()[0].x * Level.tileSize, path.getPath()[0].y * Level.tileSize);
        this.path = path;
    }


    public void follow(Level level) {
     //   System.out.println("Follow");

        if(tilePosition == path.getPath().length){
            level.entities.remove(this);
            level.lives--;
            return;
        }
        Point target = path.getPath()[tilePosition];
        if(target.x * Level.tileSize > this.getX()) {
            x+=4;
        }
        if(target.x * Level.tileSize < this.getX()){
            x-=4;
        }

        if(target.y * Level.tileSize > this.getY()){
          y+=4;
        }


        if(target.y * Level.tileSize < this.getY()){
            y-=4;
        }


        if(target.x == getX() /Level.tileSize && target.y == getY()/Level.tileSize){
            tilePosition++;
        }
    }


}
