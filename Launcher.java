public class Launcher {
    public static void main(String[] args) {
        new Thread(FrameLoop.getInstance()).start();
    }
}