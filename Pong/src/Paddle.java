import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Paddle extends Rectangle {
	
	int id;
	int ySpeed;
	int p1movementSpeed = 8;
	int botmovementSpeed = 8;
	
	Paddle(int xPos, int yPos, int paddleWidth, int paddleHeight, int paddleID) {
		super(xPos, yPos, paddleWidth, paddleHeight);
		this.id = paddleID;
	}
	
	public void keyPressed(KeyEvent e) {
		switch(id) {
		case 1:
			if(e.getKeyCode() == KeyEvent.VK_W) {
				setYDirection(-p1movementSpeed);
				move();
			}
			if(e.getKeyCode() == KeyEvent.VK_S) {
				setYDirection(p1movementSpeed);
				move();
			}
		}
	}
	
	public void keyReleased(KeyEvent e) {
		switch(id) {
		case 1:
			if(e.getKeyCode() == KeyEvent.VK_W) {
				setYDirection(0);
				move();
			}
			if(e.getKeyCode() == KeyEvent.VK_S) {
				setYDirection(0);
				move();
			}
		}
	}
	
	public void setYDirection(int yDirection) {
		ySpeed = yDirection;
	}
	
	public void move() {
		y = y + ySpeed;
	}
	
	
	public void botMovement(double moveToY) {
		int centerY = y + height / 2;
		

	    if (centerY < moveToY) {
	        y += botmovementSpeed; // Move down
	    } else if (centerY > moveToY) {
	        y -= botmovementSpeed; // Move up
	    }

	    // Ensure the bot paddle doesn't go out of bounds
	    if (y < 0) {
	        y = 0;
	    }
	    if (y > GamePanel.GAME_HEIGHT - height) {
	        y = GamePanel.GAME_HEIGHT - height;
	    }
		
	}
	
	
	public void draw(Graphics g) {
		Color teal = new Color(0, 128, 128);
		Color maroon = new Color(128, 0, 0);
		if (id == 1) {
			g.setColor(teal);
			
		}
		else {
			g.setColor(maroon);
		}
		g.fillRect(x, y, width, height);
	}
	
	
	
}
