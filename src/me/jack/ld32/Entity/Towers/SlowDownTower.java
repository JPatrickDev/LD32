package me.jack.ld32.Entity.Towers;

import me.jack.ld32.Entity.Entity;
import me.jack.ld32.Entity.EntityProjectile;
import me.jack.ld32.Entity.PathFollowingEntity;
import me.jack.ld32.Entity.Projectile.Projectile;
import me.jack.ld32.Entity.Projectile.SpoonProjectile;
import me.jack.ld32.Level.Level;
import me.jack.ld32.Upgrades.Spoon.SpoonFireRateUpgrade;
import me.jack.ld32.Upgrades.Spoon.SpoonSpeedUpgrade;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Jack on 19/04/2015.
 */
public class SlowDownTower extends Tower {

    public static int fireRate = 50;
    public static int spoonSpeed = 10;
    private int width, height;

    public static float damage = 0.25f;

    public SlowDownTower(int x, int y) {
        super(x, y, 6 * Level.tileSize, 3, 0, 2000f, "Slow Down Tower", "Slows down enemies inside its attack range");
        this.width = 1;
        this.height = 1;
    }


    @Override
    public void render(Graphics g) {
        g.drawImage(icon, x, y);


    }

    static Random r = new Random();

    int wait = 0;

    @Override
    public void update(Level level) {
        ArrayList<Entity> enemiesInRange = level.getEnimiesInRange(attackCircle);
        for (Entity e : enemiesInRange) {
            PathFollowingEntity pfe = (PathFollowingEntity) e;
                pfe.isSlowed = true;
        }

    }

    @Override
    public void hitByProjectile(Projectile projectile) {

    }

}