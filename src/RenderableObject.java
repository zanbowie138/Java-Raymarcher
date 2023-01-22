package src;
import libs.vec3;

public abstract class RenderableObject extends Object{
  private vec3 color;
  public abstract float signedDist(vec3 point);
  public abstract vec3 getNormal(vec3 point);

  public vec3 getColor() {
    return this.color;
  }

  public void setColor(vec3 color) {
    this.color = color;
  }
}