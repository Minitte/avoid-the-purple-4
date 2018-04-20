/**
 * 
 */
package game.collision;

import game.math.Mat22;
import game.math.Proj;
import game.math.Vec2;

/**
 * @author Davis
 *
 */
public class CollisionBody {
	public Vec2[] vertices;
	public Vec2[] normals;

	/**
	 * @param vertices
	 */
	public CollisionBody(Vec2[] vertices) {
		super();
		this.vertices = vertices;
		
		normals = new Vec2[vertices.length];
		
		for (int i = 0; i < vertices.length; i++) {
			normals[i] = new Vec2(vertices[i]).minus(vertices[(i + 1) % vertices.length]).normalize();
		}
	}
	
	/**
	 * Translates all vertices by amt
	 * @param amt
	 */
	public void translateVertices(Vec2 amt) {
		for (int i = 0; i < vertices.length; i++) {
			vertices[i].add(amt);
		}
	}
	
	/**
	 * Rotates the body
	 * @param r
	 */
	public void rotate(float r) {
		Mat22 rot = new Mat22(r);
		
		for (int i = 0; i < vertices.length; i++) {
			vertices[i].multiply(rot);
			normals[i].multiply(rot);
		}
	}
	
	/**
	 * Checks for collision using SAT
	 * @param other
	 * @return
	 */
	public boolean checkCollision(CollisionBody other) {
		for (int i = 0; i < normals.length; i++) {
			Proj pA = projectOnAxis(normals[i]);
			Proj pB = other.projectOnAxis(normals[i]);
			if (!Proj.overlap(pA, pB)) {
				return false;
			}
		}
		
		for (int i = 0; i < other.normals.length; i++) {
			Proj pA = projectOnAxis(other.normals[i]);
			Proj pB = other.projectOnAxis(other.normals[i]);
			if (!Proj.overlap(pA, pB)) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Projects the shape onto the axis
	 * @param axis
	 * @return
	 */
	public Proj projectOnAxis(Vec2 axis) {
		float min = axis.dot(vertices[0]);
		float max = min;
		
		for (int i = 0; i < vertices.length; i++) {
			float p = axis.dot(vertices[i]);
			
			if (p < min) {
				min = p;
			} else if (p > max) {
				max = p;
			}
		}
		
		return new Proj(min, max);
	}
	
}
