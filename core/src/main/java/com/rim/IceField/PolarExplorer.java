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
        decreaseHeatLevel();
    }

    //Method useSkill performs the ability of PolarExplorer to get the max number of players that could stand on the current iceberg.

    /** the method shows how many players
     * can stand on an iceberg
     * @param map map of the game
     * @param dir direction
     * @return true id the acction is succesful
     * @throws Exception
     */
    @Override
    public boolean useSkill(Map map,String str) throws Exception
    {
        if(checkDir(str,map)&&"north".equals(str))
        {
            System.out.println("Max num of players on iceberg: " + map.Icebergs[currentIceberg.y - 1][currentIceberg.x].getMaxNumOfPlayers());
            return true;
        }
       else if(checkDir(str,map)&&"south".equals(str))
        {
            System.out.println("Max num of players on iceberg: " + map.Icebergs[currentIceberg.y + 1][currentIceberg.x].getMaxNumOfPlayers());
            return true;
        }
        else if(checkDir(str,map)&&"west".equals(str))
        {
            System.out.println("Max num of players on iceberg: " + map.Icebergs[currentIceberg.y][currentIceberg.x - 1].getMaxNumOfPlayers());
            return true;
        }
        else if(checkDir(str,map)&&"east".equals(str))
        {
            System.out.println("Max num of players on iceberg: " + map.Icebergs[currentIceberg.y][currentIceberg.x + 1].getMaxNumOfPlayers());
            return true;
        }
     else {
            System.out.println("Sorry, you are on the edge of the map, impossible to yse skill");
            return false;
        }
        return true;

}
}
