package com.narxoz.rpg.visitor;

import com.narxoz.rpg.artifact.Armor;
import com.narxoz.rpg.artifact.ArtifactVisitor;
import com.narxoz.rpg.artifact.Potion;
import com.narxoz.rpg.artifact.Ring;
import com.narxoz.rpg.artifact.Scroll;
import com.narxoz.rpg.artifact.Weapon;

/**
 * Estimates resale prices for every artifact type.
 */
public class GoldAppraiser implements ArtifactVisitor {

    private int totalGold;

    @Override
    public void visit(Weapon weapon) {
        int estimate = weapon.getValue() + weapon.getAttackBonus() * 12;
        totalGold += estimate;
        System.out.println("[Gold] Weapon " + weapon.getName()
                + " resale estimate: " + estimate + " gold");
    }

    @Override
    public void visit(Potion potion) {
        int estimate = potion.getValue() + potion.getHealing() * 2;
        totalGold += estimate;
        System.out.println("[Gold] Potion " + potion.getName()
                + " resale estimate: " + estimate + " gold");
    }

    @Override
    public void visit(Scroll scroll) {
        int estimate = scroll.getValue() + scroll.getSpellName().length() * 5;
        totalGold += estimate;
        System.out.println("[Gold] Scroll " + scroll.getName()
                + " resale estimate: " + estimate + " gold");
    }

    @Override
    public void visit(Ring ring) {
        int estimate = ring.getValue() + ring.getMagicBonus() * 20;
        totalGold += estimate;
        System.out.println("[Gold] Ring " + ring.getName()
                + " resale estimate: " + estimate + " gold");
    }

    @Override
    public void visit(Armor armor) {
        int estimate = armor.getValue() + armor.getDefenseBonus() * 10;
        totalGold += estimate;
        System.out.println("[Gold] Armor " + armor.getName()
                + " resale estimate: " + estimate + " gold");
    }

    public int getTotalGold() {
        return totalGold;
    }
}
