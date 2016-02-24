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
public class Inventory {
    
    private String[][] inv;
    
    /**
     *
     */
    public Inventory() {
        inv = new String[10][10];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 10; j++) {
                inv[i][j] = ".";
            }
        }
    }
    
    public void add(String[] obj) {
        int p = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 10; j++) {
                if(inv[i][j] == "."){
                    if(p < 3){
                        inv[i][j] = obj[p];
                        p++;
                    }
                }
            }
        }
    }
    
    public String[][] getInv(){
        return inv;
    }
}
