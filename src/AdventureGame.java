import game.GameMap;
import game.Monsters;

import java.util.Random;
import java.util.Scanner;

public class AdventureGame {

    public static void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }  


    public static void main(String[] args) {

        GameMap map = new GameMap("T1mYr", 8, 8);
        map.display();

        Scanner input = new Scanner(System.in);
        String scanDirection;

        boolean keyFound = false;

        while (true) {
            System.out.print("> : ");

            scanDirection = input.nextLine().toLowerCase();
            char command = scanDirection.charAt(0);
            
            if (command == 'x') break;

            // clean up to redraw
            clearScreen();

            // Update position of the player
            String status = map.movePlayer(command);
            map.display();
            System.out.println("< : " + status);

            // Getting new coordinates of the player based on command
            int pX = map.player.getX();
            int pY = map.player.getY();
            
            // Trying to find if it empty or not
            char roomStatus = map.resolveRoomContent(pX, pY);
            System.out.println("room status: ");

            switch (roomStatus) {
                case 'B':
                    int bossID = map.foundSpecialRoom(pX, pY); 

                    // map.specialRooms.get(bossID) is used to access Rooms class
                    if (map.specialRooms.get(bossID).boss.isDead())                                       {
                        System.out.println("\nMonster is dead");
                        break;
                    }

                    System.out.println("\nYou encountered moster:" + map.specialRooms.get(bossID).boss.getMonstersName());
                    if(!map.player.fight(map.specialRooms.get(bossID).boss)) {                        
                        System.out.println("\nYou was killed...");
                        return;
                    }
                    

                    // To not make it double fight
                    map.specialRooms.get(bossID).boss.markDead();
                    
                    // We don't equip the loot if its attack is lower then ours

                    System.out.println("\tCONGRATS!!! You found a loot: " + map.specialRooms.get(bossID).lootName);
                    if (map.specialRooms.get(bossID).lootAttack < map.player.getAttack()) {
                        System.out.println("\t\tBut it doesn't worse your attention: " + map.specialRooms.get(bossID).lootAttack.toString() + " vs. " +
                        map.player.getAttack());
                    } else {
                        System.out.println("\t\tThis sword will suit you well!");
                        System.out.println("\t\tYou new Attack value == " + map.specialRooms.get(bossID).lootAttack);

                        map.player.setAttack(map.specialRooms.get(bossID).lootAttack);
                    }


                    // We give player a potion based on a random value + current HP value

                    Random random = new Random();
                    int index = random.nextInt(100);
                    
                    if (map.player.getHealthScore() < 50) {
                        if (index < 70) {
                            map.player.givePotion();
                        }
                    // if HP > 50
                    } else {
                        if (index < 30) {
                            map.player.givePotion();
                        }
                    }   

                    // If we found it - we can fight a dragon
                    if ((map.specialRooms.get(bossID).hasKey) && (keyFound == false)) {
                        keyFound = true;
                        System.out.println("YOU FOUND a KEY --> GO FIGHT (D)RAGON!!!");
                    }

                    break;
                case 'D':
                    if (keyFound == false) {
                        System.out.println("Door is looked ==> You need a key!");
                        break;
                    }

                    Monsters Dragon = new Monsters("RED DRAGON", 100, 20);
                    if(map.player.fight(Dragon) == false) {
                        System.out.println("You was burned to ashes by the dragon 🐉...\nTHIS IS THE END OF YOUR JOURNEY");
                        return;
                    }

                    System.out.println("YOU WON!!!");
                    return;

            }            
        }        

        input.close();
    }
}
