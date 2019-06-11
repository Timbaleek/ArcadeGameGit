package main;

import org.lwjgl.opengl.GL11;

import util.Pair;

public abstract class GameEntity {
	
	public Pair pos;
	float size;
	
	public GameEntity(Pair pos, float size){
		this.pos = pos;
		this.size = size;
	}
	
	public void render(){
		GL11.glColor3f(255, 0, 100);
		GL11.glBegin(GL11.GL_QUADS);
	    GL11.glVertex2f(pos.x,pos.y);
	    GL11.glVertex2f(pos.x,pos.y+size);
	    GL11.glVertex2f(pos.x+size,pos.y+size);
	    GL11.glVertex2f(pos.x+size,pos.y);
	    GL11.glEnd();
	}

}
