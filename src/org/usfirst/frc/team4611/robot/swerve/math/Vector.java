package org.usfirst.frc.team4611.robot.swerve.math;

public class Vector {

	private double x;
	private double y;
	
	public Vector(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public Vector scale(double scalar) {
		return new Vector(x*scalar, y*scalar); 
	}
	
	public Vector subtract(Vector v) {
		return new Vector(x-v.getX(), y-v.getY());
	}
	
	
	public Vector add(Vector v) {
		return new Vector(x+v.getX(), y+v.getY());
	}
	
	public double getDotProduct(Vector v) {
		return v.getX()*x + v.getY()*y;
	}
	
	public double getMagnitude() {
		return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	}
	
	public void setX(double nx) {
		x = nx;
	}
	
	public void setY(double ny) {
		y = ny;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public double getAngle() {
		if(x == 0.0 && y == 0.0) {
			return 0;
		}else if(x == 0 && y > 0) {
			return Math.PI/2;
		}else if(x == 0 && y < 0) {
			return 3*Math.PI/2;
		}else if(y == 0 && x > 0) {
			return 0;
		}else if(y == 0 && x < 0) {
			return Math.PI;
		}		
		return Math.atan(y/x);
	}
}
