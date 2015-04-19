package me.jack.ld32.Upgrades.Pen;

import me.jack.ld32.Entity.Towers.PenTower;
import me.jack.ld32.Entity.Towers.SpoonTower;
import me.jack.ld32.Entity.Towers.Tower;
import me.jack.ld32.Upgrades.Upgrade;

/**
 * Created by Jack on 19/04/2015.
 */
public class PenFireRateUpgrade  extends Upgrade {

    public PenFireRateUpgrade() {
        super("Pen Fire Rate", "Increase the rate of fire", 8, 50,1, 0, 150f);
    }

    @Override
    public void apply(Tower tower) {
        PenTower.fireRate =  40;
    }


}
