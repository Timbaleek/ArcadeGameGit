package main;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;
public class Main {

	final static int screenWidth = 1920;
	final static int screenHeight = 1080;
	final static int numOfWorlds = 1;
	static World[] worlds = new World[numOfWorlds];
	static int currentWorld = 0;
	//private static ColoredGameEntity c;
	//private static AnimatedGameEntity a;
	public static PlayerGameEntity p;
	//private static TexturedGameEntity ground;
	//final static int levelWidth = 10000;
	
	public static void main(String[] args) {
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
		GL11.glScalef(0.5f, 0.5f, 1);
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

		//c = new ColoredGameEntity(100, 100, 200, 200, new Color(255,0,0));
		//a = new AnimatedGameEntity(1400, 200, 300, 100, "anim", 0, 8, 0.3f);
		for(int i = 0; i<numOfWorlds; i++){
			worlds[i] = new World(i);
		}
		p = new PlayerGameEntity(new Vector2f(screenWidth/2-50, 500), new Vector2f(100,200), "anim", 4, 8, 0.1f,new Vector2f(0,0), new Vector2f(0,0.03f), 1, 5, 0.5f);//0.5f);

		xCor = (screenWidth/2)-(p.getSize().x/2);
		//ground = new TexturedGameEntity(0, height-200, levelWidth, height, "ground");
		//background = new TexturedGameEntity(0, 0, levelWidth, height, "background");
	}
	
	private static void update() {
		//a.updateState();
		p.updateState(p.isWalking);
		p.updateInput();
		worlds[currentWorld].update();
		
		p.updateMovement();
		p.update();
	}

	private static void render() {
		//colored
		//GL11.glDisable(GL11.GL_TEXTURE_2D);
		//c.render();
		//textured
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glPushMatrix();
		GL11.glTranslatef(-p.pos.x+xCor, -p.pos.y + screenHeight/2, 0);
		//background.render();
		//ground.render();
		//a.render();
		worlds[currentWorld].render();
		p.render();
		
		GL11.glPopMatrix();
	}

	public static long getTime() {
		return System.nanoTime() / 1000000;
	}
}
