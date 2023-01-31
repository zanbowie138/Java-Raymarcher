package src;
import libs.utils;
import libs.vec3;

public class SphereSine extends RenderableObject{
    private int radius;

  public SphereSine() {
    this.setPos(new vec3(0,0,0));
    this.radius = 10;
    this.setColor(new vec3(1,1,1));
  }
  public SphereSine(vec3 pos, int radius) {
    this.setPos(pos);
    this.radius = radius;
    this.setColor(new vec3(1,1,1));
  }

  public SphereSine(vec3 pos, int radius, vec3 color) {
    this.setPos(pos);
    this.radius = radius;
    this.setColor(color);
  }

  @Override
  public float signedDist(vec3 point) {
    return vec3.getDist(this.pos(),point) - this.radius + utils.sineDisplace(point, this.getTime());
  }

  public int getRadius() {
    return this.radius;
  }
}
