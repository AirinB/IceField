package com.rim.IceField;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;


//Map class
public class Map {
    public Game game;
    private final int MAP_HEIGHT = 10;                                  //map's height
    private final int MAP_WIDTH = 10;                                   //map's width
    public Iceberg[][] Icebergs = new Iceberg[MAP_HEIGHT][MAP_WIDTH];   //icebergs declaration
    private boolean charge, flare, gun;                                   //boolean for items existence on the map
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
                        3, false, ranSnow(), ranItem());

            }
        }
    }


    /**
     * Returns the item specified
     *
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
                if (!charge)
                {
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
        //No items on hole
    }

    /**
     * Used for creating numbers
     *
     * @return a random number
     */
    public int ranSnow() {
        Random rand = new Random();
        int upperBound = 4;
        return rand.nextInt(upperBound) + 1;
    }

    /**
     * Displays the map in the console
     */
    public void showMap() {
        for (int Y = 0; Y < MAP_HEIGHT; Y++) {
            for (int X = 0; X < MAP_WIDTH; X++) {
                if (!Icebergs[Y][X].getCurrentPlayers().isEmpty())
                    System.out.print("O ");
                if (Icebergs[Y][X].getAmountOfSnow() > 0 && Icebergs[Y][X].getCurrentPlayers().isEmpty())
                    System.out.print("- ");
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
                        System.out.print("<>");
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
     for(int i =0;i<10;i++)
     {
         this.game = game;
         for(int j = 0;j<10;j++)
         {
             Icebergs[i][j] = new Iceberg();
             Icebergs[i][j].y = i;
             Icebergs[i][j].x = j;
         }
     }

    }


    /**
     * Generates the map used for the tests
     */
    //Method for generating icebergs and items on the map.
    public void generateMap() { //hardcode

        Food food1 = new Food();
        Food food2 = new Food();
        Food food3 = new Food();
        Food food4 = new Food();
        Food food5 = new Food();
        DivingSuit divingSuit = new DivingSuit();
        Rope rope1 = new Rope();
        Rope rope2 = new Rope();
        Shovel shovel1 = new Shovel();
        Shovel shovel2 = new Shovel();
        Flare flare = new Flare();
        Charge charge = new Charge();
        Gun gun = new Gun();

        Icebergs[1][1].setItem(shovel1);
        Icebergs[6][6].setItem(shovel2);
        Icebergs[4][8].setItem(rope1);
        Icebergs[7][9].setItem(rope2);
        Icebergs[2][7].setItem(charge);
        Icebergs[4][5].setItem(flare);
        Icebergs[9][9].setItem(gun);
        Icebergs[4][1].setItem(divingSuit);
        Icebergs[6][3].setItem(food1);
        Icebergs[5][5].setItem(food2);
        Icebergs[3][2].setItem(food3);
        Icebergs[9][4].setItem(food4);
        Icebergs[8][5].setItem(food5);
//---------------------------------------------------------
        Icebergs[4][3].setType("hole");
        Icebergs[4][3].setMaxNumOfPlayers(0);

        Icebergs[2][0].setType("hole");
        Icebergs[2][0].setMaxNumOfPlayers(0);

        Icebergs[2][6].setType("hole");
        Icebergs[2][6].setMaxNumOfPlayers(0);

        Icebergs[6][1].setType("hole");
        Icebergs[6][1].setMaxNumOfPlayers(0);

       // Icebergs[7][5].setType("hole");
       // Icebergs[7][5].setMaxNumOfPlayers(0);

        Icebergs[0][1].setType("hole");
        Icebergs[0][1].setMaxNumOfPlayers(0);
        Icebergs[0][1].setIsStable(false);

        Icebergs[1][0].setType("instable");
        Icebergs[1][0].setMaxNumOfPlayers(1);
        Icebergs[1][0].setIsStable(false);

    }
}
