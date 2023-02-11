package src;
import libs.utils;
import libs.vec3;

public abstract class RenderableObject extends Object{
  private long time;
  private Material material;
  private int[][] texture;

  public abstract float signedDist(vec3 point);

  public vec3 getColor(vec3 pt) {
    if (texture != null) {
      return this.getTextureColor(pt);
    }
    return getMatColor();
  }

  public void setColor(vec3 color) {
    material.setColor(color);
  }

  private vec3 estimateNormal(vec3 p) {
    final float EPSILON = 0.01f;
    return new vec3(
        signedDist(new vec3(p.x + EPSILON, p.y, p.z)) - signedDist(new vec3(p.x - EPSILON, p.y, p.z)),
        signedDist(new vec3(p.x, p.y + EPSILON, p.z)) - signedDist(new vec3(p.x, p.y - EPSILON, p.z)),
        signedDist(new vec3(p.x, p.y, p.z  + EPSILON)) - signedDist(new vec3(p.x, p.y, p.z - EPSILON))
    ).normalize();
  }

  public vec3 getNormal(vec3 p) {
    return estimateNormal(p);
  }

  public void setTime(long time) {
    this.time = time;
  }

  public long getTime() {
    return this.time;
  }
  
  public vec3 getTextureColor(vec3 pt) {
    vec3 unitVec = vec3.getDir(this.pos(), pt);
    float[] uv = utils.getUV(unitVec);
    int color = texture[(int)(uv[0] * (texture.length-1))][(int)(uv[1] * (texture[0].length-1))];
    return utils.intToRGB(color);
  }

  public void setTexture(int[][] texture) {
    this.texture = texture;
  }

  public vec3 getMatColor() {
    return material.getColor();
  }

  public void setMaterial(Material mat) {
    this.material = mat;
  }

  public float getReflect() {
    return material.getReflect();
  }
}