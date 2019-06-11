package main;

import org.lwjgl.input.Keyboard;

import util.Pair;

public class Player extends CollidingGameEntity{
	
	public Player(Pair pos, float size, String name) {
		super(pos, size, name);
	}

	public void updateInput(){
			if ((Keyboard.isKeyDown(Keyboard.KEY_A))){
				//pos.x -= 0.1;
				vel.x -= 0.01;
			}
			if ((Keyboard.isKeyDown(Keyboard.KEY_D))){
				//pos.x += 0.1;
				vel.x += 0.01;
				//translate(+0.1f,0);
			}
			if ((Keyboard.isKeyDown(Keyboard.KEY_W))){
				//pos.y -= 0.1;
				vel.y -= 0.01;
				//translate(0,-0.1f);
			}
			if ((Keyboard.isKeyDown(Keyboard.KEY_S))){
				//pos.y += 0.1;
				vel.y += 0.01;
				//translate(0,+0.1f);
			}
	}
}
