import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;


public class GameFrame extends JFrame { // holds game panel 

	
	GamePanel gp;
	
	GameFrame() {
		
		gp = new GamePanel();
		this.add(gp);
		this.setTitle("Pong");
		this.setResizable(false);
		this.setBackground(Color.black);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.pack(); // game frame adjusts and fits around game panel
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
	}
}
