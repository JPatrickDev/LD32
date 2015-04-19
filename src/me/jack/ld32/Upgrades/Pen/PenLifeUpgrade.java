package me.jack.ld32.Upgrades.Pen;

import me.jack.ld32.Entity.Towers.PenTower;
import me.jack.ld32.Entity.Towers.SpoonTower;
import me.jack.ld32.Entity.Towers.Tower;
import me.jack.ld32.Upgrades.Upgrade;

/**
 * Created by Jack on 19/04/2015.
 */
public class PenLifeUpgrade extends Upgrade {


    public PenLifeUpgrade() {
        super("Pen Life ", "Increase the time before the pen projectiles die", 8, 50,1, 0, 150f);
    }

    @Override
    public void apply(Tower tower) {
       PenTower.projectileLife = 1f;
    }



}
