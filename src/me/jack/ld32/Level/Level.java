package me.jack.ld32.Level;

import me.jack.ld32.Entity.BasicEnemy;
import me.jack.ld32.Entity.Entity;
import me.jack.ld32.Entity.PathFollowingEntity;
import me.jack.ld32.Level.Tile.Tile;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.awt.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Jack on 18/04/2015.
 */
public class Level {

    private int width, height;
    private int[][] tiles;
    public Path path;

    public static final int tileSize = 32;//8 might be too small


    public CopyOnWriteArrayList<Entity> entities  = new CopyOnWriteArrayList<Entity>();

    public int round = 0;

    public Level(int width, int height) {
        this.width = width;
        this.height = height;
        tiles = new int[width][height];
        for (int x = 0; x != width; x++) {
            for (int y = 0; y != height; y++) {
                if (x % 2 == 0 && y%2 == 0) {
                    setTileAt(x,y,1);
                }
            }
        }
    }

    public void loadFromImg(String img) throws SlickException {
        Image i = new Image(img);
        int w = i.getWidth();
        int h = i.getHeight();

        for(int x = 0;x!= w;x++){
            for(int y = 0;y!= h;y++){
                Color color = i.getColor(x, y);
                if(color.getRed() == 30 && color.getGreen() == 255 && color.getBlue() == 0){
                    setTileAt(x,y,0);
                }else{
                    setTileAt(x,y,1);
                }
            }
        }
        path = Path.generatePath(this);
    }

    public int calculateEnemies(){
        int i = round * 2;
        System.out.println("Round: " + round + " i " + i);
        return i;
    }
    public void render(Graphics g) {
        for (int x = 0; x != width; x++) {
            for (int y = 0; y != height; y++) {
                int i = tiles[x][y];
                Tile t = Tile.tileLookup.get(i);
                if(t == null)continue;
                t.render(g,x,y);
            }
        }
        g.setColor(Color.white);
        for(Point p :path.getPath()){
            g.fillRect(p.x*tileSize,p.y*tileSize,tileSize,tileSize);
        }

        for(Entity e : entities){
            e.render(g);
        }
    }

    int toSpawn = 0;
    int spawnWait = 0;

    public void update(){
        for(Entity e : entities){
            e.update(this);
        }
        boolean roundOver = checkRoundOver();
        if(roundOver && toSpawn== 0){
            round++;
            toSpawn = calculateEnemies();
        }

        //System.out.println(toSpawn);
            if(toSpawn != 0){
          spawnWait++;
            if(spawnWait > 30 && Math.random() > 0.25){
                spawnWait = 0;
                toSpawn--;
                entities.add(new BasicEnemy(path));
            }
        }
    }

    private boolean checkRoundOver() {
        boolean i = true;
        for(Entity e : entities){
            if(e instanceof PathFollowingEntity){
                i = false;
                break;
            }
        }
        return i;
    }

    public boolean solid(int x,int y){
        return Tile.tileLookup.get(getTileAt(x,y)).isSolid();
    }


    public int getTileAt(int x, int y) {
        if(x < 0 || y < 0 || y >= height || x >=width){
            return -1;
        }
        return tiles[x][y];
    }

    public void setTileAt(int x, int y, int i) {
        tiles[x][y] = i;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int[][] getTiles() {
        return tiles;
    }
}
