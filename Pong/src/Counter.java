import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Counter extends Rectangle {
	
	static int GAME_WIDTH;
	static int GAME_HEIGHT;
	int p1score;
	int p1lives = 3;
	
	
	public Counter(int gameWidth, int gameHeight) {
		// TODO Auto-generated constructor stub
		Counter.GAME_WIDTH = gameWidth;
		Counter.GAME_HEIGHT = gameHeight;

	}

	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.setFont(new Font("Sans", Font.PLAIN, 45));
		
		g.drawLine(GAME_WIDTH/2, 0, GAME_WIDTH/2, GAME_HEIGHT);
		g.drawString("Score : " + String.valueOf(p1score/100)+String.valueOf(p1score/10)+String.valueOf(p1score%10), (GAME_WIDTH/2) - 300, 50);
		
		g.setFont(new Font("Sans", Font.PLAIN, 30));
		g.drawString("Lives: " + String.valueOf(p1lives), 100, 525);


		
	}
}
