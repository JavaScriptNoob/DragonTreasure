package game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class GameMap {
    
    // TODO: made it private for the final build as they become public for debug purposeses

    public Maze maze;
    public Player player;    
    public HashMap<Integer, Rooms> specialRooms;
    private final int[][] specialRoomsCoordinates;

    public GameMap(String nickname, int h, int w) {
        this.player = new Player(nickname);
        this.maze = new Maze(w, h);
        
        Random random = new Random();
        Integer whoHasKey = random.nextInt(5);

        specialRooms = new HashMap<>();
        specialRooms.put(0, new Rooms("Suspension bridge", 
            "You are standing in front of the entrance to the dungeon. \n" + 
            "The ancient gates keep their sinister secrets \nbut retreat for you is like death to you.\nYou need to open them and enter.", 0 == whoHasKey));
        specialRooms.put(1, new Rooms("Long halls of eternal darkness", "You find yourself in a long gloomy corridor littered with the remains of daredevils of the past.\n Now it all depends on where you go.", 1 == whoHasKey));
        specialRooms.put(2, new Rooms("Infected wine cellars", "\nNear the door, you find a suspended silver key with an imperial double-headed eagle.\nYou decide to take it.", 2 == whoHasKey));
        specialRooms.put(3, new Rooms("Armory of heroes of the past", "You discover a huge wine cellar shrouded in impenetrable cobwebs. \nPassing forward, you step on the decayed remains of a knight in a golden mask,\nnext to which lies a sword made of Damascus steel.\nAll this he will not need you take a sword and put on a mask.", 
                    3 == whoHasKey));
        specialRooms.put(4, new Rooms("The source of our fear", "You see a huge monster. He's running at you.", 4 == whoHasKey));
        specialRooms.put(5, new Rooms("The warehouse of the cursed king", "You open the door slightly and see a storeroom in front of you. /nOn the floor is a huge layer of ash from decayed books and barrels. \nSuddenly you notice a phial marked with a red snake wrapped around the sword and realize that it is a potion.", 5 == whoHasKey));
        
        specialRooms.put(6, new Rooms("The Dragon's mine", "You discover mines with untold treasures", null, null, null, null));

        specialRoomsCoordinates = new int[7][2];
        for(int i = 0; i < 6; i++) {
            specialRoomsCoordinates[i][0] = 1 + random.nextInt(this.maze.x - 2);
            specialRoomsCoordinates[i][1] = 1 + random.nextInt(this.maze.y - 2);
        }

        specialRoomsCoordinates[6][0] = this.maze.x - 1;
        specialRoomsCoordinates[6][1] = this.maze.y - 1;
        
    }

    

    public int foundSpecialRoom(int x, int y) {
        for(int i = 0; i < 6; i++ ) {
            if ((x == specialRoomsCoordinates[i][0]) && (y == specialRoomsCoordinates[i][1]))
                return i;
        }
        return -1;
    }

    public char resolveRoomContent(int x, int y) {
        for(int i = 0; i < 6; i++) {
            if(specialRoomsCoordinates[i][0] == x && specialRoomsCoordinates[i][1] == y)
                return 'B';
        }

        if (specialRoomsCoordinates[6][0] == x && specialRoomsCoordinates[6][1] == y)
            return 'D';

        if (this.player.getX() == x && this.player.getY() == y) 
            return '☭';

        return ' ';
    }

    public String movePlayer(char dir) {
        Integer currentX = this.player.getX();
        Integer currentY = this.player.getY();

        /*
            As for the movement, we need to check two logical conditions:
                1) If player "trying" to leave map by crossing borders
                2) If player "trying" to go through the wall
            
            **Only** if both conditions are false ==> we accept the command

            Conditions 1) is easy to check as we need to compare current position 
                against minimum and maximum coordinate (0,0 vs maxX,maxY)
            
            Condition 2) requires to "unpack" the information regarding borders of the cell 
                and verify if there is no wall (position & WALL_CODE) == 1
            
                WALL_CODEs are the following (as described in DIR class):

                1 == NORTH
                2 == SOUTH
                4 == WEST
                8 == EAST
        */

        switch (dir) {
            case 'n':
                if ((currentY == 0) || ((this.maze.maze[currentX][currentY] & 1) == 0)) {
                    return "Passage blocked from the north by the wall";
                }
                this.player.updatedPosition(this.player.getX(), currentY - 1);
                break;
            case 's':                
                if ((currentY == this.maze.y - 1) || ((this.maze.maze[currentX][currentY] & 2) == 0)) {
                    return "Passage blocked from the south by the wall";
                }
                this.player.updatedPosition(currentX, currentY + 1);
                break;
            case 'w':
                if ((currentX == 0) || ((this.maze.maze[currentX][currentY] & 8) == 0)) {
                    return "Passage blocked from the west by the wall";
                }                    
                this.player.updatedPosition(currentX - 1, currentY);
                break;
            case 'e':
                if ((currentX == this.maze.x - 1) || ((this.maze.maze[currentX][currentY] & 4) == 0)) {
                    return "Passage blocked from the east by the wall";
                }         
                this.player.updatedPosition(currentX + 1, currentY);
                break;                                
            default:
                return "[ERROR] Illegal command";
        }

        return "Moving forward...";

    }

    public void display() {
        // https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println

        String ANSI_RESET = "\u001B[0m";
        String ANSI_BLACK = "\u001B[30m";
        String ANSI_RED = "\u001B[31m";
        String ANSI_GREEN = "\u001B[32m";
        String ANSI_YELLOW = "\u001B[33m";
        String ANSI_BLUE = "\u001B[34m";
        String ANSI_PURPLE = "\u001B[35m";
        String ANSI_CYAN = "\u001B[36m";
        String ANSI_WHITE = "\u001B[37m";
        
		for (int i = 0; i < this.maze.y; i++) {                        

			for (int j = 0; j < this.maze.x; j++) {
                System.out.print(ANSI_YELLOW);
                System.out.print((this.maze.maze[j][i] & 1) == 0 ? "+---" : "+   ");                                
			}
            System.out.println("+");
            System.out.print(ANSI_RESET);

			for (int j = 0; j < this.maze.x; j++) {
                // We are trying to identify, if anything is presented in the room

                char roomStatus = resolveRoomContent(j, i);
                String roomContent = " " + resolveRoomContent(j, i) + " ";
                
                System.out.print(ANSI_YELLOW);
                System.out.print((this.maze.maze[j][i] & 8) == 0 ? '|' : ' ');
                System.out.print(ANSI_RESET);

                System.out.print(ANSI_RED);
                System.out.print(roomContent);
                System.out.print(ANSI_RESET);
            }
            System.out.print(ANSI_YELLOW);
            System.out.println("|");
            System.out.print(ANSI_RESET);
		}
		for (int j = 0; j < this.maze.x; j++) {
			System.out.print("+---");
		}
		System.out.println("+");
	}
}