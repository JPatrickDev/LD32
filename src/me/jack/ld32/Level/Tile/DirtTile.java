package me.jack.ld32.Level.Tile;

import me.jack.ld32.Level.Level;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

/**
 * Created by Jack on 18/04/2015.
 */
public class DirtTile extends Tile{

    public DirtTile() {
        super("Dirt", false);
    }

    @Override
    public void render(Graphics g, int x, int y) {
        g.setColor(new Color(122,95,104));
        g.fillRect(x* Level.tileSize,y*Level.tileSize,Level.tileSize,Level.tileSize);
        g.setColor(Color.white);
    }

    @Override
    public void update(Level level, int x, int y) {

    }


}
