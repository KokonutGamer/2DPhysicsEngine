package physics2D.primitive;

import org.joml.Vector2f;

import physics2D.rigidbody.RigidBody2D;

public class AxisAlignedBoundingBox {
	private Vector2f size = new Vector2f();
	private Vector2f halfSize = new Vector2f();
	private RigidBody2D rigidBody = null;
	
	public AxisAlignedBoundingBox() {
		this.halfSize = new Vector2f(size).mul(0.5f);
	}
	
	public AxisAlignedBoundingBox(Vector2f min, Vector2f max) {
		this.size = new Vector2f(max).sub(min);
		this.halfSize = new Vector2f(size).mul(0.5f);
	}
	
	public Vector2f getMin() {
		return new Vector2f(rigidBody.getPosition()).sub(halfSize);
	}
	
	public Vector2f getMax() {
		return new Vector2f(rigidBody.getPosition()).add(halfSize);
	}
}
