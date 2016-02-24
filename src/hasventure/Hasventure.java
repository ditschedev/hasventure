/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hasventure;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author tobias.dittmann
 */
public class Hasventure extends JFrame implements ActionListener {
    
    private JButton schliessen;
    private JButton einstellung;
    private JButton info;
    private JButton ende;
    private JButton tutyes;
    private JButton tutno;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Hasventure frame = new Hasventure("Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,400);


        frame.setLayout(null);
        frame.setVisible(true);
    }
    
    public Hasventure(String title){
        super(title);
        
        schliessen = new JButton("Spiel starten");
        schliessen.setBounds(120,40,160,40);
        schliessen.addActionListener(this);
        add(schliessen);

        einstellung = new JButton("Einstellungen");
        einstellung.setBounds(120,120,160,40);
        einstellung.addActionListener(this);
        add(einstellung);

        info = new JButton("Credits");
        info.setBounds(120,200,160,40);
        info.addActionListener(this);
        add(info);

        ende = new JButton("Ende");
        ende.setBounds(120,280,160,40);
        ende.addActionListener(this);
        add(ende);
    }
    
    public static void fenster(boolean tut) throws IOException{
	
        JFrame fenster = new JFrame("Game");
        fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenster.setSize(1300,1030);
        fenster.setVisible(true);
        fenster.add(new gui(tut));	
    }
    
    public void tutAsk() throws IOException{
        JFrame fenster = new JFrame("Tutorial");
        fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenster.setSize(420,210);
        JLabel l = new JLabel("Möchtest du das Tutorial spielen?");
        tutyes = new JButton("Ja");
        tutno = new JButton("Nein");
        l.setBounds(20,20,420,40);
        tutyes.setBounds(20, 40, 160, 40);
        tutno.setBounds(180, 40, 160, 40);
        tutyes.addActionListener(this);
        tutno.addActionListener(this);
        fenster.add(l);
        fenster.add(tutyes);
        fenster.add(tutno);
        fenster.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()== schliessen ){
            try {	
                tutAsk();
            } catch (IOException ex) {
                Logger.getLogger(Hasventure.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(e.getSource() == tutyes){
            try {
                fenster(true);
            } catch (IOException ex) {
                Logger.getLogger(Hasventure.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(e.getSource() == tutno){
            try {
                fenster(false);
            } catch (IOException ex) {
                Logger.getLogger(Hasventure.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (e.getSource() == info ){
            Object[] options = { "OK"};

            JOptionPane.showOptionDialog(null,"Programmiert von Tobi und Moritz !","Information",JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE,null,options,options[0]);	
        }

        if(e.getSource() == einstellung){

        //	auswahl();

        }

        if(e.getSource() == ende){
                System.exit(0);
        }
    }
    
}
