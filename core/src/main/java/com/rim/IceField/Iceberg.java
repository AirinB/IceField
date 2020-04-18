package com.rim.IceField;


import java.util.ArrayList;

public class Iceberg {
    private boolean isStable;
    private int num;
    //private int numOfPlayers; NO NEED, I JUST ADD THE LIST OF PLAYERS ON THE ICEBERG
    private String type;
    private int maxNumOfPlayers;
    private boolean hasIgloo;
    private ArrayList<PlayerBase> drowningPlayers = new ArrayList<PlayerBase>();
    private ArrayList<PlayerBase> currentPlayers = new ArrayList<PlayerBase>(); //List I added
    private ArrayList<Iceberg> neighborIcebergs = new ArrayList<Iceberg>();
    private int amountOfSnow;
    private ItemBase item;

    public Iceberg(boolean isStable, int num, String type, int maxNumOfPlayers, boolean hasIgloo, int amountOfSnow, ItemBase item) {
        this.isStable = isStable;
        this.num = num;
        this.type = type;
        this.maxNumOfPlayers = maxNumOfPlayers;
        this.hasIgloo = hasIgloo;
        this.amountOfSnow = amountOfSnow;
        this.item = item;

    }

    public void setNeighborIcebergs(ArrayList<Iceberg> arr) {
        neighborIcebergs.addAll(arr);
    }

    public void setItem(ItemBase item) {
        System.out.println("setItem()");
        this.item = item;
    }

    public ItemBase getItem() {

        System.out.println("getItem()");
        return this.item;
    }

    public void setAmountOfSnow(int n) {

        System.out.println("setAmountOfSnow()");
        this.amountOfSnow = n;
    }

    public int getAmountOfSnow() {
        System.out.println("getAmountOfSnow()");
        return this.amountOfSnow;
    }

    public ArrayList<Iceberg> getNeighborIcebergs() {

        System.out.println("getNeighborIcebergs()");
        return this.neighborIcebergs;
    }

    public ArrayList<PlayerBase> getCurrentPlayers() {

        System.out.println("getCurrentPlayers()");
        return this.currentPlayers;
    }


    public ArrayList<PlayerBase> getDrowningPlayers() {

        System.out.println("getDrowningPlayers()");
        return this.drowningPlayers;
    }

    public void setHasIgloo(boolean b) {

        System.out.println("setHasIgloo()");
        this.hasIgloo = b;
    }

    public boolean getHasIgloo() {

        System.out.println("getHasIgloo()");
        return this.hasIgloo;
    }

    public void setMaxNumOfPlayers(int n) {

        System.out.println("setMaxNumOfPlayers()");
        this.maxNumOfPlayers = n;
    }

    public int getMaxNumOfPlayers() {
        System.out.println("getMaxNumOfPlayers()");
        return maxNumOfPlayers;
    }

    public String getType() {
        System.out.println("getType()");
        return type;
    }

    public void setType(String str) {
        System.out.println("setType()");
        type = str;

    }

    public int getNum() {
        System.out.println("getNum()");
        return num;
    }

    public void setNum(int n) {

        System.out.println("setNum()");
        num = n;
    }

    public boolean getIsStable() {

        System.out.println("getIsStable()");
        return isStable;
    }

    public void setIsStable(boolean b) {

        System.out.println("setIsStable()");
        isStable = b;
    }

    public void Add_currentPlayers(PlayerBase p) {
        System.out.println("Add_currentPlayers()");
        currentPlayers.add(p);
        System.out.println("Player" + p.ID + " was added to the iceberg number: " + num);
    }

    public void Remove_currentPlayers(PlayerBase p) {
        System.out.println("Remove_currentPlayers()");
        currentPlayers.remove(p);
        System.out.println("Player was removed from  the iceberg number: " + num);
    }

    public void Add_drowningPlayers(PlayerBase p) {
        System.out.println("Add_drowningPlayers()");
        drowningPlayers.add(p);
        System.out.println("The " + p.ID + "has fallen in the water");

    }

    public void Remove_drowningPlayers(PlayerBase p) {
        System.out.println("Remove_drowningPlayers()");
        drowningPlayers.remove(p);
        System.out.println("The player" + p.ID + " is saved. You are a hero!");
    }


}