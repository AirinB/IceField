package com.rim.IceField;


import java.util.ArrayList;

public class Iceberg {
    private boolean isStable;
    public int x;
    public int y;
    //private int numOfPlayers; NO NEED, I JUST ADD THE LIST OF PLAYERS ON THE ICEBERG
    private String type;
    private int maxNumOfPlayers;
    private boolean hasIgloo;
    private ArrayList<PlayerBase> drowningPlayers = new ArrayList<PlayerBase>();
    private ArrayList<PlayerBase> currentPlayers = new ArrayList<PlayerBase>(); //List I added
    private int amountOfSnow;
    private ItemBase item;

    public Iceberg(boolean isStable, int num, String type, int maxNumOfPlayers, boolean hasIgloo, int amountOfSnow, ItemBase item) {
        this.isStable = isStable;
        //this.num = num;
        this.type = type;
        this.maxNumOfPlayers = maxNumOfPlayers;
        this.hasIgloo = hasIgloo;
        this.amountOfSnow = amountOfSnow;
        this.item = item;

    }


    public void setItem(ItemBase item) {
        this.item = item;
    }

    public ItemBase getItem() {
        return this.item;
    }

    public void setAmountOfSnow(int n) {
        this.amountOfSnow = n;
    }

    public int getAmountOfSnow() {
        return this.amountOfSnow;
    }


    public ArrayList<PlayerBase> getCurrentPlayers() {
        return this.currentPlayers;
    }


    public ArrayList<PlayerBase> getDrowningPlayers() {
        return this.drowningPlayers;
    }

    public void setHasIgloo(boolean b) {
        this.hasIgloo = b;
    }

    public boolean getHasIgloo() {
        return this.hasIgloo;
    }

    public void setMaxNumOfPlayers(int n) {
        this.maxNumOfPlayers = n;
    }

    public int getMaxNumOfPlayers() {
        return maxNumOfPlayers;
    }

    public String getType() {
        return type;
    }

    public void setType(String str) {
        type = str;

    }

   /* public int getNum() {
        System.out.println("getNum()");
        return num;
    }

    public void setNum(int n) {

        System.out.println("setNum()");
        num = n;
    }*/

    public boolean getIsStable() {
        return isStable;
    }

    public void setIsStable(boolean b) {
        isStable = b;
    }

    public void Add_currentPlayers(PlayerBase p) {
        currentPlayers.add(p);
        //  System.out.println("Player" + p.ID + " was added to the iceberg number: " + num);
    }

    public void Remove_currentPlayers(PlayerBase p) {
        currentPlayers.remove(p);
        // System.out.println("Player was removed from  the iceberg number: " + num);
    }

    public void Add_drowningPlayers(PlayerBase p) {
        drowningPlayers.add(p);
        System.out.println("The " + p.ID + "has fallen in the water");

    }

    public void Remove_drowningPlayers(PlayerBase p) {
        drowningPlayers.remove(p);
        System.out.println("The player" + p.ID + " is saved. You are a hero!");
    }


}