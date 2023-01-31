public class FrameLoop implements Runnable{
    private static FrameLoop instance;

    private Renderer renderer;
    private InputManager inputManager;

    private boolean running;

    private float deltaTime;

    private long nextStatTime;
    private int fps;
    private long lastTime;

    private long startTime;

    private FrameLoop() {
        this.renderer = new Renderer();
        this.inputManager = new InputManager();
    }

    public static FrameLoop getInstance() {
        if (instance == null) {
            instance = new FrameLoop();
        }
        return instance;
    }

    @Override
    public void run() {
        running = true;
        lastTime = System.currentTimeMillis();
        startTime = System.currentTimeMillis();
      
        while (running) {
            this.render();
            this.printStats();
        }
    }

    private void printStats() {
        // Print FPS every second
        if (System.currentTimeMillis() > nextStatTime) {
            //System.out.println(String.format("FPS: %d", fps));
            renderer.updateFPS(fps);
            fps = 0;
            nextStatTime = System.currentTimeMillis() + 1000;
        }
    }

    private void render() {
        updateDeltaTime();
        renderer.render(getDeltaTime());
        fps++;
    }

    private float updateDeltaTime() {
        // Return time since last call in milliseconds
        float elapsedTime = System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();
        return deltaTime = elapsedTime;
    }

    public float getDeltaTime() {
        return deltaTime;
    }

    public long getTime() {
        return System.currentTimeMillis() - startTime;
    }
    
    public InputManager getInputManager() {
        return inputManager;
    }
}
