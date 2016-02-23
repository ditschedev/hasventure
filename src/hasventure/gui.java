/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hasventure;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;

/**
 *
 * @author tobias.dittmann
 */
public class gui extends JPanel implements ActionListener {
    Timer time;
    Image img;
    int key;
    int nx,nx2;


    int figur_y = 235;


    int X_Bild;
    int lauf;
    int laufy;

    int anzahl = 0;
    int anzahl2= 0;


    Image img2;
    int left = 75;

    public gui(){

        nx = 0;
        nx2 = 690;

        key = 0;
        lauf = 0;
        laufy = 0;

        setFocusable(true);

        ImageIcon s = new ImageIcon((getClass().getResource("char.png")));

        img2 = s.getImage();


        addKeyListener(new AL());


        time = new Timer(5,this);
        time.start();

    }
        public void actionPerformed(ActionEvent e){
		bewegen();
	
		repaint();
		
	}
	
	public void paint(Graphics g){
		
		super.paint(g);
		Graphics2D f2 =(Graphics2D)g;
		

		if (getXBild() == 510+ (anzahl *2350)){
			anzahl += 1;
			nx = 0;
			
		}
		
		if (getXBild() == 1690+(anzahl2*2350)){
			anzahl2 += 1;
			nx2 = 0;
		}
		
      
		f2.drawImage(img2,left,figur_y,null);
		
	}
	
	private int getXBild() {
            return X_Bild;
	}

	
	
	public void bewegen(){

            if (lauf != -2) {

                if (left + lauf <= 75) {
                    left += lauf;
                } else {
                    X_Bild += lauf;
                    nx += lauf;
                    nx2 += lauf;
                }
            } else {

                if (left + lauf > 0) {

                        left += lauf;


                }
            }

	}
	
	
	private class AL extends KeyAdapter{
		
            public AL(){

            }

            public void keyPressed(KeyEvent e){


                key = e.getKeyCode();

                if(key == KeyEvent.VK_LEFT){
                        lauf = -2;
                }

                if(key == KeyEvent.VK_RIGHT){
                        lauf = 2;
                }
                
                if(key == KeyEvent.VK_DOWN){
                        laufy = 2;
                }
                
                if(key == KeyEvent.VK_UP){
                        laufy = 2;
                }

                if(key == KeyEvent.VK_ESCAPE){
                        System.exit(0);
                }


            }


            public void keyReleased(KeyEvent e){

                key = e.getKeyCode();

                if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT){
                        lauf = 0;
                }

            }
	
	}
}
