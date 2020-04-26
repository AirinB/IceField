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

    /**
     * decrease the heat
     * every few seconds
     */
    @Override
    public void run() {
        decreaseHeatLevel();
    }

    //Method useSkill performs the ability of Eskimo to construct the Igloo

    /**
     * Constructs the igloo in the showed direction
     * @param map map of the game
     * @param dir direction
     * @return true if the action was successful
     */
    @Override
    public boolean useSkill( Map map,String str) {


        if(checkDir(str,map)&&"north".equals(str))
        {
            if (!map.Icebergs[currentIceberg.y - 1][currentIceberg.x].getHasIgloo()) {
                System.out.println("An Igloo has been created!");
                map.Icebergs[currentIceberg.y - 1][currentIceberg.x].setHasIgloo(true);
                return true;
            }
            else {
                System.out.println("There is an igloo on this iceberg already!");
                return false;
            }
        }
        else if(checkDir(str,map)&&"south".equals(str))
        {
            if (!map.Icebergs[currentIceberg.y + 1][currentIceberg.x].getHasIgloo()) {
                System.out.println("An Igloo has been created!");
                map.Icebergs[currentIceberg.y + 1][currentIceberg.x].setHasIgloo(true);
                return true;}
           else {
                    System.out.println("There is an igloo on this iceberg already!");
                    return false;
                }
        }

        else if(checkDir(str,map)&&"west".equals(str))
        {
            if (!map.Icebergs[currentIceberg.y][currentIceberg.x - 1].getHasIgloo()) {
            System.out.println("An Igloo has been created!");
            map.Icebergs[currentIceberg.y][currentIceberg.x - 1].setHasIgloo(true);
            return true;
        }
            else {
                System.out.println("There is an igloo on this iceberg already!");
                return false;
            }
        }

        else if(checkDir(str,map)&&"east".equals(str))
        {
            if (!map.Icebergs[currentIceberg.y][currentIceberg.x + 1].getHasIgloo()) {
                System.out.println("An Igloo has been created!");
                map.Icebergs[currentIceberg.y][currentIceberg.x + 1].setHasIgloo(true);
                return true;
            }
            else {
                System.out.println("There is an igloo on this iceberg already!");
                return false;
            }
        }

        else
        {
            System.out.println("No such direction, try again");
            return false;
        }

        return true;
    }

}
