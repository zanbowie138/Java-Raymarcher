public class FrameLoop implements Runnable{

    private Renderer renderer;

    private boolean running;

    private long nextStatTime;
    private int fps;
    private long lastTime;

    public FrameLoop() {
        this.renderer = new Renderer();
    }

    @Override
    public void run() {
        running = true;
        lastTime = System.currentTimeMillis();
      
        while (running) {
            this.render();
            this.printStats();
        }
    }
    private void printStats() {
        // Print FPS every second
        if (System.currentTimeMillis() > nextStatTime) {
            System.out.println(String.format("FPS: %d", fps));
            fps = 0;
            nextStatTime = System.currentTimeMillis() + 1000;
        }
    }
    private void render() {
        renderer.render(deltaTime());
        fps++;
    }
    private float deltaTime() {
        // Return time since last call in milliseconds
        float elapsedTime = System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();
        return elapsedTime;
    }
}
