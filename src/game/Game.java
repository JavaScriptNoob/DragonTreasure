package game;

import java.util.HashMap;
import java.util.Scanner;
public class Game {
    private String scanName;
    private HashMap<Integer, Rooms> createMap;
    Scanner inputName = new Scanner(System.in);

    public void start() {


       System.out.println(" (       (                            )       )                    (                      (              (           \n" +
               " )\\ )    )\\ )     (       (        ( /(    ( /(            *   )   )\\ )           (       )\\ )           )\\ )        \n" +
               "(()/(   (()/(     )\\      )\\ )     )\\())   )\\())         ` )  /(  (()/(   (       )\\     (()/(      (   (()/(   (    \n" +
               " /(_))   /(_)) ((((_)(   (()/(    ((_)\\   ((_)\\           ( )(_))  /(_))  )\\   ((((_)(    /(_))     )\\   /(_))  )\\   \n" +
               "(_))_   (_))    )\\ _ )\\   /(_))_    ((_)   _((_)         (_(_())  (_))   ((_)   )\\ _ )\\  (_))    _ ((_) (_))   ((_)  \n" +
               " |   \\  | _ \\   (_)_\\(_) (_)) __|  / _ \\  | \\| |         |_   _|  | _ \\  | __|  (_)_\\(_) / __|  | | | | | _ \\  | __| \n" +
               " | |) | |   /    / _ \\     | (_ | | (_) | | .` |           | |    |   /  | _|    / _ \\   \\__ \\  | |_| | |   /  | _|  \n" +
               " |___/  |_|_\\   /_/ \\_\\     \\___|  \\___/  |_|\\_|           |_|    |_|_\\  |___|  /_/ \\_\\  |___/   \\___/  |_|_\\  |___| \n" +
               "                                                                                                                     ");
       System.out.println("Hello Gamer.\nPlease write your name");
        scanName = inputName.nextLine();
        Player player = new Player(scanName);

        createMap = new HashMap<>();
        createMap.put(1, new Rooms("Suspension bridge", "You are standing in front of the entrance to the dungeon. \nThe ancient gates keep their sinister secrets \nbut retreat for you is like death to you.\nYou need to open them and enter.", null, null, null, 2));
        createMap.put(2, new Rooms("Long halls of eternal darkness", "You find yourself in a long gloomy corridor littered with the remains of daredevils of the past.\n Now it all depends on where you go.", 4, 3, 1, null));
        createMap.put(3, new Rooms("Infected wine cellars", "\nNear the door, you find a suspended silver key with an imperial double-headed eagle.\nYou decide to take it.", 2, null, null, 6));
        createMap.put(4, new Rooms("Armory of heroes of the past", "You discover a huge wine cellar shrouded in impenetrable cobwebs. \nPassing forward, you step on the decayed remains of a knight in a golden mask,\nnext to which lies a sword made of Damascus steel.\nAll this he will not need you take a sword and put on a mask.", null, 2, null, 5));
        createMap.put(5, new Rooms("The source of our fear", "You see a huge monster. He's running at you.", null, null, 4, 8));
        createMap.put(6, new Rooms("The warehouse of the cursed king", "You open the door slightly and see a storeroom in front of you. /nOn the floor is a huge layer of ash from decayed books and barrels. \nSuddenly you notice a phial marked with a red snake wrapped around the sword and realize that it is a potion.", 5, null, 3, 7));
        createMap.put(7, new Rooms("The Dragon's mine", "You discover mines with untold treasures", null, null, null, null));

        while (true) {
            if (player.getCurrentLocation() != 5 && player.getCurrentLocation() != 7) {
                player.move(createMap);
                System.out.println(player.getCurrentLocation());
                player.getItems();
            } else if (player.getCurrentLocation() == 5) {
                System.out.println("room with monster ");
                Monsters monsters = new Monsters("Super Goblin", 70, 8);
               player.fight(monsters);
               player.move(createMap);


            } else if (player.getCurrentLocation() == 7) {
                System.out.println("Room with dragon");
                Monsters dragon = new Monsters("Red Dragon", 85, 14);
                player.fight(dragon);
                player.move(createMap);

            }
        }


    }


}
