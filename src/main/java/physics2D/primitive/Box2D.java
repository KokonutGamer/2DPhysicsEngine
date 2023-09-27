package physics2D.primitive;

import org.joml.Vector2f;

import physics2D.rigidbody.RigidBody2D;

public class Box2D {
	private Vector2f size = new Vector2f();
	private Vector2f halfSize = new Vector2f();
	private RigidBody2D rigidBody = null;
	
	public Box2D() {
		this.halfSize = new Vector2f(size).mul(0.5f);
	}
	
	public Box2D(Vector2f min, Vector2f max) {
		this.size = new Vector2f(max).sub(min);
		this.halfSize = new Vector2f(size).mul(0.5f);
	}
	
	public Vector2f getMin() {
		return new Vector2f(rigidBody.getPosition()).sub(halfSize);
	}
	
	public Vector2f getMax() {
		return new Vector2f(rigidBody.getPosition()).add(halfSize);
	}
	
	public Vector2f[] getVertices() {
		Vector2f min = getMin();
		Vector2f max = getMax();
		
		Vector2f[] vertices = {
				new Vector2f(min.x, min.y),
				new Vector2f(min.x, max.y),
				new Vector2f(max.x, min.y),
				new Vector2f(max.x, max.y)
		};
		
		if (rigidBody.getRotation() != 0.0f) {			
			for(Vector2f vert : vertices) {
				// TODO implement line
				// Rotates point (Vector2f) about center (Vector2f) by rotation (float in degrees)
			}
		}
		
		return vertices;
	}
}
