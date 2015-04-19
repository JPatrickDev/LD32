package me.jack.ld32;

import me.jack.ld32.States.GameOverState;
import me.jack.ld32.States.InGameState;
import me.jack.ld32.States.LevelSelectState;
import me.jack.ld32.States.UpgradesState;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created by Jack on 16/04/2015.
 */
public class LD32Game extends StateBasedGame{

    public LD32Game(String name) {
        super(name);
    }

    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {
        addState(new LevelSelectState());
        addState(new InGameState());
        addState(new UpgradesState());
        addState(new GameOverState());
    }
}
