package me.jack.ld32.Entity.Projectile;

import me.jack.ld32.Level.Level;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 * Created by Jack on 18/04/2015.
 */
public abstract class Projectile {


    private float damage;
    private int moveSpeed;
    private String name;
    public Image i;
    public float life;

    public static SpriteSheet projectileSprites = null;
    public Projectile(float damage, int moveSpeed, String name, float life,int tX,int tY) {


        if(projectileSprites == null){
            try {
                projectileSprites = new SpriteSheet("res/projectiles.png",16,16);
            } catch (SlickException e) {
                e.printStackTrace();
            }
        }

        this.damage = damage;
        this.moveSpeed = moveSpeed;
        this.name = name;
        this.life = life;
        i = projectileSprites.getSprite(tX,tY);



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

    public Image getI() {
        return i;
    }
}
