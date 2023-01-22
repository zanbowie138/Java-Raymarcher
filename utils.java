class utils {
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
  public static int getRGB(int r, int g, int b) {
    return (r << 16) | (g << 8) | b;
  }
  public static int getRGB(vec3 color) {
    return getRGB((int)color.x(),(int)color.y(),(int)color.z());
  }
}