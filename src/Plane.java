package src;
import libs.vec3;

public class Plane extends RenderableObject {
  private vec3 normal;
  private float dist;

  public Plane() {
    this.normal = new vec3(0,1,0);
    this.setColor(new vec3(1,1,1));
  }
  public Plane(vec3 normal, float dist) {
    this.normal = normal;
    this.dist = dist;
    this.setColor(new vec3(1,1,1));
  }

  public Plane(vec3 normal, float dist, vec3 color) {
    this.normal = normal;
    this.dist = dist;
    this.setColor(color);
  }

  @Override
  public float signedDist(vec3 point) {
    return Math.abs(vec3.dot(this.normal, point) - dist);
  }

  public vec3 getNormal() {
    return this.normal;
  }
}