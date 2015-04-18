package me.jack.ld32.Entity.Towers;

import me.jack.ld32.Entity.Entity;
import me.jack.ld32.Level.Level;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;

/**
 * Created by Jack on 18/04/2015.
 */
public abstract class Tower extends Entity{

    protected Circle attackCircle;
    public Tower(int x, int y,int attackRadius) {
        super(x, y);
        attackCircle = new Circle(x + Level.tileSize/2,y + Level.tileSize/2,attackRadius);
    }

}
