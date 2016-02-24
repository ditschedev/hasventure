/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hasventure;

/**
 *
 * @author Tobias
 */
public class Chest {
    
    private boolean open;
    private String[] itms;
    
    public Chest(int x, int y, int level){
        open = false;
        itms = new String[3];
        for (int i = 0; i < itms.length; i++) {
            itms[i] = ".";
        }
        addContents(level);
    }
    
    private void addContents(int l){
        if(l == 1){
            itms[0] = "I2";
        } else if(l == 2){
            itms[0] = "I2";
            itms[1] = "I3";
        }
    }
    
    public void removeChest(){
        for (int i = 0; i < itms.length; i++) {
            itms[i] = ".";
        }
        open = true;
    }
    
    public void setOpen(){
        open = true;
    }
    
    public String[] getContents() {
        return itms;
    }
    
    public boolean isOpened() {
        return open;
    }
}
