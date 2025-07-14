package sim;

import java.util.*;

public class World {
    public static final int WIDTH = 20;
    public static final int HEIGHT = 20;

    private Entity[][] grid = new Entity[WIDTH][HEIGHT];

    public void init() {
        Random rand = new Random();

        // Add 20 prey
        for (int i = 0; i < 20; i++) {
            int x = rand.nextInt(WIDTH);
            int y = rand.nextInt(HEIGHT);
            if (grid[x][y] == null) {
                Prey prey = new Prey(x, y);
                grid[x][y] = prey;
            }
        }

        // Add 5 predators
        for (int i = 0; i < 5; i++) {
            int x = rand.nextInt(WIDTH);
            int y = rand.nextInt(HEIGHT);
            if (grid[x][y] == null) {
                Predator predator = new Predator(x, y);
                grid[x][y] = predator;
            }
        }
    }

    public List<int[]> getAdjacentEmpty(int x, int y) {
        List<int[]> options = new ArrayList<>();
        int[][] dirs = {{-1,0}, {1,0}, {0,-1}, {0,1}};

        for (int[] dir : dirs) {
            int nx = x + dir[0];
            int ny = y + dir[1];
            if (nx >= 0 && ny >= 0 && nx < WIDTH && ny < HEIGHT && grid[nx][ny] == null) {
                options.add(new int[]{nx, ny});
            }
        }

        return options;
    }

    public List<int[]> getAdjacentPrey(int x, int y) {
        List<int[]> options = new ArrayList<>();
        int[][] dirs = {{-1,0}, {1,0}, {0,-1}, {0,1}};

        for (int[] dir : dirs) {
            int nx = x + dir[0];
            int ny = y + dir[1];
            if (nx >= 0 && ny >= 0 && nx < WIDTH && ny < HEIGHT) {
                Entity e = grid[nx][ny];
                if (e instanceof Prey) {
                    options.add(new int[]{nx, ny});
                }
            }
        }

        return options;
    }

    public void update() {
        // Copy of entities so we don’t act on new ones
        List<Entity> toAct = new ArrayList<>();

        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                if (grid[x][y] != null) {
                    toAct.add(grid[x][y]);
                }
            }
        }

        for (Entity e : toAct) {
            e.act(this);
        }
    }

    public void draw() {
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                Entity e = grid[x][y];
                System.out.print((e == null ? "." : e.toString()) + " ");
            }
            System.out.println();
        }
    }

    public Entity get(int x, int y) {
        if (x < 0 || y < 0 || x >= WIDTH || y >= HEIGHT) return null;
        return grid[x][y];
    }

    public void set(int x, int y, Entity e) {
        if (x >= 0 && y >= 0 && x < WIDTH && y < HEIGHT) {
            grid[x][y] = e;
        }
    }
}
