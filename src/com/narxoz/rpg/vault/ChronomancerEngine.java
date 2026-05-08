package com.narxoz.rpg.vault;

import com.narxoz.rpg.artifact.Armor;
import com.narxoz.rpg.artifact.Inventory;
import com.narxoz.rpg.artifact.Potion;
import com.narxoz.rpg.artifact.Ring;
import com.narxoz.rpg.artifact.Scroll;
import com.narxoz.rpg.artifact.Weapon;
import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.combatant.HeroMemento;
import com.narxoz.rpg.memento.Caretaker;
import com.narxoz.rpg.visitor.CurseDetector;
import com.narxoz.rpg.visitor.EnchantmentScanner;
import com.narxoz.rpg.visitor.GoldAppraiser;
import com.narxoz.rpg.visitor.WeightCalculator;
import java.util.List;

/**
 * Orchestrates the Chronomancer's Vault demo run.
 */
public class ChronomancerEngine {

    /**
     * Runs the vault sequence for the supplied party.
     *
     * @param party the heroes entering the vault
     * @return a summary of the completed vault sequence
     */
    public VaultRunResult runVault(List<Hero> party) {
        if (party == null || party.isEmpty()) {
            System.out.println("No heroes entered the Chronomancer's Vault.");
            return new VaultRunResult(0, 0, 0);
        }

        Inventory vaultInventory = buildVaultInventory();
        Hero leadHero = party.get(0);
        leadHero.setInventory(vaultInventory.copy());

        System.out.println();
        System.out.println("=== Chronomancer's Vault Opens ===");
        printParty(party);
        System.out.println("Vault inventory contains " + vaultInventory.size() + " artifacts.");

        System.out.println();
        System.out.println("--- Visitor Appraisal Chamber ---");
        GoldAppraiser goldAppraiser = new GoldAppraiser();
        vaultInventory.accept(goldAppraiser);
        System.out.println("Gold appraisal total: " + goldAppraiser.getTotalGold() + " gold");

        EnchantmentScanner enchantmentScanner = new EnchantmentScanner();
        vaultInventory.accept(enchantmentScanner);
        System.out.println("Magical signatures found: " + enchantmentScanner.getSignaturesFound());

        CurseDetector curseDetector = new CurseDetector();
        vaultInventory.accept(curseDetector);
        System.out.println("Cursed artifacts detected: " + curseDetector.getCursedItems());

        WeightCalculator weightCalculator = new WeightCalculator();
        vaultInventory.accept(weightCalculator);
        System.out.println("Open/closed visitor total weight: "
                + weightCalculator.getTotalWeight() + " kg");

        System.out.println();
        System.out.println("--- Memento Time Crystal ---");
        Caretaker caretaker = new Caretaker();
        System.out.println("Before snapshot: " + leadHero);
        caretaker.save(leadHero.createMemento());
        int mementosCreated = caretaker.size();
        System.out.println("Snapshot saved. Caretaker now stores "
                + caretaker.size() + " opaque memento.");

        System.out.println();
        System.out.println("--- Vault Trap Changes State ---");
        leadHero.takeDamage(55);
        leadHero.spendMana(25);
        leadHero.spendGold(60);
        leadHero.getInventory().addArtifact(new Ring("Temporal Shackles", 0, 2, -4));
        System.out.println("After trap: " + leadHero);

        System.out.println();
        System.out.println("--- Rewind From Memento ---");
        HeroMemento snapshot = caretaker.undo();
        int restoredCount = 0;
        if (snapshot != null) {
            leadHero.restoreFromMemento(snapshot);
            restoredCount++;
        }
        System.out.println("After rewind: " + leadHero);
        System.out.println("Caretaker remaining snapshots: " + caretaker.size());

        return new VaultRunResult(vaultInventory.size(), mementosCreated, restoredCount);
    }

    private Inventory buildVaultInventory() {
        Inventory inventory = new Inventory();
        inventory.addArtifact(new Weapon("Sunsteel Blade", 120, 5, 12));
        inventory.addArtifact(new Potion("Moonwell Tonic", 45, 1, 30));
        inventory.addArtifact(new Scroll("Void Step Glyph", 80, 1, "Void Step"));
        inventory.addArtifact(new Ring("Chrono Loop Ring", 150, 1, 5));
        inventory.addArtifact(new Armor("Gravestone Plate", 95, 22, 9));
        return inventory;
    }

    private void printParty(List<Hero> party) {
        System.out.println("Party entering the vault:");
        for (Hero hero : party) {
            System.out.println(" - " + hero);
        }
    }
}
