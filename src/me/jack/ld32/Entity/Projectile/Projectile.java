package me.jack.ld32.Entity.Projectile;

import me.jack.ld32.Level.Level;

/**
 * Created by Jack on 18/04/2015.
 */
public abstract class Projectile {


    private float damage;
    private int moveSpeed;
    private String name;
    //public Image i;
    public float life;

    public Projectile(float damage, int moveSpeed, String name, float life) {
        this.damage = damage;
        this.moveSpeed = moveSpeed;
        this.name = name;
        this.life = life;

    }

    public abstract void onSpawn(Level level);

    public abstract void onHitEntity(Level level);

    public abstract void onDestroy(Level level);

    public int getMoveSpeed() {
        return moveSpeed;
    }

    public float getDamage() {
        return damage;
    }


}
