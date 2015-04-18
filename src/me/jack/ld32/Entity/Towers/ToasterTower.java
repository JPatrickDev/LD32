package me.jack.ld32.Entity.Towers;

import me.jack.ld32.Entity.Entity;
import me.jack.ld32.Entity.EntityProjectile;
import me.jack.ld32.Entity.Projectile.Projectile;
import me.jack.ld32.Entity.Projectile.ToasterProjectile;
import me.jack.ld32.Level.Level;
import me.jack.ld32.Level.Tile.Tile;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Jack on 18/04/2015.
 */
public class ToasterTower extends Tower {

    private int width, height;

    public ToasterTower(int x, int y) {
        super(x, y, 3 * Level.tileSize, 0, 0, 100f,"Toaster Tower","Throws toasters");
        this.width = 1;
        this.height = 1;
    }

    Color attackCircleColor = new Color(255,0,0,5);
    @Override
    public void render(Graphics g) {
        g.setLineWidth(5f);
        g.setColor(Color.orange);

        g.fillRect(x, y, Level.tileSize * width, height * Level.tileSize);

        g.setColor(attackCircleColor);
        g.fill(attackCircle);
    }

    Random r = new Random();


    int wait = 0;
    @Override
    public void update(Level level) {
        ArrayList<Entity> enemiesInRange = level.getEnimiesInRange(attackCircle);
        if(enemiesInRange.size() == 0)return;
        Entity target = enemiesInRange.get(r.nextInt(enemiesInRange.size()));
        wait++;
        if(wait <= 20)return;
        wait = 0;

        ToasterProjectile tP = new ToasterProjectile();
        EntityProjectile projectile = new EntityProjectile(getX(),getY(),target.getX(),target.getY(),tP);
        level.addEntity(projectile);
    }

    @Override
    public void hitByProjectile(Projectile projectile) {

    }
}
