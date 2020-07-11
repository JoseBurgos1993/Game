
public class Entity {
	protected int id;
	protected int x, y, width, height;
	protected int dx, dy = 0;
	
	public Entity(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
}
