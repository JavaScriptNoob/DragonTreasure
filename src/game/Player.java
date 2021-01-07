package game;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Player {
    private final String playerName;
    private int healthScore = 100;
    private int currentLocation = 1;
    private int playersAttack = 10;
    List<Items> inventory = new ArrayList<>();

    Scanner input = new Scanner(System.in);

    public Player(String playerName) {

        this.playerName = playerName;
        System.out.println("~^~^~'====>`~^~^~^~^~~^~^~'====>`~^~^~^~^~~^~^~'====>`~^~^~^~^~~^~^~'====>`~^~^~^~^~~^~^~'====>\n" +
                playerName + "welcome to Dragon Treasure. Your jorney is starting now!\nI wish you to enjoy of this game.\nYour health rate is equal to 100 and you have not any items in inventory." +
                "\nWhile moving around the map, you can set the desired direction by the sides of the world.\nKeep in mind that the dungeon is very dark and sometimes you will need to find out which way to go."+
                "\n~^~^~'====>`~^~^~^~^~~^~^~'====>`~^~^~^~^~~^~^~'====>`~^~^~^~^~~^~^~'====>`~^~^~^~^~~^~^~'====>");
    }

    public void move(HashMap<Integer, Rooms> createMap) {
        System.out.println("You are staying on the  "+createMap.get(currentLocation).getRoomName()+".\n"+createMap.get(1).getDescription()+"\n\"~^~^~'====>`~^~^~^~^~~^~^~'====>`~^~^~^~^~~^~^~'====>`~^~^~^~^~~^~^~'====>`~^~^~^~^~~^~^~'====>");
        String roomName = createMap.get(currentLocation).getRoomName();
        String roomDescription = createMap.get(currentLocation).getDescription();
        String scanDirection;


        scanDirection = input.nextLine().toLowerCase();
        System.out.println(scanDirection);
        if (healthScore < 100) {
            System.out.println("yor can to heal yourself");
        }
        //Север
        if (scanDirection.equals("north") && createMap.get(currentLocation).getN() == null) {
            System.out.println("No exit");

        } else if (scanDirection.equals("north") && createMap.get(currentLocation).getN() != null) {
            currentLocation = createMap.get(currentLocation).getN();
            System.out.println("Something is working. We are now at the  room " + currentLocation + " Room calls " + roomName + " " + roomDescription);
        } //ЮГ
        else if (scanDirection.equals("south") && createMap.get(currentLocation).getS() == null) {
            System.out.println("No exit");

        } else if (scanDirection.equals("south") && createMap.get(currentLocation).getS() != null) {
            currentLocation = createMap.get(currentLocation).getS();
            System.out.println("Something is working. We are now at the  room " + currentLocation + " Room calls " + roomName + " " + roomDescription);
        } //ЗАПАД
        else if (scanDirection.equals("west") && createMap.get(currentLocation).getW() == null) {
            System.out.println("No exit");
        } else if (scanDirection.equals("west") && createMap.get(currentLocation).getW() != null) {
            currentLocation = createMap.get(currentLocation).getW();
            System.out.println("Something is working. We are now at the  room " + currentLocation + " Room calls " + roomName + " " + roomDescription);
        }//Восток
        else if (scanDirection.equals("east") && createMap.get(currentLocation).getE() == null) {
            System.out.println("No exit");
        } else if (scanDirection.equals("east") && createMap.get(currentLocation).getE() != null) {
            currentLocation = createMap.get(currentLocation).getE();
            System.out.println("Something is working. We are now at the  room " + currentLocation + " Room calls " + roomName + " " + roomDescription);
        }

    }

    public void fight(Monsters monsters) {
        System.out.println("55");
        boolean loopIsRunning = true;

        while (loopIsRunning) {
            int playerDamage = new java.util.Random().nextInt(playersAttack);
            int monstersDamage = new java.util.Random().nextInt(monsters.getMonstersAttack());
            if (healthScore > 0 && monsters.getMonstersHealth() > 0) {
                System.out.println("Yoy ecouter monster. Its attack  you and take " + monstersDamage + " your health score eual now " + (healthScore - monstersDamage));
                healthScore = healthScore - monstersDamage;
                monsters.setMonstersHealth(monsters.getMonstersHealth() - playerDamage);
                System.out.println("Monster damage  =" + monstersDamage + "    MonsterAttack =" + monsters.getMonstersAttack() + "MonstersHwalth" + monsters.getMonstersHealth());
                System.out.println("Players Damage" + playerDamage + "  PlayersAttack " + playersAttack + "  PlayersHealth = " + healthScore);
                System.out.println(healthScore);
            } else if (healthScore <= 0) {
                System.out.println("You dead");

                loopIsRunning = false;
            } else if (monsters.getMonstersHealth() < 1) {
                System.out.println("monster dead");
                loopIsRunning = false;
            }


        }

    }

    public void getItems() {
        if (currentLocation == 4) {
            System.out.println("You get a sword");
            Items item1 = new Items("Sword", "of Damascus sword", 4);
            playersAttack += 5;
            System.out.println(playersAttack);
            inventory.add(item1);
            for (Items items : inventory) {
                System.out.println(inventory);  // Will invoke overrided `toString()` method
                System.out.println(inventory.get(0).getItemsName());
            }
        } else if (currentLocation == 6) {
            System.out.println("You get a potion");
            Items item2 = new Items("Potion", "Potion can heal you", 6);
            inventory.add(item2);
            System.out.println(inventory.get(0));
        } else if (currentLocation == 3) {
            System.out.println("You get a key");
            Items item3 = new Items("Key", "Key can open the door", 2);
            inventory.add(new Items("Key", "Key can open the door", 2));
            System.out.println(inventory);
            System.out.println(inventory.get(0).getItemsName());
        }
    }

    public void UseTheItem() {
        boolean arrayIsNotEmpty = inventory.isEmpty();
        if(arrayIsNotEmpty== false){
            for(Items items: inventory){
                if(items.getItemsName().equals("Potion")
                ){
                    System.out.println(items.getItemsDescription()+"  "+items.getItemsRoom());
                }

            }

        }
    }

    public int getHealthScore() {
        return healthScore;
    }

    public void setHealthScore(int healthScore) {
        this.healthScore = healthScore;
    }

    public int getCurrentLocation() {
        return currentLocation;
    }


}
