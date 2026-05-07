package com.narxoz.rpg.visitor;

import com.narxoz.rpg.artifact.Armor;
import com.narxoz.rpg.artifact.ArtifactVisitor;
import com.narxoz.rpg.artifact.Potion;
import com.narxoz.rpg.artifact.Ring;
import com.narxoz.rpg.artifact.Scroll;
import com.narxoz.rpg.artifact.Weapon;

/**
 * Reads magical signatures from artifacts.
 */
public class EnchantmentScanner implements ArtifactVisitor {

    private int signaturesFound;

    @Override
    public void visit(Weapon weapon) {
        signaturesFound++;
        System.out.println("[Magic] Weapon " + weapon.getName()
                + " hums with +" + weapon.getAttackBonus() + " battle force");
    }

    @Override
    public void visit(Potion potion) {
        signaturesFound++;
        System.out.println("[Magic] Potion " + potion.getName()
                + " contains " + potion.getHealing() + " healing essence");
    }

    @Override
    public void visit(Scroll scroll) {
        signaturesFound++;
        System.out.println("[Magic] Scroll " + scroll.getName()
                + " stores spell: " + scroll.getSpellName());
    }

    @Override
    public void visit(Ring ring) {
        signaturesFound++;
        System.out.println("[Magic] Ring " + ring.getName()
                + " bends time by +" + ring.getMagicBonus() + " arcane focus");
    }

    @Override
    public void visit(Armor armor) {
        signaturesFound++;
        System.out.println("[Magic] Armor " + armor.getName()
                + " projects +" + armor.getDefenseBonus() + " protection");
    }

    public int getSignaturesFound() {
        return signaturesFound;
    }
}
