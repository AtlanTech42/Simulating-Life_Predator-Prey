package sim;

import java.util.List;
import java.util.Random;

public class Predator{

    public static int[][] act(int[][] oldWorld, int[][] newWorld, int x, int y) {

        // Try to eat prey
        List<int[]> preyNearby = World.getAdjacentPrey(x, y);
        if (!preyNearby.isEmpty()) {
            int[] preyPos = preyNearby.get(new Random().nextInt(preyNearby.size()));
            newWorld[ preyPos[0] ][ preyPos[1] ] = 0;  // eat prey and moves into spot
        } else {
            List<int[]> preySpots = World.getClosestPrey(x, y);
            List<int[]> moves = World.getAdjacentEmpty(newWorld, x, y);

            if (!preySpots.isEmpty()) {
                // Get one nearby prey
                int[] target = preySpots.get(new Random().nextInt(preySpots.size())); // Random prey

                // Direction vector toward prey
                int dx = Integer.compare(target[0], x); // -1, 0, or 1
                int dy = Integer.compare(target[1], y); // -1, 0, or 1

                int nx;
                int ny;
                if ( Math.abs(dx) == Math.abs(dy)) {
                    nx = x + dx;
                    ny = y;
                } else {
                    nx = x;
                    ny = y + dy;
                }

                // Check if the spot is empty before moving
                if (nx >= 1 && ny >= 1 && nx < World.WIDTH - 1 && ny < World.HEIGHT - 1 &&
                   (oldWorld[ nx ][ ny ] == 0 || oldWorld[ nx ][ ny ] == 1 )) {
                    newWorld[  x ][  y ] = 0;
                    newWorld[ nx ][ ny ] = 1;
                }
            } else if (!moves.isEmpty()) {
                int[] move = moves.get(new Random().nextInt(moves.size()));
                newWorld[  x ][  y ] = 0;
                newWorld[move[0]][move[1]] = 1;
            }
        }
        /*
        // Reproduce if strong
        if (energy >= 10) {
            List<int[]> spots = world.getAdjacentEmpty(x, y);
            if (!spots.isEmpty()) {
                int[] spawn = spots.get(new Random().nextInt(spots.size()));
                world.set(spawn[0], spawn[1], new Predator(spawn[0], spawn[1]));
                energy -= 5; // cost to reproduce
            }
        }

        // Die if starved
        if (energy <= 0) {
            world.set(x, y, null);
        }*/

        return newWorld;
    }
}
