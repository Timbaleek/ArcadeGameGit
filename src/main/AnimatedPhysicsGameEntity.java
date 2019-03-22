package main;

import org.lwjgl.util.vector.Vector2f;

public class AnimatedPhysicsGameEntity extends AnimatedGameEntity{

	Vector2f vel, acc;
	float friction;
	
	public AnimatedPhysicsGameEntity(Vector2f pos, Vector2f size, String path, int state, int stateCount,
			float secPerState, Vector2f vel, Vector2f acc, float friction) {
		super(pos, size, path, state, stateCount, secPerState);
		this.vel = vel;
		this.acc = acc;
		this.friction = friction;
	}

	public void updateMovement(){
		Vector2f.add(vel,acc,vel);
		Vector2f.add(pos,vel,pos);
	}

}
