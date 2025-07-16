package sim;

import java.util.List;
import java.util.Random;

public class Prey {

    public static int[][] act(int[][] oldWorld, int[][] newWorld, int x, int y) {

        List<int[]> predatorSpots = World.getClosestPredator(x, y);
        // Try to move
        List<int[]> moves = World.getAdjacentEmpty(newWorld, x, y);
        if (!predatorSpots.isEmpty()) {
            // Get one nearby prey
            int[] target = predatorSpots.getFirst();  // Closest seen prey

            // Direction vector toward prey
            int dx = Integer.compare(target[0], x); // -1, 0, or 1
            int dy = Integer.compare(target[1], y); // -1, 0, or 1

            int nx = x - dx;
            int ny = y - dy;

            // Check if the spot is empty before moving
            if (nx >= 1 && ny >= 1 && nx < World.WIDTH - 1 && ny < World.HEIGHT - 1 && oldWorld[nx][ny] == 0) {
                newWorld[  x ][  y ] = 0;
                newWorld[ nx ][ ny ] = 2;
            }
        } else if (!moves.isEmpty()) {
            int[] move = moves.get(new Random().nextInt(moves.size()));
            newWorld[  x ][  y ] = 0;
            newWorld[ move[0] ][ move[1] ] = 2;
        }

        /*
        // Try to reproduce
        if (age >= 5) {
            List<int[]> spawnSpots = world.getAdjacentEmpty(x, y);
            if (!spawnSpots.isEmpty()) {
                int[] spawn = spawnSpots.get(new Random().nextInt(spawnSpots.size()));
                world.set(spawn[0], spawn[1], new Prey(spawn[0], spawn[1]));
                age = 0; // reset after reproduction
            }
        }*/
        return newWorld;
    }

    @Override
    public String toString() {
        return "P";
    }
}
