package me.jack.ld32.Entity.Projectile;

import me.jack.ld32.Level.Level;

/**
 * Created by Jack on 18/04/2015.
 */
public class ToasterProjectile extends Projectile{

    float rot = 0f;
    public ToasterProjectile(float damage) {
        super(damage, 10, "Toaster", 1f, 0, 0);
        i.setCenterOfRotation(8,8);
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
