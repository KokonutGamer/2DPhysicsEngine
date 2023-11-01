package physics2D;

import static org.junit.jupiter.api.Assertions.*;

import org.joml.Vector2f;
import org.junit.jupiter.api.Test;

import physics2D.rigidbody.IntersectionDetector2D;
import renderer.Line2D;

class CollisionDetectorTest {
	private final float EPSILON = 0.000001f;

	@Test
	public void endpointsOnLineTest() {
		Line2D line = new Line2D(new Vector2f(0, 0), new Vector2f(12, 4));
		Vector2f startPoint = new Vector2f(0, 0);
		Vector2f endPoint = new Vector2f(12, 4);

		assertTrue(IntersectionDetector2D.pointOnLine(startPoint, line));
		assertTrue(IntersectionDetector2D.pointOnLine(endPoint, line));
	}

	@Test
	public void verticalLineTest() {
		Line2D line = new Line2D(new Vector2f(0, 0), new Vector2f(0, 5));
		Vector2f point = new Vector2f(0, 5);

		assertTrue(IntersectionDetector2D.pointOnLine(point, line));
	}
}
