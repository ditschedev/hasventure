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
import java.io.IOException;
import javax.swing.*;

/**
 *
 * @author tobias.dittmann
 */
public class gui extends JPanel implements ActionListener {
    Image img;
    int key;
    String[][] f;
    public static String title;
    private boolean onChest = false;
    private boolean onLadder = false;
    int x;
    int y;
    
    int sX;
    int sY;

    public gui() throws IOException{

        key = 0;
        
        x = 0;
        y = 0;

        setFocusable(true);
        
        Dungeon d = new Dungeon(1);
        
        f = d.getDungeonTiles();
        title = d.getTitle();
        sX = d.spawnX();
        sY = d.spawnY();
        addKeyListener(new AL());

    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        bewegen();

        repaint();

    }

    @Override
    public void paint(Graphics g){
        super.removeAll();
        super.paint(g);
        Graphics2D f2 =(Graphics2D)g;
        
        f2.drawString("Level Name: " + title, 32, 32);
        int xwert = 32;
        int ywert = 32;
        
        for(int x=0;x<f.length;x++){
            for(int y=0;y<f.length;y++){
                ImageIcon icon = null;
                ImageIcon bonus = null;
                ImageIcon bonus2 = null;
                if(f[x][y] == "G"){
                    icon = new ImageIcon((getClass().getResource("images/ground.png")));
                } else if(f[x][y] == "B"){
                    icon = new ImageIcon((getClass().getResource("images/wall.png")));
                } else if(f[x][y] == "S"){
                    sX = x;
                    sY = y;
                    icon = new ImageIcon((getClass().getResource("images/ground.png")));
                    bonus = new ImageIcon((getClass().getResource("images/avatar.png")));
                } else if(f[x][y] == "C"){
                    icon = new ImageIcon((getClass().getResource("images/ground.png")));
                    bonus = new ImageIcon((getClass().getResource("images/chest.png")));
                } else if(f[x][y] == "N"){
                    icon = new ImageIcon((getClass().getResource("images/ground.png")));
                    bonus = new ImageIcon((getClass().getResource("images/ladder.png")));
                } else if(f[x][y] == "NS"){
                    icon = new ImageIcon((getClass().getResource("images/ground.png")));
                    bonus = new ImageIcon((getClass().getResource("images/ladder.png")));
                    bonus2 = new ImageIcon((getClass().getResource("images/avatar.png")));
                } else if(f[x][y] == "CS"){
                    icon = new ImageIcon((getClass().getResource("images/ground.png")));
                    bonus = new ImageIcon((getClass().getResource("images/chest.png")));
                    bonus2 = new ImageIcon((getClass().getResource("images/avatar.png")));
                }
                if(icon != null){
                    Image icoimg = icon.getImage();
                    f2.drawImage(icoimg, xwert, ywert, 32, 32, this);
                }
                if(bonus != null){
                    Image bonimg = bonus.getImage();
                    f2.drawImage(bonimg, xwert, ywert, 32, 32, this);
                }
                if(bonus2 != null){
                    Image bon2img = bonus2.getImage();
                    f2.drawImage(bon2img, xwert, ywert, 32, 32, this);
                }
                xwert=xwert+32;
            }
            ywert=ywert+32;
            xwert=32;
        }

    }



    public void bewegen(){
        if(!f[sX][sY].equals("C") || !f[sX][sY].equals("CS")){
            f[sX][sY] = "G";
        } else {
            f[sX][sY] = "C";
        }
        int oldX = sX;
        int oldY = sY;
        if(x != 0){
            if(x == -1){
                sX--;
            } else {
                sX++;
            }
        } else if(y != 0) {
            if(y == -1){
                sY--;
            } else {
                sY++;
            }
        }
        if(f[sX][sY].equals("C")){
            f[sX][sY] = "CS";
            onChest = true;
            Object[] options = { "OK"};
            JOptionPane.showOptionDialog(null,"Du stehst gerade auf einer Kiste, öffne sie mit 'o'!","Hinweis",JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE,null,options,options[0]);	
            repaint();
        } else if(f[sX][sY].equals("L")){
            f[sX][sY] = "LS";
            onLadder = true;
            Object[] options = { "OK"};
            JOptionPane.showOptionDialog(null,"Du stehst gerade auf einer Leiter, kletter nach oben mit 'k'!","Hinweis",JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE,null,options,options[0]);	
            repaint();
        } else if(!f[sX][sY].equals("B")) {
            f[sX][sY] = "S";
        } else {
            f[oldX][oldY] = "S";
        }

    }
	
	
    private class AL extends KeyAdapter{

        public AL(){

        }

        @Override
        public void keyPressed(KeyEvent e){


            key = e.getKeyCode();

            if(key == KeyEvent.VK_LEFT){
                y = -1;
            }

            if(key == KeyEvent.VK_RIGHT){
                y = 1;
            }

            if(key == KeyEvent.VK_DOWN){
                x = 1;
            }

            if(key == KeyEvent.VK_UP){
                x = -1;
            }

            if(key == KeyEvent.VK_ESCAPE){
                
            }
            if(key == KeyEvent.VK_O){
                if(onChest){
                    Object[] options = { "OK"};
                    JOptionPane.showOptionDialog(null,"Kiste erfolgreich geöffnet!","Hinweis",JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE,null,options,options[0]);	
                    
                    f[sX][sY] = "S";
                }
            }
            
            bewegen();
            repaint();
            x=0;
            y=0;
        }


        @Override
        public void keyReleased(KeyEvent e){

            key = e.getKeyCode();

            if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT){
                x=0;
            }

            if(key == KeyEvent.VK_DOWN || key == KeyEvent.VK_UP){
                y=0;
            }

        }

    }
}
