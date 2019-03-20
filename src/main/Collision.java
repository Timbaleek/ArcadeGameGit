package main;

import org.lwjgl.util.vector.Vector2f;

public class Collision {
	//static Vector2f normal = new Vector2f();
	
	// g1 = dynamic, g2 = static
	public static boolean detectCollision(AnimatedPhysicsGameEntity g1, GameEntity g2) {
		if ((g1.pos.x+g1.vel.x < g2.pos.x + g2.size.x && g1.pos.x + g1.size.x + g1.vel.x > g2.pos.x)&&(g1.pos.y + g1.vel.y < g2.pos.y + g2.size.y && g1.pos.y + g1.size.y + g1.vel.y > g2.pos.y)){
			// COLLISON
			return true;
		}
		return false;
	}
	
	public static Surface aCollision(AnimatedPhysicsGameEntity g1, GameEntity g2){ //left 0, top 1, right 2, bottom 3
		Vector2f timeToCollide = new Vector2f(0,0);
		if(detectCollision(g1, g2)){
			if(g1.vel.x > 0){
				timeToCollide.x = ((g1.pos.x+g1.size.x) - g2.pos.x)/g1.vel.x;
				if(g1.vel.y > 0){
					timeToCollide.y = ((g1.pos.y+g1.size.y) - g2.pos.y)/g1.vel.y;
					System.out.println(timeToCollide);
					if(timeToCollide.x<timeToCollide.y){
						g1.pos.x = g2.pos.x - g1.size.x;
						g1.vel.x = 0;
						return Surface.LEFT;
					} else {
						g1.pos.y = g2.pos.y - g1.size.y;
						g1.vel.y = 0;
						return Surface.TOP;
					}
				} else{
					timeToCollide.y = (g1.pos.y - (g2.pos.y + g2.size.y))/g1.vel.y;
					if(timeToCollide.x<timeToCollide.y){
						g1.pos.x = g2.pos.x - g1.size.x;
						g1.vel.x = 0;
						return Surface.LEFT;
					} else {
						g1.pos.y = g2.pos.y + g2.size.y;
						g1.vel.y = 0;
						return Surface.BOTTOM;
					}
				}
			} else {
				timeToCollide.x = (g1.pos.x - (g2.pos.x + g2.size.x))/g1.vel.x;
				if(g1.vel.y > 0){
					timeToCollide.y = ((g1.pos.y+g1.size.y) - g2.pos.y)/g1.vel.y;
					if(timeToCollide.x<timeToCollide.y){
						g1.pos.x = g2.pos.x + g2.size.x;
						g1.vel.x = 0;
						return Surface.RIGHT;
					} else {
						g1.pos.y = g2.pos.y - g1.size.y;
						g1.vel.y = 0;
						return Surface.TOP;
					}
				} else{
					timeToCollide.y = (g2.pos.y - (g1.pos.y+g1.size.y))/g1.vel.y;
					if(timeToCollide.x<timeToCollide.y){
						g1.pos.x = g2.pos.x + g2.size.x;
						g1.vel.x = 0;
						return Surface.RIGHT;
					} else {
						g1.pos.y = g2.pos.y + g2.size.y;
						g1.vel.y = 0;
						return Surface.BOTTOM;
					}
				}
			}
		}
		return Surface.NONE;
	}
	
//	public static void collisionResponse(AnimatedPhysicsGameEntity g1, GameEntity g2){
		/*float collisiontime = aCollision(g1, g2);
		
		System.out.println(collisiontime);
		System.out.println(normal);
		g1.pos.x += g1.vel.x * collisiontime;
		g1.pos.y += g1.vel.y * collisiontime;
		
		float projection = (g1.vel.x*normal.y + g1.vel.y*normal.x) * (1-collisiontime);
		g1.vel.x = projection * normal.y;
		g1.vel.y = projection * normal.x;*/
//	}
	

//	public static void resolveCollision(AnimatedPhysicsGameEntity g1, GameEntity g2, Vector2f overlap){
//		if(overlap != null){
//			if(overlap.x < g2.size.x / 2){
//				Vector2f.sub(g2.pos, g1.size, g1.pos);
//			} else{
//				Vector2f.add(g2.pos, g2.size, g1.pos);
//			}
//		}
		
		
//		if(g1.vel.x > 0){
//			//Vector2f.add(g1.pos, (Vector2f) g1.vel.scale(g1.pos.x + g1.size.x + g1.vel.x-g2.pos.x), g1.pos);
//			//g1.pos.x += -(g1.vel.x*(g2.pos.x/(g1.pos.x + g1.size.x + g1.vel.x)));
//			g1.pos.x = g2.pos.x-g1.size.x;
//			g1.vel.x = 0;
//		} else {
//			g1.vel.x = 0;
//			g1.pos.x = g2.pos.x + g2.size.x;
//		}
//		
//		if(g1.vel.y < 0){
//			//g1.pos.y += -(g1.vel.y*(g2.pos.y/(g1.pos.y + g1.size.y + g1.vel.y)));
//			g1.pos.y = g2.pos.y + g1.size.y;
//			g1.vel.y = 0;
//		} else {
//			g1.vel.y = 0;
//			g1.pos.y = g2.pos.y - g2.size.y;
//		}
		
		// set X
//		if (g1.pos.x + /*g1.size.x +*/ g1.vel.x > g2.pos.x + g2.size.x) {
//			g1.vel.x = 0;
//			g1.pos.x = g2.pos.x + g2.size.x;
//		} else if (g1.pos.x + g1.vel.x < g2.pos.x) {
//			g1.vel.x = 0;
//			g1.pos.x = g2.pos.x - g1.size.x;
//		}
		// set Y
//		if (g1.pos.y + /*g1.size.y +*/ g1.vel.y > g2.pos.y + g2.size.y) {
//			g1.vel.y = 0;
//			g1.pos.y = g2.pos.y + g2.size.y;
//		} else if (g1.pos.y + g1.vel.y < g2.pos.y) {
//			g1.vel.y = 0;
//			g1.pos.y = g2.pos.y - g1.size.y;
//		
//		}
//	}

//	public static float SwiftCollision(AnimatedPhysicsGameEntity g1, GameEntity g2){
//		Vector2f entry = new Vector2f(), exit = new Vector2f();
//		float[] timeToEntry = new float[2], timeToExit = new float[2];
//		float entryTime, exitTime;
//		
//		if(g1.vel.x > 0){
//			entry.x = g2.pos.x - (g1.pos.x + g1.size.x);
//			exit.x = (g2.pos.x + g2.size.x) - g1.pos.x;
//		} else {
//			entry.x = (g2.pos.x + g2.size.x) - g1.pos.x;
//			exit.x = g2.pos.x - (g1.pos.x + g1.size.x);
//		}
//		if(g1.vel.y > 0){
//			entry.y = g2.pos.y - (g1.pos.y + g1.size.y);
//			exit.y = (g2.pos.y + g2.size.y) - g1.pos.y;
//		} else {
//			entry.y = (g2.pos.y + g2.size.y) - g1.pos.y;
//			exit.y = g2.pos.y - (g1.pos.y + g1.size.y);
//		}
//		
//		if(g1.vel.x == 0){
//			timeToEntry[0] = Float.POSITIVE_INFINITY;
//			timeToExit[0] = Float.POSITIVE_INFINITY;
//		} else {
//			timeToEntry[0] = entry.x / g1.vel.x;
//			timeToExit[0] = exit.x / g1.vel.x;
//		}
//		if(g1.vel.y == 0){
//			timeToEntry[1] = Float.POSITIVE_INFINITY;
//			timeToExit[1] = Float.POSITIVE_INFINITY;
//		} else {
//			timeToEntry[1] = entry.y / g1.vel.y;
//			timeToExit[1] = exit.y / g1.vel.y;
//		}
//		
//		entryTime = Util.getMax(timeToEntry);
//		exitTime = Util.getMin(timeToExit);
//		
//		if(entryTime > exitTime || entry.x < 0 && entry.y < 0 || entry.x > 1 || entry.x > 1){
//			normal.x = 0.0f;
//			normal.y = 0.0f;
//			return 0;
//		} else {
//			if(timeToEntry[0] < timeToEntry[1]){
//				if(entry.x < 0){
//					normal.x = 1.0f;
//	                normal.y = 0.0f;
//	            }
//		        else
//	            {
//	                normal.x = -1.0f;
//	                normal.y = 0.0f;
//	            }
//	        }
//	        else
//	        {
//	        	if(entry.y < 0){
//					normal.x = 0.0f;
//	                normal.y = 1.0f;
//	            }
//		        else
//	            {
//	                normal.x = 0.0f;
//	                normal.y = -1.0f;
//	            }
//			}
//			return entryTime;
//		}
//	}
}
