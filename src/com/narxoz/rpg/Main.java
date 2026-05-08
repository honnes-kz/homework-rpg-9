package com.narxoz.rpg;

import com.narxoz.rpg.artifact.Inventory;
import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.vault.ChronomancerEngine;
import com.narxoz.rpg.vault.VaultRunResult;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("=== Homework 9 Demo: Visitor + Memento ===");

        Hero arin = new Hero("Arin", 125, 45, 18, 9, 160, new Inventory());
        Hero mira = new Hero("Mira", 90, 80, 11, 5, 90, new Inventory());
        List<Hero> party = List.of(arin, mira);

        ChronomancerEngine engine = new ChronomancerEngine();
        VaultRunResult result = engine.runVault(party);

        System.out.println();
        System.out.println("Final VaultRunResult: " + result);
    }
}