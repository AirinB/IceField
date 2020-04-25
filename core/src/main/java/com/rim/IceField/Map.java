package com.rim.IceField;

import java.util.ArrayList;


//Map class
public class Map {

    public Iceberg[][] Icebergs = new Iceberg[10][10];
    public int[][] map = new int[10][10];

    private ArrayList<ItemBase> items;      //List of items

    //Getter for list of icebergs
    public Iceberg[][] getIcebergs() {

        return Icebergs;
    }

    //Getter for list of items
    public ArrayList<ItemBase> getItems() {
        return items;
    }


    //Constructor for Map
    public Map() {
        this.items = new ArrayList<ItemBase>();
    }


    //Method for generating icebergs and items on the map.
    public void generateItemsOnMap() {

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
        Icebergs[0][0] = first;
        first.y = 0;
        first.x = 0;
        first.setItem(shovel);
        Iceberg second = new Iceberg(true, 2, "stable", 20, false, 1, rope);
        Icebergs[0][1] = second;
        second.y = 0;
        second.x = 1;
        Iceberg third = new Iceberg(true, 3, "stable", 20, false, 1, null);
        Icebergs[1][0] = third;
        third.y = 1;
        third.x = 0;

        Iceberg forth = new Iceberg(true, 4, "instable", 1, false, 1, null);

        Icebergs[1][1] = forth;
        forth.y = 1;
        forth.x = 1;
        Iceberg fifth = new Iceberg(true, 5, "hole", 0, false, 1, null);


        Icebergs[1][2] = fifth;
        fifth.y = 1;
        fifth.x = 2;
        System.out.println("Icebergs were generated!");


        //Setting the neighboring icebergs.
        /*ArrayList<Iceberg> neighbour1 = new ArrayList<Iceberg>();
        neighbour1.add(second);
        first.setNeighborIcebergs(neighbour1);

        ArrayList<Iceberg> neighbour2 = new ArrayList<Iceberg>();
        neighbour2.add(third);
        second.setNeighborIcebergs(neighbour2);

        ArrayList<Iceberg> neighbour3 = new ArrayList<Iceberg>();
        neighbour3.add(forth);
        third.setNeighborIcebergs(neighbour3);

        ArrayList<Iceberg> neighbour4 = new ArrayList<Iceberg>();
        neighbour4.add(fifth);
        forth.setNeighborIcebergs(neighbour4);

        ArrayList<Iceberg> neighbour5 = new ArrayList<Iceberg>();
        neighbour5.add(first);
        fifth.setNeighborIcebergs(neighbour5);

*/
    }

}
