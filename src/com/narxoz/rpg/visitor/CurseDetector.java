package com.narxoz.rpg.visitor;

import com.narxoz.rpg.artifact.Armor;
import com.narxoz.rpg.artifact.ArtifactVisitor;
import com.narxoz.rpg.artifact.Potion;
import com.narxoz.rpg.artifact.Ring;
import com.narxoz.rpg.artifact.Scroll;
import com.narxoz.rpg.artifact.Weapon;

/**
 * Flags artifacts that are risky inside the vault.
 */
public class CurseDetector implements ArtifactVisitor {

    private int cursedItems;

    @Override
    public void visit(Weapon weapon) {
        boolean cursed = weapon.getAttackBonus() > 10;
        report("Weapon", weapon.getName(), cursed, "high attack draws blood oaths");
    }

    @Override
    public void visit(Potion potion) {
        boolean cursed = potion.getHealing() < 25;
        report("Potion", potion.getName(), cursed, "weak brews may be unstable");
    }

    @Override
    public void visit(Scroll scroll) {
        boolean cursed = scroll.getSpellName().toLowerCase().contains("void");
        report("Scroll", scroll.getName(), cursed, "void magic attracts echoes");
    }

    @Override
    public void visit(Ring ring) {
        boolean cursed = ring.getMagicBonus() < 0;
        report("Ring", ring.getName(), cursed, "negative focus drains time");
    }

    @Override
    public void visit(Armor armor) {
        boolean cursed = armor.getWeight() > 18;
        report("Armor", armor.getName(), cursed, "heavy armor can bind the wearer");
    }

    public int getCursedItems() {
        return cursedItems;
    }

    private void report(String type, String name, boolean cursed, String reason) {
        if (cursed) {
            cursedItems++;
            System.out.println("[Curse] " + type + " " + name + " is dangerous: " + reason);
        } else {
            System.out.println("[Curse] " + type + " " + name + " is stable");
        }
    }
}
