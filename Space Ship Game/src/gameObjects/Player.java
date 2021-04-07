package gameObjects;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import graphics.Assets;
import input.KeyBoard;
import math.Vector2D;
import states.GameState;

public class Player extends MovingObject{
	
	private Vector2D heading;
	
	private Vector2D acceleration;
	
	private final double ACC = 0.2;
	
	private final double deltaAngle = 0.1;
	
	private GameState gameState; //para laser
	
	

	public Player(Vector2D position, Vector2D velocity,double maxVel, BufferedImage texture, GameState gameState) {
		super(position, velocity,maxVel, texture);
		this.gameState = gameState;
		heading = new Vector2D(0,1);
		acceleration = new Vector2D();
		
	}

	@Override
	public void update() {	
		
		if(KeyBoard.SHOOT)
		{
			gameState.getMovingObject().add(new Laser(getCenter().add(heading.scale(Assets.player.getWidth()/2)),heading,10,angle,Assets.redLaser));
		}
		
		if (KeyBoard.RIGHT)
			angle += deltaAngle;
		
		if(KeyBoard.LEFT)
			angle -= deltaAngle;
		
		if(KeyBoard.UP) {
			acceleration = heading.scale(ACC);
			
		}else { //vector con magnitud = 1 
			if (velocity.getMagnitude() != 0)
				acceleration =( velocity.scale(-1).normalize()).scale(ACC/2);
			
		}
		
		
		velocity = velocity.add(acceleration);
		
		velocity.limit(maxVel);
		
	
		
		
		
		heading= heading.setDirection(angle- Math.PI/2);// Java se usan radianes
		
		position = position.add(velocity);
		
		
	}
	

	@Override
	public void draw(Graphics g) {
		
		Graphics2D g2d = (Graphics2D)g;
		
		at = AffineTransform.getTranslateInstance(position.getX(),position.getY());
		at.rotate(angle,Assets.player.getWidth()/2,Assets.player.getHeight()/2);
		
		g2d.drawImage(Assets.player,at,null);
		
	}
	public Vector2D getCenter() {
		// obtener centro del jugador para que dispare desde ahí
		return new Vector2D(position.getX()+Assets.player.getWidth()/2,position.getY()+Assets.player.getHeight()/2);
	}

}
