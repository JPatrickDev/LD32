package me.jack.ld32.States;

import me.jack.ld32.Entity.Enemy.BlueEnemy;
import me.jack.ld32.Entity.Towers.Tower;
import me.jack.ld32.Level.Level;
import org.lwjgl.input.Keyboard;
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
    private boolean viewUpgrades = false;

    public boolean betweenRounds = false;
    Image nextRound = null;
    Image upgradeButton = null;
    Image guiBg = null;

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        gameContainer.setAlwaysRender(true);
        gameContainer.setUpdateOnlyWhenVisible(false);
        nextRound = new Image("res/nextRound.png");
        upgradeButton = new Image("res/upgradeButton.png");
        guiBg = new Image("res/in_game_gui_background.png");
        start();
    }

    Color goodPlacement = new Color(50, 255, 0, 100);
    Color badPlacement = new Color(255, 100, 100, 100);
    int i = 0;

    public static void increaseCost(Tower tower,float increase){
        for(Tower t : Tower.towers){
            if(t.name.equals(tower.name)){
                t.cost+=increase;
            }
        }
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) throws SlickException {
        level.render(g);

        g.setColor(Color.red);
        g.drawImage(guiBg,0,480);
        g.setColor(Color.white);
        g.drawString("Round: " + level.round, 5, 490);
        g.drawString("Lives remaining: " + level.lives, 5, 505);
        g.drawString("Money: " + level.money, 5, 520);

        g.drawString("Towers:", 300, 500);
        int x = 250;
        int y = 532;
        Input i = gameContainer.getInput();
        for (Tower tower : Tower.towers) {
            g.drawImage(tower.icon, x, y);
            g.drawString("$" + tower.cost, x-8, y + 32);
            if (level.money < tower.cost) {
                g.setColor(badPlacement);
                g.fillRect(x, y, 32, 32);
                g.setColor(Color.white);

            }

            x += 70;
        }
        x = 250;


        // g.setLineWidth(100f);

        g.drawString("Exp: " + level.exp + "/" + level.expForLevel(level.level),460,500);
        g.drawString("Exp Level: " + level.level, 460, 520);

        if(betweenRounds) {
            g.drawImage(nextRound, 600, 550);
            g.drawImage(upgradeButton,600,502);
        }


        //g.setLineWidth(1f);
        Input input = gameContainer.getInput();
        int mX = input.getMouseX();
        int mY = input.getMouseY();

        if (holdingTower) {

            int tileX = mX / Level.tileSize;
            int tileY = mY / Level.tileSize;
            int screenX = tileX * Level.tileSize;
            int screenY = tileY * Level.tileSize;
            Circle drawCircle = new Circle(screenX + 16, screenY + 16, holding.attackCircle.getRadius());
            boolean canPlace = level.solid(tileX, tileY);
            if (canPlace) {
                g.setColor(goodPlacement);
                g.fill(drawCircle);
                g.setColor(Color.green);
                g.draw(drawCircle);
                g.setColor(Color.white);
            } else {
                g.setColor(badPlacement);
                g.fill(drawCircle);
                g.setColor(Color.red);
                g.draw(drawCircle);
                g.setColor(Color.white);
            }


            g.drawImage(holding.icon, screenX, screenY);
        }



        for (Tower tower : Tower.towers) {
            if(i.getMouseX() > x && i.getMouseY() > y && i.getMouseX() < x + 32 && i.getMouseY() < y + 32){
                int xx = i.getMouseX();
                int yy = i.getMouseY()- 32;
                System.out.println("Hover");
                g.setColor(Color.black);
                g.fillRect(xx,yy,240, 64);
                g.setColor(Color.white);
                g.drawString(tower.name,xx,yy+5);
                g.drawString(tower.description,xx,yy+25);
            }
            x += 70;
        }


    }

    boolean holdingTower = false;
    Tower holding = null;

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        level.update(this);
        if(viewUpgrades){
            UpgradesState.parent= level;
            stateBasedGame.enterState(1);
            viewUpgrades = false;
        }


    }


    @Override
    public void keyPressed(int key, char c) {
        super.keyPressed(key, c);
        if(key == Keyboard.KEY_U){
            viewUpgrades = true;
        }
    }



    @Override
    public void mousePressed(int button, int x, int y) {
        super.mousePressed(button, x, y);
        if (button == 1) {
            if (holdingTower == true) {
                holding = null;
                holdingTower = false;
            }
        }
        if (y >= 480) {
            //UI
            int xx = 250;
            int yy = 532;
            for (Tower tower : Tower.towers) {
                System.out.println(tower.name);
                Rectangle hit = new Rectangle(xx, yy, 32, 32);
                if (hit.contains(x, y) && tower.cost <= level.money) {
                    System.out.println("Holding: " + tower.name);
                    holding = tower;
                    holdingTower = true;
                }
                xx += 64;
                //  g.drawImage(nextRound,600,550);

                if(x > 600 && y > 550 && x <= 600+194 && y <= 550+45){
                    if(betweenRounds){
                        level.startNextRound();
                        betweenRounds= false;
                    }
                    System.out.println("Next round clicked");
                }
                if(x > 600 && y > 505 && x <= 600+194 && y <= 505+45){

                    if(!viewUpgrades)
                        viewUpgrades = true;
                    System.out.println("Upgrades button clicked");
                }
            }
        } else {
            //level
            if (button == 0 && holdingTower && holding != null && holding.cost <= level.money) {
                System.out.println("Placing");
                if (y >= 480) return;
                int tX = x / Level.tileSize;
                int tY = y / Level.tileSize;
                Tower tower = Tower.create(tX, tY, holding);// new ToasterTower(tX * Level.tileSize, tY * Level.tileSize);
                boolean success = level.placeTower(tower);
                if (success) {
                    holdingTower = false;
                    holding = null;
                    level.money -= tower.cost;
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
        level.entities.add(new BlueEnemy(level.path));
    }

    @Override
    public int getID() {
        return 0;
    }


}
