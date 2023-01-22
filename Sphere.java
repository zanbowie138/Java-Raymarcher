class Sphere extends RenderableObject {
  private int radius;

  public Sphere() {
    this.setPos(new vec3(0,0,0));
    this.radius = 10;
    this.setColor(new vec3(255,255,255));
  }
  public Sphere(vec3 pos, int radius) {
    this.setPos(pos);
    this.radius = radius;
    this.setColor(new vec3(255,255,255));
  }

  public Sphere(vec3 pos, int radius, vec3 color) {
    this.setPos(pos);
    this.radius = radius;
    this.setColor(color);
  }

  @Override
  public float signedDist(vec3 point) {
    return vec3.getDist(this.pos(),point) - this.radius;
  }

  public vec3 getNormal(vec3 point) {
    return vec3.getDirVec(this.pos(),point);
  }
}