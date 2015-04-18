package me.jack.ld32.Entity.Towers;

import me.jack.ld32.Entity.Entity;
import me.jack.ld32.Level.Level;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;

import java.util.ArrayList;

/**
 * Created by Jack on 18/04/2015.
 */
public abstract class Tower extends Entity {

    public Circle attackCircle;
    public static SpriteSheet icons;

    public Image icon;
    public static ArrayList<Tower> towers = new ArrayList<Tower>();

    static {
        towers.add(new ToasterTower(-1, -1));
    }

    public float cost;

    public String name,description;

    public Tower(int x, int y, int attackRadius, int iconX, int iconY, float cost,String name, String description) {
        super(x, y);
        if (icons == null) {
            try {
                icons = new SpriteSheet("res/towericon.png", 32, 32);
            } catch (SlickException e) {
                e.printStackTrace();
            }
        }
        icon = icons.getSprite(iconX, iconY);
        attackCircle = new Circle(x + Level.tileSize / 2, y + Level.tileSize / 2, attackRadius);
        this.cost = cost;

        this.name = name;
        this.description = description;
    }

    public static Tower create(int tX, int tY, Tower holding) {
        if (holding instanceof ToasterTower) {
            return new ToasterTower(tX * Level.tileSize, tY * Level.tileSize);
        }
        return null;
    }
}
