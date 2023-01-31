import java.awt.event.*;
public class InputManager implements KeyListener, MouseListener {
    private int horizontal;
    private int vertical;
    
    public InputManager() {
      horizontal = 0;
      vertical = 0;
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
        else {
          vertical = 0;
        }
      }
      if (e.getKeyCode() == KeyEvent.VK_S) {
        if (vertical == 0)
          vertical = -1;
        else {
          vertical = 0;
        }
      }
      if (e.getKeyCode() == KeyEvent.VK_A) {
        if (horizontal == 0)
          horizontal = -1;
        else {
          horizontal = 0;
        }
      }
      if (e.getKeyCode() == KeyEvent.VK_D) {
        if (horizontal == 0)
          horizontal = 1;
        else {
          horizontal = 0;
        }
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
    }

    @Override
    public void mouseReleased(MouseEvent e) {
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
}
