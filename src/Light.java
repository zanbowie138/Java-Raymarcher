package src;
import libs.vec3;

public class Light extends Object{
    private vec3 color;
    private float ambient;
    private float intensity;

    public Light(vec3 pos, vec3 color) {
        this.setPos(pos);
        this.color = color;
        this.ambient = 0.1f;
    }
    public vec3 getColor() {
        return this.color;
    }
    public float getAmbient() {
        return this.ambient;
    }
    public float getIntensity() {
        return this.intensity;
    }
}