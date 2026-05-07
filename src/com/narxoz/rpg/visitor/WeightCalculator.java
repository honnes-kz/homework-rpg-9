package com.narxoz.rpg.visitor;

import com.narxoz.rpg.artifact.Armor;
import com.narxoz.rpg.artifact.ArtifactVisitor;
import com.narxoz.rpg.artifact.Potion;
import com.narxoz.rpg.artifact.Ring;
import com.narxoz.rpg.artifact.Scroll;
import com.narxoz.rpg.artifact.Weapon;

/**
 * Fourth visitor added as the open/closed proof.
 */
public class WeightCalculator implements ArtifactVisitor {

    private int totalWeight;

    @Override
    public void visit(Weapon weapon) {
        add("Weapon", weapon.getName(), weapon.getWeight(), "balanced for combat");
    }

    @Override
    public void visit(Potion potion) {
        add("Potion", potion.getName(), potion.getWeight(), "easy to carry");
    }

    @Override
    public void visit(Scroll scroll) {
        add("Scroll", scroll.getName(), scroll.getWeight(), "almost weightless");
    }

    @Override
    public void visit(Ring ring) {
        add("Ring", ring.getName(), ring.getWeight(), "fits in a pouch");
    }

    @Override
    public void visit(Armor armor) {
        add("Armor", armor.getName(), armor.getWeight(), "requires strong shoulders");
    }

    public int getTotalWeight() {
        return totalWeight;
    }

    private void add(String type, String name, int weight, String note) {
        totalWeight += weight;
        System.out.println("[Weight] " + type + " " + name
                + " weighs " + weight + " kg - " + note);
    }
}
