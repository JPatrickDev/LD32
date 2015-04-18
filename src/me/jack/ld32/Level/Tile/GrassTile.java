package me.jack.ld32.Level.Tile;

import me.jack.ld32.Level.Level;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

/**
 * Created by Jack on 18/04/2015.
 */
public class GrassTile extends Tile {

    public static Image image;

    public GrassTile() {
        super("Grass", true);
        if (image == null) {
            image = tileSheet.getSubImage(0, 0);
        }
    }

    @Override
    public void render(Graphics g, int x, int y) {
            g.drawImage(image,x * Level.tileSize,y*Level.tileSize);
    }

    @Override
    public void update(Level level, int x, int y) {

    }
}
