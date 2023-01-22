class Light extends Object{
    private vec3 color;
    private float intensity;
    public Light(vec3 pos, vec3 color) {
        this.setPos(pos);
        this.color = color;
    }
    public vec3 color() {
        return this.color;
    }
    public float calcIntensity() {
        return this.intensity;
    }
}