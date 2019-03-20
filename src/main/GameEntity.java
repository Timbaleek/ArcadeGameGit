package main;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;

public class GameEntity {
	
	public Vector2f pos, size;// rp2, rp3, rp4, p2, p3, p4;
	//private float x, y, width, height;
	
	public GameEntity(Vector2f pos, Vector2f size){
//		this.x = x;
//		this.y = y;
//		this.width = width;
//		this.height = height;
		this.pos = pos;
		this.size = size;
		
		//rp2 = new Vector2f((float)(Math.cos(rotation)*size.x),(float)(Math.sin(rotation)*size.x));
		//rp3 = new Vector2f((float)(Math.cos(rotation)*size.x-(Math.sin(rotation)*size.y)),(float)(Math.sin(rotation)*size.x+(Math.cos(rotation)*size.y)));
		//rp4 = new Vector2f((float)(-(Math.sin(rotation)*size.y)),(float)(Math.cos(rotation)*size.y));
		
		//System.out.println("rp:" + pos + rp2 + ", " + rp3 + ", " + rp4);
//		Vector2f.add(this.pos, rp2, p2);
//		Vector2f.add(this.pos, rp3, p3);
//		Vector2f.add(this.pos, rp4, p4);
//		p2 = new Vector2f(pos.x+rp2.x, pos.y+rp2.y);
//		p3 = new Vector2f(pos.x+rp3.x, pos.y+rp3.y);
//		p4 = new Vector2f(pos.x-rp4.x, pos.y+rp4.y);
//		
//		System.out.println("p:" + pos + ", " + p2 + ", " + p3 + ", " + p4);
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
		//Vector2f[] positions = {pos,p2,p3,p4};
		
		return pos;
	}

//	public void setPos(Vector2f pos) {
//		Vector2f translation = null;
//		Vector2f.sub(this.pos, pos, translation);
//		Vector2f.add(p2, translation, p2);
//		Vector2f.add(p3, translation, p3);
//		Vector2f.add(p4, translation, p4);
//		this.pos = pos;
//	}
	
//	public void updatePos(){
//		Vector2f.add(pos, rp2, p2);
//		Vector2f.add(pos, rp3, p3);
//		Vector2f.add(pos, rp4, p4);
//	}

	public Vector2f getSize() {
		return size;
	}

	public void setPos(Vector2f dest) {
		pos = dest;
	}

}
