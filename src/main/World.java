package main;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.lwjgl.util.vector.Vector2f;

public class World {
	
	List<TexturedGameEntity> texturedGameEntities = new ArrayList<TexturedGameEntity>();
	List<AnimatedGameEntity> animatedGameEntities = new ArrayList<AnimatedGameEntity>();
	List<TexturedPhysicsGameEntity> texturedPhysicsEntities = new ArrayList<TexturedPhysicsGameEntity>();
	List<AnimatedPhysicsGameEntity> animatedPhysicsEntities = new ArrayList<AnimatedPhysicsGameEntity>();
	public int levelWidth, groundHeight;
	private static TexturedGameEntity background;
	//private static TexturedGameEntity[] background = new TexturedGameEntity[8];
	private static Line ground;
	float frictionGround = 1.005f;
	
	public World(int worldNumber){
		readWorld(worldNumber);
	}	
	
	PlayerGameEntity p;
	public void update() {
		p = Main.p;
	// Map Collision
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
			a.updateState(Direction.RIGHT);
			Direction surface = Collision.aCollision(Main.p, a);
			if(surface == Direction.TOP){
				Main.p.vel.x /= a.friction;
				Main.p.grounded = true;
			}
		}
		for(AnimatedGameEntity a:animatedGameEntities){
			a.updateState(Direction.RIGHT);
		}
		
		for(TexturedPhysicsGameEntity tp:texturedPhysicsEntities){
			
		}
		//updateBackground();
	}
	
//	float leftEdge = 0, rightEdge = Main.screenWidth;
//	public void updateBackground(){
//		for(TexturedGameEntity b:background){
//			if(p.pos.x - (b.pos.x+b.size.x)>Main.screenWidth){
//				b.pos.x = rightEdge;
//				rightEdge += b.size.x;
//			} else if(b.pos.x -p.pos.x<Main.screenWidth){
//				b.pos.x = leftEdge - b.size.x;
//				leftEdge -= b.size.x;
//			}
//		}
//	}
	
	public void render() {
		background.render();
//		for(TexturedGameEntity b:background){
//			b.render();
//		}
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
		ground.render();
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
		while(scanner.hasNextLine()){
			//process each line
			String line = scanner.nextLine();
			String[]object = line.split(",");
			switch (object[0]) {
			case "init":
//				texturedGameEntities = new TexturedGameEntity[Integer.parseInt(object[1])];
//				animatedGameEntities = new AnimatedGameEntity[Integer.parseInt(object[2])];
//				texturedPhysicsEntities = new TexturedPhysicsGameEntity[Integer.parseInt(object[3])];
//				animatedPhysicsEntities = new AnimatedPhysicsGameEntity[Integer.parseInt(object[4])];
				ground = new Line(new Vector2f(0,0),new Vector2f(levelWidth, Main.screenHeight));
				background = new TexturedGameEntity(new Vector2f(0,0), new Vector2f(levelWidth, Main.screenHeight*2), "world"+worldNumber+"/background");///background");
//				for(int i = 0; i < background.length; i++){
//					if(i < 4){
//						background[i] = new TexturedGameEntity(new Vector2f(i*(Main.screenWidth/4),0), new Vector2f(Main.screenHeight/2, Main.screenHeight/2), "anim");
//					} else {
//						background[i] = new TexturedGameEntity(new Vector2f((i-4)*(Main.screenWidth/4),Main.screenHeight/2), new Vector2f(Main.screenHeight/2, Main.screenHeight/2), "anim");
//					}
//					
//				};
				break;
			case "settings":
				levelWidth = Integer.parseInt(object[1]);
				//groundHeight = Integer.parseInt(object[2]);
				break;
			case "textured":
				texturedGameEntities.add(new TexturedGameEntity(new Vector2f(Integer.parseInt(object[1]),Integer.parseInt(object[2])), //pos
						new Vector2f(Integer.parseInt(object[3]), Integer.parseInt(object[4])), //size
						"world"+worldNumber+"/"+object[6])); //path
				break;
			case "animated":
				animatedGameEntities.add(new AnimatedGameEntity(new Vector2f(Integer.parseInt(object[1]),Integer.parseInt(object[2])),
						new Vector2f(Integer.parseInt(object[3]), Integer.parseInt(object[4])),
						"world"+worldNumber+"/"+object[6],
						Integer.parseInt(object[7]), Integer.parseInt(object[8]), Float.parseFloat(object[9]))); //animation
				break;
			case "texturedphysics":
				texturedPhysicsEntities.add(new TexturedPhysicsGameEntity(new Vector2f(Integer.parseInt(object[1]),Integer.parseInt(object[2])),
						new Vector2f(Integer.parseInt(object[3]), Integer.parseInt(object[4])),
						"world"+worldNumber+"/"+object[6],
						new Vector2f(Float.parseFloat(object[7]), Float.parseFloat(object[8])),
						new Vector2f(Float.parseFloat(object[9]), Float.parseFloat(object[10])))); //
				break;
			case "animatedphsyics":
				animatedPhysicsEntities.add(new AnimatedPhysicsGameEntity(new Vector2f(Integer.parseInt(object[1]),Integer.parseInt(object[2])),
						new Vector2f(Integer.parseInt(object[3]), Integer.parseInt(object[4])),
						"world"+worldNumber+"/"+object[6],
						Integer.parseInt(object[7]), Integer.parseInt(object[8]), Float.parseFloat(object[9]),
						new Vector2f(Float.parseFloat(object[10]), Float.parseFloat(object[11])),
						new Vector2f(Float.parseFloat(object[12]), Float.parseFloat(object[13])),
						Float.parseFloat(object[14])));
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
