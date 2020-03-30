package com.rim.IceField;

import java.util.ArrayList;


//Map class
public class Map {


    private ArrayList<Iceberg> icebergs;    //List of icebergs
    private ArrayList<ItemBase> items;      //List of items

    //Getter for list of icebergs
    public ArrayList<Iceberg> getIcebergs() {
        System.out.println("getIcebergs");
        return icebergs;
    }

    //Getter for list of items
    public ArrayList<ItemBase> getItems() {
        System.out.println("getItems()");
        return items;
    }


    //Constructor for Map
    public Map() {

        this.items = new ArrayList<ItemBase>();
        this.icebergs = new ArrayList<Iceberg>();

    }



    //Method for generating icebergs and items on the map.
    public void generateItemsOnMap() {

        System.out.println("generateItemsOnMap()");

        // - - - - - - - - - - - - - - - - - - - - - Items - - - - - - - - - - - - - - - - - - - - -

        //This is done for the test-cases needed in this iteration.
        //Creation of different items on the map.

        ItemBase shovel = new Shovel();
        items.add(shovel);

        ItemBase rope = new Rope();
        items.add(rope);

        ItemBase food = new Food();
        items.add(food);

        ItemBase flare = new Food();
        items.add(flare);

        ItemBase divingSuit = new DivingSuit();
        items.add(divingSuit);

        ItemBase gun = new Food();
        items.add(gun);

        System.out.println("Items were generated!");


        // - - - - - - - - - - - - - - - - - - - - - Icebergs - - - - - - - - - - - - - - - - - - - - -


        //Creation of the icebergs on the map.
        //Different types of icebergs are generated(stable, instable, hole) with different amount of snow.


        Iceberg first = new Iceberg(true, 1, "stable", 20, false, 1, null);
        icebergs.add(first);

        Iceberg second = new Iceberg(true, 2, "stable", 20, false, 2, null);
        icebergs.add(second);

        Iceberg third = new Iceberg(true, 3, "stable", 20, false, 3, null);
        icebergs.add(third);

        Iceberg forth = new Iceberg(true, 4, "instable", 3, false, 2, null);
        icebergs.add(forth);

        Iceberg fifth = new Iceberg(true, 5, "hole", 0, false, 1, null);
        icebergs.add(fifth);

        System.out.println("Icebergs were generated!");


        //Setting the neighboring icebergs.
        ArrayList<Iceberg> neighbour1 = new ArrayList<Iceberg>();
        neighbour1.add(second);
        first.setNeighborIcebergs(neighbour1);

        ArrayList<Iceberg> neighbour2 = new ArrayList<Iceberg>();
        neighbour2.add(third);
        first.setNeighborIcebergs(neighbour2);

        ArrayList<Iceberg> neighbour3 = new ArrayList<Iceberg>();
        neighbour3.add(forth);
        first.setNeighborIcebergs(neighbour3);

        ArrayList<Iceberg> neighbour4 = new ArrayList<Iceberg>();
        neighbour4.add(fifth);
        first.setNeighborIcebergs(neighbour4);

        ArrayList<Iceberg> neighbour5 = new ArrayList<Iceberg>();
        neighbour5.add(first);
        first.setNeighborIcebergs(neighbour5);


    }

}
