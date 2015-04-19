package me.jack.ld32.States;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import uk.co.jdpatrick.JEngine.Sound.SoundEngine;

/**
 * Created by Jack on 19/04/2015.
 */
public class LevelSelectState extends BasicGameState {


    Image menuBg;
    private boolean startGame = false;
    private int game = -1;

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        menuBg = new Image("res/mainmenu.png");
        SoundEngine.getInstance().addSound("toaster_shot",new Sound("res/sound/toaster_shot.wav"));
        SoundEngine.getInstance().addSound("life_lost", new Sound("res/sound/life_lost.wav"));
        SoundEngine.getInstance().addSound("no",new Sound("res/sound/no.wav"));
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        super.enter(container, game);
        startGame = false;
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        graphics.drawImage(menuBg, 0, 0);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        if(startGame) {
            InGameState.setLevel = game;
            stateBasedGame.enterState(0);
        }
    }


    @Override
    public void mouseClicked(int button, int x, int y, int clickCount) {
        super.mouseClicked(button, x, y, clickCount);
        if(button != 0)return;

        if(x > 86 && y > 149 && x < 280 && y < 149+120){
            startGame = true;
            game = 0;
        }
        if(x > 298 && y > 149 && x < 498 && y < 149+120){
            startGame = true;
            game = 1;
        }
        if(x > 514 && y > 149 && x < 714 && y < 149+120){
            startGame = true;
            game = 2;
        }

    }

    @Override
    public int getID() {
        return 3;
    }
}
