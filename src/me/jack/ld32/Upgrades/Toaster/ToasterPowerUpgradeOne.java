package me.jack.ld32.Upgrades.Toaster;

import me.jack.ld32.Entity.Towers.ToasterTower;
import me.jack.ld32.Entity.Towers.Tower;
import me.jack.ld32.Upgrades.Upgrade;

/**
 * Created by Jack on 18/04/2015.
 */
public class ToasterPowerUpgradeOne extends Upgrade{

    public ToasterPowerUpgradeOne() {
        super("Power Upgrade I", "Increased toaster damage", 6, 25, 0,0,200f);
    }

    @Override
    public void apply(Tower tower) {
       ToasterTower.damage =  1;
    }
}
