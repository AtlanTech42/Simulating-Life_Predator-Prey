package sim;

import javax.swing.*;
import java.awt.*;

public class SimPanel extends JPanel {
    private int[][] world;

    public SimPanel(int[][] world) {
        this.world = world;
        setPreferredSize(new Dimension(World.WIDTH * 20, World.HEIGHT * 20));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int y = 0; y < World.HEIGHT; y++) {
            for (int x = 0; x < World.WIDTH; x++) {

                if (world[x][y] == 1) {
                    g.setColor(Color.RED);
                } else if (world[x][y] == 2) {
                    g.setColor(Color.GREEN);
                } else {
                    g.setColor(Color.LIGHT_GRAY);
                }

                g.fillRect(x * 20, y * 20, 20, 20);
                g.setColor(Color.BLACK);
                g.drawRect(x * 20, y * 20, 20, 20);
            }
        }
    }
}
