package util;

import org.lwjgl.opengl.GL11;

public class Pair {
	
	public float x,y;
	
	public Pair(float x, float y){
		this.x = x;
		this.y = y;
	}

	public String print() {
		return (x + ", " + y);
	}
	
	public void render(){
		GL11.glColor3f(255, 0, 100);
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex2f(x-5,y-5);
		GL11.glVertex2f(x+5,y-5);
		GL11.glVertex2f(x+5,y+5);
		GL11.glVertex2f(x-5,y+5);
		GL11.glEnd();
	}
}
