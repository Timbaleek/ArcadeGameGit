package mainSAT;

import org.lwjgl.util.vector.Vector2f;

public class TexturedPhysicsGameEntity extends TexturedGameEntity{

	//private int x, y;
	float velX,velY,accX,accY;
	
	public TexturedPhysicsGameEntity(Vector2f pos, Vector2f size, float rot, String path, Vector2f vel, Vector2f acc) {
		super(pos, size, rot, path);
		this.velX = velX;
		this.velY = velY;
		this.accX = accX;
		this.accY = accY;
	}

	public void updateMovement(){
		setX((getX()+velX));
		setY((getY()+velY));
		velX /= 1.02f;
		velY /= 1.02f;
		velX -= accX;
		velY -= accY;
	}
	
	public boolean detectCollision(GameEntity c){
		if(getX()+getWidth()+getVelX()>c.getX() && c.getX()+c.getWidth()>getX()+getVelX() || getY()+getHeight()+getVelY()>c.getY() && c.getY()+c.getHeight()>getY()+getVelY()){
			System.out.println("Coll");
			return true;
		}
		return false;
	}
	
	public void doCollision(GameEntity b){
		if(this.getY()<b.getY()+b.getHeight()){
			this.setVelY(0);
		}
		if(this.getY()>b.getY()){
			this.setVelY(0);
		}
		if(this.getX()<b.getX()+b.getWidth()){
			this.setVelX(0);
		}
		if(this.getX()>b.getX()){
			this.setVelX(0);
		}
	}

	public float getVelX() {
		return velX;
	}

	public void setVelX(float velX) {
		this.velX = velX;
	}

	public float getVelY() {
		return velY;
	}

	public void setVelY(float velY) {
		this.velY = velY;
	}
	

	public float getAccX() {
		return accX;
	}

	public void setAccX(float accX) {
		this.accX = accX;
	}

	public float getAccY() {
		return accY;
	}

	public void setAccY(float accY) {
		this.accY = accY;
	}
}
