package me.jack.ld32.Entity.Towers;

import me.jack.ld32.Level.Level;
import me.jack.ld32.Level.Tile.Tile;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

/**
 * Created by Jack on 18/04/2015.
 */
public class ToasterTower extends Tower{

    public ToasterTower(int x, int y) {
        super(x, y);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.orange);

        g.fillRect(x*Level.tileSize,y*Level.tileSize,Level.tileSize,Level.tileSize);

        g.setColor(Color.white);
    }

    @Override
    public void update(Level level) {

    }
}
