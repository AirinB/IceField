package com.rim.IceField;


import java.util.List;

public class Iceberg {
    private boolean isStable;
    private int num;
    //private int numOfPlayers; NO NEED, I JUST ADD THE LIST OF PLAYERS ON THE ICEBERG
    private String type;
    private int maxNumOfPlayers;
    private boolean hasIgloo;
    private List<PlayerBase> drowningPlayers;
    private List<PlayerBase> currentPlayers; //List I added
    private List<Iceberg> neighborIcebergs;
    private int amountOfSnow;
    private ItemBase item;



    public void setItem(ItemBase item)
    {
        this.item = item;
    }
    public ItemBase getItem()
    {
        return this.item;
    }
    public void setAmountOfSnow(int n)
    {
        this.amountOfSnow = n;
    }

    public int getAmountOfSnow()
    {
        return this.amountOfSnow;
    }

    public List<Iceberg> getNeighborIcebergs()
    {
        return this.neighborIcebergs;
    }

    public List<PlayerBase> getCurrentPlayers()
    {
        return this.currentPlayers;
    }


    public List<PlayerBase> getDrowningPlayers()
    {
        return this.drowningPlayers;
    }

    public void setHasIgloo(boolean b)
    {
        this.hasIgloo = b;
    }
    public boolean getHasIgloo()
    {
        return this.hasIgloo;
    }

    public void setMaxNumOfPlayers(int n)
    {
        this.maxNumOfPlayers = n;
    }
    public int getMaxNumOfPlayers()
    {
        return maxNumOfPlayers;
    }
public String getType()
{
    return type;
}
public void setType(String str)
{
    type = str;

}
    public int getNum()
    {
        return num;
    }
    public void setNum(int n )
    {
        num = n;
    }
    public boolean getIsStable()
    {
        return isStable;
    }

    public void setIsStable(boolean b)
    {
        isStable=b;
    }

 public void Add_currentPlayers(PlayerBase p)
 {
     currentPlayers.add(p);
     System.out.println("Player was added to the iceberg number: "+num);
 }

 public void Remove_currentPlayers(PlayerBase p)
 {
     currentPlayers.remove(p);
     System.out.println("Player was removed from  the iceberg number: "+num);
 }

 public void Add_drowningPlayers(PlayerBase p)
 {
     drowningPlayers.add(p);
     System.out.println("The "+p.ID+"has fallen in the water");

 }
 public void Remove_drowningPlayers(PlayerBase p)
 {
     drowningPlayers.remove(p);
     System.out.println("The player"+p.ID+" is saved. You are a hero!");
 }



}