package me.jack.ld32.States;

import me.jack.ld32.Entity.BasicEnemy;
import me.jack.ld32.Level.Level;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
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
