/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marsrover1302;

import javax.swing.JOptionPane;

/**
 *
 * @author kjsouribio
 */
public class JOptionCancel {
    
    public void JOptionCancel(String input){
        if (input == null || (input != null && ("".equals(input))) || input == null) {
                int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "EXIT", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.NO_OPTION) {
                    JOptionPane.showMessageDialog(null, "The show must go on, comrade.");
                } else {
                    JOptionPane.showMessageDialog(null, "Houston...\nWe're coming home.");
                    System.exit(0);
                }
//      System.exit(0);// or you can throw exception
            }
    }
    
}
