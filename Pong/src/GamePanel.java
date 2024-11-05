import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

import javax.swing.*;


public class GamePanel extends JPanel implements Runnable {
	
	static final int GAME_WIDTH = 1000; // 
	static final int GAME_HEIGHT = (int) (GAME_WIDTH * (0.5555));
	static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
	static final int BALL_DIAMETER = 20;
	static final int PADDLE_WIDTH = 25;
	static final int PADDLE_HEIGHT = 100;
	static final int GAME_OVER_LIVES = 0;
	static boolean gameRun = true;
	Thread gameThread;
	Image img;
	Graphics graphics;
	Random rand;
	Paddle p1;
	Paddle bot1;
	Ball ball;
	Counter score;

	
	GamePanel() {
		setPaddles();
		setBall();
		
		score = new Counter(GAME_WIDTH, GAME_HEIGHT);
		this.setFocusable(true); // if keys pressed, focused (key presses are read)
		this.addKeyListener(new AL());
		this.setPreferredSize(SCREEN_SIZE);
		
		gameThread = new Thread(this); // passing through this because we are implementing runnable
		gameThread.start(); // starts new thread => JVM calls run() method of Runnable in a separate call stack
		
	}
	
	public void setBall() {
		rand = new Random();
		ball = new Ball((GAME_WIDTH/2)-(BALL_DIAMETER/2), rand.nextInt(GAME_HEIGHT - BALL_DIAMETER), BALL_DIAMETER, BALL_DIAMETER);
		
	}
	
	public void setPaddles() {
		p1 = new Paddle(30, (GAME_HEIGHT/2)-(PADDLE_HEIGHT/2), PADDLE_WIDTH, PADDLE_HEIGHT, 1);
		bot1 = new Paddle(GAME_WIDTH-PADDLE_WIDTH - 30, (GAME_HEIGHT/2)-(PADDLE_HEIGHT/2), PADDLE_WIDTH, PADDLE_HEIGHT, 2);

	}
	
	public void paint(Graphics g) {
		img = createImage(getWidth(), getHeight()); // retrieve width and height of this panel
		graphics = img.getGraphics(); // create a graphic
		draw(graphics); // call draw method to draw all the components (pass through graphics)
		g.drawImage(img, 0, 0, this); // draw image (image, coordinates, jpanel)
	}
	
	public void draw(Graphics g) {
		p1.draw(g);
		bot1.draw(g);
		ball.draw(g);
		score.draw(g);
	}
	
	public void move() {
		p1.move();
		bot1.botMovement(ball.getY());
		ball.move();
	}
	
	public void checkCollision(){ // for paddle and ball 
		// stops paddles from going out of window 
		
		// player
		if (p1.y <= 0) 
			p1.y = 0;
		if (p1.y >= (GAME_HEIGHT - PADDLE_HEIGHT)) 
			p1.y = GAME_HEIGHT - PADDLE_HEIGHT;
		
		
		// bot
		if (bot1.y <= 0) 
			bot1.y = 0;
		if (bot1.y >= (GAME_HEIGHT - PADDLE_HEIGHT)) 
			bot1.y = GAME_HEIGHT - PADDLE_HEIGHT;
		
		
		// bounce ball off top and bottom of the game window
		if (ball.y <= 0) 
			ball.setYDirection(-ball.ySpeed);
		if (ball.y >= GAME_HEIGHT-BALL_DIAMETER) // consider ball's diameter since it checks the top left corner of the ball
			ball.setYDirection(-ball.ySpeed);
		
		
		// bounces ball of paddles
		if (ball.intersects(p1)) {
			ball.xSpeed = Math.abs(ball.xSpeed);
			ball.xSpeed++;
			if (ball.ySpeed > 0) {
				ball.ySpeed++;
			}
			else
				ball.ySpeed--; // if y speed is negative, the ball is going upwards, change direction decrementing speed
			ball.setXDirection(ball.xSpeed);
			ball.setYDirection(ball.ySpeed);

		}
		if (ball.intersects(bot1)) {
			ball.xSpeed = Math.abs(ball.xSpeed);
			ball.xSpeed++;
			if (ball.ySpeed > 0) {
				ball.ySpeed++;
			}
			else
				ball.ySpeed--; // if y speed is negative, the ball is going upwards, change direction decrementing speed
			ball.setXDirection(-ball.xSpeed);
			ball.setYDirection(ball.ySpeed);
		}
		
		
		
		// p1 loses a life, set paddles and ball
		if (ball.x <= 0) {
			score.p1lives--;
			//System.out.println("Player 1 Lives: " + score.p1lives);
			if(score.p1lives > GAME_OVER_LIVES) {
				setPaddles();
				setBall();
			}
			else
				gameRun = false;
			
		}
		
		// p1 gains a pt, set paddles and ball
		if (ball.x >= GAME_WIDTH - BALL_DIAMETER) {
			score.p1score++;
			setPaddles();
			setBall();
			System.out.println("Player 1 Score: " + score.p1score);
		}
	}
	
	public void run() {
		// game loop
		
		long lastTime = System.nanoTime();
		double tickAmt = 100.0; // number of updates per second 
		double ns = 1000000000 / tickAmt; // amount of nanoseconds per update
		double delta = 0; // accumulates the time difference between each loop cycle to make sure there's consistent frame timing 
		
		// delta ensures that updates only occur once enough time has passed
		
		
		while(gameRun) { // loops continuously until gameRun is false => handles moving objects and checking for collisions
			long now = System.nanoTime(); // gets current time in nanoseconds 
			delta += (now - lastTime)/ns; // calculates time difference between current time and the last time, divides it by amt of nanoseconds per update
			lastTime = now; // updates last time to current time for next iteration of the loop
			System.out.println(delta);
			if (delta >= 1) { // checks if enough time has passed to process an update 
				move();
				checkCollision();
				repaint(); // redraw game screen with updated positions of entities (paddles and ball)
				delta--;
				//System.out.println("Test");
				
			}
		}
		
		
	}
	
	public class AL extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			p1.keyPressed(e);
			
		}
		
		public void keyReleased(KeyEvent e) {
			p1.keyReleased(e);
		}
	}
}
