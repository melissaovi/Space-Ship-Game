package states;

import java.awt.Graphics;
import java.util.ArrayList;

import gameObjects.MovingObject;
import gameObjects.Player;
import graphics.Assets;
import math.Vector2D;


public class GameState {
	
	private Player player;
	private ArrayList<MovingObject> movingObject = new ArrayList<MovingObject>();
	
	public GameState() {
		
		player = new Player(new Vector2D(100,500),new Vector2D(),5,Assets.player,this);
		//velocidad maxima = 5
		movingObject.add(player);
		
	}
	public void update() {
		
		for(int i = 0; i<movingObject.size();i++)
			movingObject.get(i).update();
		
	}
	
	public void draw(Graphics g) {
		for(int i = 0; i<movingObject.size();i++)
			movingObject.get(i).draw(g);
		
	
	}
	public ArrayList<MovingObject> getMovingObject() {
		return movingObject;
	}
	
	

}
