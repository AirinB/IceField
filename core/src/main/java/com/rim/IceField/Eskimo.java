package com.rim.IceField;

import com.badlogic.gdx.graphics.Texture;

import java.io.IOException;

//Eskimo class
public class Eskimo extends PlayerBase {
    //Boolean for checking if Eskimo has built the Igloo
    private boolean usedIgloo;

    //Constructor
    public Eskimo() {
        super();
        this.tag = "Eskimo";    //Setting the name
        this.heatLevel = 5;     //Setting number of heat units
    }

    /**
     * decrease the heat
     * every few seconds
     */
    @Override
    public void run() {
        try {
            decreaseHeatLevel();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Method useSkill performs the ability of Eskimo to construct the Igloo

    /**
     * Constructs the igloo in the showed direction
     *
     * @param dir direction
     * @return true if the action was successful
     */
    @Override
    public boolean useSkill(String dir) {
        Map map = game.getMap();
        if (usedIgloo == false) {

            usedIgloo = true;

            if ("north".equals(dir)) {
                if (currentIceberg.y - 1 < 0) {
                    System.out.println("Sorry, you are on the edge of the map, impossible to yse skill");
                    return false;
                } else {
                    if (!map.Icebergs[currentIceberg.y - 1][currentIceberg.x].getHasIgloo()) {
                        System.out.println("An Igloo has been created!");
                        map.Icebergs[currentIceberg.y - 1][currentIceberg.x].setHasIgloo(true);
                    } else {
                        System.out.println("There is an igloo on this iceberg already!");
                        return false;
                    }
                }
            } else if ("south".equals(dir)) {

                if (currentIceberg.y + 1 > 9) {
                    System.out.println("Sorry, you are on the edge of the map, impossible to yse skill");
                    return false;
                } else {
                    if (!map.Icebergs[currentIceberg.y + 1][currentIceberg.x].getHasIgloo()) {
                        System.out.println("An Igloo has been created!");
                        map.Icebergs[currentIceberg.y + 1][currentIceberg.x].setHasIgloo(true);
                    } else {
                        System.out.println("There is an igloo on this iceberg already!");
                        return false;
                    }
                }

            } else if ("west".equals(dir)) {

                if (currentIceberg.x - 1 < 0) {
                    System.out.println("Sorry, you are on the edge of the map, impossible to yse skill");
                    return false;
                } else {
                    if (!map.Icebergs[currentIceberg.y][currentIceberg.x - 1].getHasIgloo()) {
                        System.out.println("An Igloo has been created!");
                        map.Icebergs[currentIceberg.y][currentIceberg.x - 1].setHasIgloo(true);
                    } else {
                        System.out.println("There is an igloo on this iceberg already!");
                        return false;
                    }
                }

            } else if ("east".equals(dir)) {

                if (currentIceberg.x + 1 > 9) {
                    System.out.println("Sorry, you are on the edge of the map, impossible to yse skill");
                    return false;
                } else {
                    if (!map.Icebergs[currentIceberg.y][currentIceberg.x + 1].getHasIgloo()) {
                        System.out.println("An Igloo has been created!");
                        map.Icebergs[currentIceberg.y][currentIceberg.x + 1].setHasIgloo(true);
                    } else {
                        System.out.println("There is an igloo on this iceberg already!");
                        return false;
                    }
                }

            } else {
                System.out.println("No such direction, try again");
                return false;
            }
            usedIgloo = false;
            return true;


        }
        else{
            System.out.println("Unable, skill has already been used");
            return false;
        }
    }
}
