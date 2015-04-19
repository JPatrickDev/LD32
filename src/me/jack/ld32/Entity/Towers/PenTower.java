package me.jack.ld32.Entity.Towers;

import me.jack.ld32.Entity.Entity;
import me.jack.ld32.Entity.EntityProjectile;
import me.jack.ld32.Entity.Projectile.PenProjectile;
import me.jack.ld32.Entity.Projectile.Projectile;
import me.jack.ld32.Level.Level;
import me.jack.ld32.Upgrades.Pen.PenFireRateUpgrade;
import me.jack.ld32.Upgrades.Pen.PenLifeUpgrade;
import me.jack.ld32.Upgrades.Spoon.SpoonFireRateUpgrade;
import me.jack.ld32.Upgrades.Spoon.SpoonSpeedUpgrade;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Jack on 19/04/2015.
 */
public class PenTower extends Tower{

    public static int fireRate = 70;
    public static int penSpeed = 15;
    public static float projectileLife = 0.25f;
    private int width, height;

    public static float damage = 0.5f;

    public PenTower(int x, int y) {
        super(x, y, 2 * Level.tileSize, 1, 0, 100f, "Pen Tower", "Fires 8 pens in a circle");

        this.width = 1;
        this.height = 1;

        upgrades.add(new PenLifeUpgrade());
        upgrades.add(new PenFireRateUpgrade());
    }



    @Override
    public void render(Graphics g) {
        g.setLineWidth(5f);
        g.setColor(Color.red);

        g.fillRect(x, y, Level.tileSize * width, height * Level.tileSize);

    }

    static Random r = new Random();

    int wait = 0;

    @Override
    public void update(Level level) {
        ArrayList<Entity> enemiesInRange = level.getEnimiesInRange(attackCircle);
        if (enemiesInRange.size() == 0) return;

        wait++;
        if (wait <= fireRate) return;
        wait = 0;


        PenProjectile tPU = new PenProjectile(damage, penSpeed,projectileLife);
        EntityProjectile projectile = new EntityProjectile(getX(), getY(), getX(), getY() - 64, tPU);
        level.addEntity(projectile);


        PenProjectile tPD = new PenProjectile(damage, penSpeed,projectileLife);
        EntityProjectile projectileD = new EntityProjectile(getX(), getY(), getX(), getY() + 64, tPD);
        level.addEntity(projectileD);


        PenProjectile tPL = new PenProjectile(damage, penSpeed,projectileLife);
        EntityProjectile projectileL = new EntityProjectile(getX(), getY(), getX()  - 64, getY(), tPL);
        level.addEntity(projectileL);


        PenProjectile tPR = new PenProjectile(damage, penSpeed,projectileLife);
        EntityProjectile projectileR = new EntityProjectile(getX(), getY(), getX() + 64, getY() , tPR);
        level.addEntity(projectileR);

        addDiag(level);

    }

    private void addDiag(Level level){
        PenProjectile tPU = new PenProjectile(damage, penSpeed,projectileLife);
        EntityProjectile projectile = new EntityProjectile(getX(), getY(), getX() - 64, getY() - 64, tPU);
        level.addEntity(projectile);


        PenProjectile tPD = new PenProjectile(damage, penSpeed,projectileLife);
        EntityProjectile projectileD = new EntityProjectile(getX(), getY(), getX() + 64, getY() + 64, tPD);
        level.addEntity(projectileD);


        PenProjectile tPL = new PenProjectile(damage, penSpeed,projectileLife);
        EntityProjectile projectileL = new EntityProjectile(getX(), getY(), getX()  - 64, getY()  + 64, tPL);
        level.addEntity(projectileL);


        PenProjectile tPR = new PenProjectile(damage, penSpeed,projectileLife);
        EntityProjectile projectileR = new EntityProjectile(getX(), getY(), getX() - 64, getY()  + 64 , tPR);
        level.addEntity(projectileR);
    }

    @Override
    public void hitByProjectile(Projectile projectile) {

    }


}
