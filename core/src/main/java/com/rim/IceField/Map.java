package com.rim.IceField;

import java.util.ArrayList;

public class Map {


      private ArrayList<Iceberg> icebergs;
      private ArrayList<ItemBase> items;



    public ArrayList<Iceberg> getIcebergs() {
        return icebergs;
    }


    public ArrayList<ItemBase> getItems() {
        return items;
    }


    public Map(){

        this.items = new ArrayList<ItemBase>();
        this.icebergs =  new ArrayList<Iceberg>();

    }



    public void generateItemsOnMap() {


         // - - - - - - - - - - - - - - - - - - - - - Items - - - - - - - - - - - - - - - - - - - - -

        //This is done for the test-cases needed in this iteration.

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



          //Items have to be added by Dinu to adjust it to the test-cases.


          Iceberg first = new Iceberg(true, 1, "stable", 20, false, 1, null  );
          icebergs.add(first);

          Iceberg second = new Iceberg(true, 2, "instable", 3, false, 2, null  );
          icebergs.add(second);

          Iceberg third = new Iceberg(true, 3, "stable", 20, false, 3, null  );
          icebergs.add(third);

          Iceberg forth = new Iceberg(true, 4, "hole", 0, false, 2, null  );
          icebergs.add(forth);

          Iceberg fifth = new Iceberg(true, 5, "stable", 20, false, 1, null );
          icebergs.add(fifth);

          System.out.println("Icebergs were generated!");

          ArrayList<Iceberg> neighbour1 = new ArrayList<Iceberg>();
          neighbour1.add(second);
          first.setNeighborIcebergs(neighbour1);

        ArrayList<Iceberg> neighbour2 = new ArrayList<Iceberg>();
        neighbour2.add(third);
        first.setNeighborIcebergs(neighbour2);

        ArrayList<Iceberg> neighbour3 = new ArrayList<Iceberg>();
        neighbour3.add(forth);
        first.setNeighborIcebergs(neighbour3);

        ArrayList<Iceberg> neighbour4 = new ArrayList<Iceberg>();
        neighbour4.add(fifth);
        first.setNeighborIcebergs(neighbour4);

        ArrayList<Iceberg> neighbour5 = new ArrayList<Iceberg>();
        neighbour5.add(first);
        first.setNeighborIcebergs(neighbour5);





    }

}
