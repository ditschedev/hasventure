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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author tobias.dittmann
 */
public class gui extends JPanel implements ActionListener {
    Image img;
    int key;
    String[][] f;
    String[][] inv;
    Inventory inventory;
    public static String title;
    private boolean onChest = false;
    private boolean onLadder = false;
    private Chest c;
    private boolean chestopened = false;
    int x;
    int y;
    int level;
    int sX,sY;
    int cX,cY;
    Dungeon dungeon;
    Graphics2D f2;

    public gui(boolean tutorial) throws IOException{
        
        if(tutorial){
            level = 1;
        } else {
            level = 2;
        }
        
        key = 0;
        x = 0;
        y = 0;

        setFocusable(true);
        
        dungeon = new Dungeon(1,level);
        inventory = new Inventory();
        inv = inventory.getInv();
        f = dungeon.getDungeonTiles();
        title = dungeon.getTitle();
        sX = dungeon.spawnX();
        sY = dungeon.spawnY();
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
        f2 = (Graphics2D)g;
        
        f2.drawString("Level Name: " + title + " // Level: " + level, 32, 32);
        initTiles();
        initInv();
    }
    
    private void initInv(){
        int xwert = 878;
        int ywert = 780;
        f2.drawString("Inventory", 878, 740);
        for(int x=0;x<inv.length;x++){
            for(int y=0;y<inv.length;y++){
                ImageIcon icon = null;
                ImageIcon bonus = null;
                if(inv[x][y] == "."){
                    icon = new ImageIcon((getClass().getResource("images/inv_empty.png")));
                } else if(inv[x][y] == "I2"){
                    icon = new ImageIcon((getClass().getResource("images/inv_empty.png")));
                    bonus = new ImageIcon((getClass().getResource("images/ladders.png")));
                } else if(inv[x][y] == "I3"){
                    icon = new ImageIcon((getClass().getResource("images/inv_empty.png")));
                    bonus = new ImageIcon((getClass().getResource("images/chest.png")));
                }
                if(icon != null){
                    Image icoimg = icon.getImage();
                    f2.drawImage(icoimg, xwert, ywert, 32, 32, this);
                }
                if(bonus != null){
                    Image bonimg = bonus.getImage();
                    f2.drawImage(bonimg, xwert, ywert, 32, 32, this);
                }
                xwert=xwert+34;
            }
            ywert=ywert+34;
            xwert=878;
        }
    }
    
    private void initTiles(){
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
                    cX = x;
                    cY = y;
                    c = new Chest(cX,cY,level);
                    icon = new ImageIcon((getClass().getResource("images/ground.png")));
                    bonus = new ImageIcon((getClass().getResource("images/chest.gif")));
                } else if(f[x][y] == "L"){
                    icon = new ImageIcon((getClass().getResource("images/ground.png")));
                    bonus = new ImageIcon((getClass().getResource("images/ladders.png")));
                } else if(f[x][y] == "LS"){
                    icon = new ImageIcon((getClass().getResource("images/ground.png")));
                    bonus = new ImageIcon((getClass().getResource("images/ladders.png")));
                    bonus2 = new ImageIcon((getClass().getResource("images/avatar.png")));
                } else if(f[x][y] == "CS"){
                    icon = new ImageIcon((getClass().getResource("images/ground.png")));
                    bonus = new ImageIcon((getClass().getResource("images/chest.gif")));
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
        boolean special = false;
        if(sX == cX && sY == cY ){
            f[sX][sY] = "C";
            special = true;
        } else {
            f[sX][sY] = "G";
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
            if(level == 1 && title.equals("Tutorial")){
                JOptionPane.showOptionDialog(null,"Du stehst gerade auf einer Kiste, öffne sie mit 'o'!","Hinweis",JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE,null,options,options[0]);
            }	
            if(special){
                f[oldX][oldY] = "C";
            }
            repaint();
        } else if(f[sX][sY].equals("L")){
            f[sX][sY] = "LS";
            onLadder = true;
            Object[] options = { "OK"};
            if(level == 1 && title.equals("Tutorial")){
                JOptionPane.showOptionDialog(null,"Du stehst gerade auf einer Leiter, kletter nach oben mit 'k'!","Hinweis",JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE,null,options,options[0]);	

            }
            repaint();
        } else if(!f[sX][sY].equals("B")) {
            f[sX][sY] = "S";
            if(special){
                f[oldX][oldY] = "C";
            }
        } else {
            if(special){
                f[oldX][oldY] = "CS";
            } else {
                f[oldX][oldY] = "S";
            }
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
                    if(chestopened == false){
                        c.setOpen();
                        chestopened = c.isOpened();
                        System.out.println(c.isOpened());
                        inventory.add(c.getContents());
                        c.removeChest();
                        Object[] options = { "OK"};
                        JOptionPane.showOptionDialog(null,"Kiste erfolgreich geöffnet!","Hinweis",JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE,null,options,options[0]);
                    } else {
                        Object[] options = { "OK"};
                        JOptionPane.showOptionDialog(null,"Kiste wurde bereits geöffnet!","Hinweis",JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE,null,options,options[0]);
                    }
                    
                    f[sX][sY] = "S";
                }
            }
            if(key == KeyEvent.VK_K){
                if(onLadder){
                    level++;
                    try {
                        dungeon = new Dungeon(1,level);
                    } catch (IOException ex) {
                        Logger.getLogger(gui.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    f = dungeon.getDungeonTiles();
                    title = dungeon.getTitle();
                    sX = dungeon.spawnX();
                    sY = dungeon.spawnY();
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
