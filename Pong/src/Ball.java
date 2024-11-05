import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;


public class Ball extends Rectangle {
	
	Random random;
	int xSpeed;
	int ySpeed;
	int startSpeed = 2;
	
	Ball(int xPos, int yPos, int width, int height) {
		super(xPos, yPos, width, height);
		random = new Random();
		int randomXDirection = random.nextInt(2);
		if (randomXDirection == 0) 
			randomXDirection--;
		setXDirection(randomXDirection*startSpeed);
		
		
		int randomYDirection = random.nextInt(2);
		if (randomYDirection == 0) 
			randomYDirection--;
		setYDirection(randomYDirection*startSpeed);
	}
	
	public void setXDirection(int randomX) {
		xSpeed = randomX;
	}
	
	public void setYDirection(int randomY) {
		ySpeed = randomY;
	}
	
	public void move() {
		x += xSpeed;
		y += ySpeed;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.fillOval(x, y, height, width);
	}
	
	public double getY() {
		return y;
	}
	
	
}
