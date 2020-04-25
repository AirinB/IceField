package com.rim.IceField;

import org.mini2Dx.core.game.BasicGame;
import org.mini2Dx.core.graphics.Graphics;

import java.util.ArrayList;
import java.util.Scanner;


public class GameMain extends BasicGame {
    public static final String GAME_IDENTIFIER = "com.rim.IceField";


    //private Texture texture;

    @Override
    public void initialise() {
        //texture = new Texture("mini2Dx.png");
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
        //g.drawTexture(texture, 0f, 0f);
    }

    public static void main(String[] args) throws Exception {


        System.out.println("Introduce the number of players in the game: ");
        Scanner input = new Scanner(System.in);
        int numberOfPlayers = input.nextInt();
        //List of players.
        ArrayList<PlayerBase> playersList = new ArrayList<PlayerBase>();
        // Filling the list of players with a number of players specified by the user
        for (int i = 0; i < numberOfPlayers; i++) {
            System.out.println("Press 1 to create a new Eskimo, 2 for a Polar Explorer");
            int m = input.nextInt();
            switch (m) {
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
        //Will create the items on icebergs and the icebergs themeselves. We have defined icebergs and items on the in class Map to use for the use cases.
        g1.getMap().generateItemsOnMap();
        //putting all created icebergs in a list
        //for(int i = 0;i<5;i++)
        // {
        //     System.out.println(g1.getMap().getIcebergs().get(i).getNum());
        // }

        for (int i = 0; i < numberOfPlayers; i++) {
            //Here we put all players on the first iceberg (the one on possition 0 in the list).
            g1.getMap().Icebergs[0][0].Add_currentPlayers(playersList.get(i));
            //Set the first iceberg as Player's iceberg for every player. The plaeyr needs to know which iceberg they are currently on.
            playersList.get(i).currentIceberg = g1.getMap().Icebergs[0][0];


        }
        //We start the game. This method will actually generate the items on map.
        g1.newGame(playersList);
        Scanner in = new Scanner(System.in);

        for (int i = 0; i < numberOfPlayers; i++) {
            int s = 0;
            //since every player has 4 turns
            for (int j = 0; j < 4; j++) {

                playersList.get(i).turn(); // Some more turn() log

                System.out.println("Choose an scenario by entering its corresponding number :\n 1 - Move\n | 2 - Use skill\n | 3 - Save Character\n | " +
                        "4 - Use Item \n| 5 - Pick Item\n | 6 - in water\n | 7 - end of game\n | 8 - Fall because iceberg is unstable\n |" +
                        " 9 - Blizzard blows \n | 9 - Blizzard blows\n | 10 - Blizzart kills\n | 11 - Turn\n");


                System.out.println("Choose an scenario by entering its corresponding number :\n 1 - Move | 2 - Use skill | 3 - Save Character | " +
                        "4 - Use Item | 5 - Pick Item | 6 - in water | 7 - end of game | 8 - Fall because iceberg is unstable | 9 - Blizzard blows | 10 - Blizzard kills| 11 - Turn");

                int m = input.nextInt();
                switch (m) {
                    case 1:

                        String str = in.nextLine();
                        //Method move in playerBase, gets the direction of movement and actually changes the possition of the character from current iceberg to neighboring iceberg
                        playersList.get(i).move(str, g1.getMap());
                        System.out.println("------------------------------------------------------------------END OF TEST CASE------------------------------------------------------------------");
                        return;

                    case 2: // Use skill
                        //Depending on whether it's a eskimo or polar Explorer they will use the corresponding skill.
                        //We send current iceberg for the eskimo to check if it has an igloo and for the explorer to check its neighbours.
                        str = in.nextLine();
                        playersList.get(i).useSkill(g1.getMap(), str);
                        System.out.println("------------------------------------------------------------------END OF TEST CASE------------------------------------------------------------------");
                        return;

                    case 3:// Save character
                        //We make 4 moves since the 5th iceberg is a whole and we need the player to fall for the test case to be able to save them
                       /* playersList.get(0).move(1, g);
                        playersList.get(0).move(1);
                        playersList.get(0).move(1);
                        playersList.get(0).move(1);
                        //playersList.get(0).fall();

                        playersList.get(1).move(1);// to get on neighboring iceberg and save the one from hole
                        playersList.get(1).removeSnow();
                        playersList.get(1).pickItem(); // There is a rope on the second iceberg
                        playersList.get(1).move(1);
                        playersList.get(1).move(1);
                        //Second player will save the first one after picking a rope and when they get on the neighboring iceberg.
                        playersList.get(1).SavePlayer(playersList.get(0));
                        System.out.println("------------------------------------------------------------------END OF TEST CASE------------------------------------------------------------------");
                        return;*/


                    case 4:// use item
                        Food food = new Food();
                        Shovel shovel = new Shovel();
                        DivingSuit divingSuit = new DivingSuit();
                        //Adds item to player's inventory
                        playersList.get(0).inventory.addItem(food);
                        playersList.get(0).inventory.addItem(shovel);
                        playersList.get(0).inventory.addItem(divingSuit);


                        System.out.println("Choose which item you want to use: Diving Suit, Food, Shovel?");
                        Scanner scan = new Scanner(System.in);
                        String sstr = scan.nextLine();
                        scan.close();
                        //Get's the item that the player wants to use from the inventory
                        playersList.get(0).inventory.getItem(sstr);
                        //Uses the item
                        playersList.get(0).useItem(playersList.get(0).inventory.getItem(sstr));
                        System.out.println("The item was used");
                        // from the first player get the useItemFunction and pass the item that was choosed by the user
                        System.out.println("------------------------------------------------------------------END OF TEST CASE------------------------------------------------------------------");
                        return;
                    case 5: //Pick item
                        int counter = 0;
                        //we need to remove all the snow from an iceberg in order to be able to pick the item.
                        while (counter < playersList.get(0).getCurrentIceberg().getAmountOfSnow()) {
                            playersList.get(0).removeSnow();
                            counter++;
                        }
                        playersList.get(0).pickItem();
                        System.out.println("The item is picked");

                        System.out.println("------------------------------------------------------------------END OF TEST CASE------------------------------------------------------------------");
                        return;

                    case 6: //Put on diving suit (The)
                        System.out.println(" Pless 1 for the scenario when you have a diving suit\n" +
                                " 2 for when you do not and there is no one to save you\n 3 The other player saves you ");
                        playersList.get(0).fall();
                        int userInput1 = input.nextInt();
                        if (userInput1 == 1) {
                            System.out.println("You wear a diving suit");
                            DivingSuit divingSuit2 = new DivingSuit();
                            playersList.get(0).inventory.addItem(divingSuit2);
                            playersList.get(0).useItem(playersList.get(0).inventory.getItem("Diving Suit"));
                            System.out.println("------------------------------------------------------------------END OF TEST CASE------------------------------------------------------------------");
                            return;
                        } else if (userInput1 == 2) {
                            //Player dies
                            playersList.get(0).die();
                            //if a player dies the game is over and they lose
                            Game.GameOver();
                            System.out.println("------------------------------------------------------------------END OF TEST CASE------------------------------------------------------------------");
                            return;
                        } else {
                            System.out.println("Player " + playersList.get(1).getTag() + " Press 1 if you want to save the other player");
                            int userInput2 = input.nextInt();
                            if (userInput2 == 1) playersList.get(1).SavePlayer(playersList.get(0));
                            else {
                                playersList.get(0).die();
                                g1.GameOver();
                                System.out.println("------------------------------------------------------------------END OF TEST CASE------------------------------------------------------------------");
                                return;
                            }

                        }

                        System.out.println("------------------------------------------------------------------END OF TEST CASE------------------------------------------------------------------");
                        return;

                    case 7: // Game over by putting together flare gun.
                        System.out.println("Choose: \n 1 for Win scenarion \n 2 for lose scenario");
                        int userInput2 = input.nextInt();
                        if (userInput2 == 1) {
                            Gun gun = new Gun();
                            Flare flare = new Flare();
                            Charge charge = new Charge();
                            playersList.get(0).inventory.addItem(gun);
                            playersList.get(0).inventory.addItem(flare);
                            playersList.get(0).inventory.addItem(charge);
                            g1.GameOver();
                            System.out.println("------------------------------------------------------------------END OF TEST CASE------------------------------------------------------------------");
                            return;
                        } else {
                            playersList.get(0).die();
                            g1.GameOver();
                            System.out.println("------------------------------------------------------------------END OF TEST CASE------------------------------------------------------------------");
                            return;
                        }
                   /* case 8: //Instable iceberg
                        try {
                            playersList.get(0).move(1);
                            playersList.get(0).move(1);  //to stay on unstable iceberg
                            playersList.get(0).move(1);

                            playersList.get(1).move(1);
                            playersList.get(1).move(1);
                            playersList.get(1).move(1);
                        } catch (Exception e)             //This exception is thrown ny Move(). When maxNumof players on iceberg < curren - it becomes a hole ----> we notify everybody that he falls
                        {
                            //for every player on the 4th iceberg
                            for (PlayerBase player : g1.getMap().getIcebergs().get(3).getCurrentPlayers()) {
                                //player falls in water
                                player.fall();
                                playersList.get(0).die();
                                playersList.get(1).die();
                                g1.GameOver();
                                System.out.println("------------------------------------------------------------------END OF TEST CASE------------------------------------------------------------------");
                                return;
                            }
                        }*/
                    case 9: //Blizzard blows
                        //method blow gets the current iceberg on which it blows and the list of players to see who is affected
                        Blizzard.blow(playersList, g1.getMap());
                        //Displays the heatlevel of the first player
                        System.out.println(playersList.get(0).getHeatLevel());
                        System.out.println("------------------------------------------------------------------END OF TEST CASE------------------------------------------------------------------");
                        return;

                    case 10:
                        System.out.println("Please, specify which character was created the first, because test case will be run for him");
                        System.out.println("1. Eskimos");
                        System.out.println("2. Polar Explorer");
                        int userInput3 = input.nextInt();
                        if (userInput3 == 2) {
                            for (int z = 0; z < 4; z++) {
                                Blizzard.blow(playersList, g1.getMap());

                            }


                        }
                        if (userInput3 == 1) {
                            for (int z = 0; z < 5; z++) {
                                Blizzard.blow(playersList, g1.getMap());

                            }
                        }
                        g1.GameOver();
                        System.out.println("------------------------------------------------------------------END OF TEST CASE------------------------------------------------------------------");
                        return;

                 /*   case 11:   //Imitation of Turn(). Imitation of 4 actions of 1st Player and Turn goes to the 2nd
                        playersList.get(i).turn();
                        Food food1 = new Food();
                        Food food2 = new Food();
                        Food food3 = new Food();
                        Food food4 = new Food();
                        playersList.get(0).inventory.addItem(food1);
                        playersList.get(0).inventory.addItem(food2);
                        playersList.get(0).inventory.addItem(food3);

                        for(int l = 0; l<3;l++)
                        {
                           playersList.get(0).decreaseHeatLevel();

                        }

                            playersList.get(0).useItem(food1);
                        playersList.get(0).useItem(food2);
                        System.out.println("Number of moves left :"+  playersList.get(0).numOfMoves);
                        playersList.get(0).useItem(food3);
                        System.out.println("Number of moves left :"+  playersList.get(0).numOfMoves);
                        playersList.get(0).useItem(food4);
                        System.out.println("Number of moves left :"+  playersList.get(0).numOfMoves);
                        playersList.get(0).move(1);
                        System.out.println("Number of moves left :"+  playersList.get(0).numOfMoves);
                        System.out.println("Num of moves "+playersList.get(0).getNumOfMoves());
                        playersList.get(1).turn();
                        System.out.println("Num of moves "+playersList.get(1).getNumOfMoves());
                        System.out.println("------------------------------------------------------------------END OF TEST CASE------------------------------------------------------------------");
                        return;
                }*/

                }


            }
        }


    }
}






