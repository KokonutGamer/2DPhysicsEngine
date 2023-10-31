package physics2D.rigidbody;

import org.joml.Vector2f;

import physics2D.primitive.AxisAlignedBoundingBox;
import physics2D.primitive.Box2D;
import physics2D.primitive.Circle;
import renderer.Line2D;

public class IntersectionDetector2D {

	// Point vs. Primitive Tests
	public static boolean pointOnLine(Vector2f point, Line2D line) {
		float dy = line.getEnd().y - line.getStart().y;
		float dx = line.getEnd().x - line.getStart().x;
		float m = dy / dx;

		float b = line.getEnd().y - (m * line.getEnd().x);

		// Check the line equation
		// TODO refactor to account for floating point precision
		return point.y == m * point.x + b;
	}

	public static boolean pointInCircle(Vector2f point, Circle circle) {
		Vector2f circleCenter = circle.getCenter();
		Vector2f centerToPoint = new Vector2f(point).sub(circleCenter);

		// Note: more expensive to find square root - instead, just square the values
		return centerToPoint.lengthSquared() < circle.getRadius() * circle.getRadius();
	}

	public static boolean pointInAxisAlignedBoundingBox(Vector2f point, AxisAlignedBoundingBox box) {
		Vector2f min = box.getMin();
		Vector2f max = box.getMax();

		return point.x <= max.x && min.x <= point.x && point.y <= max.y && min.y <= point.y;
	}
	
	public static boolean pointInBox2D(Vector2f point, Box2D box) {
		return false;
	}

	// Line vs. Primitive Tests

}
