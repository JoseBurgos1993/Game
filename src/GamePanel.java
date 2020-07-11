import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

/*	TODO
 * 	Look up the suppressWarnings("serial"). Has something to do with 'static'?
 *  Look up addNotify()
 *  Check if there is a point in taking g2d as a parameter in render() if g2d is already 'global'.
 *  
 *  Engine Design Questions:
 *  	- I want entities, but what are entities? Is the entity the hitbox, or is it something else?
 *  		- Entities should contain an array? of hitboxes. This allows more complex hitbox design (i.e. every character isn't just a rectangle).
 *  		- More complex hitbox shapes are possible, but considering that rectangles are super easy to work with, and that fighting games seem
 *  		  to do fine with them, I'm inclined to stick to rectangles. I don't need more complexity for what I'm doing.
 *  	- Is the scenery made up of entities?
 *  		- This may make building long walls and floors a chore. DO RESEARCH on how others do environments.
 *  		- Considering that sprite sheets use a system where the entire .PNG is in memory, but only a small part is displayed (the current frame
 *  		  of the sprite), I may be able to the same with the environment. Draw the entire static portion of the map as a single image, and simply
 *  		  display what is necessary on screen. Maybe like an extra 5% to prevent BS from happening.
 *  	- Entity will be a super class from which all other types (player, enemies, objects, etc...) will be derived.
 *  		- All entities should store their location, size, and type. I was going to add spritesheets or images, but I realized this could cause memeory
 */

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable, KeyListener{
	
	// Panel Attributes \\
	private static int WIDTH = 800;
	private static int HEIGHT = 600;
	private Thread thread;
	private boolean running;
	private long targetTime;
	//public static FileReader fileReader; <--- May use for file reading
	private static final int FRAME_RATE = 60;
	
	// Controls \\ 0 - Left, 1 - Right, 2 - Confirm, 3 - Cancel, 4 - Action1, 5 - Pause
	private boolean[] controls = new boolean[] {false,false,false,false,false,false};
	
	// Render \\
	private static Graphics2D g2d;
	private BufferedImage image;
	
	// Constructor \\
	public GamePanel() {
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
		setFocusable(true);
		requestFocus();
		addKeyListener(this);
	}
	
	// System Functions \\
	public void addNotify() {
		super.addNotify();
		thread = new Thread(this);
		thread.start();
	}
	
	private void setFPS(int fps) {
		targetTime = 1000/fps;
	}
	
	public void run() {
		if(running) return;
		init();
		
		long startTime, elapsed, wait;
		
		while(running) {
			startTime = System.nanoTime();
			update();
			requestRender();
			
			elapsed = System.nanoTime() - startTime;
			wait = targetTime - elapsed / 1000000;
			if(wait > 0) {
				try {
					Thread.sleep(wait);
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	// Game Functions \\
	public void init() {
		setFPS(FRAME_RATE);
		image = new BufferedImage(WIDTH,HEIGHT, BufferedImage.TYPE_INT_ARGB);
		g2d = image.createGraphics();
		running = true;
	}
	
	private void update() {
		/* TODO
		 * Read player input
		 * Resolve the frame (Do all the things. Break this up into smaller parts)
		 * Add more...
		 * */
	}
	private void findCameraLocation() {
		/*
		 * TODO
		 * There's gotta be a better way than doing this every frame. Or maybe I'm to anal about this.
		 * I wrote this a while back. The idea is that the 'camera' is focused on a target. As the target moves, the camera follows.
		 * The reason we divide width and height by 2 is because we want the camera to look the the focus target's center.
		 * We then compare these values to the min and max values for x and y. We don't want the camera to be able to travel out of bounds. So when an edge
		 * is met, the camera stops moving.
		 * I think it might be good to add another modifier to these values. For instance, the idea that the camera is looking dead center at the target is weird.
		 * Most games have the character a little towards the bottom because more is coming at them from the sides and top than the bottom. Obviously this isn't
		 * the case for every game. Games where threats from below pop up do have a more centered camera. Some games make it so crouching actually pulls the camera down
		 * a bit, as if the character was crouching to take a look below. Same happen when pressing up. Also the camera might travel slightly left or right to match
		 * the facing direction of the player. I'm not as fond of that one though. Having a transform modifier might be a good idea regardless.
		 * 
		 * cameraX = cameraFocusTarget.getX() + modX - cameraFocusTarget.getWidth() / 2;
		 * cameraY = cameraFocusTarget.getY() + modY - cameraFocusTarget.getHeight() / 2;
		 * 
		 * if(cameraX < cameraMinX) cameraX = cameraMinX;
		 * else if(cameraX > cameraMaxX) cameraX = cameraMaxX;
		 * 
		 * if(cameraY < cameraMinY) cameraY = cameraMinY;
		 * else if(cameraY > cameraMaxY) cameraY = cameraMaxY;
		*/
	}
	private void requestRender() {
		render(g2d);
		Graphics g = getGraphics();
		g.drawImage(image, 0, 0, null);
		g.dispose();
	}
	
	private void render(Graphics2D g2d) {
		
		g2d.setColor(Color.BLUE);
		g2d.clearRect(0, 0, WIDTH, HEIGHT);
		g2d.fillRect(100, 100, 400, 300);
		
		/* TODO
		 * I think it's good to set layers.
		 * Render the sky box layer first, then a couple of different background layers. Then the ground layer, the object/character layer, and any foreground layers.
		 * 
		 * */
	}
	// Key Listeners \\
	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
			case KeyEvent.VK_A:     controls[0] = true;
			case KeyEvent.VK_D:     controls[1] = true;
			case KeyEvent.VK_J:     controls[2] = true;
			case KeyEvent.VK_K:     controls[3] = true;
			case KeyEvent.VK_L:     controls[4] = true;
			case KeyEvent.VK_SPACE: controls[5] = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()) {
			case KeyEvent.VK_A:     controls[0] = false;
			case KeyEvent.VK_D:     controls[1] = false;
			case KeyEvent.VK_J:     controls[2] = false;
			case KeyEvent.VK_K:     controls[3] = false;
			case KeyEvent.VK_L:     controls[4] = false;
			case KeyEvent.VK_SPACE: controls[5] = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

}
