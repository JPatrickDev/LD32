package me.jack.ld32.Upgrades.Toaster;

import me.jack.ld32.Entity.Towers.Tower;
import me.jack.ld32.Upgrades.Upgrade;

/**
 * Created by Jack on 18/04/2015.
 */
public class FireRateUpgrade extends Upgrade {

    public FireRateUpgrade() {
        super("Fire Rate", "Increase the rate of fire of the Toaster Gun", 5, 50, 0, 0, 300f);
    }

    @Override
    public void apply(Tower tower) {

    }
}
