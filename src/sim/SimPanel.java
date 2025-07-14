package sim;

import javax.swing.*;
import java.awt.*;

public class SimPanel extends JPanel {
    private World world;

    public SimPanel(World world) {
        this.world = world;
        setPreferredSize(new Dimension(World.WIDTH * 20, World.HEIGHT * 20));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int y = 0; y < World.HEIGHT; y++) {
            for (int x = 0; x < World.WIDTH; x++) {
                Entity e = world.get(x, y);

                if (e instanceof Prey) {
                    g.setColor(Color.GREEN);
                } else if (e instanceof Predator) {
                    g.setColor(Color.RED);
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
