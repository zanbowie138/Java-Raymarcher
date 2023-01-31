package libs;
import java.awt.Image;
import java.awt.image.BufferedImage;
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

  public static float sineDisplace(vec3 p, float t) {
    float t_scale = .003f;
    float dis = 50f;
    float scale = 0.5f;
    //System.out.println(p);
    return (float)utils.sin(dis*(p.x + t_scale*t)) + (float)utils.sin(dis*(p.y + t_scale*t)) + (float)utils.sin(dis*(p.z + t_scale*t)) * scale;
  }
  public static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
    Image resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_DEFAULT);
    BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
    outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
    return outputImage;
  }
  public static void p(Object p) {
    System.out.println(p);
  }
  public static float getNormalized (float x, float y) {
    return (float)Math.sqrt(1 - x*x + y*y);
  }
}