
public class Hitbox {
	/*
	 * Should X and Y be global or local? Which is to say, should the X and Y be the absolute locations on the map or relative to the owner entity's location point?
	 * Should Layer simply be some int that I just have to know how to use? Or should it be an Enum with distinct name value things?
	 */
	private int x, y, w, h;
	private int layer;
	private boolean active;
	public Hitbox(int x, int y, int w, int h, int layer, boolean active) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.layer = layer;
		this.active = active;
	}
	public int getX() { return this.x; }
	public int getY() { return this.y; }
	public int getW() { return this.w; }
	public int getH() { return this.h; }
	public int getLayer() { return this.layer; }
	public boolean isActive() { return this.active; }
}
