package src;
import libs.vec3;

public class Material{
  private vec3 color;
  private float reflectance;
  public Material(vec3 color, float reflectance) {
    this.color = color;
    this.reflectance = reflectance;
  }

  public Material(vec3 color){
    this.color = color;
    this.reflectance = 0.5f;
  }

  public vec3 getColor() {
    return color;
  }

  public void setColor(vec3 color) {
    this.color = color;
  }

  public float getReflect() {
    return reflectance;
  }
}