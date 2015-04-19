package me.jack.ld32.Entity.Towers;

import me.jack.ld32.Entity.Entity;
import me.jack.ld32.Level.Level;
import me.jack.ld32.Upgrades.Upgrade;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Jack on 18/04/2015.
 */
public abstract class Tower extends Entity {

    public Circle attackCircle;
    public static SpriteSheet icons;

    public Image icon;
    public static ArrayList<Tower> towers = new ArrayList<Tower>();

    public ArrayList<Upgrade> upgrades = new ArrayList<Upgrade>();
    public static HashMap<String, ArrayList<Upgrade>> appliedUpgrades = new HashMap<String, ArrayList<Upgrade>>();

    static {
        towers.add(new ToasterTower(-1, -1));
        towers.add(new SpoonTower(-1,-1));
        towers.add(new PenTower(-1,-1));
        towers.add(new SlowDownTower(-1,-1));
    }

    public float cost;

    public String name, description;

    public Tower(int x, int y, int attackRadius, int iconX, int iconY, float cost, String name, String description) {
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
        if (keyFound(name)) {
            for (Upgrade appliedUpgrade : appliedUpgrades.get(name)) {
                System.out.println("Applying upgrade " + appliedUpgrade.getTitle());
                appliedUpgrade.apply(this);
            }
        }
    }

    public static Tower create(int tX, int tY, Tower holding) {
        if (holding instanceof ToasterTower) {
            return new ToasterTower(tX * Level.tileSize, tY * Level.tileSize);
        }
        else if(holding instanceof SpoonTower){
            return new SpoonTower(tX*Level.tileSize,tY*Level.tileSize);
        }
        else if(holding instanceof PenTower){
            return new PenTower(tX*Level.tileSize,tY*Level.tileSize);
        }
        else if(holding instanceof SlowDownTower){
            return new SlowDownTower(tX*Level.tileSize,tY*Level.tileSize);
        }
        return null;
    }

    public void upgrade(Upgrade selectedUpgrade) {
        System.out.println("Upgrading");
        upgrades.remove(selectedUpgrade);
        selectedUpgrade.apply(this);
    }

    public static float getCurrentCost(Tower tower){
        for(Tower t : towers){
            if(t.name.equals(tower.name)){
                return t.cost;
            }
        }
        return 0.0f;
    }
    public static void setApplied(Upgrade selectedUpgrade,Tower type){

        if (keyFound(type.name)) {
            System.out.println("Key found");
            appliedUpgrades.get(type.name).add(selectedUpgrade);
        } else {
            System.out.println("No key found");
            appliedUpgrades.put(type.name, new ArrayList<Upgrade>());
            appliedUpgrades.get(type.name).add(selectedUpgrade);
        }
    }

    public static boolean keyFound(String key){
        for(String kk : appliedUpgrades.keySet()){
            if(kk.equals(key))
                return true;
        }
        return false;
    }

}
