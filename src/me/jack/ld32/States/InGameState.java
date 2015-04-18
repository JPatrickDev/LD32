package me.jack.ld32.States;

import me.jack.ld32.Entity.BasicEnemy;
import me.jack.ld32.Entity.Towers.ToasterTower;
import me.jack.ld32.Entity.Towers.Tower;
import me.jack.ld32.Level.Level;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created by Jack on 18/04/2015.
 */
public class InGameState extends BasicGameState {

    Level level;

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        gameContainer.setAlwaysRender(true);
        gameContainer.setUpdateOnlyWhenVisible(false);
        start();
    }

    Color goodPlacement = new Color(50, 255, 0, 100);
    Color badPlacement = new Color(255, 100, 100, 100);
    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) throws SlickException {
        level.render(g);

        g.setColor(Color.red);
        g.fillRect(0, 480, 800, 600 - 480);
        g.setColor(Color.white);
        g.drawString("Round: " + level.round, 5, 490);
        g.drawString("Lives remaining: " + level.lives, 5, 500);

        g.drawString("Towers:", 300, 500);
        int x = 300;
        int y = 532;
        for (Tower tower : Tower.towers) {
            g.drawImage(tower.icon, x, y);
            x += 34;
        }

        Input input = gameContainer.getInput();
        int mX = input.getMouseX();
        int mY = input.getMouseY();

        if (holdingTower) {

            int tileX = mX/Level.tileSize;
            int tileY = mY/Level.tileSize;
            int screenX = tileX * Level.tileSize;
            int screenY = tileY * Level.tileSize;
            Circle drawCircle = new Circle(screenX  + 16, screenY + 16, holding.attackCircle.getRadius());
            boolean canPlace = level.solid(tileX,tileY);
            if(canPlace){
                g.setColor(goodPlacement);
                g.fill(drawCircle);
                g.setColor(Color.green);
                g.draw(drawCircle);
                g.setColor(Color.white);
            }else{
                g.setColor(badPlacement);
                g.fill(drawCircle);
                g.setColor(Color.red);
                g.draw(drawCircle);
                g.setColor(Color.white);
            }


            g.drawImage(holding.icon, screenX,screenY);
        }

    }

    boolean holdingTower = false;
    Tower holding = null;

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        level.update();


    }

    @Override
    public void mousePressed(int button, int x, int y) {
        super.mousePressed(button, x, y);
        if(button == 1){
            if(holdingTower == true){
                holding = null;
                holdingTower = false;
            }
        }
        if (y >= 480) {
            //UI
            int xx = 300;
            int yy = 532;
            for (Tower tower : Tower.towers) {
                Rectangle hit = new Rectangle(xx, yy, 32, 32);
                if (hit.contains(x, y)) {
                    holding = tower;
                    holdingTower = true;
                }
                x += 34;
            }
        } else {
            //level
            if (button == 0 && holdingTower && holding != null) {
                System.out.println("Placing");
                if (y >= 480) return;
                int tX = x / Level.tileSize;
                int tY = y / Level.tileSize;
                Tower tower = Tower.create(tX, tY, holding);// new ToasterTower(tX * Level.tileSize, tY * Level.tileSize);
                boolean success = level.placeTower(tower);
                if (success) {
                    holdingTower = false;
                    holding = null;
                }
            }
        }
    }

    @Override
    public void mouseReleased(int button, int x, int y) {
        super.mouseReleased(button, x, y);

    }

    public void start() throws SlickException {
        level = new Level(25, 15);
        level.loadFromImg("res/level.png");
        level.entities.add(new BasicEnemy(level.path));
    }

    @Override
    public int getID() {
        return 0;
    }


}
