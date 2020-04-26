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
    public boolean useSkill( Map map,String dir) {


        if(checkDir(dir,map)&&"north".equals(dir))
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
        else if(checkDir(dir,map)&&"south".equals(dir))
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

        else if(checkDir(dir,map)&&"west".equals(dir))
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

        else if(checkDir(dir,map)&&"east".equals(dir))
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

    }

}
