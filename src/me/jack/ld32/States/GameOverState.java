package me.jack.ld32.States;

import me.jack.ld32.Level.Level;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created by Jack on 19/04/2015.
 */
public class GameOverState extends BasicGameState {

    public static Level level;

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {

    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        graphics.drawString("GAME OVER", 50, 0);
        graphics.drawString("You survived for : " + level.round + " rounds", 50, 50);
        graphics.drawString("Press any key to play again", 50, 100);
    }

    boolean restart = false;


    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        if (restart) {
            stateBasedGame.enterState(0);
            restart = false;
        }

    }

    @Override
    public void keyPressed(int key, char c) {
        super.keyPressed(key, c);
        restart = true;
    }

    @Override
    public int getID() {
        return 2;
    }


}
