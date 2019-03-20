package mainSAT;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Color;
import org.lwjgl.util.vector.Vector2f;

public class ColoredGameEntity extends GameEntity {

	private Color c;

	public ColoredGameEntity(Vector position, Color c) {
		super(position);
		this.c = c;
	}

	public void render() {
		GL11.glColor3f(c.getRed(), c.getGreen(), c.getBlue());
		GL11.glBegin(GL11.GL_POLYGON);
		for (int i = 0; i < getPointCount(); i++) {
			GL11.glVertex2f(getPoint(i).x + getPosition().x,
			getPoint(i).y + getPosition().y);
		}
		GL11.glEnd();
	}
}
