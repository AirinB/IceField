package com.rim.IceField;

//TODO close scanner

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Game {

    private enum STATE {
        MENU,
        GAME;
    }

    private STATE startMenu = STATE.MENU;

    //Instance of Map, shared between the classes
    private Map map ;
    private ArrayList<PlayerBase> players; // the players belong to the game
    private final int maxRounds = 10;
    private int currentRound;
    private boolean[] randomBlow; //if the array element is true, the wind would blow
    private Scanner sc;



    /**
     * @param players all the players of the game
     */
    public Game(ArrayList<PlayerBase> players) {
        this.map = new Map();
        this.players = players;
        currentRound = 0;
        sc = new Scanner(System.in);
        randomBlow = new boolean[maxRounds];
        Random objGenerator = new Random(1);
        for (int i = 1; i < 10; i++) {
            randomBlow[i] = objGenerator.nextBoolean();
        }
    }



    /**
     * @return userInputList
     * the first element is the name of the command
     * second element is the argument for the command
     */
    public ArrayList<String> processInput() {
        String s;
        System.out.println("Enter command: \n");
        s = sc.nextLine();
        //   System.out.println("You entered String " + s);
        return new ArrayList<String>(Arrays.asList(s.split(" ")));
    }

    /**
     * @return true if the game ends
     * it checks if there is a win or lose
     */
    //Static method GameOver for finishing the game.
    public boolean GameOver() throws IOException {


        if (isGameLost()) return true;

        //Checking if all the conditions are preserved for winning the game
        if (isWin()) {
            System.out.println("Game Over! You Win");
            String projectPath = System.getProperty("user.dir");
            writeToFile("Outputs.txt", "Game Over! You Win");
            return true;
        }

        return false;
    }

    /**
     * @return true for lost game
     * if anyone is dead
     */
    public boolean isGameLost() throws IOException {
        //Checking if any of the player died, then the game is lost
        for (PlayerBase player : players) {
            if (player.isDead) {
                System.out.println("Game lost!");
                for (PlayerBase playerBase:players) {
                    playerBase.timer.cancel();
                }
                return true;

            }
        }
        return false;
    }

    /**
     * @return true if the players win
     */
    public boolean isWin() throws IOException {
        //Checking if all the conditions are preserved for winning the game

        boolean playersCheck = false;    //Boolean for check if all the players stay on the same iceberg
        //boolean flareGunCheck = false;   //Boolean to check if all the parts of flare gun are collected

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {


                if (map.Icebergs[i][j].getCurrentPlayers().size() == players.size())
                {

                    playersCheck = true;
                    if(map.Icebergs[i][j].getType().equals("hole"))
                    {
                        playersCheck = false;
                    }
                    break;
                }
            }
        }

        //Checking if flare gun was collected
        if (playersCheck) {
            if (Inventory.countGunItems == 3) {
                System.out.println("The flare gun is collected");
                writeToFile("Outputs.txt", "The flare gun is collected");
                return true;
            }
        }
        return false;
    }





    /**
     * @throws Exception if its not the
     *                   player's turn to make the move
     *                   this is the gameLoop, we iterate through each player
     *                   giving them the turn to make 4 moves
     */
    public void newGame() throws Exception {
        //map.generateItemsOnMap();           //Generating items on map
        System.out.println("Game started!");

        while (currentRound < maxRounds) {
            if (randomBlow[currentRound + 1]) {
                System.out.println("The next round the Blizzard would come. Take care!");
            }
            if (randomBlow[currentRound]) {
               Blizzard.blow(players,this.getMap().getIcebergs());
            }


            for (PlayerBase player : players) {
                player.isTurn = true;

                try {
                    Turn(player);
                } catch (Exception e) {
                    e.printStackTrace();
                    if (e.getMessage().equals("End of Turn and end of Game")) {
                        writeToFile("Outputs.txt", "End of Turn and end of Game");
                            int count_drown = 0;
                            for (PlayerBase p: players) {
                                if(p.isDrowning)count_drown++;
                            }
                            if(count_drown==players.size()) throw new Exception("End of the Game");
                    }
                } finally {
                    player.isTurn = false;

                }

            }
            currentRound ++;
            //there can be a limited number of rounds
        }
    }



    //New game when the inputs come from a file
    //When calling the inputs will be received by calling method loadInputs(..).


    /**
     * @param player the player whos turn it is
     * @throws Exception if its not players turn, it trows a exception
     */

    public void Turn(PlayerBase player) throws Exception {
        if (!player.isTurn()) throw new Exception("It's not this player's turn");

        System.out.println("Its players  " + player.getTag() + " turn number " + player.getID());

        try
        {
            int count_drown = 0;
            for (PlayerBase p: players) {
                if(p.isDrowning)count_drown++;
            }
            if(count_drown==players.size()) throw new Exception("End of the Game");
           else if(player.isDrowning) throw new Exception("The player is drowning");

        }

        catch (Exception e) {
            if (e.getMessage().equals("The player is drowning")) {
                System.out.println("It's next players turn. Hurry!");
                return;

            }
            else  if (e.getMessage().equals("End of the Game")) {
                sc.close();
                throw new Exception("End of Turn and end of Game");
            }
        }
        int round = 0;
        while (round < 4) {
            try {
                System.out.println("---------------------------------------------------------------------\n\n");
                System.out.println("Player " + player.getTag()+" is on iceberg " + player.getCurrentIceberg().y + player.getCurrentIceberg().x );
                System.out.println("---------------------------------------------------------------------\n\n");
                ArrayList<String> userInput = processInput();
                if (UserInteraction(userInput, player)) {// the round increases only if the action was successful
                    round++;
                    if (isGameLost()) {
                        throw new Exception("End of the Game");
                    }
                }
                System.out.println("---------------------------------------------------------------------\n\n");
                System.out.println("Player " + player.getTag()+" is on iceberg " + player.getCurrentIceberg().y + player.getCurrentIceberg().x );
                System.out.println("---------------------------------------------------------------------\n\n");
            }
            catch (Exception e) {
                //end of turn
                System.out.println("---------------------------------------------------------------------\n\n");
                System.out.println("Player " + player.getTag()+" is on iceberg " + player.getCurrentIceberg().y + player.getCurrentIceberg().x );
                System.out.println("---------------------------------------------------------------------\n\n");

                if (e.getMessage().equals("End of the Game")) {
                    sc.close();
                    throw new Exception("End of Turn and end of Game");
                }
                if (e.getMessage().equals("The player is drowning")) {
                    System.out.println("It's next players turn. Hurry!");
                    return;
                }

            }
        }
        //After the player makes their 4 moves we need to give the turn to the next player
    }

    private void writeToFile(String s, String s1) {
    }

    public void TurnFromFile(PlayerBase player, ArrayList<ArrayList<String>> fileInputs) throws Exception {
        if (!player.isTurn()) throw new Exception("It's not this player's turn");
        int round = 0;
        while (round < 4) {
            try {
                if (UserInteraction(fileInputs.get(round), player)) {// the round increases only if the action was successful
                    round++;
                }
            } catch (Exception e) {
                //end of turn
            }
        }
    }


    /**
     * @param input  the input from the player,
     *               the first element is the command and the
     *               second is the argument
     * @param player the player that is doing the acction
     * @return true if the action was succsfull
     */
    //Will be continuously called in the game loop.
    public boolean UserInteraction(ArrayList<String> input, PlayerBase player) throws Exception {
        boolean check = false;
        //For these 3 inputs that also require a direction
        if (input.get(0).equals("move") || input.get(0).equals("apply") || input.get(0).equals("save")) {
            //Check if the direction is valid
            if (validDirection(input)) {
                if (input.get(0).equals("move")) {
                    try {
                        check = player.move(input.get(1));
                        if (player.isDrowning) {
                            throw new Exception("The player is in water");
                        }
                        if(check) System.out.println("Action accepted!");
                        isGameLost();
                        return check;
                    } catch (Exception e) {
                        if (e.getMessage().equals("The player is in water")) {
                            throw new Exception("The player is drowning");
                        }
                        e.printStackTrace();
                    }

                    //Instead of apply skill just apply.
                } else if (input.get(0).equals("apply")) {

                    try {
                        check = player.useSkill(input.get(1));
                      if(check)  System.out.println("Action accepted!");
                        return check;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                }

                //Will distinguish between players based on their unique ID
                else if (input.get(0).equals("save")) {
                    if (validDirection(input)) {
                        check = player.SavePlayer(input.get(1));
                       if(check) System.out.println("Action accepted!");

                    }
                    return check;
                }

            }
        } else if (input.get(0).equals("use")) {
            //Check if the item could be used, therefore understanding if it is a valid item;

            check = player.useItem(input.get(1));
           if(check) System.out.println("Action accepted!");
            return check;


        } else if (input.get(0).equals("pick")) {
            if (player.currentIceberg.getItem() == null) {
                System.out.println("There is no item on the iceberg");
                return false;
            }
            if(player.currentIceberg.getAmountOfSnow()!=0)
            {
                System.out.println("There is snow on the iceberg");
                return false;
            }
            System.out.println("Would you like to pick " + player.currentIceberg.getItem().getTag() + "press 1 for yes, 2 for no");
            writeToFile("Outputs.txt", "Would you like to pick " + player.currentIceberg.getItem().getTag() + "press 1 for yes, 2 for no");
            Scanner input1 = new Scanner(System.in);
            int y = input1.nextInt();
            if (y == 1) {
                try {
                    check = player.pickItem();
                 if(check)   System.out.println("Action accepted!");
                    return check;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return false;
        } else if (input.get(0).equals("remove") && input.get(1).equals("snow")) {
            check = player.removeSnow();
            if(check==true)
            System.out.println("Action accepted!");
            else  System.out.println("There is no snow on the iceberg");
            return check;
        } else if (input.get(0).equals("fire")) {
            GameOver();
           if( GameOver()) System.out.println("Action accepted!");
            if (!GameOver()) {
                System.out.println("You can't fire the gun, work more!");
                writeToFile("Outputs.txt", "You can't fire the gun, work more!");

                return false;
            }
            return true;
        } else if (input.get(0).equals("inv")) {
            System.out.println("Action accepted!");
            printInventory(player);
            return false;
        } else if (input.get(0).equals("help")) {
            System.out.println("Action accepted!");
            printHelp();
            return false;

        } else if (input.get(0).equals("info")) {
            int partsCollected = getPlayerInfo(player);
            System.out.println("Parts collected:" + partsCollected);
            writeToFile("Outputs.txt", "Parts collected:" + partsCollected);
            System.out.println("Action accepted!");
            return false;

        } else if (input.get(0).equals("exit")) {
            System.exit(0);
            System.out.println("Action accepted!");
            return false;
        } else if (input.get(0).equals("map")) {
            map.showMap();
            System.out.println("Action accepted!");
            return false;
        } else {
            System.out.println("There is no such command. Enter 'help' to see available actions");
            writeToFile("Outputs.txt", "There is no such command. Enter 'help' to see available actions");
            return false;
        }
        return false;
    }

    Map getMap() {
        return map;
    }

    public boolean validDirection(ArrayList<String> input) {
        return input.get(1).equals("north") || input.get(1).equals("south") ||
                input.get(1).equals("east") || input.get(1).equals("west") ||
                input.get(2).equals("north") || input.get(2).equals("south") ||
                input.get(2).equals("east") || input.get(1).equals("west");
    }

    /**
     * @param player the player
     *               to which the inventory belongs to
     */
    private void printInventory(PlayerBase player) throws IOException {
        int numFood = 0;
        int charge = 0;
        int DivingSuit = 0;
        int Flare = 0;
        int Gun = 0;
        int rope = 0;
        int shovel = 0;
        System.out.println("Your inventory:");
        writeToFile("Outputs.txt", "Your inventory:");
        if (player.getInventory().getItems().size() == 0) {
            System.out.println("Your inventory is empty");
            return;
        }

        for (int i = 0; i < player.getInventory().getItems().size(); i++) {
            if (player.getInventory().getItemAt(i).tag.equals("food")) {
                numFood++;
            } else if (player.getInventory().getItemAt(i).tag.equals("charge")) {
                charge++;
            } else if (player.getInventory().getItemAt(i).tag.equals("flare")) {
                Flare++;
            } else if (player.getInventory().getItemAt(i).tag.equals("diving suit")) {
                DivingSuit++;
            } else if (player.getInventory().getItemAt(i).tag.equals("gun")) {
                Gun++;
            } else if (player.getInventory().getItemAt(i).tag.equals("rope")) {
                rope++;
            } else if (player.getInventory().getItemAt(i).tag.equals("shovel")) {
                shovel++;
            }

        }
        System.out.println("You have: " + "Food - " + numFood + "\nCharge - " + charge + "Flare - " + Flare + "\nDiving suit - "
                + DivingSuit + "\nGun - " + Gun + "\nRope - " + rope + "\nShovel" + shovel);
    }

    /**
     * @param player the player who's turn is know
     *               the method displays the inventory content
     * @return how many parts of the gun are collected
     */
    private int getPlayerInfo(PlayerBase player) {
        System.out.println("Action accepted!");
        System.out.println("Heat level: " + player.getHeatLevel());
        System.out.println("Moves left: ");
        System.out.print(4 - player.numOfMoves);
        int partsCollected = 0;
        //for every player
        for (PlayerBase p : players) {
            //we check every item in their inventory if it is a Gun, flare or charge
            for (int j = 0; j < p.getInventory().getItems().size(); j++) {
                if (p.getInventory().getItems().get(j).getTag().equals("gun") ||
                        p.getInventory().getItems().get(j).getTag().equals("flare") ||
                        p.getInventory().getItems().get(j).getTag().equals("charge")) {
                    partsCollected++;
                    //break;
                }


            }
        }
        return partsCollected;
    }


    /**
     * prints the help message
     */
    private void printHelp() {
        // Show all possible inputs
        System.out.println("Move NORTH / SOUTH / WEST / EAST - will move the player in the corresponding direction.");
        System.out.println("use food / shovel / diving suit - will make use of the corresponding item if the user has it in the inventory.");
        System.out.println("pick - will pick the item from the iceberg that the player is on.");
        System.out.println("apply NORTH / SOUTH / WEST / EAST - will apply apply current player's skill in the corresponding direction");
        System.out.println("save (player ID) - will save the player with the given id if they are in trouble and the conditions for saving them are met.");
        System.out.println("remove snow - will remove one unit of snow from the current iceberg.");
        System.out.println("fire - will fire the gun if all conditions for firing it are met.");
        System.out.println("inv - will display all items in current player's inventory.");
        System.out.println("info - will display current player's heat level, number of moves left and the number of gun parts collected by the team.");
        System.out.println("exit - Will exit the game.");
        System.out.println("map - will show the map to the user.");
    }

}



