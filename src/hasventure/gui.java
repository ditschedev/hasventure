/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hasventure;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.Timer;

/**
 *
 * @author tobias.dittmann
 */
public class gui {
    Timer time;
	Image img;
	int key;
	int nx,nx2;
	
	
	int figur_y = 235;

			
	int X_Bild;
	int lauf;
	
	int anzahl = 0;
	int anzahl2= 0;
	
	
		Image img2;
		int left = 75;

	public gui(){
		
		

		
		nx = 0;
		nx2 = 690;
		
		key = 0;
		lauf = 0;
		
		setFocusable(true);
		
		
		ImageIcon u = new ImageIcon((getClass().getResource("background.jpg")));
		img = u.getImage();
		
		
		ImageIcon s = new ImageIcon((getClass().getResource("char.png")));
		
		img2 = s.getImage();
		
	
		addKeyListener(new AL());
		
		//neu
		//149 y hoch Springen - Höhe 
		//block1 = new Block(250, 110, 50, 50, Color.YELLOW);
		//
		
		Sprung sprung = new Sprung();
	
		
		time = new Timer(5,this);
		time.start();
		
	}
}
