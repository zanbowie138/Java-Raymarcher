package libs;
public class utils {
  public static int getRGB(int r, int g, int b) {
    return (r << 16) | (g << 8) | b;
  }
  public static int getRGB(vec3 color) {
    return getRGB((int)color.x(),(int)color.y(),(int)color.z());
  }
}