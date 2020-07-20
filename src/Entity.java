import java.util.ArrayList;

public class Entity {
	/*
	 * ID is sort of just a place holder. I probably will make use of it eventually. But right now it has no use.
	 */
	protected int id;
	protected int x, y, width, height;
	protected int dx, dy = 0;
	protected ArrayList<Hitbox> hitboxes = new ArrayList<Hitbox>();
	
	public Entity(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	public int getX() { return this.x; }
	public int getY() { return this.y; }
	public int getWidth() { return this.width; }
	public int getHeight() { return this.height; }
}
