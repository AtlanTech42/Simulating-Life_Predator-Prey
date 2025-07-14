import sim.World;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        World world = new World();
        world.init();

        while (true) {
            world.draw();
            world.update();
            Thread.sleep(500); // 500 ms pause
            System.out.println("\n\n");
        }
    }
}
