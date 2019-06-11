package util;

import org.lwjgl.opengl.GL11;

public class Vec {
	Pair pos,dir;
	public Pair axisLen;
	float len;
	
	public Vec(Pair pos, Pair dir){
		this.pos = pos;
		this.dir = dir;
		axisLen = new Pair(dir.x-pos.x, dir.y-pos.y);
		len = getLength();
	}
	
	public float getLength(){
		return (float) Math.sqrt(axisLen.x*axisLen.x + axisLen.y*axisLen.y);
	}
	
	public Vec getUnitVec(){
		
		//Vec unitVec = new Vec(pos,new Pair((axisLen.x)/len+pos.x,(axisLen.y)/len+pos.y));
		//unitVec.len = 1;
		return this.scale(1/getLength());
	}
	
	public Vec scale(float s){
		return new Vec(pos,new Pair(((axisLen.x)*s)+pos.x,((axisLen.y)*s)+pos.y));
	}
	
	public Vec getNormal(){
		Pair mid = scale(0.5f).dir;
		Pair b = new Pair(axisLen.y+mid.x,-axisLen.x+mid.y);
		return new Vec(mid,b);
	}
	
	public float angleDotProduct(Vec other, double angle){
		return (float) (len*other.len*Math.cos(angle));
	}
	
	public float dotProduct(Vec other){
		return axisLen.x*other.axisLen.x + axisLen.y*other.axisLen.y;
	}
	
	public boolean isSameDir(Vec other){
		if(this.getUnitVec().dir == other.getUnitVec().dir){
			return true;
		}
		return false;
	}
	
	public String print(){
		return ("Pos: " + pos.print() + ", Dir: " + dir.print());
	}
	
	public Pair getPos(){return pos;} //Position (Origin to A)
	public Pair getDir(){return dir;} //Direction (A to B)
	public Pair getOri(){return new Pair(axisLen.x/len,axisLen.y/len);} //Orientation
	
	public void render(Pair point){
		 GL11.glBegin(GL11.GL_LINE_STRIP);
		 GL11.glVertex2f(point.x+pos.x,point.y+pos.y);
		 GL11.glVertex2f(point.x+dir.x,point.y+dir.y);
		 GL11.glEnd();
	}
}
