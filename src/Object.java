package src;
import libs.vec3;

public abstract class Object {
  private vec3 pos;
  
  public void setPos(vec3 pos) {
    this.pos = pos;
  }
  public vec3 pos() {
    return this.pos;
  }
  public float x() {
    return pos.x;
  }
  public float y() {
    return pos.y;
  }
  public float z() {
    return pos.z;
  }
  public String toString() {
    return pos.toString();
  }
}