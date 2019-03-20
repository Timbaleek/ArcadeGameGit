package mainSAT;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Color;

public class AnimatedGameEntity extends TexturedGameEntity{

	private int state;
	private float coordDivision,secPerState;
	private long lastTime;
	
	public AnimatedGameEntity(Vector2f pos, Vector2f size, float rot, String path, int state, int stateCount, float secPerState) {
		super(pos, size, rot, path);
		this.state = state;
		this.coordDivision = 1/(float)stateCount;
		this.secPerState = secPerState * 1000;
	}
	
	public void render(){
		float x1 = coordDivision*state;
		float x2 = coordDivision*(state+1);
		
		Vector2f[] positions = getPos();
		Color.white.bind();
		getTex().bind();
		GL11.glBegin(GL11.GL_QUADS);
	    GL11.glTexCoord2f(x1, 0);
	    GL11.glVertex2f(positions[0].x, positions[0].y);
	    GL11.glTexCoord2f(x1, 1);
	    GL11.glVertex2f(positions[1].x, positions[1].y);
	    GL11.glTexCoord2f(x2, 1);
	    GL11.glVertex2f(positions[2].x, positions[2].y);
	    GL11.glTexCoord2f(x2, 0);
	    GL11.glVertex2f(positions[3].x, positions[3].y);
	    GL11.glEnd();
	}
	
	public void updateState(){
		if(Main.getTime() - lastTime >secPerState){
			state ++;
		    state%=8;
		    lastTime = Main.getTime();
		}
	}
	
}
