package me.jack.ld32.Entity.Projectile;

import me.jack.ld32.Level.Level;

/**
 * Created by Jack on 19/04/2015.
 */
public class SpoonProjectile extends Projectile{
    float rot = 0f;
    public SpoonProjectile(float damage,int speed) {
        super(damage, speed, "Spoon", 1f, 1, 0);
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
