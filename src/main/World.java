package main;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import org.lwjgl.util.vector.Vector2f;

public class World {
	
	TexturedGameEntity[] texturedGameEntities;
	AnimatedGameEntity[] animatedGameEntities;
	TexturedPhysicsGameEntity[] texturedPhysicsEntities;
	AnimatedPhysicsGameEntity[] animatedPhysicsEntities;
	public int levelWidth, groundHeight;
	private static TexturedGameEntity background;
	float frictionGround = 1.005f;
	
	public World(int worldNumber){
		readWorld(worldNumber);
	}	
	
	PlayerGameEntity p;
	public void update() {
		p = Main.p;
		if(p.pos.x+p.vel.x<Main.xCor){
			p.vel.setX(0);
			p.pos.x = Main.xCor;
		} else if (p.pos.x+p.getSize().x+p.vel.x>levelWidth-Main.xCor){
			p.vel.setX(0);
			p.pos.x = levelWidth-Main.xCor-p.size.x;
		}
		if(p.pos.y+p.getSize().y+p.vel.y>Main.screenHeight-groundHeight){
			p.vel.setY(0);
			p.pos.y = Main.screenHeight-groundHeight - p.size.y;
			p.vel.x /= frictionGround;
			p.grounded=true;
		} else{
			p.grounded=false;
		}
		
		for(AnimatedPhysicsGameEntity a:animatedPhysicsEntities){
			a.updateState(true);
			//Collision.resolveCollision(Main.p, a, Collision.detectCollision(Main.p, a));
			Surface surface = Collision.aCollision(Main.p, a);
			if(surface == Surface.TOP){
				Main.p.vel.x /= a.friction;
				Main.p.grounded = true;
			}
		}
		for(AnimatedGameEntity a:animatedGameEntities){
			a.updateState(true);
		}
		
		for(TexturedPhysicsGameEntity tp:texturedPhysicsEntities){
//			if(Main.p.detectCollision(tp)){
//				Main.p.doCollision(tp);
//			}
		}
//		for(AnimatedPhysicsGameEntity ap:animatedPhysicsEntities){
//			if(ap.detectXCollision(Main.p)){
//				if(ap.detectYCollision(Main.p)){
//				Main.p.setVelX(0);
//				}
//			} if(ap.detectYCollision(Main.p)){
//				if(ap.detectXCollision(Main.p)){
//				Main.p.setVelY(0);
//				}
//			}
//			else{
//				System.out.println("nocoll");
//			}
//		}
		
	}
	
	public void render() {
		background.render();
		for(TexturedGameEntity t:texturedGameEntities){
			t.render();
		}
		for(AnimatedGameEntity a:animatedGameEntities){
			a.render();
		}
		for(TexturedPhysicsGameEntity tp:texturedPhysicsEntities){
			tp.render();
		}
		for(AnimatedPhysicsGameEntity ap:animatedPhysicsEntities){
			ap.render();
		}
	}

	private void readWorld(int worldNumber) {
		Path path = Paths.get("res/world" + worldNumber + "/world.txt");
		Scanner scanner = null;
		try {
			scanner = new Scanner(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Reading World: " + worldNumber);
		//read line by line
		int t = 0, a = 0, tp = 0, ap = 0;
		while(scanner.hasNextLine()){
		    //process each line
		    String line = scanner.nextLine();
		    String[]object = line.split(",");
		    switch (object[0]) {
		    case "init":
		    	texturedGameEntities = new TexturedGameEntity[Integer.parseInt(object[1])];
		    	animatedGameEntities = new AnimatedGameEntity[Integer.parseInt(object[2])];
		    	texturedPhysicsEntities = new TexturedPhysicsGameEntity[Integer.parseInt(object[3])];
		    	animatedPhysicsEntities = new AnimatedPhysicsGameEntity[Integer.parseInt(object[4])];
				background = new TexturedGameEntity(new Vector2f(0,0), new Vector2f(levelWidth, Main.screenHeight), "world"+worldNumber+"/background");
		    	break;
		    case "settings":
		    	levelWidth = Integer.parseInt(object[1]);
		    	groundHeight = Integer.parseInt(object[2]);
		    	break;
			case "textured":
				texturedGameEntities[t] = new TexturedGameEntity(new Vector2f(Integer.parseInt(object[1]),Integer.parseInt(object[2])), //pos
						new Vector2f(Integer.parseInt(object[3]), Integer.parseInt(object[4])), //size
						"world"+worldNumber+"/"+object[6]); //path
			    t++;
				break;
			case "animated":
				animatedGameEntities[a] = new AnimatedGameEntity(new Vector2f(Integer.parseInt(object[1]),Integer.parseInt(object[2])),
						new Vector2f(Integer.parseInt(object[3]), Integer.parseInt(object[4])),
						"world"+worldNumber+"/"+object[6],
						Integer.parseInt(object[7]), Integer.parseInt(object[8]), Float.parseFloat(object[9])); //animation
				a++;
				break;
			case "texturedphysics":
				texturedPhysicsEntities[tp] = new TexturedPhysicsGameEntity(new Vector2f(Integer.parseInt(object[1]),Integer.parseInt(object[2])),
						new Vector2f(Integer.parseInt(object[3]), Integer.parseInt(object[4])),
						"world"+worldNumber+"/"+object[6],
						new Vector2f(Float.parseFloat(object[7]), Float.parseFloat(object[8])),
						new Vector2f(Float.parseFloat(object[9]), Float.parseFloat(object[10]))); //
				tp++;
				break;
			case "animatedphsyics":
				animatedPhysicsEntities[ap] = new AnimatedPhysicsGameEntity(new Vector2f(Integer.parseInt(object[1]),Integer.parseInt(object[2])),
						new Vector2f(Integer.parseInt(object[3]), Integer.parseInt(object[4])),
						"world"+worldNumber+"/"+object[6],
						Integer.parseInt(object[7]), Integer.parseInt(object[8]), Float.parseFloat(object[9]),
						new Vector2f(Float.parseFloat(object[10]), Float.parseFloat(object[11])),
						new Vector2f(Float.parseFloat(object[12]), Float.parseFloat(object[13])),
						Float.parseFloat(object[14]));
				ap++;
				break;
			default:
				System.out.println("Error reading file");
				break;
			}
		    System.out.println(line);
		}
		scanner.close();
	}
}
