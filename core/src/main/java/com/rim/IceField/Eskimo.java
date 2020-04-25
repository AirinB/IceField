package com.rim.IceField;

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

    @Override
    public void run() {
        decreaseHeatLevel();
    }

    //Method useSkill performs the ability of Eskimo to construct the Igloo

    @Override
    public void useSkill(Map map, String dir) {

        if ("north".equals(dir)) { //Up

            if (currentIceberg.y - 1 < 0) {
                System.out.println("Sorry, you are on the edge of the map, impossible to yse skill");
            } else {
                if (map.Icebergs[currentIceberg.y - 1][currentIceberg.x].getHasIgloo() == false) {
                    System.out.println("An Igloo has been created!");
                    map.Icebergs[currentIceberg.y - 1][currentIceberg.x].setHasIgloo(true);
                } else {
                    System.out.println("There is an igloo on this iceberg already!");
                }
            }

        } else if ("south".equals(dir)) {

            if (currentIceberg.y + 1 > 9) {
                System.out.println("Sorry, you are on the edge of the map, impossible to yse skill");
            } else {
                if (map.Icebergs[currentIceberg.y + 1][currentIceberg.x].getHasIgloo() == false) {
                    System.out.println("An Igloo has been created!");
                    map.Icebergs[currentIceberg.y + 1][currentIceberg.x].setHasIgloo(true);
                } else {
                    System.out.println("There is an igloo on this iceberg already!");
                }
            }

        } else if ("west".equals(dir)) {

            if (currentIceberg.x - 1 < 0) {
                System.out.println("Sorry, you are on the edge of the map, impossible to yse skill");
            } else {
                if (map.Icebergs[currentIceberg.y][currentIceberg.x - 1].getHasIgloo() == false) {
                    System.out.println("An Igloo has been created!");
                    map.Icebergs[currentIceberg.y][currentIceberg.x - 1].setHasIgloo(true);
                } else {
                    System.out.println("There is an igloo on this iceberg already!");
                }
            }

        } else if ("east".equals(dir)) {

            if (currentIceberg.x + 1 > 9) {
                System.out.println("Sorry, you are on the edge of the map, impossible to yse skill");
            } else {
                if (map.Icebergs[currentIceberg.y][currentIceberg.x + 1].getHasIgloo() == false) {
                    System.out.println("An Igloo has been created!");
                    map.Icebergs[currentIceberg.y][currentIceberg.x + 1].setHasIgloo(true);
                } else {
                    System.out.println("There is an igloo on this iceberg already!");
                }
            }

        }


    }

}
