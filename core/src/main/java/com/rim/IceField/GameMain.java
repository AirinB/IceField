package com.rim.IceField;

import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.game.BasicGame;
import org.mini2Dx.core.graphics.Graphics;

import java.util.ArrayList;
import java.util.Scanner;


public class GameMain extends BasicGame {
    public static final String GAME_IDENTIFIER = "com.rim.IceField";

    private Texture texture;

    @Override
    public void initialise() {
        texture = new Texture("mini2Dx.png");
    }

    @Override
    public void update(float delta) {
        //Test
    }

    @Override
    public void interpolate(float alpha) {

    }

    @Override
    public void render(Graphics g) {
        g.drawTexture(texture, 0f, 0f);
    }

    public static void main(String[] args) throws Exception {


        System.out.println("Introduce the number of players in the game: ");
        Scanner input = new Scanner(System.in);
        int numberOfPlayers = input.nextInt();
        ArrayList<PlayerBase> playersList = new ArrayList<PlayerBase>();

        for (int i = 0 ; i < numberOfPlayers; i++)
        {
            System.out.println("Press 1 to create a new Eskimo, 2 for a Polar Explorer");
            int m = input.nextInt();
            switch(m)
            {
                case 1:
                    Eskimo e = new Eskimo();
                    playersList.add(e);
                    System.out.println("New Eskimo added.");
                    break;
                case 2:
                    PolarExplorer pe = new PolarExplorer();
                    playersList.add(pe);
                    System.out.println("New Polar Explorer added.");
                    break;
            }
        }
        Game g1 = new Game();
        g1.getMap().generateItemsOnMap();
        //for(int i = 0;i<5;i++)
        // {
        //     System.out.println(g1.getMap().getIcebergs().get(i).getNum());
        // }

            for(int i = 0 ; i< numberOfPlayers ; i++) {

                g1.getMap().getIcebergs().get(0).Add_currentPlayers(playersList.get(i));
                playersList.get(i).currentIceberg = g1.getMap().getIcebergs().get(0);


        }

        g1.newGame(playersList);

        for (int i = 0; i < numberOfPlayers; i++)
        {
            for (int j = 0; j < 4; j++) {
                playersList.get(i).turn(); // Some more turn() log
                System.out.println("Choose an action by entering its corresponding number : 1 - Move | 2 - Use skill | 3 - Save Character | 4 - Use Item | 5 - Pick Item ");
                int m = input.nextInt();
                switch (m) {
                    case 1:
                        System.out.println("Press 1 to move up, 2 - right, 3 - down, 4 - left:");
                        int direction = input.nextInt();
                        if (direction == 1) {
                            playersList.get(i).move('N');

                               /*for(int k =0;k<5;k++)
                               {
                                   System.out.println(g1.getMap().getIcebergs().get(k).getNeighborIcebergs().get(0));
                               }*/

                            //At this point all players will move in one direction, to the1 next Iceberg in the list. More functionality in the future.
                            playersList.get(i).getCurrentIceberg().Remove_currentPlayers(playersList.get(i));

                            Iceberg newCurrent = playersList.get(i).getCurrentIceberg().getNeighborIcebergs().get(0);

                            playersList.get(i).getCurrentIceberg().getNeighborIcebergs().get(0).Add_currentPlayers(playersList.get(i));
                            //    list1.get(i).setCurrentIceberg(newCurrent);
                        }
                        break;

                    case 2: // Use skill
                        playersList.get(i).useSkill(playersList.get(i).getCurrentIceberg());
                        break;

                    case 3:// Save character

                        playersList.get(0).move('N');
                        playersList.get(0).move('N');  // to fall in a hole
                        playersList.get(0).move('N');
                        playersList.get(0).move('N');
                        playersList.get(0).fall();

                        playersList.get(1).move('N');// to get on neighboring iceberg and save the one from hole
                        playersList.get(1).pickItem(); // There is a rope on the second iceberg
                        playersList.get(1).move('N');
                        playersList.get(1).move('N');

                        playersList.get(1).SavePlayer(playersList.get(0));
                        break;

                    case 4:// use item
                        Food food = new Food();
                        Shovel shovel = new Shovel();
                        DivingSuit divingSuit = new DivingSuit();
                        playersList.get(0).inventory.addItem(food);
                        playersList.get(0).inventory.addItem(shovel);
                        playersList.get(0).inventory.addItem(divingSuit);


                        System.out.println("Choose which item you want to use: Diving Suit, Food, Shovel?");
                        Scanner scan= new Scanner(System.in);
                        String str=scan.nextLine();
                        scan.close();
                        playersList.get(0).inventory.getItem(str);
                        playersList.get(0).useItem( playersList.get(0).inventory.getItem(str));
                        System.out.println("The item was used");
                        // from the first player get the useItemFunction and pass the item that was choosed by the user
                        break;
                    case 5: //Pick item
                        int counter = 0;
                        while(counter < playersList.get(0).getCurrentIceberg().getAmountOfSnow()){
                            playersList.get(i).removeSnow();
                            counter++;
                        }
                        playersList.get(0).pickItem();
                        System.out.println("The item is picked");

                      break;

                    case 6: //Put on diving suit (The)
                        System.out.println("You are in water! Pless 1 for the scenario when you have a diving suit\n" +
                                " 2 for when you do not and there is no one to save you\n 3 The other player saves you ");
                        playersList.get(0).fall();
                        int userInput1 = input.nextInt();
                        if(userInput1 == 1){
                            System.out.println("You wear a diving suit");
                            DivingSuit divingSuit2 = new DivingSuit();
                            playersList.get(0).inventory.addItem(divingSuit2);
                            playersList.get(0).useItem( playersList.get(0).inventory.getItem("Diving Suit"));
                        }else if(userInput1 == 2){

                            playersList.get(0).die();
                            Game.GameOver(playersList);
                            return;
                        }else{
                            System.out.println("Player " + playersList.get(1).getTag() + " Press 1 if you want to save the other player");
                            int userInput2 = input.nextInt();
                            if(userInput2 == 1) playersList.get(1).SavePlayer( playersList.get(0));
                            else {
                                playersList.get(0).die();
                                g1.GameOver(playersList);
                                return;
                            }

                        }

                        break;

                    case 7: // Game over by putting together flare gun.
                        System.out.println("Choose: \n 1 for Win scenarion \n 2 for lose scenario");
                        int userInput2 = input.nextInt();
                        if(userInput2 == 1){
                            Gun gun = new Gun();
                            Flare flare = new Flare();
                            Charge charge = new Charge();
                            playersList.get(0).inventory.addItem(gun);
                            playersList.get(0).inventory.addItem(flare);
                            playersList.get(0).inventory.addItem(charge);
                            g1.GameOver(playersList);
                            return;
                        }else{
                            playersList.get(0).die();
                            g1.GameOver(playersList);
                            return;
                        }


                }

            }
        }


    }

}




