package src;
import libs.vec3;

public class Sphere extends RenderableObject {
  private int radius;

  public Sphere() {
    this.setPos(new vec3(0,0,0));
    this.radius = 10;
    this.setMaterial(new Material(new vec3(1,1,1)));
  }
  public Sphere(vec3 pos, int radius) {
    this.setPos(pos);
    this.radius = radius;
    this.setMaterial(new Material(new vec3(1,1,1)));
  }

  public Sphere(vec3 pos, int radius, Material mat) {
    this.setPos(pos);
    this.radius = radius;
    this.setMaterial(mat);
  }

  @Override
  public float signedDist(vec3 point) {
    return vec3.getDist(this.pos(),point) - this.radius;
  }

  @Override
  public vec3 getNormal(vec3 p) {
    return vec3.getDir(this.pos(),p);
  }

  public int getRadius() {
    return this.radius;
  }
}