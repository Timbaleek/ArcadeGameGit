package main;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import util.Pair;
import util.Vec;

public class Main {
	//User Input
	final static int screenWidth = 1920;
	final static int screenHeight = 1080;
	
	static Player p;
	static CollidingGameEntity o;
	static Vec v = new Vec(new Pair(400,600), new Pair(600,700));
	static Vec v1 = v.getNormal();
	static Pair nullPair = new Pair(0,0);
	
	public static void main(String[] args) {
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
	
	private static void init() {
		p = new Player(new Pair(300,400), 100, "player");
		o = new CollidingGameEntity(new Pair(200,200), 500, "other");
		System.out.println(v.dotProduct(v1));

		//System.out.println(p.isColliding(o));
	}
	
	private static void update() {
		p.updateInput();
		p.update();
		System.out.println(p.collisionDetection(o));
		Vec mvt = p.collisionResponse(o);
		if(mvt != null){
			p.translate(p.collisionResponse(o).axisLen.x,p.collisionResponse(o).axisLen.y);
		}	
	}

	private static void render() {
		//colored
			//GL11.glDisable(GL11.GL_TEXTURE_2D);
		//textured
			//GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glPushMatrix();
			GL11.glTranslatef(-p.pos.x + screenWidth/2, -p.pos.y + screenHeight/2, 0);
			
			o.render();
			p.render();
			//v.render(nullPair);
			//1.render(nullPair);
			
			GL11.glPopMatrix();
		}
	
}
