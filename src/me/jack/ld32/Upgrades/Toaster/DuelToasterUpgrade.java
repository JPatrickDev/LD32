package me.jack.ld32.Upgrades.Toaster;

import me.jack.ld32.Entity.Towers.ToasterTower;
import me.jack.ld32.Entity.Towers.Tower;
import me.jack.ld32.Upgrades.Upgrade;

/**
 * Created by Jack on 19/04/2015.
 */
public class DuelToasterUpgrade extends Upgrade {

    public DuelToasterUpgrade() {
        super("Duel Toaster", "Fire two toasters at once",15, 25, 0,0,200f);
    }

    @Override
    public void apply(Tower tower) {
        ToasterTower.duel = true;
    }
}
