package mainSAT;

public class Vector {

	public final float x;
	public final float y;

	public Vector(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public float dot(Vector v) {
		return x * v.x + y * v.y;
	}

	public float length() {
		return (float) Math.sqrt(x * x + y * y);
	}

	public Vector normalize() {
		float l = length();
		return new Vector(x / l, y / l);
	}

	public Vector getNormalVectorLeft() {
		return new Vector(-y, x);
	}

	public Vector getNormalVectorRight() {
		return new Vector(y, -x);
	}

}
