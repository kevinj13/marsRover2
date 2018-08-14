/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marsrover1303;

import javax.swing.JOptionPane;

/**
 *
 * @author kjsouribio
 */
public class MarsRover1303 {

    /**
     *
     * @param args the command line arguments
     *
     */
    static String roverStartingPosition = "";
    static String roverFinalPosition = "";
    static int roverCount;
    
    static JOptionCancel cancel = new JOptionCancel();
    static ErrorMessages error = new ErrorMessages();

    public static void main(String[] args) throws InterruptedException {

        // TODO code application logic here
        JOptionPane.showMessageDialog(null, "Welcome to the Nando's \nMars Rover Launch Program. \nClick 'OK' to continue", "Nando's Mars Rover Launch", JOptionPane.INFORMATION_MESSAGE);
        moveRover.plateauSize(); //Call plateauSize method from moveRover class to get plateau size.

        while (true) {
            //Thought it would be a great idea to give users the freedom to input how many rovers to send; instead of just 2
            //Used an array to differentiate the rovers

            String roverInput = JOptionPane.showInputDialog("How many rovers would you like to send?"); //roverInput; size = number of rovers
            cancel.JOptionCancel(roverInput);
            //try catch method used to make sure user inputs a positive integer
            try {
                roverCount = Integer.parseInt(roverInput);
                if (roverCount == 0 || roverCount < 0) {
                    error.zeroOrLessThanZero();

                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                error.pleaseEnterANumber();
            }

        }

        moveRover[] rover = new moveRover[roverCount];
        //loop through array, each item being a rover
        for (int i = 0; i < rover.length; i++) {
            rover[i] = new moveRover();
            JOptionPane.showMessageDialog(null, "Let's implement Rover #" + (i + 1), "\n\nRover #" + (i + 1), JOptionPane.INFORMATION_MESSAGE);

            rover[i].roverInput(); //Calling roverInput from moveRover class to input rover instructions
            moveRover.move();

            roverStartingPosition += rover[i].printStartingPosition() + "\n";
            roverFinalPosition += rover[i].printFinalPosition() + "\n";
        }

        String plateauSize = rover[0].printPlateauSize();

        //Used this message box to be more identical to desired output based on the task given.
        JOptionPane.showMessageDialog(null, "INPUT: \n\n"
                + plateauSize + "\n\n"
                + roverStartingPosition + "\n\n"
                + "OUTPUT \n\n"
                + roverFinalPosition + "\n\n", "RESULTS", JOptionPane.INFORMATION_MESSAGE);

        JOptionPane.showMessageDialog(null, "Ground Control to Major Tom. \nIt's a success!", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }
}
