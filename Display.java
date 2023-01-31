import javax.swing.JFrame;
import libs.*;

// import java.awt.Color;
// import java.awt.Graphics;
// import java.awt.image.BufferedImage;
// import java.awt.image.BufferStrategy;
// import java.awt.Canvas;
// import java.awt.Dimension;
// import java.awt.Font;

import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;

public class Display extends JFrame implements KeyListener, MouseListener{
    private int horizontal;
    private int vertical;

    private vec3 originalMouse;
    private boolean dragging;
    private vec3 cameraVec;

    private Canvas canvas;
    private float fps;

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

        this.setFont(new Font("TimesRoman", Font.PLAIN, 32)); 

        fps = 0;

        setFocusable(true);
        this.addKeyListener(this);
        this.addMouseListener(this);
        canvas.addKeyListener(this);
        canvas.addMouseListener(this);

        horizontal = 0;
        vertical = 0;
        originalMouse = new vec3(0,0,0);
        dragging = false;
        cameraVec = new vec3(0,0,-1);
    }

    public void render(BufferedImage frame) {
        BufferStrategy bufferStrategy = canvas.getBufferStrategy();
        Graphics graphics = bufferStrategy.getDrawGraphics();

        graphics.setColor(Color.white);
        graphics.drawImage(frame, 0,0, canvas.getWidth(), canvas.getHeight(), null);
        graphics.drawString(String.format("FPS: %d", (int)fps), 30, canvas.getHeight() - 30);

        graphics.dispose();
        bufferStrategy.show();
    }

    public void updateFPS(float fps) {
        this.fps = fps;
    }

    public int getHorizontal() {
        return horizontal;
    }
    public int getVertical() {
        return vertical;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            if (vertical == 0)
            vertical = 1;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            if (vertical == 0)
            vertical = -1;
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            if (horizontal == 0)
            horizontal = -1;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            if (horizontal == 0)
            horizontal = 1;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            vertical = 0;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            vertical = 0;
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            horizontal = 0;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            horizontal = 0;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        originalMouse = new vec3(e.getX(), e.getY(),0);
        dragging = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        dragging = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    public vec3 updateCam() {
        if (dragging) {
            //System.out.println("x: " + this.getX() + " y: " + this.getY());
            float x = utils.sin(this.getX() - originalMouse.x * 0.01f);
            float y = utils.sin(this.getY()- originalMouse.y * 0.01f);
            float z = utils.getNormalized(x, y);
            System.out.println(vec3.add(cameraVec, new vec3(x, y, z)));
            return vec3.add(cameraVec, new vec3(x, y, z));
        }
        return cameraVec;
    }
}