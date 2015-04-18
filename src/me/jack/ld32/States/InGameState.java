package me.jack.ld32.States;

import me.jack.ld32.Entity.BasicEnemy;
import me.jack.ld32.Entity.Towers.ToasterTower;
import me.jack.ld32.Level.Level;
import org.newdawn.slick.*;
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

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) throws SlickException {
        level.render(g);

        g.setColor(Color.red);
        g.fillRect(0, 480, 800, 600 - 480);
        g.setColor(Color.white);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        level.update();


    }

    @Override
    public void mouseReleased(int button, int x, int y) {
        super.mouseReleased(button, x, y);
        if (button == 0) {
            System.out.println("Placing");
            int tX = x / Level.tileSize;
            int tY = y / Level.tileSize;
            ToasterTower tower = new ToasterTower(tX * Level.tileSize, tY * Level.tileSize);
            level.placeTower(tower);
        }
    }

    public void start() throws SlickException {
        level = new Level(25, 15);
        level.loadFromImg("/res/level.png");
        level.entities.add(new BasicEnemy(level.path));
    }

    @Override
    public int getID() {
        return 0;
    }


}
