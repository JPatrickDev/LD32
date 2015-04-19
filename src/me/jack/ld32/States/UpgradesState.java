package me.jack.ld32.States;

import me.jack.ld32.Entity.Towers.ToasterTower;
import me.jack.ld32.Entity.Towers.Tower;
import me.jack.ld32.Level.Level;
import me.jack.ld32.Upgrades.Toaster.FireRateUpgrade;
import me.jack.ld32.Upgrades.Upgrade;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.awt.Font;
import java.util.ArrayList;


/**
 * Created by Jack on 18/04/2015.
 */
public class UpgradesState extends BasicGameState {

    TrueTypeFont ttf;
    TrueTypeFont smallPrint;

    public static Level parent;
    private boolean backToGame = false;

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        gameContainer.setShowFPS(false);
        i = new Image("res/upgrades_gui_tower_list_row_bg.png");


        Font font = new Font("Verdana", Font.BOLD, 32);
        TrueTypeFont ttf = new TrueTypeFont(font, true);
        this.ttf = ttf;
        font = new Font("Verdana", Font.PLAIN, 16);
        this.smallPrint = new TrueTypeFont(font, true);

      //  towers.add(new ToasterTower(0, 0));
    }

    //ArrayList<Tower> towers = new ArrayList<Tower>();

    Image i = null;

    Tower selected = null;

    Upgrade selectedUpgrade = null;
    //Image resizedIcon = null;

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {

        graphics.fillRect(0, 0, 200, 600);
        graphics.setColor(Color.green);
        graphics.fillRect(200, 0, 800 - 200, 150);
        graphics.setColor(Color.red);
        graphics.fillRect(400, 150, 600, 600 - 150);
        graphics.setColor(Color.orange);
        graphics.fillRect(200, 150, 200, 600 - 150);
        graphics.setColor(Color.white);

        int i = 0;
        for (Tower tower : Tower.towers) {
            drawTower(tower, i, graphics, selected == tower);

            i++;
        }

        i = 0;
        if (selected != null) {
            for (Upgrade upgrade : selected.upgrades) {
                drawUpgrade(upgrade, i, graphics, false);
                i++;
            }
        }

        drawTowerInfo(selected, graphics);
        drawUpgradeInfo(selectedUpgrade, graphics);
    }

    int heightPerTower = 64;

    private void drawTower(Tower tower, int pos, Graphics g, boolean selected) {
        int x = 0;
        int y = heightPerTower * pos;
        g.drawImage(i, x, y);
        g.drawImage(tower.icon,x + 8,y + 16);
        smallPrint.drawString(x + 50, y + 5, tower.name);
    }

    private void drawTowerInfo(Tower tower, Graphics g) {
        ttf.drawString(555, 0, "Money: " + parent.money);

        ttf.drawString(555, 30, "Exp Level: " + parent.level);
        if (tower == null) return;
        int startX = 200;
        int startY = 0;
        ttf.drawString(startX, startY, tower.name, Color.white);
        ttf.drawString(startX, startY + 50, tower.description, Color.white);
        ttf.drawString(startX, startY + 100, "Cost: " + selected.cost);

    }


    private void drawUpgrade(Upgrade upgrade, int pos, Graphics g, boolean selected) {
        if (upgrade == null) return;
        int x = 200;
        int y = heightPerTower * pos + 150;
        g.drawImage(i, x, y);
        smallPrint.drawString(x + 45, y + 10, upgrade.getTitle());
        smallPrint.drawString(x + 45, y + 25, "Exp Level " + upgrade.getActivationLevel());
    }

    private void drawUpgradeInfo(Upgrade upgrade, Graphics g) {
        if (upgrade == null) return;
        int startX = 400;
        int startY = 150;
        ttf.drawString(startX, startY, upgrade.getTitle());
        smallPrint.drawString(startX, startY += 50, upgrade.getDescription());
        smallPrint.drawString(startX, startY += 50, "Unlocked at Exp Level " + upgrade.getActivationLevel());
        smallPrint.drawString(startX, startY += 50, "Increases tower cost by " + upgrade.getCostIncrease());


        //        graphics.fillRect(400, 150, 600, 600 - 150);
        g.setColor(Color.cyan);
        g.fillRect(startX + 100, startY + 150, 200, 50);
        g.setColor(Color.white);
        ttf.drawString(startX + 100, startY + 150, "Purchase");

    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        super.enter(container, game);
        selected = null;
        selectedUpgrade = null;
    }

    @Override
    public void mousePressed(int button, int x, int y) {
        super.mousePressed(button, x, y);
        if (x <= 200) {
            int pos = y / heightPerTower;
            System.out.println("Clicked on " + pos);
            selected = Tower.towers.get(pos);// TODO Error handling
        }
        //    graphics.fillRect(200, 150, 200, 600 - 150);
        if (x > 200 && y >= 150 && x < 400 && y < 600) {
            int pos = (y - 150) / heightPerTower;
            System.out.println("Clicked on " + pos);
            if (selected != null) {
                selectedUpgrade = selected.upgrades.get(pos);//TODO Error handling
            }
        }

        //     g.fillRect(startX + 100,startY + 150,200,50);
        if (x > 500 && y > 450 && x < 700 && y < 500 && parent.level >= selectedUpgrade.getActivationLevel() && parent.money >= selectedUpgrade.getCost()) {
            System.out.println("Purchase clicked");
            parent.applyUpgrade(selectedUpgrade, selected);
            selected.upgrades.remove(selectedUpgrade);
            Tower.setApplied(selectedUpgrade, selected);
            InGameState.increaseCost(selected, selectedUpgrade.getCostIncrease());
            backToGame = true;
        }
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        if (backToGame) {
            stateBasedGame.enterState(0);
            backToGame = false;
        }
    }

    @Override
    public void keyPressed(int key, char c) {
        super.keyPressed(key, c);
        if (key == Keyboard.KEY_ESCAPE) {
            backToGame = true;//TODO button
        }
    }

    @Override
    public int getID() {
        return 1;
    }
}
