package game;


// https://rosettacode.org/wiki/Maze_generation#Java

public enum DIR {

        /* We are defining four directions and give them 
            * unique identifier (1, 2, 4, 8)
            * delta shift 

            For example : E (4, 1, 0) (EAST)
                (
                    4, --> unique identifier
                    1, --> increment of cordinates for X + 1
                    0, --> No changes for Y
                )

            To initialize each value of enum we are calling DIR() constructor where

            DIR(
                int bit, --> unique identifer
                int dx,  --> delta shift by X
                int dy)  --> delta shift by Y
        
        */
        N(1, 0, -1), S(2, 0, 1), E(4, 1, 0), W(8, -1, 0);
        
		public final int bit;
		public final int dx;
        public final int dy;
        
		public DIR opposite; // Used to identify opposite direction
 
		// use the static initializer to resolve forward references
		static {
			N.opposite = S;
			S.opposite = N;
			E.opposite = W;
			W.opposite = E;
		}
 
		private DIR(int bit, int dx, int dy) {
			this.bit = bit;
			this.dx = dx;
			this.dy = dy;
		}
};