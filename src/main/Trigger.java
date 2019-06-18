package main;

import org.lwjgl.util.vector.Vector2f;

public class Trigger extends TexturedGameEntity{

	public Trigger(Vector2f pos, Vector2f size, String path) {
		super(pos, size, path);
		// TODO Auto-generated constructor stub
	}

	public void update(AnimatedPhysicsGameEntity player){
		if(Collision.detectCollision(player, this)){
			player.pos = new Vector2f(Main.screenWidth/2,200);
		}
	}
}
