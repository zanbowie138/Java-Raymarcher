package libs;
import java.lang.Math;

public class vec3 {
  public float x;
  public float y;
  public float z;

  public vec3() {
    x = 0;
    y = 0;
    z = 0;
  }

  public vec3(float num) {
    this.x = num;
    this.y = num;
    this.z = num;
  }
  
  public vec3(float x, float y, float z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  public String toString() {
    return String.format("vec3(%f,%f,%f)",x,y,z);
  }

  public vec3 set(float x, float y, float z) {
    this.x = x;
    this.y = y;
    this.z = z;
    return this;
  }
  
  public vec3 set(vec3 vec3) {
    this.x = vec3.x;
    this.y = vec3.y;
    this.z = vec3.z;
    return this;
  }

  public vec3 add(vec3 other) {
    // Adds the other vector to this vector
    return this.set(new vec3(this.x + other.x, this.y + other.y, this.z + other.z));
  }
  
  public vec3 sub(vec3 other) {
    // Subtracts the other vector from this vector
    return this.set(new vec3(this.x - other.x, this.y - other.y, this.z - other.z));
  }
  
  public vec3 mult(vec3 other) {
    // Multiplies the other vector to this vector
    return this.set(new vec3(this.x * other.x, this.y * other.y, this.z * other.z));
  }
  public vec3 mult(float scalar) {
    // Multiplies the other vector to this vector
    return this.set(new vec3(this.x * scalar, this.y * scalar, this.z * scalar));
  }
  
  public vec3 div(vec3 other) {
    // Divides the other vector from this vector
    return this.set(new vec3(this.x / other.x, this.y / other.y, this.z / other.z));
  }
  public vec3 div(float scalar) {
    // Divides the other vector from this vector
    return this.set(new vec3(this.x / scalar, this.y / scalar, this.z / scalar));
  }

  public vec3 abs() {
    // Returns the absolute value of the vector
    return this.set(new vec3(Math.abs(x),Math.abs(y),Math.abs(z)));
  }


  public float getMagnitude() {
    // Returns the magnitude of the vector
    return (float)Math.sqrt(Math.pow(x,2)+Math.pow(y,2)+Math.pow(z,2));
  }

  public vec3 normalize() {
    // Normalizes the vector to a length of 1
    float len = this.getMagnitude();
    return this.set(new vec3(x/len,y/len,z/len));
  }

  public vec3 clamp(float min, float max) {
    // Clamps the vector to a minimum and maximum value
    return this.set(new vec3(Math.min(Math.max(x,min),max),Math.min(Math.max(y,min),max),Math.min(Math.max(z,min),max)));
  }

  public vec3 clampMin(float min) {
    // Clamps the vector to a minimum value
    return this.set(new vec3(Math.max(x,min),Math.max(y,min),Math.max(z,min)));
  }

  public vec3 clampMax(float max) {
    // Clamps the vector to a maximum value
    return this.set(new vec3(Math.min(x,max),Math.min(y,max),Math.min(z,max)));
  }

  public vec3 reverse() {
    // Reverses the vector
    return this.set(new vec3(-x,-y,-z));
  }

  public vec3 mod(float num) {
    // Returns the modulus of the vector
    return this.set(new vec3(x%num,y%num,z%num));
  }

  public vec3 pow(float power) {
    // Raises the vector to a power
    return this.set(new vec3((float)Math.pow(x,power),(float)Math.pow(y,power),(float)Math.pow(z,power)));
  }

  public vec3 copy() {
    // Returns a copy of the vector
    return new vec3(x,y,z);
  }

  public static vec3 asVec3(float[] arr) {
    // Converts a float array of length 3 to a vec3
    return new vec3(arr[0],arr[1],arr[2]);
  }
  
  public static vec3 add(vec3 base, vec3... addends) {
    vec3 output = new vec3().set(base);
    for (vec3 vec : addends) {
      output.add(vec);
    }
    return output;
  }
  
  public static vec3 sub(vec3 base, vec3... subtrahends) {
    vec3 output = new vec3().set(base);
    for (vec3 vec : subtrahends) {
      output.sub(vec);
    }
    return output;
  }
  
  public static vec3 mult(vec3 base, vec3... multiplicators) {
    vec3 output = new vec3().set(base);
    for (vec3 vec : multiplicators) {
      output.mult(vec);
    }
    return output;
  }
  
  public static vec3 div(vec3 base, vec3... divisors) {
    vec3 output = new vec3().set(base);
    for (vec3 vec : divisors) {
      output.div(vec);
    }
    return output;
  }

  public static vec3 scale(vec3 base, float scalar) {
    // Returns a vector scaled by a scalar
    return new vec3().set(base).mult(scalar);
  }

  public static float getDist(vec3 vec1, vec3 vec2) {
    // Returns the distance between two vectors
    return vec3.sub(vec1,vec2).getMagnitude();
  }

  public static vec3 getDirVec(vec3 vec1, vec3 vec2) {
    // Returns a normalized vector pointing from vec1 to vec2
    return vec3.sub(vec2,vec1).normalize();
  }

  public static float dot(vec3 vec1, vec3 vec2) {
    // Returns the dot product of two vectors
    return vec1.x*vec2.x + vec1.y*vec2.y + vec1.z*vec2.z;
  }

  public static vec3 reflect(vec3 axis, vec3 vec) {
    // Returns a vector that is reflected across the axis
    // https://math.stackexchange.com/questions/13261/how-to-get-a-reflection-vector
    return vec3.sub(vec,vec3.scale(axis,2*vec3.dot(axis,vec)));
  }
}
