package main;

import org.lwjgl.util.vector.Vector2f;

public class AnimatedPhysicsGameEntity extends AnimatedGameEntity{

	//private int x, y;
	Vector2f vel, acc;
	//float velX,velY,accX,accY;
	float friction;
	
	public AnimatedPhysicsGameEntity(Vector2f pos, Vector2f size, String path, int state, int stateCount,
			float secPerState, Vector2f vel, Vector2f acc, float friction) {
		super(pos, size, path, state, stateCount, secPerState);
		this.vel = vel;
		this.acc = acc;
		this.friction = friction;
//		this.velX = velX;
//		this.velY = velY;
//		this.accX = accX;
//		this.accY = accY;
	}

	public void updateMovement(){
		//setPos(new Vector2f(getPos().x,getPos().y));
		Vector2f.add(vel,acc,vel);
		Vector2f.add(pos,vel,pos);
		System.out.println(vel);
		System.out.println(pos);
	}

//	public boolean detectYCollision(GameEntity c){
//		if(c.getY()+c.getHeight() > this.getY() && c.getY()+c.getY() < this.getY() + this.getHeight()){
//			System.out.println("y");
//			return true;
//		}
//		return false;
//	}
//	
//	public boolean detectXCollision(AnimatedPhysicsGameEntity c){
////		System.out.println(getX()+getWidth() + ">" + c.getX());
////		System.out.println(getX() + "<" + (c.getX()+c.getWidth()));
//		if(c.getX()+c.getVelX()+c.getWidth() > this.getX() && c.getX()+c.getVelX() < this.getX()+this.getWidth()){
//			System.out.println("x");
//			return true;
//		}
//		return false; 
//	}
//	
//	public void doCollision(GameEntity b){
//		if(this.getY()<b.getY()+b.getHeight()){
//			this.setVelY(0);
//		}
//		if(this.getY()>b.getY()){
//			this.setVelY(0);
//		}
//		if(this.getX()<b.getX()+b.getWidth()){
//			this.setVelX(0);
//		}
//		if(this.getX()>b.getX()){
//			this.setVelX(0);
//		}
//	}

}
