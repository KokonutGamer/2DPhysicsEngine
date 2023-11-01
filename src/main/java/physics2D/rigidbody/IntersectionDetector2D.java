package physics2D.rigidbody;

import org.joml.Vector2f;

import physics2D.primitive.AxisAlignedBoundingBox;
import physics2D.primitive.Box2D;
import physics2D.primitive.Circle;
import renderer.Line2D;
import util.GMath;

public class IntersectionDetector2D {

	// Point vs. Primitive Tests
	public static boolean pointOnLine(Vector2f point, Line2D line) {
		float dy = line.getEnd().y - line.getStart().y;
		float dx = line.getEnd().x - line.getStart().x;
		if (dx == 0f) {
			// Vertical line test
			return GMath.compare(point.x, line.getStart().x);
		}
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
		// Translate the point into local space
		Vector2f pointLocalBoxSpace = new Vector2f(point);
		// IMPORTANT: we rotate the point into local space in the other direction of the
		// box's direction
		GMath.rotate(pointLocalBoxSpace, 1.0f * box.getRigidBody().getRotation(), box.getRigidBody().getPosition());

		Vector2f min = box.getMin();
		Vector2f max = box.getMax();

		return pointLocalBoxSpace.x <= max.x && min.x <= pointLocalBoxSpace.x && pointLocalBoxSpace.y <= max.y
				&& min.y <= pointLocalBoxSpace.y;
	}

	// Line vs. Primitive Tests
	public static boolean lineAndCircle(Line2D line, Circle circle) {
		if (pointInCircle(line.getStart(), circle) || pointInCircle(line.getEnd(), circle)) {
			return true;
		}

		Vector2f segment = new Vector2f(line.getEnd()).sub(line.getStart());

		// Project point (circle position) onto segment
		// Parameterized position t
		Vector2f circleCenter = circle.getCenter();
		Vector2f centerToLineStart = new Vector2f(circleCenter).sub(line.getStart());
		float t = centerToLineStart.dot(segment) / segment.dot(segment);

		// if t is less than 0 or greater than 1, it is NOT on the line segment
		if (t < 0.0f || t > 1.0f) {
			return false;
		}

		// Find the closest point to the line segment
		Vector2f closestPoint = new Vector2f(line.getStart()).add(segment.mul(t));

		return pointInCircle(closestPoint, circle);
	}
}
