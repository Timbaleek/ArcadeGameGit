package main;

import java.io.IOException;

import org.lwjgl.opengl.*;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class TexturedGameEntity extends GameEntity{

	private Texture tex;
	private String path;
	
	public TexturedGameEntity(Vector2f pos, Vector2f size, String path) {
		super(pos, size);
		this.size = size;
		this.path = path;
		initTex();
	}
	
	protected void initTex(){
		try {
			tex = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/" + path + ".png"));
			tex.setTextureFilter(GL11.GL_NEAREST);
		} catch (IOException e) {
			System.out.println("Texture:" + path + ".png not found");
			e.printStackTrace();
		}
	}
	
	protected Texture getTex(){
		return tex;
	}
	
	public void render(){
		Color.white.bind();
		getTex().bind();
		GL11.glBegin(GL11.GL_QUADS);
	    GL11.glTexCoord2f(0, 0);
		GL11.glVertex2f(pos.x,pos.y);
	    GL11.glTexCoord2f(0, 1);
	    GL11.glVertex2f(pos.x,pos.y+size.y);
	    GL11.glTexCoord2f(1, 1);
	    GL11.glVertex2f(pos.x+size.x,pos.y+size.y);
	    GL11.glTexCoord2f(1, 0);
	    GL11.glVertex2f(pos.x+size.x,pos.y);
	    GL11.glEnd();
	    
	}
}
