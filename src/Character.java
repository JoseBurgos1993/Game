
public class Character extends Entity {

	public Character(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	}
	public void brake() {
		if(dx > 0) {
			dx -= 2;
			if(dx < 0) dx = 0;
		} else if(dx < 0) {
			dx += 2;
			if(dx > 0) dx = 0;
		}

		if(dy > 0) {
			dy -= 2;
			if(dy < 0) dy = 0;
		} else if(dy < 0) {
			dy += 2;
			if(dy > 0) dy = 0;
		}
	}
	public void accelerateX(int num) {
		dx += num;
		if(dx > 20) dx = 20;
		if(dx < -20) dx = -20;
	}
	public void accelerateY(int num) {
		dy += num;
		if(dy > 20) dy = 20;
		if(dy < -20) dy = -20;
	}
	public void move() {
		x += dx;
		y += dy;
		
		this.brake();
	}
}
