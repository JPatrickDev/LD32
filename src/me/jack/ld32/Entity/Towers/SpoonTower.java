package me.jack.ld32.Entity.Towers;

import me.jack.ld32.Entity.Entity;
import me.jack.ld32.Entity.EntityProjectile;
import me.jack.ld32.Entity.Projectile.Projectile;
import me.jack.ld32.Entity.Projectile.SpoonProjectile;
import me.jack.ld32.Entity.Projectile.ToasterProjectile;
import me.jack.ld32.Level.Level;
import me.jack.ld32.Upgrades.Spoon.SpoonFireRateUpgrade;
import me.jack.ld32.Upgrades.Spoon.SpoonSpeedUpgrade;
import me.jack.ld32.Upgrades.Toaster.DuelToasterUpgrade;
import me.jack.ld32.Upgrades.Toaster.FireRateUpgrade;
import me.jack.ld32.Upgrades.Toaster.ToasterPowerUpgradeOne;
import me.jack.ld32.Upgrades.Toaster.ToasterPowerUpgradeTwo;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Jack on 19/04/2015.
 */
public class SpoonTower extends Tower {

    public static int fireRate = 50;
    public static int spoonSpeed = 10;
    private int width, height;

    public static float damage = 0.5f;

    public SpoonTower(int x, int y) {
        super(x, y, 5 * Level.tileSize, 1, 0, 100f, "Spoon Tower", "Throws Spoons");

        this.width = 1;
        this.height = 1;

        upgrades.add(new SpoonSpeedUpgrade());
        upgrades.add(new SpoonFireRateUpgrade());

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

        int i = r.nextInt(enemiesInRange.size());
        Entity target = enemiesInRange.get(i);
        SpoonProjectile tP = new SpoonProjectile(damage,spoonSpeed);
        EntityProjectile projectile = new EntityProjectile(getX(), getY(), target.getX(), target.getY(), tP);
        level.addEntity(projectile);

    }

    @Override
    public void hitByProjectile(Projectile projectile) {

    }
}