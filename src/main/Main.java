package main;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;

import arduinoCom.ArduinoCommunication;
public class Main {

	final static int screenWidth = 1920;
	final static int screenHeight = 1080;
	final static int numOfWorlds = 1;
	static World[] worlds = new World[numOfWorlds];
	static int currentWorld = 0;
	public static PlayerGameEntity p;
	
	public static void main(String[] args) {
		//Initialize Communication with Arduino
		try {
			ArduinoCommunication.arduinoListen();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		//Initialize LWJGL
		try {
			Display.setDisplayMode(new DisplayMode(screenWidth, screenHeight));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		Display.setTitle("Arcade");

    	GL11.glEnable(GL11.GL_BLEND);
    	GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, screenWidth, screenHeight, 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		init();
		
		while (!Display.isCloseRequested()) {
			 
		    GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);	

		    update();
		    render();
	 
		    Display.update();
		}
	 
		Display.destroy();
	    
	}
	
	public static float xCor;
	private static void init() {

		for(int i = 0; i<numOfWorlds; i++){
			worlds[i] = new World(i);
		}
		p = new PlayerGameEntity(new Vector2f(screenWidth/2-50, 500), new Vector2f(100,200), "anim", 4, 8, 0.1f,new Vector2f(0,0), new Vector2f(0,0.03f), 1, 5, 0.5f);//0.5f);
		xCor = (screenWidth/2)-(p.getSize().x/2);
	}
	
	private static void update() {
		p.updateInput();
		//System.out.println(p.isWalking);
		p.updateState(p.isWalking);
		
		worlds[currentWorld].update();
		
		p.updateMovement();
		p.update();
	}

	private static void render() {
	//colored
		//GL11.glDisable(GL11.GL_TEXTURE_2D);
	//textured
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glPushMatrix();
		GL11.glTranslatef(-p.pos.x+xCor, -p.pos.y + screenHeight/2, 0);
		
		worlds[currentWorld].render();
		p.render();
		
		GL11.glPopMatrix();
	}

	public static long getTime() {
		return System.nanoTime() / 1000000;
	}
}
