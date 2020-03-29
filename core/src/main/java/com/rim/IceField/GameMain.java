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

    public static void main(String[] args) {


        System.out.println("Introduce the number of players in the game: ");
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        ArrayList<PlayerBase> list1 = new ArrayList<PlayerBase>();

        for (int i = 0; i < n; i++) {
            System.out.println("Press 1 to create a new Eskimo, 2 for a Polar Explorer");
            int m = in.nextInt();
            switch (m) {
                case 1:
                    Eskimo e = new Eskimo();
                    list1.add(e);
                    System.out.println("New Eskimo added.");
                    break;
                case 2:
                    PolarExplorer pe = new PolarExplorer();
                    list1.add(pe);
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

        for (int i = 0; i < n; i++) {

            g1.getMap().getIcebergs().get(0).Add_currentPlayers(list1.get(i));
            list1.get(i).currentIceberg = g1.getMap().getIcebergs().get(0);


        }

        g1.newGame(list1);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 4; j++) {
                list1.get(i).turn(); // Some more turn() log
                System.out.println("Choose an action by entering its corresponding number : 1 - Move | 2 - Use skill | 3 - Save Character | 4 - Use Item | 5 - Pick Item ");
                int m = in.nextInt();
                switch (m) {
                    case 1:
                        System.out.println("Press 1 to move up, 2 - right, 3 - down, 4 - left:");
                        int direction = in.nextInt();
                        switch (direction) {
                            case 1:

                               /*for(int k =0;k<5;k++)
                               {
                                   System.out.println(g1.getMap().getIcebergs().get(k).getNeighborIcebergs().get(0));
                               }*/

                                //At this point all players will move in one direction, to the1 next Iceberg in the list. More functionality in the future.
                                list1.get(i).getCurrentIceberg().Remove_currentPlayers(list1.get(i));

                                Iceberg newCurrent = list1.get(i).getCurrentIceberg().getNeighborIcebergs().get(0);

                                list1.get(i).getCurrentIceberg().getNeighborIcebergs().get(0).Add_currentPlayers(list1.get(i));
                                //    list1.get(i).setCurrentIceberg(newCurrent);


                        }
                        break;


                }
            }
        }
    }
}




