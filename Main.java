import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import java.util.Arrays;
class Main {
  public static void main(String[] args) {
    int[] dimensions = {200,200};
    int[] resolution = {200,200};

    vec3 camPosition = new vec3(0,0,-20);

    vec3 sphereCenter = new vec3(0,0,0);
    int sphereSize = 50;

    vec3 lightPosition = new vec3(0,-100,-100);
    vec3 lightColor = new vec3(1,1,1);
    
    Camera camera = new Camera(camPosition);

    Sphere sphere = new Sphere(sphereCenter, sphereSize, new vec3(255,0,0));

    Light light = new Light(lightPosition,lightColor);
    
    imageIoWritePNG(generateFrameIso(camera, dimensions, resolution, light, new RenderableObject[]{sphere}), "output.png");
    System.out.println("Finished!");
  }

  // Generates a frame of the scene in isometric view
  public static BufferedImage generateFrameIso(Camera camera, int[] dimensions, int[] resolution, Light light, RenderableObject[] objects) {
    // Generates a frame of the scene
    BufferedImage frame = new BufferedImage(resolution[0], resolution[1], BufferedImage.TYPE_INT_RGB);
    vec3 transform = new vec3(-dimensions[0]/2, -dimensions[1]/2, 0);
    float pixel_x = (float)dimensions[0]/(float)resolution[0];
    float pixel_y = (float)dimensions[1]/(float)resolution[1];
    vec3 dir = new vec3(0,0,1);
    int maxDist = 1000;

    for (int x = 0; x < resolution[0]; x++) {
      for (int y = 0; y < resolution[1]; y++) {
        vec3 px = new vec3(camera.x() + x * pixel_x, camera.y() + y * pixel_y, camera.z()).add(transform);
        //System.out.println(px);
        boolean finished = false;
        while (!finished) {
          float closestSignedDist = utils.rayMarch(objects, px);
          Sphere usedObject = (Sphere)objects[0];
          //System.out.println(closestSignedDist);
          if (closestSignedDist < 0.1) {
            float ambient = 0.1f;
            float diffuse = vec3.dot(usedObject.getNormal(px),vec3.getDirVec(px,light.pos()));
            if (diffuse < 0) {
              diffuse = 0;
            }
            vec3 baseColor = new vec3(255,0,0);
            frame.setRGB(x, y, utils.getRGB(baseColor.mult(diffuse)));
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