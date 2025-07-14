import sim.*;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        World world = new World();
        world.init();

        SimPanel panel = new SimPanel(world);
        JFrame frame = new JFrame("Predator-Prey Simulation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);

        // Simulation loop
        new Timer(400, e -> {
            world.update();
            panel.repaint();
        }).start();
    }
}
