package libs;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.awt.Graphics2D;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class utils {
  // A fast lookup table for sin and cos
  // Taken from https://stackoverflow.com/questions/16930581/fast-sine-and-cosine-function-in-java
  // Credit to user: maybeWeCouldStealAVan

  static final int precision = 100; // gradations per degree, adjust to suit
  static final int modulus = 360*precision;
  static final float[] sin = new float[modulus]; // lookup table
  static { 
      // a static initializer fills the table
      // in this implementation, units are in degrees
      for (int i = 0; i<sin.length; i++) {
          sin[i]=(float)Math.sin((i*Math.PI)/(precision*180));
      }
  }
  // Private function for table lookup
  private static float sinLookup(int a) {
      // If a is negative, return the negative of the sin of a
      return a>=0 ? sin[a%(modulus)] : -sin[-a%(modulus)];
  }

  public static float sin(float a) {
    // Returns sin of float a in degrees
      return sinLookup((int)(a * precision + 0.5f));
  }
  public static float cos(float a) {
    // Returns cos of float a in degrees
      return sinLookup((int)((a+90f) * precision + 0.5f));
  }

  // End of lookup table

  public static int rgbToInt(float r, float g, float b) {
    return ((int)(r*255) << 16) | ((int)(g*255) << 8) | (int)(b*255);
  }
  public static int rgbToInt(vec3 color) {
    return rgbToInt(color.x,color.y,color.z);
  }

  public static vec3 convertRGB(vec3 color) {
    return new vec3(color.x*255,color.y*255,color.z*255);
  }

  public static vec3 intToRGB(int color) {
    float r = ((color>>16)&0xff)/255.0f;
    float g = ((color>>8)&0xff)/255.0f;
    float b = ((color>>0)&0xff)/255.0f;

    return new vec3(r,g,b);
  }

  public static float sineDisplace(vec3 p, float t) {
    float t_scale = .003f;
    float dis = 50f;
    float scale = 0.5f;
    //System.out.println(p);
    return (float)utils.sin(dis*(p.x + t_scale*t)) + (float)utils.sin(dis*(p.y + t_scale*t)) + (float)utils.sin(dis*(p.z + t_scale*t)) * scale;
  }
  public static void p(Object p) {
    System.out.println(p);
  }
  public static float getNormalized (float x, float y) {
    return (float)Math.sqrt(1 - x*x + y*y);
  }

  public static float[] getUV(vec3 unitVec) {
    float u = 0.5f + (float)(Math.atan2(unitVec.y,unitVec.x)/(2*Math.PI));
    float v = 0.5f + (float)(Math.asin(unitVec.z)/Math.PI);

    return new float[]{u,v};
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

  public static int[][] get2D(BufferedImage image) {
      // https://stackoverflow.com/questions/6524196/java-get-pixel-array-from-image
      final int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
      final int width = image.getWidth();
      final int height = image.getHeight();
      int[][] result = new int[height][width];
       for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel++) {
          result[row][col] = pixels[pixel];
          col++;
          if (col == width) {
             col = 0;
             row++;
          }
       }
      return result;
   }

  public static BufferedImage convertToARGB(BufferedImage image)
  {
    BufferedImage newImage = new BufferedImage(
        image.getWidth(), image.getHeight(),
        BufferedImage.TYPE_INT_RGB);
    Graphics2D g = newImage.createGraphics();
    g.drawImage(image, 0, 0, null);
    g.dispose();
    return newImage;
  }
  
  public static int summation(int end) {
    int output = 0;
    for (int i = end; i > 0; i--) {
      output+=i;
    }
    return output;
  }
}