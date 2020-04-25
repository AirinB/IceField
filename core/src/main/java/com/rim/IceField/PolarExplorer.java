package com.rim.IceField;

//Po;arExplorer class
public class PolarExplorer extends PlayerBase {

    //Constructor
    public PolarExplorer() {
        super();
        this.tag = "PolarExplorer";    //Setting the name
        this.heatLevel = 4;            //Setting number of heat units
    }

    @Override
    public void run() {
        decreaseHeatLevel();
    }

    //Method useSkill performs the ability of PolarExplorer to get the max number of players that could stand on the current iceberg.
    @Override
    public void useSkill(Map map, String dir) throws Exception {

        if ("north".equals(dir)) { //Up

            if (currentIceberg.y - 1 < 0) {
                System.out.println("Sorry, you are on the edge of the map, impossible to use skill");
            } else {
                System.out.println("Max num of players on iceberg: " + map.Icebergs[currentIceberg.y - 1][currentIceberg.x].getMaxNumOfPlayers());
            }


            }

        else if ("south".equals(dir)) { //Down
            if (currentIceberg.y +1>9) {
                System.out.println("Sorry, you are on the edge of the map, impossible to yse skill");
            } else {
             //   System.out.println("Current iceberg ["+currentIceberg.y+"]["+currentIceberg.x+"]");
                System.out.println("Max num of players on iceberg: " + map.Icebergs[currentIceberg.y +1][currentIceberg.x].getMaxNumOfPlayers());
            }


        }
        else if ("west".equals(dir)) { //Left
            if (currentIceberg.x-1<0) {
                System.out.println("Sorry, you are on the edge of the map, impossible to yse skill");
            } else {
                System.out.println("Max num of players on iceberg: " + map.Icebergs[currentIceberg.y][currentIceberg.x-1].getMaxNumOfPlayers());
            }


        }
        else if ("east".equals(dir)) { //Right
            if (currentIceberg.x+1>9) {
                System.out.println("Sorry, you are on the edge of the map, impossible to yse skill");
            } else {
                System.out.println("Max num of players on iceberg: " + map.Icebergs[currentIceberg.y][currentIceberg.x+1].getMaxNumOfPlayers());
            }

        }

    }
}
