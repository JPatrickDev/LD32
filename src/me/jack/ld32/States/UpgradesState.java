package me.jack.ld32.States;

import me.jack.ld32.Entity.Towers.ToasterTower;
import me.jack.ld32.Entity.Towers.Tower;
import me.jack.ld32.Upgrades.Toaster.FireRateUpgrade;
import me.jack.ld32.Upgrades.Upgrade;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.awt.Font;


/**
 * Created by Jack on 18/04/2015.
 */
public class UpgradesState extends BasicGameState {

    TrueTypeFont ttf;
    TrueTypeFont smallPrint;
    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {

        i = new Image("res/upgrades_gui_tower_list_row_bg.png");
        selected = new ToasterTower(0, 0);
        selectedUpgrade = new FireRateUpgrade();
        resizedIcon = selected.icon.getScaledCopy(2f);
        Font font = new Font("Verdana", Font.BOLD, 32);
        TrueTypeFont ttf = new TrueTypeFont(font, true);
        this.ttf = ttf;
        font = new Font("Verdana",Font.PLAIN,16);
        this.smallPrint = new TrueTypeFont(font,true);
    }


    Image i = null;

    Tower selected = null;

    Upgrade selectedUpgrade = null;
    Image resizedIcon = null;

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

        for (int i = 0; i != 5; i++) {
            drawTower(selected, i, graphics, false);
            drawUpgrade(null, i, graphics, false);
        }

        drawTowerInfo(selected, graphics);
        drawUpgradeInfo(selectedUpgrade,graphics);
    }

    int heightPerTower = 64;

    private void drawTower(Tower tower, int pos, Graphics g, boolean selected) {
        int x = 0;
        int y = heightPerTower * pos;
        g.drawImage(i, x, y);
    }

    private void drawTowerInfo(Tower tower, Graphics g) {
        int startX = 200;
        int startY = 0;
        ttf.drawString(startX, startY, tower.name, Color.white);
        ttf.drawString(startX, startY + 50, tower.description, Color.white);
        ttf.drawString(startX, startY + 100, "Cost: " + selected.cost);
        g.drawImage(resizedIcon, startX + 350, startY + 50);
    }


    private void drawUpgrade(Upgrade tower, int pos, Graphics g, boolean selected) {
        int x = 200;
        int y = heightPerTower * pos + 150;
        g.drawImage(i, x, y);
    }

    private void drawUpgradeInfo(Upgrade upgrade, Graphics g){
        int startX = 400;
        int startY = 150;
        ttf.drawString(startX,startY,upgrade.getTitle());
        smallPrint.drawString(startX,startY+=50,upgrade.getDescription());
        smallPrint.drawString(startX,startY+=50,"Unlocked at Exp Level " + upgrade.getActivationLevel());
        smallPrint.drawString(startX,startY+=50,"Increases tower cost by " + upgrade.getCostIncrease());
    }
    @Override
    public void mousePressed(int button, int x, int y) {
        super.mousePressed(button, x, y);
        if (x <= 200) {
            int pos = y / heightPerTower;
            System.out.println("Clicked on " + pos);
        }
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {

    }

    @Override
    public int getID() {
        return 1;
    }
}
