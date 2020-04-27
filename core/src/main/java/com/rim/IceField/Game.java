package com.rim.IceField;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Game {



    //Instance of Map, shared between the classes
    private Map map;
    private ArrayList<PlayerBase> players; // the players belong to the game
    private final int maxRounds = 10;
    private int currentRound;
    private boolean[] randomBlow; //if the array element is true, the wind would blow


    /**
     * @param players all the players of the game
     */
    public Game(ArrayList<PlayerBase> players, Map map ) {
        this.map = map;
        this.players = players;
        currentRound = 0;
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
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter command: \n");
        s = sc.nextLine();
     //   System.out.println("You entered String " + s);
        sc.close();
        return new ArrayList<String>(Arrays.asList(s.split(" ")));
    }


        /**
     * @return true if the game ends
     * it checks if there is a win or lose
     */
    //Static method GameOver for finishing the game.
    public boolean GameOver() {


        if (isGameLost()) return true;

        //Checking if all the conditions are preserved for winning the game
        if (isWin()) {
            System.out.println("Game Over! You Win");
            return true;
        }

        return false;
    }

        /**
     * @return true for lost game
     * if anyone is dead
     */
    public boolean isGameLost(){
        //Checking if any of the player died, then the game is lost
        for (PlayerBase player : players) {
            if (player.isDead) {
                System.out.println("Game lost!");
                return true;

            }
        }
        return false;
    }


        /**
     * @return true if the players win
     */
    public boolean isWin() {
        //Checking if all the conditions are preserved for winning the game

        boolean playersCheck = false;    //Boolean for check if all the players stay on the same iceberg
        //boolean flareGunCheck = false;   //Boolean to check if all the parts of flare gun are collected

        //MODIFIED TO 2 FOR TESTING PURPOSES
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                if (map.Icebergs[i][j].getCurrentPlayers().size() == players.size()
                        && !map.Icebergs[i][j].getType().equals("hole")) {
                    playersCheck = true;
                    break;
                }
            }
        }

        //Checking if flare gun was collected
        if (playersCheck) {
            if (Inventory.countGunItems == 3) {
                System.out.println("The flare gun is collected");
                return true;
            }
        }
        return false;
    }


        /**
     * @throws Exception if its not the
     * player's turn to make the move
     * this is the gameLoop, we iterate through each player
     * giving them the turn to make 4 moves
     */
    public void newGame() throws Exception {
        map.generateItemsOnMap();           //Generating items on map
        System.out.println("Game started!");
        while (currentRound < maxRounds) {
            if (randomBlow[currentRound + 1]) {
                System.out.println("The next round the Blizzard would come. Take care!");
            }
            if (randomBlow[currentRound]) {
                Blizzard.blow(players, map);
            }
            for (PlayerBase player : players) {
                ArrayList<String> input = processInput();
                player.isTurn = true;

                try {
                    Turn(player, input);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    player.isTurn = false;
                }

            }

            //there can be a limited number of rounds
        }
    }
        //New game when the inputs come from a file
        //When calling the inputs will be received by calling method loadInputs(..).
        public void newGame (ArrayList < PlayerBase > playersList, ArrayList < String > inputs) throws Exception {
            int count = 0;
            //the case when the inputs are not comming from a file
            map.generateItemsOnMap();           //Generating items on map
            System.out.println("Game started!");
            while (currentRound < maxRounds) {
                if (randomBlow[currentRound + 1]) {
                    System.out.println("The next round the Blizzard would come. Take care!");
                }
                if (randomBlow[currentRound]) {
                    Blizzard.blow(players, map);
                }
                for (PlayerBase player : players) {
                    ArrayList<String> fourInputs = new ArrayList<String>();
                    for (int i = 0; i < 4; i++) {
                        fourInputs.add(inputs.get(count));
                        count++;
                    }
                    Turn(player, fourInputs);
                }
                currentRound++;
            }

            //there can be a limited number of rounds
        }


    /**
     * @param player the player whos turn it is
     * @throws Exception if its not players turn, it trows a exception
     */

        public void Turn (PlayerBase player, ArrayList<String> userInput) throws Exception {
            if (!player.isTurn()) throw new Exception("It's not this player's turn");
            int round = 0;
            while (round < 4) {
                try {
                    if (UserInteraction(userInput, player)) {// the round increases only if the action was successful
                        round++;
                    }
                } catch (Exception e) {
                    //end of turn
                }
            }
            //After the player makes their 4 moves we need to give the turn to the next player
        }


    /**
     * @param input the input from the player,
     * the first element is the command and the
     * second is the argument
     * @param player the player that is doing the acction
     * @return true if the action was succsfull
     */
        //Will be continuously called in the game loop.
        public boolean UserInteraction (ArrayList < String > input, PlayerBase player)
        {
            boolean check;
            //For these 3 inputs that also require a direction
            if (input.get(0).equals("move") || input.get(0).equals("apply") || input.get(0).equals("save")) {
                //Check if the direction is valid
                if (validDirection(input)) {
                    if (input.get(0).equals("move")) {
                        try {
                            check = player.move(input.get(1), this.getMap());
                            System.out.println("Action accepted!");
                            isGameLost();
                            return check;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        //Instead of apply skill just apply.
                    } else if (input.get(0).equals("apply")) {

                        try {
                            check = player.useSkill(this.getMap(), input.get(1));
                            System.out.println("Action accepted!");
                            return check;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                    }

                    //Will distinguish between players based on their unique ID
                    else if (input.get(0).equals("save")) {
                        check = player.SavePlayer(input.get(1), input.get(2), map);
                        System.out.println("Action accepted!");
                        return check;
                    }

                }
            } else if (input.get(0).equals("use")) {
                //Check if the item could be used, therefore understanding if it is a valid item;

                check = player.useItem(input.get(1));
                System.out.println("Action accepted!");
                return check;


            } else if (input.get(0).equals("pick")) {
                System.out.println("Would you like to pick " + player.currentIceberg.getItem() + "press 1 for yes, 2 for no");
                Scanner input1 = new Scanner(System.in);
                int y = input1.nextInt();
                if (y == 1) {
                    try {
                        player.pickItem();
                        System.out.println("Action accepted!");
                        return true;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                return false;
            } else if (input.get(0).equals("remove snow")) {
                check = player.removeSnow();
                System.out.println("Action accepted!");
                return check;
            } else if (input.get(0).equals("fire")) {
                GameOver();
                System.out.println("Action accepted!");
                if (!GameOver()) {
                    System.out.println("You can't fire the gun, work more!");
                    return false;
                }
                return true;
            } else if (input.get(0).equals("inv")) {
                System.out.println("Action accepted!");
                printInventory(player);
            } else if (input.get(0).equals("help")) {
                System.out.println("Action accepted!");
                printHelp();
                return true;

            } else if (input.get(0).equals("info")) {
                int partsCollected = getPlayerInfo(player);
                System.out.println("Parts collected:" + partsCollected);
                return true;

            } else {
                System.out.println("There is no such command. Enter 'help' to see available actions");
                return false;
            }
            return false;
        }

    private Map getMap() {
            return map;
    }

    public boolean validDirection (ArrayList < String > input)
        {

            return input.get(1).equals("NORTH") || input.get(1).equals("SOUTH") || input.get(1).equals("EAST") || input.get(1).equals("WEST") ||
                    input.get(2).equals("NORTH") || input.get(2).equals("SOUTH") || input.get(2).equals("EAST") || input.get(1).equals("WEST");

        }

    /**
     * @param player the player
     * to which the inventory belongs to
     */
        private void printInventory (PlayerBase player){
            int numFood = 0;
            int charge = 0;
            int DivingSuit = 0;
            int Flare = 0;
            int Gun = 0;
            int rope = 0;
            int shovel = 0;
            for (int i = 0; i < player.getInventory().getItems().size(); i++) {
                if (player.getInventory().getItemAt(i).tag.equals("Food")) {
                    numFood++;
                } else if (player.getInventory().getItemAt(i).tag.equals("Charge")) {
                    charge++;
                } else if (player.getInventory().getItemAt(i).tag.equals("Flare")) {
                    Flare++;
                } else if (player.getInventory().getItemAt(i).tag.equals("DivingSuit")) {
                    DivingSuit++;
                } else if (player.getInventory().getItemAt(i).tag.equals("Gun")) {
                    Gun++;
                } else if (player.getInventory().getItemAt(i).tag.equals("rope")) {
                    rope++;
                } else if (player.getInventory().getItemAt(i).tag.equals("shovel")) {
                    shovel++;
                }
                System.out.println("You have: " + "Food - " + numFood + "Charge - " + charge + "Flare - " + Flare + "Diving suit - "
                        + DivingSuit + "Gun - " + Gun + "Rope - " + rope + "shovel" + shovel);
            }
        }

    /**
     * @param player the player who's turn is know
     * the method displays the inventory content
     * @return how many parts of the gun are collected
     */
        private int getPlayerInfo (PlayerBase player){
            System.out.println("Action accepted!");
            System.out.println("Heat level: " + player.getHeatLevel());
            System.out.println("Moves left: ");
            System.out.print(4 - player.numOfMoves);
            int partsCollected = 0;
            //for every player
            for (PlayerBase playerBase : players) {
                //we check every item in their inventory if it is a Gun, flare or charge
                for (int j = 0; j < playerBase.getInventory().getItems().size(); j++) {
                    if (playerBase.getInventory().getItems().get(j).getTag().equals("Gun") ||
                            playerBase.getInventory().getItems().get(j).getTag().equals("Flare") ||
                            playerBase.getInventory().getItems().get(j).getTag().equals("Charge")) {
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
        private void printHelp () {
            // Show all possible inputs
            System.out.println("Move NORTH / SOUTH / LEFT / RIGHT - will move the player in the corresponding direction.");
            System.out.println("use food / shovel / diving suit - will make use of the corresponding item if the user has it in the inventory.");
            System.out.println("pick - will pick the item from the iceberg that the player is on.");
            System.out.println("apply NORTH / SOUTH / WEST / EAST - will apply apply current player's skill in the corresponding direction");
            System.out.println("save (player ID) - will save the player with the given id if they are in trouble and the conditions for saving them are met.");
            System.out.println("remove snow - will remove one unit of snow from the current iceberg.");
            System.out.println("fire - will fire the gun if all conditions for firing it are met.");
            System.out.println("inv - will display all items in current player's inventory.");
            System.out.println("info - will display current player's heat level, number of moves left and the number of gun parts collected by the team.");
        }

        public ArrayList<ArrayList<String>> loadInputs (String path) throws FileNotFoundException {
            File inputs = new File(path);
            Scanner sc = new Scanner(inputs);
            ArrayList<ArrayList<String>> fromFile = new ArrayList<ArrayList<String>>();
            while (sc.hasNextLine()){
                String line = sc.nextLine();
                ArrayList<String> newLine = new ArrayList<String>(Arrays.asList(line.split(" ")));
                fromFile.add(newLine);
            }
            return fromFile;
        }

        public void writeToFile(String path, String output) throws IOException {
            FileWriter outputFile = new FileWriter(path, true);
            outputFile.write(output + "\n");
            outputFile.close();
        }


    //for testing at this stage

    public  boolean isWinForTest() {
        //Checking if all the conditions are preserved for winning the game

        boolean playersCheck = false;    //Boolean for check if all the players stay on the same iceberg
        //boolean flareGunCheck = false;   //Boolean to check if all the parts of flare gun are collected

        //Checking if all the players stand on the same iceberg and it's not a hole
        //MODIFIED TO 2 FOR TESTING PURPOSES
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                if (map.Icebergs[i][j].getCurrentPlayers().size() == players.size() && !map.Icebergs[i][j].getType().equals("hole"))
                    playersCheck = true;
            }
        }

        //Checking if flare gun was collected
        if (playersCheck) {
            if (Inventory.countGunItems == 3) {
                System.out.println("The flare gun is collected");
                return true;
            }
        }
        return false;
    }

    public  boolean GameOverForTest() {
        //Checking if all the conditions are preserved for winning the game
        if (isWinForTest()) {
            System.out.println("Game Over! You Win");
            return true;
        }

        return false;
    }
}



