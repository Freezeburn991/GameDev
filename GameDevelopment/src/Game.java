import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

/*
 * BufferStrategy - A mechanism to handle our Graphics Buffering
 * 				  - Allows us to swap and access our Graphics Buffers
 * 
 * Canvas - A JFrame component that we can draw our graphics to.
 * 		  - Handles our BufferSTrategy , it handles the switching
 * */

public class Game extends JFrame implements Runnable {

	private Canvas canvas = new Canvas();

	public Game() {

		// Makes our program to shut down when we exit out.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Set the position and size of our frame.
		setBounds(0, 0, 1000, 800);
		// Put our frame in the center of the screen
		setLocationRelativeTo(null);
		// Add our graphics component
		add(canvas);
		// Make our frame visible.
		setVisible(true);
		// Create our object for buffer strategy
		canvas.createBufferStrategy(2);

	}

	public static void main(String[] args) {
		
		Game game = new Game();
		Thread gameThread = new Thread(game);
		gameThread.start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		BufferStrategy bufferStrategy = canvas.getBufferStrategy();
		int i = 0;
		int x = 5;
		// Bad loop, dont do it
		while (true) {
			i++;
			if (i == 10) {
				i = 0;
				x++;
			}
			bufferStrategy = canvas.getBufferStrategy();
			Graphics graphics = bufferStrategy.getDrawGraphics();
			super.paint(graphics);

			// Painting the Background
			graphics.setColor(Color.black);
			graphics.fillRect(0, 0, getWidth(), getHeight());

			// Painting the Oval
			graphics.setColor(Color.red);
			graphics.fillOval(x, 500, 100, 100);

			graphics.dispose();
			bufferStrategy.show();

		}

	}
}
