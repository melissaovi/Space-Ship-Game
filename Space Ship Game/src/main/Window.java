package main;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import graphics.Assets;
import input.KeyBoard;
import states.GameState;

public class Window extends JFrame implements Runnable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final int width = 800,height = 600;
	
	private Canvas canvas;
	private Thread thread;
	private boolean running = false;
	private BufferStrategy bs;
	private Graphics g;
	
	// corre a la misma velocidad en cualquier compu
	
	private final int FPS = 60;
    private double TARGETTIME = 1000000000/FPS;
    private double delta = 0;
    private int AVERAGEFPS = FPS;
    
    private GameState gameState;
    
    private KeyBoard keyBoard;

	
	
	
	
	public Window() {
		setTitle("Space Ship Game");
		setSize(width,height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		
		canvas = new Canvas();
		keyBoard = new KeyBoard();
		
		
		canvas.setPreferredSize(new Dimension(width,height));
	    canvas.setMaximumSize(new Dimension(width,height));
	    canvas.setMinimumSize(new Dimension(width,height));
	    canvas.setFocusable(true);
	    add(canvas);
	    
	    canvas.addKeyListener(keyBoard);
	}

	public static void main(String[] args) {
		new Window().start();

	}
	
	
	
	
	private void update(){
		keyBoard.update();
		gameState.update();
		

    }

    private void draw(){
        bs = canvas.getBufferStrategy();
        
        if(bs == null)
        {
            canvas.createBufferStrategy(3);//3 numero buffer que necesita un canvas
            return;
        }

        g = bs.getDrawGraphics();
        
      //---------------------
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);
        //g.drawImage(Assets.player,100,100,null);
        
        gameState.draw(g);
        g.drawString(""+AVERAGEFPS,10,10);
        
        



        //---------------------
        g.dispose();
        bs.show();
    }
    private void init() {  //se llama solo una vez
    	Assets.init();
    	gameState = new GameState();
    	
    }
    
    @Override
    public void run() {
    	
    	long now = 0;
    	long lastTime = System.nanoTime();
    	int frames =0;
    	long time =0;
    	
    	
    	init();
    	
    	while(running) {
    	 now = System.nanoTime();
    	 delta += (now - lastTime)/TARGETTIME;
    	 time += (now - lastTime);
    	 lastTime = now;
    	 
    	 if(delta >=1) {
    		 update();
    		 draw();
    		 delta --;
    		 frames ++;
    		 //System.out.println(frames);
    	 }
    	 if(time >= 1000000000) {
    		 AVERAGEFPS = frames; //variable global
    		 frames =0;
    		 time =0;
    	 }
    		
    	}
    	
    	stop();	
    	
    }
	
	public void start() {
		thread = new Thread(this);
		thread.start();
		running= true;
		
	}
	public void stop() {
		try {
			thread.join();
			running = false;
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		
	}

	
	

}
