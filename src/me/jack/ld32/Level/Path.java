package me.jack.ld32.Level;

import me.jack.ld32.Level.Tile.DirtTile;
import me.jack.ld32.Level.Tile.Tile;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Jack on 18/04/2015.
 */
public class Path {

    private Point[] path;

    public Path(Point[] path) {
        this.path = path;
    }

    public Point[] getPath() {
        return path;
    }

    public static Path generatePath(Level level) {
        ArrayList<Point> points = new ArrayList<Point>();

        int startX = 0;
        int startY = -1;
        for (int y = 0; y != level.getHeight(); y++) {
            int ii = level.getTileAt(startX, y);
            Tile t = Tile.tileLookup.get(ii);

            boolean solid = t.isSolid();
            if (!solid) {
                startY = y;
                break;
            }
        }
        System.out.println("Start: " + startY);

        int x = startX;
        int y = startY;

        while (x != level.getWidth()-1) {
            Tile up = Tile.tileLookup.get(level.getTileAt(x, y - 1));
            Tile down = Tile.tileLookup.get(level.getTileAt(x, y + 1));
            Tile left = Tile.tileLookup.get(level.getTileAt(x - 1, y));
            Tile right = Tile.tileLookup.get(level.getTileAt(x + 1, y));

            int dx = 0;
            int dy = 0;

            boolean found = false;
            if (up != null &&!found) {
                Point newPos = new Point(x,y-1);
                if (up instanceof DirtTile && !(visited(newPos,points))) {
                    found= true;
                    dy = -1;
                }
            }
            if (down != null&&!found) {
                Point newPos = new Point(x,y+1);
                if (down instanceof DirtTile && !(visited(newPos,points))) {
                    found= true;
                    dy = 1;
                }
            }


            if (left != null &&!found) {
                Point newPos = new Point(x-1,y);
                if (left instanceof DirtTile && !(visited(newPos,points))) {
                    found= true;
                    dx = -1;
                }
            }
            if (right != null &&!found) {
                Point newPos = new Point(x+1,y);
                if (right instanceof DirtTile && !(visited(newPos,points))){
                    found= true;
                    dx = 1;
                }
            }

            points.add(new Point(x, y));
            x += dx;
            y += dy;
            System.out.println(x);
        }
        points.add(new Point(x, y));
       // finalPoint(x,y,points);
        Point[] pointsArray = new Point[points.size()];
        return new Path(points.toArray(pointsArray));
    }

    private static void finalPoint(int x, int y, ArrayList<Point> points) {

    }

    public static boolean visited(Point p, ArrayList<Point> points){
        for(Point pp : points){
            if(pp.x == p.x && pp.y == p.y){
                return true;
            }
        }
        return false;
    }
}
