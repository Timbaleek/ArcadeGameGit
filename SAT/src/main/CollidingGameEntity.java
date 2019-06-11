package main;

import java.util.ArrayList;

import org.lwjgl.opengl.GL11;

import util.CollisionLoader;
import util.CollisionUtil;
import util.Pair;
import util.Vec;

public class CollidingGameEntity extends GameEntity{

	ArrayList<Vec> edges = new ArrayList<Vec>();
	//ArrayList<Vec> normals = new ArrayList<Vec>();
	ArrayList<Vec> axes = new ArrayList<Vec>();
	ArrayList<Pair> points = new ArrayList<Pair>();
	ArrayList<Pair> absPoints = new ArrayList<Pair>();
	Pair delta = new Pair(0,0);
	Pair startPos;
	Pair vel = new Pair(0,0);
	
	public ArrayList<Pair> getPoints() {
		return points;
	}

	public void translate(float x, float y) {
		delta = new Pair(pos.x-startPos.x,pos.y-startPos.y);
		for(int i = 0; i<points.size(); i++){
			points.set(i, new Pair(points.get(i).x+x,points.get(i).y+y));
		}
	}
	
	public CollidingGameEntity(Pair pos, float size, String name) {
		super(pos, size);
		
		points = CollisionLoader.load(name, size);
		
		
		for(int i = 0; i<points.size(); i++){
			points.set(i, new Pair(points.get(i).x+pos.x,points.get(i).y+pos.y));
		}
		
		startPos = points.get(0);
		
		ArrayList<Pair> oris = new ArrayList<Pair>();
		//last edge and normal
//		Vec le = new Vec(points.get(points.size()-1),points.get(0));
//		edges.add(le);
//		normals.add(le.getNormal());
//		oris.add(le.getNormal().getOri());
		//edges and normals
		for(int i = 0; i < points.size(); i++){
			Vec e = new Vec(points.get(i),points.get((i+1)%(points.size())));
			edges.add(e);
			Vec n = e;//.getNormal();
			
			//Avoid doubles
			Pair a = n.getOri();
			boolean found = false;
			for(int j = 0; j<oris.size(); j++){
				if((a.x == oris.get(j).x && a.y == oris.get(j).y)||(a.x == -oris.get(j).x && a.y == -oris.get(j).y)){
					System.out.println("Deleted: " + n.print());
					found = true;
				}
			}
			if(found == false){
				oris.add(a);
				axes.add(n.getUnitVec());
				//normals.add(n.getUnitVec());
			}
		}
	}
	
	public Vec collisionDetection(CollidingGameEntity other){
		float minDist = 1999999999;
		Vec smallestOverlapAxis = null;
		for(int i = 0; i<axes.size(); i++){
			//thisMax < otherMin or thisMin > otherMax
			Pair p1 = CollisionUtil.getMinMax(this.points,axes.get(i))[0];
			Pair p2 = CollisionUtil.getMinMax(other.points,axes.get(i))[0];
			//x=min, y=max
			float dist = CollisionUtil.getIntervalDistance(p1.x,p1.y,p2.x,p2.y);
			//System.out.println(dist);
			if(dist>=0){return null;} //no collision
			if(dist < minDist){
				minDist = dist;
				smallestOverlapAxis = axes.get(i);
				System.out.println("player: " + i);
			}
		}
		for(int i = 0; i<other.axes.size(); i++){
			//thisMax < otherMin or thisMin > otherMax
			Pair p1 = CollisionUtil.getMinMax(this.points,other.axes.get(i))[0];
			Pair p2 = CollisionUtil.getMinMax(other.points,other.axes.get(i))[0];
			float dist = CollisionUtil.getIntervalDistance(p1.x,p1.y,p2.x,p2.y);
			//System.out.println(dist);
			if(dist>=0){return null;} //no collision
			if(dist < minDist){
				minDist = dist;
				smallestOverlapAxis = axes.get(i);
				System.out.println("object: " + i);
			}
		}
		return smallestOverlapAxis; //a collision
	}
	
	public Vec collisionResponse(CollidingGameEntity other){
		Vec smallestOverlapAxis = collisionDetection(other);
		if(smallestOverlapAxis!=null){
			Vec normal = smallestOverlapAxis.getNormal();
			System.out.println(normal.print());
			normal.render(pos);
			float len = smallestOverlapAxis.getLength();
			return smallestOverlapAxis.scale(len/normal.getLength()); // mvt
		}
		return null;
	}
	
	public boolean isColliding(CollidingGameEntity other){
		if(collisionDetection(other)!=null){
			return true;
		}
		return false;
	}
	
	public void update(){
		if(vel.x != 0 || vel.y != 0){
			translate(vel.x, vel.y);
			vel.x /= 1.02f;
			vel.y /= 1.02f;
		}
	}
	

	public void render(){
		GL11.glColor3f(0, 0, 255);
		GL11.glBegin(GL11.GL_POLYGON);
		for(Pair p:points){
			GL11.glVertex2f(p.x,p.y);
		}
	    GL11.glEnd();
/*	   
	    GL11.glLineWidth(3.8f);
	    GL11.glColor3f(255, 0, 0);
	    for(Vec e:edges){
	    	e.render(delta);
	    }
	    GL11.glColor3f(0, 255, 0);
	    for(Vec n:normals){
	    	n.render(delta);
	    }*/
	}
}
