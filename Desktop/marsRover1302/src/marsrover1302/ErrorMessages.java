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
public class ErrorMessages {

    public void zeroOrLessThanZero() {
        JOptionPane.showMessageDialog(null, "It cannot be 0 or less than 0.", "Warning", JOptionPane.INFORMATION_MESSAGE);

    }

    public void pleaseEnterANumber() {
        JOptionPane.showMessageDialog(null, "Please enter a number.", "Warning", JOptionPane.INFORMATION_MESSAGE);

    }

    public void plateauSizeError() {
        JOptionPane.showMessageDialog(null, "It cannot be more than the size of the plateau or less than 0.", "Warning", JOptionPane.INFORMATION_MESSAGE);

    }

}
