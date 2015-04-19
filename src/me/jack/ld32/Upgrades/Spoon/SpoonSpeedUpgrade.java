package me.jack.ld32.Upgrades.Spoon;

import me.jack.ld32.Entity.Towers.SpoonTower;
import me.jack.ld32.Entity.Towers.ToasterTower;
import me.jack.ld32.Entity.Towers.Tower;
import me.jack.ld32.Upgrades.Upgrade;

/**
 * Created by Jack on 19/04/2015.
 */
public class SpoonSpeedUpgrade extends Upgrade{

    public SpoonSpeedUpgrade() {
        super("Spoon Speed", "Increase the speed of the Spoons", 8, 50,1, 0, 150f);
    }

    @Override
    public void apply(Tower tower) {
        SpoonTower.spoonSpeed =  20;
    }


}
