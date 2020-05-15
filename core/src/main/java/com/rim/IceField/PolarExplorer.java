package com.rim.IceField;

//Po;arExplorer class
public class PolarExplorer extends PlayerBase {


    //Constructor
    public PolarExplorer() {
        super();
        this.tag = "PolarExplorer";    //Setting the name
        this.heatLevel = 4;            //Setting number of heat units
    }

    /**
     * decreases the heat
     * level every few seconds
     */
    @Override
    public void run() {
        try {
            decreaseHeatLevel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Method useSkill performs the ability of PolarExplorer to get the max number of players that could stand on the current iceberg.

    /**
     * the method shows how many players
     * can stand on an iceberg
     *
     * @param dir direction
     * @return true id the acction is succesful
     * @throws Exception
     */
    @Override
    public boolean useSkill(String dir) throws Exception {
        Map map = game.getMap();
        if ("north".equals(dir)) { //Up

            if (currentIceberg.y - 1 < 0) {
                System.out.println("Sorry, you are on the edge of the map, impossible to use skill");
                return false;
            } else {
                System.out.println("Max num of players on iceberg: " + map.Icebergs[currentIceberg.y - 1][currentIceberg.x].getMaxNumOfPlayers());
            }


        } else if ("south".equals(dir)) { //Down
            if (currentIceberg.y + 1 > 9) {
                System.out.println("Sorry, you are on the edge of the map, impossible to yse skill");
                return false;
            } else {
                //   System.out.println("Current iceberg ["+currentIceberg.y+"]["+currentIceberg.x+"]");
                System.out.println("Max num of players on iceberg: " + map.Icebergs[currentIceberg.y + 1][currentIceberg.x].getMaxNumOfPlayers());
            }


        } else if ("west".equals(dir)) { //Left
            if (currentIceberg.x - 1 < 0) {
                System.out.println("Sorry, you are on the edge of the map, impossible to yse skill");
                return false;
            } else {
                System.out.println("Max num of players on iceberg: " + map.Icebergs[currentIceberg.y][currentIceberg.x - 1].getMaxNumOfPlayers());
            }


        } else if ("east".equals(dir)) { //Right
            if (currentIceberg.x + 1 > 9) {
                System.out.println("Sorry, you are on the edge of the map, impossible to yse skill");
                return false;
            } else {
                System.out.println("Max num of players on iceberg: " + map.Icebergs[currentIceberg.y][currentIceberg.x + 1].getMaxNumOfPlayers());
            }

        }
        return true;

    }
}
