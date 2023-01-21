class Sphere extends RenderableObject {
  private int radius;
  public Sphere() {
    this.setPos(new vec3(0,0,0));
    this.radius = 10;
  }
  public Sphere(vec3 pos, int radius) {
    this.setPos(pos);
    this.radius = radius;
  }

  @Override
  public float signedDist(vec3 point) {
    return vec3.getDist(this.pos(),point) - this.radius;
  }
}