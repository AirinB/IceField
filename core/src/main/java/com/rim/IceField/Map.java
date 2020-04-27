package com.rim.IceField;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;


//Map class
public class Map {

    private final int MAP_HEIGHT = 10;                                  //map's height
    private final int MAP_WIDTH = 10;                                   //map's width
    public Iceberg[][] Icebergs = new Iceberg[MAP_HEIGHT][MAP_WIDTH];   //icebergs declaration
    private boolean charge,flare,gun;                                   //boolean for items existence on the map
    private ArrayList<ItemBase> items;                            //List of items

    //Getter for list of icebergs
    public Iceberg[][] getIcebergs() {
        return Icebergs;
    }




    public void loadMap(/*String path*/) throws FileNotFoundException {
//        File map = new File(path);
//        String[] icebergs;
//        String[] splitIcebergs;
//        Scanner sc = new Scanner(map);
//        int r = 0, c = 0;
//        while(sc.hasNextLine()) {
//            String line = sc.nextLine();
//            icebergs = line.split(" ");
//            for (String iceberg : icebergs) {
//                splitIcebergs = iceberg.split(":");
//                Icebergs[r][c] = new Iceberg(Boolean.parseBoolean(splitIcebergs[0]), splitIcebergs[1],
//                        Integer.parseInt(splitIcebergs[2]),false, ranSnow(),ranItem());
//                c++;
//            }
//            r++;
//        }
        for (int j = 0; j < MAP_HEIGHT; j++) {
            for (int i = 0; i < MAP_WIDTH; i++) {
                Icebergs[j][i] = new Iceberg(true, "Stable",
                          3,false, ranSnow(),ranItem());

            }
        }
    }


    /**
     * Returns the item specified
     * @param tag of the item to be selected
     * @return instance of an item
     */
    public ItemBase returnItem(String tag) {
        if (new Shovel().tag.equals(tag)) return new Shovel();
        if (new Charge().tag.equals(tag)) return new Charge();
        if (new DivingSuit().tag.equals(tag)) return new DivingSuit();
        if (new Flare().tag.equals(tag)) return new Flare();
        if (new Food().tag.equals(tag)) return new Food();
        if (new Gun().tag.equals(tag)) return new Gun();
        if (new Rope().tag.equals(tag)) return new Rope();
        return null;
    }

    /**
     *
     * @return item based on the random selector
     */
  
    //MAKE FUNCTION THAT RETURNS RANDOM ITEMS
    public ItemBase ranItem() {
        Random rand = new Random();
        int upperBound = 6;
        int Ran = rand.nextInt(upperBound);

        switch (Ran) {
            case 0:
                return new Shovel();
            case 1:
                return new DivingSuit();
            case 2:
                return new Food();
            case 3:
                return new Rope();
            case 4:
                if (!charge) {
                    charge = true;
                    return new Charge();
                }
            case 5:
                if (!flare) {
                    flare = true;
                    return new Flare();
                }
            case 6:
                if (!gun) {
                    gun = true;
                    return new Gun();
                }
            default:
                return new ItemBase() {
                };
        }
        //Check if flare parts were already given
        //No items on hole
    }

    /**
     * Used for creating numbers
     * @return a random number
     */
    public int ranSnow() {
        Random rand = new Random();
        int upperBound = 4;
        int Ran = rand.nextInt(upperBound) + 1;
        return Ran;
    }

    /**
     * Displays the map in the console
     */
    public void showMap() {
        for (int Y = 0; Y < MAP_HEIGHT; Y++) {
            for (int X = 0; X < MAP_WIDTH; X++) {
                if (Icebergs[Y][X].getAmountOfSnow() > 0)
                    System.out.print("❅");
                else if (Icebergs[Y][X].getAmountOfSnow() == 0) {
                    if (Icebergs[Y][X].getItem().getTag().equals("Shovel")) {
                        System.out.print("/ ");
                    } else if (Icebergs[Y][X].getItem().getTag().equals("Rope")) {
                        System.out.print("@ ");
                    } else if (Icebergs[Y][X].getItem().getTag().equals("Gun")) {
                        System.out.print("~ ");
                    } else if (Icebergs[Y][X].getItem().getTag().equals("Flare")) {
                        System.out.print("| ");
                    } else if (Icebergs[Y][X].getItem().getTag().equals("Charge")) {
                        System.out.print("* ");
                    } else if (Icebergs[Y][X].getItem().getTag().equals("Food")) {
                        System.out.print("& ");
                    } else if (Icebergs[Y][X].getItem().getTag().equals("DivingSuit")) {
                        System.out.print("/ ");
                    }
                }
            }
            System.out.println();
        }
    }


    //Getter for list of items
    public ArrayList<ItemBase> getItems() {
        return items;
    }


    //Constructor for Map
    public Map() {
        this.items = new ArrayList<ItemBase>();
    }


    /**
     * Generates the map used for the tests
     */
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



        Iceberg first = new Iceberg(true, "stable", 20, false, 1, null);

        Icebergs[0][0] = first;
        first.y = 0;
        first.x = 0;
        first.setItem(shovel);


        Iceberg second = new Iceberg(true, "stable", 20, false, 2, rope);
        Icebergs[0][1] = second;
        second.y = 0;
        second.x = 1;


        Iceberg third = new Iceberg(true, "stable", 20, false, 1, null);
        Icebergs[1][0] = third;
        third.y = 1;
        third.x = 0;

        Iceberg forth = new Iceberg(true, "instable", 1, false, 1, null);
        Icebergs[1][1] = forth;
        forth.y = 1;
        forth.x = 1;

        Iceberg fifth = new Iceberg(true, "hole", 0, false, 1, null);
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

    public void generateStaticMap(ArrayList<PlayerBase> playerBase){
        items = new ArrayList<ItemBase>();
        Iceberg[][] map = new Iceberg[4][4];
        ItemBase shovel = new Shovel();

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
        Iceberg first = new Iceberg(true, "stable", 20, false, 1, rope);


        map[0][0] = first;
        first.y = 0;
        first.x = 0;
        first.setItem(rope);


        Iceberg second = new Iceberg(true, "hole", 20, false, 2, rope);
        map[0][1] = second;
        second.y = 0;
        second.x = 1;


        Iceberg third = new Iceberg(true, "stable", 20, false, 1, null);
        map[1][0] = third;
        third.y = 1;
        third.x = 0;

        Iceberg forth = new Iceberg(true, "instable", 1, false, 1, null);
        map[1][1] = forth;
        forth.y = 1;
        forth.x = 1;

        Iceberg fifth = new Iceberg(true, "hole", 0, false, 1, null);
        map[1][2] = fifth;
        fifth.y = 1;
        fifth.x = 2;
        for (PlayerBase p: playerBase) {
            p.setCurrentIceberg(map[0][0]);
            map[0][0].Add_currentPlayers(p);
        }
        System.out.println("Icebergs were generated!");
        this.Icebergs = map;
    }

}
