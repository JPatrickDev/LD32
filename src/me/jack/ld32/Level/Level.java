package me.jack.ld32.Level;

import me.jack.ld32.Entity.Enemy.BlueEnemy;
import me.jack.ld32.Entity.Enemy.RedEnemy;
import me.jack.ld32.Entity.Entity;
import me.jack.ld32.Entity.PathFollowingEntity;
import me.jack.ld32.Entity.Towers.Tower;
import me.jack.ld32.Level.Tile.Tile;
import me.jack.ld32.States.InGameState;
import me.jack.ld32.Upgrades.Upgrade;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Jack on 18/04/2015.
 */
public class Level {

    private int width, height;
    private int[][] tiles;
    public Path path;

    public static final int tileSize = 32;


    public CopyOnWriteArrayList<Entity> entities = new CopyOnWriteArrayList<Entity>();

    public int round = 0;
    public int lives = 50;

    public float money = 200.0f;
    public int exp = 0;
    public int level = 1;

    public Level(int width, int height) {
        this.width = width;
        this.height = height;
        tiles = new int[width][height];
        for (int x = 0; x != width; x++) {
            for (int y = 0; y != height; y++) {
                if (x % 2 == 0 && y % 2 == 0) {
                    setTileAt(x, y, 1);
                }
            }
        }
    }

    public static int expForLevel(int level) {
        return 2 ^ level * 5 + 150;
    }

    public void loadFromImg(String img) throws SlickException {
        Image i = new Image(img);
        int w = i.getWidth();
        int h = i.getHeight();

        for (int x = 0; x != w; x++) {
            for (int y = 0; y != h; y++) {
                Color color = i.getColor(x, y);
                if (color.getRed() == 30 && color.getGreen() == 255 && color.getBlue() == 0) {
                    if (new Random().nextBoolean())
                        setTileAt(x, y, 0);
                    else
                        setTileAt(x, y, 2);
                } else {
                    setTileAt(x, y, 1);
                }
            }
        }
        path = Path.generatePath(this);
    }

    public void updateExp(int add) {
        System.out.println("EXP: " + exp + "   Exp for level " + expForLevel(level));
        exp += add;
        if (exp >= expForLevel(level)) {
            exp = 0;
            level++;
        }
    }

    public int calculateEnemies() {
        int i = round * 2;
        System.out.println("Round: " + round + " i " + i);
        return i;
    }

    public void render(Graphics g) {
        for (int x = 0; x != width; x++) {
            for (int y = 0; y != height; y++) {
                int i = tiles[x][y];
                Tile t = Tile.tileLookup.get(i);
                if (t == null) continue;
                t.render(g, x, y);
            }
        }
        g.setColor(Color.white);
        for (Point p : path.getPath()) {
            //   g.fillRect(p.x * tileSize, p.y * tileSize, tileSize, tileSize);
        }

        for (Entity e : entities) {
            e.render(g);
        }
    }

    int toSpawn = 0;
    int spawnWait = 0;

    public void update(InGameState state) {
        for (Entity e : entities) {
            e.update(this);
        }
        boolean roundOver = checkRoundOver();
        if (roundOver && toSpawn == 0) {
            // round++;
            // toSpawn = calculateEnemies();
            state.betweenRounds = true;
        }

        //System.out.println(toSpawn);
        if (toSpawn != 0) {
            spawnWait++;
            if (spawnWait > 20 && Math.random() > 0.25) {
                spawnWait = 0;
                toSpawn--;
                if (round >= 5) {
                    if (new Random().nextInt(5) == 0)
                        entities.add(new RedEnemy(path));
                    else
                        entities.add(new BlueEnemy(path));
                } else {
                    entities.add(new BlueEnemy(path));
                }

            }
        }
    }

    public void startNextRound() {
        round++;
        toSpawn = calculateEnemies();
    }


    private boolean checkRoundOver() {
        boolean i = true;
        for (Entity e : entities) {
            if (e instanceof PathFollowingEntity) {
                i = false;
                break;
            }
        }
        return i;
    }

    public boolean placeTower(Tower tower) {
        if (!solid((int) (tower.getX() / tileSize), (int) (tower.getY() / tileSize))) return false;
        for (Entity e : entities) {
            if (e instanceof Tower) {
                if (e.getX() == tower.getX() && e.getY() == tower.getY()) {
                    return false;
                }
            }
        }
        entities.add(tower);


        return true;
    }

    public boolean solid(int x, int y) {
        Tile tile = Tile.tileLookup.get(getTileAt(x, y));
        if (tile == null) return false;
        return tile.isSolid();
    }


    public int getTileAt(int x, int y) {
        if (x < 0 || y < 0 || y >= height || x >= width) {
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

    public ArrayList<Entity> getEnimiesInRange(Circle attackCircle) {
        ArrayList<Entity> entitiesInRange = new ArrayList<Entity>();
        for (Entity e : entities) {
            if (!(e instanceof PathFollowingEntity)) {
                continue;
            }
            org.newdawn.slick.geom.Rectangle hitBox = new Rectangle(e.getX(), e.getY(), Level.tileSize, Level.tileSize);
            if (attackCircle.intersects(hitBox)) {
                entitiesInRange.add(e);
            }
        }
        return entitiesInRange;
    }

    public void removeEntity(Entity entity) {
        entities.remove(entity);
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public void applyUpgrade(Upgrade selectedUpgrade, Tower tower) {
        for (Entity e : entities) {
            if (e instanceof Tower) {
                Tower t = (Tower) e;
                if (t.name.equals(tower.name))
                    t.upgrade(selectedUpgrade);
            }
        }
    }


}
