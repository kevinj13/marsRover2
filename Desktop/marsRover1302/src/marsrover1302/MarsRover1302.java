/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marsrover1302;

import java.util.*;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author kjsouribio
 */
public class MarsRover1302 {

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
        moveRover.plateauSize();

        while (true) {
//            Scanner roverInput = new Scanner(System.in);
//            System.out.println("How many rovers would you like to add?");
            String roverInput = JOptionPane.showInputDialog("How many rovers would you like to send?");
            cancel.JOptionCancel(roverInput);

            try {
                roverCount = Integer.parseInt(roverInput);
                if (roverCount == 0 || roverCount < 0) {
//                    System.out.println("It cannot be 0 or less than 0.");
                    error.zeroOrLessThanZero();

                } else {
                    break;
                }
            } catch (NumberFormatException e) {
//                System.out.println("Please enter a number.");
                error.pleaseEnterANumber();
            }

        }

        moveRover[] rover = new moveRover[roverCount];

        for (int i = 0; i < rover.length; i++) {
            rover[i] = new moveRover();
//            System.out.println("\n\nRover #" + (i + 1) + ": ");
            JOptionPane.showMessageDialog(null, "Let's implement Rover #" + (i + 1), "\n\nRover #" + (i + 1), JOptionPane.INFORMATION_MESSAGE);

            rover[i].roverInput();
            moveRover.move();
            roverStartingPosition += rover[i].printStartingPosition() + "\n";
            roverFinalPosition += rover[i].printFinalPosition() + "\n";
        }

        String plateauSize = rover[0].printPlateauSize();

//        System.out.println("INPUT: \n\n"
//                + plateauSize + "\n\n"
//                + roverStartingPosition + "\n\n"
//                + "OUTPUT \n\n"
//                + roverFinalPosition + "\n\n");
        JOptionPane.showMessageDialog(null, "INPUT: \n\n"
                + plateauSize + "\n\n"
                + roverStartingPosition + "\n\n"
                + "OUTPUT \n\n"
                + roverFinalPosition + "\n\n", "RESULTS", JOptionPane.INFORMATION_MESSAGE);

        JOptionPane.showMessageDialog(null, "Ground Control to Major Tom. \nIt's a success!", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);

    }
}
