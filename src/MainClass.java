import javax.swing.JFrame;

public class MainClass {

	public static void main(String[] args) {
		// Initializes window
		JFrame frame = new JFrame("Game Window");
		frame.setContentPane(new GamePanel());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
