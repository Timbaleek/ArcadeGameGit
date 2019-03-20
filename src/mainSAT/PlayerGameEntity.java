package mainSAT;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector2f;

public class PlayerGameEntity extends AnimatedPhysicsGameEntity{

	boolean canJump, grounded;
	float jumpForce;
	private long cooldownTime;
	float jumpCountdown,canStilJumpTime;
	
	public PlayerGameEntity(Vector2f pos, Vector2f size, float rot, String path, int state, int stateCount,
			float secPerState, Vector2f vel, Vector2f acc, float jumpForce, float jumpCountdown) {
		super(pos, size, rot, path, state, stateCount, secPerState, vel, acc);
		this.jumpForce = jumpForce;
		this.jumpCountdown = jumpCountdown*1000;
	}
	
	public void updateInput(){
		if ((Keyboard.isKeyDown(Keyboard.KEY_W)||Keyboard.isKeyDown(Keyboard.KEY_SPACE))&&grounded) {
			vel.setY(-jumpForce);
			//setY(getY()-jumpForce);
			canJump = false;
		} 
		if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
			Vector2f.add(vel,new Vector2f(-0.1f,0),vel);
		} if (Keyboard.isKeyDown(Keyboard.KEY_D)){
			//setVelX(getVelX()+0.1f);
			Vector2f.add(vel,new Vector2f(0.1f,0),vel);
		}
		updatePos();
	}
	
	public void update(){
		if(!grounded){
			cooldownTime = Main.getTime();
		}
		else if(Main.getTime() - cooldownTime >= jumpCountdown){
			canJump = true;
			cooldownTime = Main.getTime();
		}
	}
}
