
import java.awt.image.BufferedImage;

import libs.*;
import src.*;

public class Renderer {
  private Display display;

  private int[] dimensions;
  private int[] resolution;
  
  private Camera camera;
  private RenderableObject[] objects;
  private Light light;

  public Renderer() {
    dimensions = new int[]{200,200};
    resolution = new int[]{200,200};

    display = new Display(resolution[0], resolution[1], "Raymarcher");

    vec3 camPosition = new vec3(0,0,-20);

    vec3 sphereCenter = new vec3(0,0,0);
    int sphereSize = 50;

    vec3 lightPosition = new vec3(0,-100,-100);
    vec3 lightColor = new vec3(1,1,1);
    
    camera = new Camera(camPosition);

    Sphere sphere = new Sphere(sphereCenter, sphereSize, new vec3(255,0,0));
    objects = new RenderableObject[]{sphere};

    light = new Light(lightPosition,lightColor);
    
    //imageIoWritePNG(generateFrameIso(camera, dimensions, resolution, light, new RenderableObject[]{sphere}), "output.png");
    //System.out.println("Finished!");
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
        boolean finished = false;
        while (!finished) {
          float closestSignedDist = rayMarch(objects, px);
          Sphere usedObject = (Sphere)objects[0];

          if (closestSignedDist < 0.1) {
            float ambient = 0.1f;
            float diffuse = vec3.dot(usedObject.getNormal(px),vec3.getDirVec(px,light.pos()));
            if (diffuse < 0) {
              diffuse = 0;
            }
            vec3 baseColor = new vec3(255,0,0);
            vec3 calcColor = vec3.add(vec3.scale(baseColor,ambient),vec3.scale(baseColor,diffuse));
            if (calcColor.x() > 255) {
              calcColor.setX(255f);
            }
            frame.setRGB(x, y, utils.getRGB(calcColor));
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

  public void render(int frameCount) {
    display.render(generateFrameIso(camera, dimensions, resolution, light, objects));
  }

  public static float rayMarch(RenderableObject[] objects, vec3 px) {
    float closestSignedDist = objects[0].signedDist(px);
    //Sphere usedObject = (Sphere)objects[0];
    for (RenderableObject object : objects) {
      if (object.signedDist(px) < closestSignedDist) {
        closestSignedDist = object.signedDist(px);
        //usedObject = (Sphere)object;
      }
    }
    return closestSignedDist;
  }
}