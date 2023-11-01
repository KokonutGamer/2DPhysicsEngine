package util;

import org.joml.Vector2f;

public class GMath {

	// Rotates the given vector around an origin by a given angle in degrees
	public static void rotate(Vector2f vector, float angleInDegrees, Vector2f origin) {
		float x = vector.x - origin.x;
		float y = vector.y - origin.y;

		float cos = (float) Math.cos(Math.toRadians(angleInDegrees));
		float sin = (float) Math.sin(Math.toRadians(angleInDegrees));

		float xPrime = (x * cos) - (y * sin);
		float yPrime = (x * sin) + (y * cos);

		xPrime += origin.x;
		yPrime += origin.y;

		vector.x = xPrime;
		vector.y = yPrime;
	}

	// Checks that x and y are close enough by a given precision
	public static boolean compare(float x, float y, float epsilon) {
		return Math.abs(x - y) <= epsilon * Math.max(1.0f, Math.max(Math.abs(x), Math.abs(y)));
	}

	public static boolean compare(float x, float y) {
		return compare(x, y, Float.MIN_VALUE);
	}

	public static boolean compare(Vector2f vector1, Vector2f vector2, float epsilon) {
		return compare(vector1.x, vector2.x, epsilon) && compare(vector1.y, vector2.y, epsilon);
	}

	public static boolean compare(Vector2f vector1, Vector2f vector2) {
		return compare(vector1.x, vector2.x) && compare(vector1.y, vector2.y);
	}
}
