package main;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector2f;

import arduinoCom.ArduinoCommunication;

public class PlayerGameEntity extends AnimatedPhysicsGameEntity{

	boolean canJump, grounded;
	Direction isWalking;
	float jumpForce;
	private long cooldownTime;
	float jumpCountdown,canStilJumpTime;

	public PlayerGameEntity(Vector2f pos, Vector2f size, String path, int state, int stateCount,
			float secPerState, Vector2f vel, Vector2f acc, float friction, float jumpForce, float jumpCountdown) {
		super(pos, size, path, state, stateCount, secPerState, vel, acc,friction);
		this.jumpForce = jumpForce;
		this.jumpCountdown = jumpCountdown*1000;
	}

	public void updateInput(){
//		if ((Keyboard.isKeyDown(Keyboard.KEY_W)||Keyboard.isKeyDown(Keyboard.KEY_SPACE))&&grounded) {
//			vel.setY(-jumpForce);
//			canJump = false;
//		}
//		if (Keyboard.isKeyDown(Keyboard.KEY_A)){
//			Vector2f.add(vel,new Vector2f(-0.01f,0),vel);
//			isWalking = Direction.LEFT;
//		} else if (Keyboard.isKeyDown(Keyboard.KEY_D)){
//			Vector2f.add(vel,new Vector2f(0.01f,0),vel);
//			isWalking = Direction.RIGHT;
//		} //else {
//			isWalking = Direction.NONE;
//		}
		String cs = ArduinoCommunication.currentInput;
		//System.out.println(cs);
		//System.out.println(Character.getNumericValue(cs.charAt(0)));
		if(Character.getNumericValue(cs.charAt(0)) == 1){
			System.out.println("Walk LEFT");
			Vector2f.add(vel,new Vector2f(-0.01f,0),vel);
			isWalking = Direction.LEFT;
		} 
		if (Character.getNumericValue(cs.charAt(1)) == 1){
			System.out.println("Walk RIGHT");
			Vector2f.add(vel,new Vector2f(0.01f,0),vel);
			isWalking = Direction.RIGHT;
		} 
		if (Character.getNumericValue(cs.charAt(2)) == 0&&grounded) {
			System.out.println("JUMP");
			vel.setY(-jumpForce);
			canJump = false;
		}
//		if ((Keyboard.isKeyDown(Keyboard.KEY_W)||Keyboard.isKeyDown(Keyboard.KEY_SPACE))&&grounded) {
//			vel.setY(-jumpForce);
//			canJump = false;
//		}
//		if (Keyboard.isKeyDown(Keyboard.KEY_A)){
//			Vector2f.add(vel,new Vector2f(-0.01f,0),vel);
//			isWalking = Direction.LEFT;
//		} else if (Keyboard.isKeyDown(Keyboard.KEY_D)){
//			Vector2f.add(vel,new Vector2f(0.01f,0),vel);
//			isWalking = Direction.RIGHT;
//		} else {
//			isWalking = Direction.NONE;
//		}
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
