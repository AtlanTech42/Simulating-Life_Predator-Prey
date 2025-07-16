package sim;

import java.util.*;

public class World {
    public static final int WIDTH = 20;
    public static final int HEIGHT = 20;

    // 0 = Empty
    // 1 = Preditor
    // 2 = Prey
    public static int[][] world = new int[WIDTH][HEIGHT];

    public void init() {
        Random rand = new Random();

        // Add 20 prey
        for (int i = 0; i < 40; i++) {
            int x = rand.nextInt(WIDTH);
            int y = rand.nextInt(HEIGHT);
            if (world[x][y] == 0 ) {
                world[x][y] = 2;
            }
        }
        /*
        // Add 5 predators
        for (int i = 0; i < 5; i++) {
            int x = rand.nextInt(WIDTH);
            int y = rand.nextInt(HEIGHT);
            if (world[x][y] == 0) {
                world[x][y] = 1;
            }
        }*/
        // hard code to test
        world[10][10] = 1;
        world[13][10] = 2;
        world[10][13] = 2;
        world[13][10] = 2;
    }

    public static List<int[]> getAdjacentEmpty(int[][] newWorld, int x, int y) {
        List<int[]> options = new ArrayList<>();
        int[][] dirs = {{-1,0}, {1,0}, {0,-1}, {0,1}};

        for (int[] dir : dirs) {
            int nx = x + dir[0];
            int ny = y + dir[1];
            if (nx >= 1 && ny >= 1 && nx < WIDTH - 1 && ny < HEIGHT - 1 && newWorld[nx][ny] == 0) {
                options.add(new int[]{nx, ny});
            }
        }

        return options;
    }

    public static List<int[]> getAdjacentPrey(int x, int y) {
        List<int[]> options = new ArrayList<>();
        int[][] dirs = {{-1,0}, {1,0}, {0,-1}, {0,1}};

        for (int[] dir : dirs) {
            int nx = x + dir[0];
            int ny = y + dir[1];
            if (nx >= 0 && ny >= 0 && nx < WIDTH && ny < HEIGHT) {
                if (world[nx][ny] == 2) {
                    options.add(new int[]{nx, ny});
                    System.out.println("prey");
                }
            }
        }

        return options;
    }

    //======================================
    // getClosestPrey
    // This code is to find any prey that are close to the predator
    // so this can give the directions that they should go
    //======================================
    public static List<int[]> getClosestPrey(int x, int y) {
        List<int[]> options = new ArrayList<>();
        int[][] dirs = {         { 2,-1}, { 2, 0}, { 2, 1},
                        { 1,-2}, { 1,-1},          { 1, 1}, { 1, 2},
                        { 0,-2},                            { 0, 2},
                        {-1,-2}, {-1,-1},          {-1, 1}, {-1, 2},
                                 {-2,-1}, {-2, 0}, {-2, 1}};

        for (int[] dir : dirs) {
            int nx = x + dir[0]; // Checks the cell relative to the current position of the predator
            int ny = y + dir[1];
            if (nx >= 0 && ny >= 0 && nx < WIDTH && ny < HEIGHT) { // Makes sure it's inside the bounds
                if (world[nx][ny] == 2) {
                    options.add(new int[]{nx, ny});
                }
            }
        }

        return options;
    }
    //======================================

    //======================================
    // getClosestPredator
    // This code is to find any predator that are close to the prey
    // so this can give the directions that they should not go
    //======================================
    public static List<int[]> getClosestPredator(int x, int y) {
        List<int[]> options = new ArrayList<>();
        int[][] dirs = {         { 2,-1}, { 2, 0}, { 2, 1},
                        { 1,-2}, { 1,-1}, { 1, 0}, { 1, 1}, { 1, 2},
                        { 0,-2}, { 0,-1},          { 0, 1}, { 0, 2},
                        {-1,-2}, {-1,-1}, {-1, 0}, {-1, 1}, {-1, 2},
                                 {-2,-1}, {-2, 0}, {-2, 1}};

        for (int[] dir : dirs) {
            int nx = x + dir[0]; // Checks the cell relative to the current position of the prey
            int ny = y + dir[1];
            if (nx >= 0 && ny >= 0 && nx < WIDTH && ny < HEIGHT) { // Makes sure it's inside the bounds
                if (world[nx][ny] == 1) {
                    options.add(new int[]{nx, ny});
                }
            }
        }

        return options;
    }
    //======================================

    //======================================
    // Methode to update the world
    //======================================
    public void update() {

        int newWorld[][] = new int[WIDTH][HEIGHT]; // Create a temp world
        // Clone the old world
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                newWorld[x][y] = world[x][y];
            }
        }

        // All predators act
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                if ( world[x][y] == 1 ) {
                    newWorld = Predator.act(world, newWorld, x, y);
                }
            }
        }

        // All prey act
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                if ( world[x][y] == 2 ) {
                    newWorld = Prey.act(world, newWorld, x, y);
                }
            }
        }

        // Update the original world
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                world[x][y] = newWorld[x][y];
            }
        }
    }
    //======================================

}
