package me.jack.ld32.Upgrades.Toaster;

import me.jack.ld32.Entity.Towers.ToasterTower;
import me.jack.ld32.Entity.Towers.Tower;
import me.jack.ld32.Upgrades.Upgrade;

/**
 * Created by Jack on 19/04/2015.
 */
public class ToasterPowerUpgradeTwo extends Upgrade {

    public ToasterPowerUpgradeTwo() {
        super("Power Upgrade II", "Further increased toaster damage", 12, 100, 0,0,400f);
    }

    @Override
    public void apply(Tower tower) {
        ToasterTower.damage =  1.25f;
    }
}
