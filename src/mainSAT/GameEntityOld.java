package mainSAT;

import org.lwjgl.opengl.GL11;

public class GameEntityOld {
	
	public Polygon polygon;
	
	public GameEntityOld(Polygon polygon){
		this.polygon = polygon;
	}
	
	public void render(){
		GL11.glBegin(GL11.GL_POLYGON);
		for(int i = 0; i<polygon.getPointCount(); i++){
			GL11.glVertex2f(polygon.getPoint(i).x+polygon.getPosition().x,polygon.getPoint(i).y+polygon.getPosition().y);
		}
	    GL11.glEnd();
	}
	
	public void setPolygon(Polygon polygon) {
		this.polygon = polygon;
	}
	
	public Polygon getPolygon() {
		return polygon;
	}
}
