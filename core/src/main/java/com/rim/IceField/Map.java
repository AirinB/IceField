package com.rim.IceField;

import java.util.ArrayList;

public class Map {

      //private Blizzard blizzard;
      private ArrayList<Iceberg> icebergs;
      private ArrayList<PlayerBase> players;
      private ArrayList<ItemBase> items;



    public ArrayList<Iceberg> getIcebergs() {
        return icebergs;
    }

    public ArrayList<PlayerBase> getPlayers() {
        return players;
    }

    public ArrayList<ItemBase> getItems() {
        return items;
    }

//    public Blizzard getBlizzard(){
//        return blizzard;
//    }

    public void setIcebergs(ArrayList<Iceberg> icebergs) {
        this.icebergs = icebergs;
    }

    public void setItems(ArrayList<ItemBase> items) {
        this.items = items;
    }

    public void setPlayers(ArrayList<PlayerBase> players) {
        this.players = players;
    }

//    public void setBlizzard(Blizzard blizzard) {
//        this.blizzard = blizzard;
//    }



    public void generateItemsOnMap(ArrayList<PlayerBase> players){

          // - - - - - - - - - - - - - - - - - - - - - Blizzard - - - - - - - - - - - - - - - - - - - - -

          //this.blizzard = new Blizzard();
          //blizzard.Blow();

         // - - - - - - - - - - - - - - - - - - - - - Items - - - - - - - - - - - - - - - - - - - - -

         this.items = new ArrayList<ItemBase>();

         ItemBase shovel = new ItemBase("shovel");
         items.add(shovel);

         ItemBase rope = new ItemBase("rope");
         items.add(rope);

         ItemBase food = new ItemBase("food");
         items.add(food);

         ItemBase flare = new ItemBase("flare");
         items.add(flare);

         ItemBase divingSuit = new ItemBase("divingSuit");
         items.add(divingSuit);

         ItemBase gun = new ItemBase("gun");
         items.add(gun);

         System.out.println("Items were generated!");




          // - - - - - - - - - - - - - - - - - - - - - Icebergs - - - - - - - - - - - - - - - - - - - - -

          this.icebergs =  new ArrayList<Iceberg>();

          //Items have to be added later to create the test-cases
          Iceberg first = new Iceberg(true, players.size(), "stable", 20, false, 1, null  );
          icebergs.add(first);

          Iceberg second = new Iceberg(true, 0, "instable", 3, false, 2, null  );
          icebergs.add(second);

          Iceberg third = new Iceberg(true, 0, "stable", 20, false, 3, null  );
          icebergs.add(third);

          Iceberg forth = new Iceberg(true, 0, "hole", 0, false, 2, null  );
          icebergs.add(forth);

          Iceberg fifth = new Iceberg(true, 0, "stable", 20, false, 1, null );
          icebergs.add(fifth);

          System.out.println("Icebergs were generated!");

    }

}
