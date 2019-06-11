package main;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;

public class Line {
	Vector2f pos,pos2;
	
	public Line(Vector2f pos,Vector2f pos2){
		this.pos = pos;
		this.pos2 = pos2;
		GL11.glLineWidth(30f);
	}
	
	public void render(){
		GL11.glBegin(GL11.GL_QUADS);
	    GL11.glVertex2f(pos.x,pos.y);
	    GL11.glVertex2f(pos2.x,pos2.y);
	    GL11.glEnd();
	}
}
