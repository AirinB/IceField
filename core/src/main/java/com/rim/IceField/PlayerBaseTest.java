package com.rim.IceField;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerBaseTest {

    @Test
    void removeSnowByHand() {
        PlayerBase  p1 = new PolarExplorer();
        Iceberg ice = new Iceberg(true,1,"stable", 4, false,5,null);
        ice.Add_currentPlayers(p1);
        p1.removeSnow();
        assertEquals(4, ice.getAmountOfSnow());
        p1.removeSnow();
        assertEquals(3, ice.getAmountOfSnow());

    }

    @Test
    void removeSnowByShovel() {
        PlayerBase  p1 = new PolarExplorer();
        Shovel shovel = new Shovel();
        p1.inventory.addItem(shovel);
        Iceberg ice1 = new Iceberg(true,1,"stable", 4, false,4, null);
        ice1.Add_currentPlayers(p1);
        p1.useItem(shovel);
        assertEquals(2, ice1.getAmountOfSnow());
        p1.useItem(shovel);
        assertEquals(0, ice1.getAmountOfSnow());
        p1.useItem(shovel);
        assertEquals(0, ice1.getAmountOfSnow());

        PlayerBase  p2 = new PolarExplorer();
        Iceberg ice2 = new Iceberg(true,1,"stable", 2, false,2, null);
        ice2.Add_currentPlayers(p2);
        Inventory i = p2.getInventory();
        for (ItemBase item: i.getItems()){
            System.out.println(item);
        }
        p2.useItem(shovel);
        assertEquals(2, ice2.getAmountOfSnow());



    }
}