package mainSAT;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Color;
import org.lwjgl.util.vector.Vector2f;
public class Main {

	final static int screenWidth = 1920;
	final static int screenHeight = 1080;
	final static int numOfWorlds = 1;
	//static World[] worlds = new World[numOfWorlds];
	//static int currentWorld = 0;
	private static ColoredGameEntity c;
	public static PlayerGameEntity p;
	
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
		
		init();
		
		while (!Display.isCloseRequested()) {
			 
		    GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);	

		    update();
		    render();
	 
		    Display.update();
		}
	 
		Display.destroy();
	    
	}

	

	static float xCor;
	private static void init() {

		//c = new ColoredGameEntity(100, 100, 200, 200, new Color(255,0,0));
		//a = new AnimatedGameEntity(1400, 200, 300, 100, "anim", 0, 8, 0.3f);
//		for(int i = 0; i<numOfWorlds; i++){
//			worlds[i] = new World(i);
//		}
		//p = new PlayerGameEntity(new Vector2f(screenWidth/2-50, 0), new Vector2f(100,200), 30, "anim", 4, 8, 0.6f,new Vector2f(0,0), new Vector2f(0,0.06f), 15, 0.01f);//0.5f);
		c = new ColoredGameEntity(new Vector(100, 100), new Color(255,100,255));
		c.setPointsNumber(5);
		c.setPoint(0, new Vector(200,100));
		c.setPoint(1, new Vector(400,180));
		c.setPoint(2, new Vector(300,200));
		c.setPoint(3, new Vector(100,150));
		c.setPoint(4, new Vector(0,100));
		//xCor = (screenWidth/2)-(p.getSize().x/2);
		//ground = new TexturedGameEntity(0, height-200, levelWidth, height, "ground");
		//background = new TexturedGameEntity(0, 0, levelWidth, height, "background");
	}
	
	private static void update() {
		//a.updateState();
		//worlds[currentWorld].update();
		//p.updateState();
//		if(p.getPos()[3].x+p.vel.x<xCor){
//			p.vel.setX(0);
//		} else if (p.getPos()[1].x+p.getSize().x+p.vel.x>worlds[currentWorld].levelWidth-xCor){
//			p.vel.setX(0);
//		}
//		if(p.getPos()[0].y+p.getSize().y+p.vel.y>screenHeight-worlds[currentWorld].groundHeight){
//			p.vel.setY(0);;
//			p.grounded=true;
//		} else{
//			p.grounded=false;
//		}
		//p.updateMovement();
		//p.updateInput();
		//p.update();
		c.setPosition(c.getPosition().x, c.getPosition().y + 1);
	}

	private static void render() {
		//colored
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		c.render();
		//textured
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glPushMatrix();
		//GL11.glTranslatef(-p.getPosition().x+xCor, 0, 0);
		//background.render();
		//ground.render();
		//a.render();
		//worlds[currentWorld].render();
		//p.render();
		
		GL11.glPopMatrix();
	}

	public static long getTime() {
		return System.nanoTime() / 1000000;
	}
}
