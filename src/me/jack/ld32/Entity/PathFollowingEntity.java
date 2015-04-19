package me.jack.ld32.Entity;

import me.jack.ld32.Level.Level;
import me.jack.ld32.Level.Path;
import uk.co.jdpatrick.JEngine.Sound.SoundEngine;

import java.awt.*;

/**
 * Created by Jack on 18/04/2015.
 */
public abstract class PathFollowingEntity extends Entity {

    private int tilePosition = 0;
    private Path path;

    public int moveSpeed = 4;
    public boolean isSlowed = false;

    public PathFollowingEntity(Path path) {
        super(path.getPath()[0].x * Level.tileSize, path.getPath()[0].y * Level.tileSize);
        this.path = path;
    }


    public void follow(Level level) {
        //   System.out.println("Follow");

        int oldSpeed = moveSpeed;
        if(isSlowed){
            moveSpeed = oldSpeed/2;
        }
        if (tilePosition == path.getPath().length) {
            level.entities.remove(this);
            level.lives--;
            SoundEngine.getInstance().play("life_lost");
            return;
        }
        Point target = path.getPath()[tilePosition];
        if (target.x * Level.tileSize > this.getX()) {
            x += moveSpeed;
        }
        if (target.x * Level.tileSize < this.getX()) {
            x -= moveSpeed;
        }

        if (target.y * Level.tileSize > this.getY()) {
            y += moveSpeed;
        }


        if (target.y * Level.tileSize < this.getY()) {
            y -= moveSpeed;
        }


        if (target.x == getX() / Level.tileSize && target.y == getY() / Level.tileSize) {
            tilePosition++;
        }
        moveSpeed = oldSpeed;
        isSlowed = false;
    }


}
