package me.jack.ld32.Entity;

import me.jack.ld32.Entity.Projectile.PenProjectile;
import me.jack.ld32.Entity.Projectile.Projectile;
import me.jack.ld32.Entity.Towers.PenTower;
import me.jack.ld32.Level.Level;
import org.newdawn.slick.Graphics;

import java.awt.*;

/**
 * Created by Jack on 18/04/2015.
 */
public class EntityProjectile extends Entity {

    protected float vX;
    protected float vY;

    protected float life = 1f;

    protected Projectile projectile;

    public EntityProjectile(float x, float y, float targetX, float targetY, Projectile projectile) {
        super(x, y);
        float xSpeed = (targetX - x);
        float ySpeed = (targetY - y);

        float factor = (float) (projectile.getMoveSpeed() / Math.sqrt(xSpeed * xSpeed + ySpeed * ySpeed));
        xSpeed *= factor;
        ySpeed *= factor;
        vX = xSpeed;
        vY = ySpeed;

        this.projectile = projectile;
        this.life = projectile.life;


    }


    boolean firstTick = true;


    @Override
    public void update(Level level) {

        if (firstTick) {
            firstTick = false;
            projectile.onSpawn(level);
        }

        x += vX;
        y += vY;

        if (x < 0 || x > 800 || y < 0 || y > 600) {
            level.removeEntity(this);
        }
        life -= 0.025;
        if (life <= 0) {
            level.removeEntity(this);
            projectile.onDestroy(level);
        }

        /*
        Rectangle player = new Rectangle((int)level.getPlayer().getX(),(int)level.getPlayer().getY(),32,32);
        if(me.intersects(player)){
            onPlayerIntersect(level);
        }*/

        Rectangle me = new Rectangle((int) x, (int) y, 16, 16);
        for (Entity e : level.entities) {
            Rectangle eRekt = new Rectangle((int) e.x, (int) e.y, 32, 32);
            if (me.intersects(eRekt) && e instanceof PathFollowingEntity) {
                e.hitByProjectile(projectile);
                level.removeEntity(this);
                return;
            }
        }
    }

    @Override
    public void hitByProjectile(Projectile projectile) {

    }

    public void notifyHitEntity(Entity hit, Level level) {
        projectile.onHitEntity(level);
        level.removeEntity(this);
    }

    float angle = 0f;

    @Override
    public void render(Graphics g) {

        if (!(projectile instanceof PenProjectile)) {
            projectile.getI().rotate(angle);
            angle++;
            if (angle > 360) {
                angle = 0;
            }
        }


        g.drawImage(projectile.getI(), x, y);


    }


    public Projectile getProjectile() {
        return projectile;
    }
}
