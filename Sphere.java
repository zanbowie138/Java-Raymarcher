class Sphere extends RenderableObject {
  public Sphere(vec3 pos) {
    this.setPos(pos);
  }
  
  @Override
  public float signedDist(vec3 point) {
    return vec3.getDist(this.pos(),point);
  }
}