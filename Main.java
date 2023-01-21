import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import java.util.Arrays;
class Main {
  public static void main(String[] args) {
    int[] dimensions = {300,300};
    vec3 cam_position = new vec3(-60,-60,-300);

    vec3 sphere_center = new vec3(0,10,0);
    int sphere_size = 60;
    
    BufferedImage bufferedImage = new BufferedImage(dimensions[0], dimensions[1], BufferedImage.TYPE_INT_RGB);
    Camera cam = new Camera(cam_position);
    System.out.println(cam);
    //Sphere sphere = new Sphere();
    //cam.print(bufferedImage,new RenderableObject[] {sphere});
    //System.out.println(sphere);
    //imageIoWritePNG(bufferedImage, "img/output.png");
    System.out.println("Finished!");
  }

  public static void imageIoWritePNG(BufferedImage img, String location) {
    //Prints BufferedImage to location as a png file
    File output = new File(location);
    try {
      ImageIO.write(img, "png", output);
      System.out.println("Created image!");
    } catch (IOException e) {
      System.out.println("Exception occured :" + e.getMessage());
    }
  }
}