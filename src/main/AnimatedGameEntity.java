package main;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Color;

public class AnimatedGameEntity extends TexturedGameEntity{

	private int state, stateCount;
	private float coordDivision,secPerState;
	private long lastTime;
	
	public AnimatedGameEntity(Vector2f pos, Vector2f size, String path, int state, int stateCount, float secPerState) {
		super(pos, size, path);
		this.state = state;
		this.stateCount = stateCount;
		this.coordDivision = 1/(float)stateCount;
		this.secPerState = secPerState * 1000;
	}
	
	public void render(){
		float x1 = coordDivision*state;
		float x2 = coordDivision*(state+1);
		
		Color.white.bind();
		getTex().bind();
		GL11.glBegin(GL11.GL_QUADS);
	    GL11.glTexCoord2f(x1, 0);
	    GL11.glVertex2f(pos.x,pos.y);
	    GL11.glTexCoord2f(x1, 1);
	    GL11.glVertex2f(pos.x,pos.y+size.y);
	    GL11.glTexCoord2f(x2, 1);
	    GL11.glVertex2f(pos.x+size.x,pos.y+size.y);
	    GL11.glTexCoord2f(x2, 0);
	    GL11.glVertex2f(pos.x+size.x,pos.y);
	    GL11.glEnd();
	}
	
	public void updateState(Direction direction){
		if(direction == Direction.RIGHT){
			if(Main.getTime() - lastTime >secPerState){
				state ++;
			    state%=stateCount;
			    lastTime = Main.getTime();
			}
		} else if (direction == Direction.LEFT){
			if(Main.getTime() - lastTime >secPerState){
				state --;
			    state%=stateCount;
			    lastTime = Main.getTime();
			}
		}
	}
	
}
