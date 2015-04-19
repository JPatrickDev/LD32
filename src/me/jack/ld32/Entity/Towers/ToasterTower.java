package me.jack.ld32.Entity.Towers;

import me.jack.ld32.Entity.Entity;
import me.jack.ld32.Entity.EntityProjectile;
import me.jack.ld32.Entity.Projectile.Projectile;
import me.jack.ld32.Entity.Projectile.ToasterProjectile;
import me.jack.ld32.Level.Level;
import me.jack.ld32.Level.Tile.Tile;
import me.jack.ld32.Upgrades.Toaster.DuelToasterUpgrade;
import me.jack.ld32.Upgrades.Toaster.FireRateUpgrade;
import me.jack.ld32.Upgrades.Toaster.ToasterPowerUpgradeOne;
import me.jack.ld32.Upgrades.Toaster.ToasterPowerUpgradeTwo;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;
import uk.co.jdpatrick.JEngine.Sound.SoundEngine;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Jack on 18/04/2015.
 */
public class ToasterTower extends Tower {

    public static int fireRate = 20;
    public static boolean duel = false;
    private int width, height;

    public static float damage = 0.40f;
    public ToasterTower(int x, int y) {
        super(x, y, 5 * Level.tileSize, 0, 0, 100f,"Toaster Tower","Throws toasters");

        this.width = 1;
        this.height = 1;
        upgrades.add(new FireRateUpgrade());
        upgrades.add(new ToasterPowerUpgradeOne());
        upgrades.add(new ToasterPowerUpgradeTwo());
        upgrades.add(new DuelToasterUpgrade());
        icon.setCenterOfRotation(16,16);

    }

    Color attackCircleColor = new Color(255,0,0,100);
    float rot = 0;
    @Override
    public void render(Graphics g) {
        icon.setRotation(rot);
        g.drawImage(icon, x, y);
    }

  static Random r = new Random();

    int wait = 0;
    @Override
    public void update(Level level) {
        ArrayList<Entity> enemiesInRange = level.getEnimiesInRange(attackCircle);
        if(enemiesInRange.size() == 0)return;

        wait++;
        if(wait <= fireRate)return;
        wait = 0;

        int i = r.nextInt(enemiesInRange.size());
        Entity target = enemiesInRange.get(i);
        ToasterProjectile tP = new ToasterProjectile(damage);
        EntityProjectile projectile = new EntityProjectile(getX(),getY(),target.getX(),target.getY(),tP);
        level.addEntity(projectile);
        rot = (float) -(Math.atan2(getX() -target.getX(), getY() - target.getY()) * 180 / Math.PI);
        if(duel){

             target = enemiesInRange.get(i);
             tP = new ToasterProjectile(damage);
             projectile = new EntityProjectile(getX(),getY(),target.getX()-64,target.getY()-64,tP);
            level.addEntity(projectile);
        }

    }

    @Override
    public void hitByProjectile(Projectile projectile) {

    }
}
