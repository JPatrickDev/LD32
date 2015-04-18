package me.jack.ld32.Level.Tile;

import me.jack.ld32.Level.Level;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import java.util.HashMap;

/**
 * Created by Jack on 18/04/2015.
 */
public abstract class Tile {

    public static int idCount = 0;
    public static HashMap<Integer, Tile> tileLookup = new HashMap<Integer, Tile>();
    public static SpriteSheet tileSheet = null;
    static {
        new GrassTile();
        new DirtTile();
    }

    private int id;
    private String name;
    private boolean solid;

    public Tile(String name, boolean solid) {
        this.name = name;
        this.solid = solid;
        this.id = idCount;
        tileLookup.put(id, this);
        idCount++;

        if(tileSheet == null){
            try {
                tileSheet = new SpriteSheet("res/tiles.png",32,32);
            } catch (SlickException e) {
                e.printStackTrace();
            }
        }
    }

    public abstract void render(Graphics g, int x, int y);

    public abstract void update(Level level, int x, int y);

    public boolean isSolid() {
        return solid;
    }
}
