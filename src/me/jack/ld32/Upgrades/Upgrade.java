package me.jack.ld32.Upgrades;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 * Created by Jack on 18/04/2015.
 */
public class Upgrade {

    private String title;
    private String description;
    private int activationLevel;
    private int costIncrease;
    private Image icon;

    public static SpriteSheet upgradeIconSpriteSheet;

    public Upgrade(String title, String description, int activationLevel,int costIncrease, int tX,int tY) {
        this.title = title;
        this.description = description;
        this.activationLevel = activationLevel;
        if(upgradeIconSpriteSheet == null){
            try {
                upgradeIconSpriteSheet = new SpriteSheet("res/upgrades.png",16,16);
            } catch (SlickException e) {
                e.printStackTrace();
            }
        }
        icon = upgradeIconSpriteSheet.getSprite(tX,tY);
        this.costIncrease = costIncrease;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getActivationLevel() {
        return activationLevel;
    }

    public Image getIcon() {
        return icon;
    }


    public int getCostIncrease() {
        return costIncrease;
    }
}
