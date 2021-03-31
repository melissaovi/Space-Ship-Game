package math;

public class Vector2D {
	private double x,y; //componentes
	
	public Vector2D(double x,double y) {
		this.x = x;
		this.y = y;
		
	}
	
	public Vector2D() {//sobrecarga de métodos
		//cuando se quiera inicializar un vector
	 x = 0;
	 y =0;
	 
	}
	public Vector2D add(Vector2D v) {
		return new Vector2D(x + v.getX(),y+ v.getY());
	}
	public Vector2D scale(double value) {
		//modificar magnitud del vector
		return new Vector2D(x*value,y*value);
	}
	public void limit(double value) {
		
		if (x> value)
			x = value;
		if (x< -value)
			x = -value;
		
		if (y> value)
			y = value;
		if (y< -value)
			y = -value;
			
			
		
	}
	public Vector2D normalize() {
		return new Vector2D(x/getMagnitude(),y/getMagnitude()); //vector unitario
	}
	
	
	
	
	
	
	
	public double getMagnitude() {
		return Math.sqrt(x*x+y*y);
		
	}
	public Vector2D setDirection(double angle) {
		return new Vector2D(Math.cos(angle)*getMagnitude(),Math.sin(angle)*getMagnitude());
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

}
