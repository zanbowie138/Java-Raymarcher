package libs;
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
}