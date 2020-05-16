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

    private String[] mapAreaType = {"hole", "unstable", "stable"};
    private int[][] mapAreaConfig = {
    //   0  1  2  3  4  5  6  7  8  9
        {1, 1, 1, 2, 2, 2, 2, 2, 2, 2}, // 0
        {1, 1, 1, 2, 2, 2, 2, 2, 2, 2}, // 1
        {0, 2, 2, 2, 2, 2, 0, 2, 2, 2}, // 2
        {2, 2, 2, 2, 2, 2, 2, 2, 2, 2}, // 3
        {2, 2, 2, 0, 2, 2, 2, 2, 2, 2}, // 4
        {2, 0, 2, 2, 2, 2, 2, 2, 2, 2}, // 5
        {2, 2, 2, 2, 2, 2, 2, 2, 2, 2}, // 6
        {2, 2, 2, 2, 2, 2, 2, 2, 2, 2}, // 7
        {2, 2, 2, 2, 2, 2, 2, 2, 2, 2}, // 8
        {2, 2, 2, 2, 2, 2, 2, 2, 2, 2}, // 9
    };

    private String[] itemTypes = {"", "food", "shovel", "charge", "flare", "gun", "rope", "suit"};
    private int[][] mapItemsConfig = new int[][]{
    //   0  1  2  3  4  5  6  7  8  9
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // 0
        {0, 1, 0, 0, 0, 0, 0, 0, 0, 0}, // 1
        {0, 0, 0, 0, 0, 0, 0, 3, 0, 0}, // 2
        {0, 0, 1, 0, 0, 0, 0, 0, 0, 0}, // 3
        {0, 7, 0, 0, 0, 4, 0, 0, 6, 0}, // 4
        {0, 0, 0, 0, 0, 1, 0, 0, 0, 0}, // 5
        {0, 0, 0, 1, 0, 0, 2, 0, 0, 0}, // 6
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 6}, // 7
        {0, 0, 0, 0, 0, 1, 0, 0, 0, 0}, // 8
        {0, 0, 0, 0, 1, 0, 0, 0, 0, 5}, // 9
    };

    //Getter for list of icebergs
    public Iceberg[][] getIcebergs() {
        return Icebergs;
    }

    private String getAreaType (int row, int col) {
        System.out.println("row: " + row + ", col: " + col + ", type: " + mapAreaConfig[row][col]);
        return mapAreaType[mapAreaConfig[row][col]];
    }

    private ItemBase getAreaItem(int row, int col) {
        String itemType = itemTypes[mapItemsConfig[row][col]];
        if ("food".equals(itemType)) {
            return new Food();
        } else if ("shovel".equals(itemType)) {
            return new Shovel();
        } else if ("charge".equals(itemType)) {
            return new Charge();
        } else if ("flare".equals(itemType)) {
            return new Flare();
        } else if ("gun".equals(itemType)) {
            return new Gun();
        } else if ("rope".equals(itemType)) {
            return new Rope();
        } else if ("suit".equals(itemType)) {
            return new DivingSuit();
        }
        return new ItemBase() {
        };
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
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                String tmpType = this.getAreaType(i, j);
                ItemBase tmpItem = this.getAreaItem(i, j);
                Icebergs[i][j] = new Iceberg(true, tmpType,
                        3, false, ranSnow(), tmpItem);

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

        //Icebergs[0][0].setItem(shovel1);
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

//
//        Icebergs[0][1].setType("hole");
//        Icebergs[0][1].setMaxNumOfPlayers(0);
//        Icebergs[0][1].setIsStable(false);
//
//        Icebergs[1][0].setType("instable");
//        Icebergs[1][0].setMaxNumOfPlayers(1);
//        Icebergs[1][0].setIsStable(false);

    }


    public int getMAP_HEIGHT() {
        return MAP_HEIGHT;
    }

    public int getMAP_WIDTH() {
        return MAP_WIDTH;
    }
}
