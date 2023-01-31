
import java.awt.image.BufferedImage;

import libs.*;
import src.*;

public class Renderer{
  private Display display;
  private int[] resolution;
  
  private Camera camera;
  private RenderableObject[] objects;
  private Light light;

  private long time;

  public Renderer() {
    display = new Display(700, 700, "Raymarcher");
    resolution = new int[]{400,400};

    time = 0;

    vec3 camPosition = new vec3(0,0,-150);

    vec3 sphereCenter = new vec3(0,0,0);
    int sphereSize = 50;

    vec3 lightPosition = new vec3(0,-10,-90);
    vec3 lightColor = new vec3(1,1,1);
    
    camera = new Camera(camPosition);

    SphereSine sphere = new SphereSine(sphereCenter, sphereSize, new vec3(1,0,0));
    Plane plane = new Plane(new vec3(0f,1f,0f).normalize(), 200,new vec3(0f,1f,0f));
    utils.p(new vec3(0f,5f,10f).normalize());
    Sphere sphere2 = new Sphere(new vec3(1,0,0), 10, new vec3(0,1,0));
    objects = new RenderableObject[]{sphere,plane};

    light = new Light(lightPosition,lightColor);
  }

  // Generates a frame of the scene
  private BufferedImage generateFrame(Camera camera, int[] resolution, Light light, RenderableObject[] objects) {
    // Calculated by dividing screen width by screen height
    float aspectRatio = (float)resolution[0]/resolution[1];

    // The degrees of the field of view in the y direction
    float fovY = 70;
    float fovX = fovY * aspectRatio;

    BufferedImage frame = new BufferedImage(resolution[0], resolution[1], BufferedImage.TYPE_INT_RGB);

    int maxRayDist = 10000;
    int maxSightDist = 5000;

    for (int x = 0; x < resolution[0]; x++) {
      for (int y = 0; y < resolution[1]; y++) {
        vec3 px = camera.pos().copy();

        float dirX = (float)utils.sin((float)(fovX * ((float)x/resolution[0] - 0.5f)));
        float dirY = (float)utils.sin((float)(fovY * ((float)y/resolution[1] - 0.5f)));
        float dirZ = (float)Math.sqrt(1 - dirX*dirX - dirY*dirY);
        vec3 dir = new vec3(dirX, dirY, dirZ);

        boolean finished = false;
        while (!finished) {
          rayMarchReturn rayMarchReturn = rayMarch(objects, px);
          float closestSignedDist = rayMarchReturn.signedDist;
          RenderableObject closestObject = objects[rayMarchReturn.object];
          //System.out.println(px);

          if (closestSignedDist < 1) {
            frame.setRGB(x, y, utils.rgbToInt(calcColor(light, camera, closestObject, px)));
            finished = true;
          } 
          
          else if (closestSignedDist > maxRayDist | vec3.getDist(px, camera.pos()) > maxSightDist) {
            frame.setRGB(x, y, utils.rgbToInt(0,0,0));
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

  private rayMarchReturn rayMarch(RenderableObject[] objects, vec3 px) {
    float closestSignedDist = objects[0].signedDist(px);
    int object = 0;
    for (int i = 1; i < objects.length; i++) {
      if (objects[i].signedDist(px) < closestSignedDist) {
        closestSignedDist = objects[i].signedDist(px);
        object = i;
      }
    }
    return new rayMarchReturn(object, closestSignedDist);
  }

  private vec3 calcColor(Light light, Camera cam, RenderableObject obj, vec3 pt) {
    vec3 baseColor = obj.getColor();
    float shadowValue = isPointInShadow(pt);
    float ambientIntensity = 0.12f;
    float diffuseIntensity = 0.5f * shadowValue;
    float specularIntensity = 0.4f * shadowValue;

    vec3 ambient = vec3.scale(light.getColor(), ambientIntensity);

    
    vec3 normalVector = obj.estimateNormal(pt);
    vec3 pointToLightVector = vec3.getDirVec(pt, light.pos());
    float diffuseDot = Math.max(vec3.dot(normalVector,pointToLightVector),0);
    vec3 diffuse = new vec3(diffuseDot * diffuseIntensity);

    
    vec3 viewVector = vec3.getDirVec(pt, cam.pos());
    vec3 reflectVector = vec3.reflect(obj.estimateNormal(pt), pointToLightVector.reverse());
    float specularDot = (float)Math.pow(Math.max(vec3.dot(viewVector, reflectVector),0),32);
    vec3 specular = vec3.mult(new vec3(specularDot * specularIntensity), light.getColor());


    vec3 calcColor = vec3.add(ambient, diffuse, specular).mult(baseColor);
    return calcColor;
  }

  private float isPointInShadow(vec3 pt) {
    vec3 pointToLightVector = vec3.getDirVec(pt, light.pos());
    vec3 px = pt.copy();
    px.add(vec3.scale(pointToLightVector,1));
    boolean finished = false;

    while (!finished) {
      rayMarchReturn rayMarchReturn = rayMarch(objects, px);
      float closestSignedDist = rayMarchReturn.signedDist;
      RenderableObject closestObject = objects[rayMarchReturn.object];
      //System.out.println(closestSignedDist);

      if (closestSignedDist < .01) {
        return 0;
      } 
      
      else if (closestSignedDist > vec3.getDist(px, light.pos())) {
        return 1;
      }

      else {
        px.add(vec3.scale(pointToLightVector,closestSignedDist));
      }
    }
    return 0;
  }

  public void render(float deltaTime) {
    time += deltaTime;
    updateObjects();
    //camera.setPos(display.updateCam().mult(-150));
    display.updateCam();
    light.setPos(new vec3((float)Math.sin(time/5/100f)*100f, -90, -90));
    display.render(generateFrame(camera, resolution, light, objects));
  }

  public void updateFPS(float fps) {
    display.updateFPS(fps);
  }

  private void updateObjects() {
    int speed = 1000;
    float deltaTime = FrameLoop.getInstance().getDeltaTime()/1000;
    for (RenderableObject object : objects) {
      object.setTime(time);
    }

    camera.move(new vec3(0,0,1).mult(display.getVertical() * deltaTime * speed));
    camera.move(new vec3(1,0,0).mult(display.getHorizontal() * deltaTime * speed));
  }

  public static class rayMarchReturn {
    public final int object;
    public final float signedDist;
    public rayMarchReturn(int object, float signedDist) {
      this.signedDist = signedDist;
      this.object = object;
    }
  }
}