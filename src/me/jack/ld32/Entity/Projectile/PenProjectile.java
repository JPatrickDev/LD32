package me.jack.ld32.Entity.Projectile;

import me.jack.ld32.Level.Level;

/**
 * Created by Jack on 19/04/2015.
 */
public class PenProjectile extends Projectile {
    float rot = 0f;

    public PenProjectile(float damage, int speed,float life) {
        super(damage, speed, "Pen", life, 0, 1);
        //    i.setCenterOfRotation(16,16);
    }

    @Override
    public void onSpawn(Level level) {

    }

    @Override
    public void onHitEntity(Level level) {

    }

    @Override
    public void onDestroy(Level level) {

    }
}
