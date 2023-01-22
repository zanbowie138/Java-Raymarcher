import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.BufferStrategy;
import java.awt.Canvas;
import java.awt.Dimension;

public class Display extends JFrame {
    private Canvas canvas;

    public Display(int width, int height, String title) {
        this.setTitle(title);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setFocusable(false);

        this.add(canvas);
        this.pack();

        canvas.createBufferStrategy(4);

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void render(BufferedImage frame) {
        BufferStrategy bufferStrategy = canvas.getBufferStrategy();
        Graphics graphics = bufferStrategy.getDrawGraphics();

        graphics.setColor(Color.black);
        graphics.drawRect(0,0,canvas.getWidth(), canvas.getHeight());
        graphics.drawImage(frame, 0,0, canvas.getWidth(), canvas.getHeight(), null);

        graphics.dispose();
        bufferStrategy.show();
    }
}