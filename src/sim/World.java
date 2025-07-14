package sim;

import java.util.*;

public class World {
    public static final int WIDTH = 20;
    public static final int HEIGHT = 20;

    private Entity[][] grid = new Entity[WIDTH][HEIGHT];

    public void init() {
        // Populate with prey and predators
    }

    public void update() {
        // Go through all entities and call act()
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
