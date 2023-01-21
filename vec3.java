import java.lang.Math;
class vec3 {
  private float x;
  private float y;
  private float z;
  public vec3() {
    x = 0;
    y = 0;
    z = 0;
  }
  
  public vec3(float x, float y, float z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  public String toString() {
    return String.format("vec3(%f,%f,%f)",x,y,z);
  }

  public float x() {
    return x;
  }
  public float y() {
    return y;
  }
  public float z() {
    return z;
  }

  public vec3 set(float x, float y, float z) {
    this.x = x;
    this.y = y;
    this.z = z;
    return this;
  }

  public vec3 add(vec3 other) {
    return new vec3(this.x + other.x, this.y + other.y, this.z + other.z);
  }
  
  public vec3 sub(vec3 other) {
    return new vec3(this.x - other.x, this.y - other.y, this.z - other.z);
  }
  
  public vec3 mult(vec3 other) {
    return new vec3(this.x * other.x, this.y * other.y, this.z * other.z);
  }
  public vec3 mult(float scalar) {
    return new vec3(this.x * scalar, this.y * scalar, this.z * scalar);
  }
  
  public vec3 div(vec3 other) {
    return new vec3(this.x / other.x, this.y / other.y, this.z / other.z);
  }
  public vec3 div(float scalar) {
    return new vec3(this.x / scalar, this.y / scalar, this.z / scalar);
  }

  public vec3 abs() {
    return new vec3(Math.abs(x),Math.abs(y),Math.abs(z));
  }


  public float getMagnitude() {
    return (float)Math.sqrt(Math.pow(x,2)+Math.pow(y,2)+Math.pow(z,2));
  }

  public vec3 normalize() {
    float len = this.getMagnitude();
    return new vec3(x/len,y/len,z/len);
  }

  public static vec3 asVec3(float[] arr) {
    return new vec3(arr[0],arr[1],arr[2]);
  }
  
  public static vec3 add(vec3 base, vec3... addends) {
    vec3 output = base;
    for (vec3 vec : addends) {
      output.add(vec);
    }
    return output;
  }
  
  public static vec3 sub(vec3 base, vec3... subtrahends) {
    vec3 output = base;
    for (vec3 vec : subtrahends) {
      output.sub(vec);
    }
    return output;
  }
  
  public static vec3 mult(vec3 base, vec3... multiplicators) {
    vec3 output = base;
    for (vec3 vec : multiplicators) {
      output.mult(vec);
    }
    return output;
  }
  
  public static vec3 div(vec3 base, vec3... divisors) {
    vec3 output = base;
    for (vec3 vec : divisors) {
      output.div(vec);
    }
    return output;
  }

  public static float getDist(vec3 vec1, vec3 vec2) {
    return vec3.sub(vec1,vec2).getMagnitude();
  }
}
