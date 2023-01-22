package libs;
import java.awt.Image;
import java.awt.image.BufferedImage;
public class utils {
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
    float val = 0.006f;
    float dis = 0.3f;
    return (float)Math.sin(dis*(p.x + val*t)) + (float)Math.sin(dis*(p.y + val*t)) + (float)Math.sin(dis*(p.z + val*t));
  }
  public static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
    Image resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_DEFAULT);
    BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
    outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
    return outputImage;
  }
}