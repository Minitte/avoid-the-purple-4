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
	private static final int CIRCLE_DEGREE = 8;
	
	public Vec2[] vertices;
	public Vec2[] normals;

	/**
	 * @param vertices
	 */
	public CollisionBody(Vec2[] vertices) {
		super();
		this.vertices = vertices;
		
		calculateNormals();
	}
	
	/**
	 * circle-ish
	 * @param radius
	 * @param pt
	 */
	public CollisionBody(float radius, Vec2 pt) {
		// vertices
		vertices = new Vec2[CIRCLE_DEGREE];
		float radInc = (float)Math.PI;
		radInc *= 2;
		radInc /= CIRCLE_DEGREE;
		
		for (int i = 0; i < vertices.length; i++) {
			Vec2 out = new Vec2((float)Math.cos((float)i * radInc), (float)Math.sin((float)i * radInc)).multiply(radius);
			vertices[i] = new Vec2(pt).add(out);
		}
		
		// normals
		calculateNormals();
	}
	
	/**
	 * calculates the normals for the edges
	 */
	private void calculateNormals() {
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
	 * @param center
	 */
	public void rotate(float r, Vec2 center) {
		Mat22 rot = new Mat22(r);
		Vec2 toCenter = new Vec2(center).multiply(-1f);
		
		for (int i = 0; i < vertices.length; i++) {
			// translate to (0, 0)
			vertices[i].add(toCenter);
			
			// rotate
			vertices[i].multiply(rot);
			normals[i].multiply(rot);
			
			// translate back
			vertices[i].add(center);
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
