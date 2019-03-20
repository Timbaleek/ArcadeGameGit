package mainSAT;

import org.lwjgl.opengl.GL11;

public class GameEntity {
	private Vector[] points;
	private Vector position;
	
	
	public GameEntity(Vector position){
		this.points = new Vector[0];
		this.position = position;
	}
	
	public void render(){
		GL11.glBegin(GL11.GL_POLYGON);
		for(int i = 0; i<getPointCount(); i++){
			GL11.glVertex2f(getPoint(i).x+getPosition().x,getPoint(i).y+getPosition().y);
		}
	    GL11.glEnd();
	}
	
	public void setPointsNumber(int n){
		points = new Vector[n];
	}
	
	public void setPoint(int i, Vector v)
    {
        points[i] = v;
    }
	
	public void setPosition(float x, float y)
    {
        position = new Vector(x, y);
    }
	
	public Vector getPoint(int n){
		return points[n];
	}
	
	public Vector getPosition()
    {
        return position;
    }

    public int getPointCount()
    {
        return points.length;
    }
}
