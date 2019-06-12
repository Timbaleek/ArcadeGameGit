package main;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;

public class GameEntity {
	
	public Vector2f pos, size;
	
	public GameEntity(Vector2f pos, Vector2f size){
		this.pos = pos;
		this.size = size;
	}
	
	public void render(){
		GL11.glBegin(GL11.GL_QUADS);
	    GL11.glVertex2f(pos.x,pos.y);
	    GL11.glVertex2f(pos.x,pos.y+size.y);
	    GL11.glVertex2f(pos.x+size.x,pos.y+size.y);
	    GL11.glVertex2f(pos.x+size.x,pos.y);
	    GL11.glEnd();
	}

	public Vector2f getPos() {
		return pos;
	}

	public Vector2f getSize() {
		return size;
	}

	public void setPos(Vector2f dest) {
		pos = dest;
	}

}
