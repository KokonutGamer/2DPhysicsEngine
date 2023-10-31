package physics2D.primitive;

import org.joml.Vector2f;

import physics2D.rigidbody.RigidBody2D;

public class Circle {
	private float radius;
	private RigidBody2D body = null; // TODO create rigidbody
	
	public Circle(float radius) {
		this.radius = radius;
	}
	
	public float getRadius() {
		return radius;
	}
	
	public Vector2f getCenter() {
		return body.getPosition();
	}
}
