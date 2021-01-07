package game;

import java.util.Collections;
import java.util.Arrays;

// Rewritten and changed from original source: https://rosettacode.org/wiki/Maze_generation#Java

public class Maze {
	public final int x;
	public final int y;
	public final int[][] maze;
 
	public Maze(int x, int y) {
		this.x = x;
		this.y = y;
        maze = new int[this.x][this.y];
        
		generateMaze(0, 0);
	}
 
 
	private void generateMaze(int cx, int cy) {
        DIR[] dirs = DIR.values(); // dirs  collect {NORTH, SOUTH, EAST, WEST}
        
		Collections.shuffle(Arrays.asList(dirs)); // Used to perform the randomization of the Maze 
        
        for (DIR dir : dirs) {

            // Calculate neighbour cell cordinate

			int nx = cx + dir.dx;
            int ny = cy + dir.dy;
            
            // between is used to verify that we will not cross the border

			if (between(nx, x) && between(ny, y)
					&& (maze[nx][ny] == 0)) { // All borders of cell are in place == 0
                /* 
                    By default, each cell has all walls
                */
				maze[cx][cy] |= dir.bit;
				maze[nx][ny] |= dir.opposite.bit;
				generateMaze(nx, ny);
			}
		}
	}
 
	private static boolean between(int v, int upper) {
		return (v >= 0) && (v < upper);
	}

}