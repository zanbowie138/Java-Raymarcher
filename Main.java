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
    int[] resolution = {200,200};

    vec3 cam_position = new vec3(0,0,-1000);

    vec3 sphere_center = new vec3(0,0,0);
    int sphere_size = 60;
    
    Camera camera = new Camera(cam_position);

    Sphere sphere = new Sphere(sphere_center, sphere_size);
    

    imageIoWritePNG(generateFrameIso(camera, dimensions, resolution, new RenderableObject[]{sphere}), "output.png");
    System.out.println("Finished!");
  }

  // Generates a frame of the scene in isometric view
  public static BufferedImage generateFrameIso(Camera camera, int[] dimensions, int[] resolution, RenderableObject[] objects) {
    // Generates a frame of the scene
    BufferedImage frame = new BufferedImage(resolution[0], resolution[1], BufferedImage.TYPE_INT_RGB);
    vec3 transform = new vec3(-resolution[0]/2, -resolution[1]/2, 0);
    float pixel_x = dimensions[0]/resolution[0];
    float pixel_y = dimensions[1]/resolution[1];
    vec3 dir = new vec3(0,0,1);
    int maxDist = 1000;

    for (int x = 0; x < resolution[0]; x++) {
      for (int y = 0; y < resolution[1]; y++) {
        vec3 px = new vec3(camera.x() + x * pixel_x, camera.y() + y * pixel_y, camera.z()).add(transform);
        boolean finished = false;
        while (!finished) {
          float closestSignedDist = objects[0].signedDist(px);
          RenderableObject usedObject = objects[0];
          for (RenderableObject object : objects) {
            if (object.signedDist(px) < closestSignedDist) {
              closestSignedDist = object.signedDist(px);
              usedObject = object;
            }
          }
          if (closestSignedDist < 0.1) {
            frame.setRGB(x, y, utils.getRGB(255,255,255));
            finished = true;
          } 
          else if (closestSignedDist > maxDist) {
            frame.setRGB(x, y, utils.getRGB(0,0,0));
            finished = true;
          }
          else {
            px.add(vec3.scale(dir,closestSignedDist));
          }
        }
      }
    }
    return frame;
  }

  public static void imageIoWritePNG(BufferedImage img, String location) {
    // Prints BufferedImage to location as a png file
    File output = new File(location);
    try {
      ImageIO.write(img, "png", output);
      System.out.println("Created image!");
    } catch (IOException e) {
      System.out.println("Exception occured :" + e.getMessage());
    }
  }
}