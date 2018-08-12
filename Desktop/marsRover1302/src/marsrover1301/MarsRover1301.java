/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marsrover1301;

import java.util.*;

/**
 *
 * @author kjsouribio
 */
public class MarsRover1301 {

    /**
     *
     * @param args the command line arguments
     *
     */
    static String roverStartingPosition = "";
    static String roverFinalPosition = "";
    static int roverCount;

    public static void main(String[] args) {

        // TODO code application logic here
        moveRover.plateauSize();

        while (true) {
            Scanner roverInput = new Scanner(System.in);
            System.out.println("How many rovers would you like to add?");
            try {
                roverCount = Integer.parseInt(roverInput.next());
                if (roverCount == 0 || roverCount < 0) {
                    System.out.println("It cannot be 0 or less than 0.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");

            }

        }

        moveRover[] rover = new moveRover[roverCount];

        for (int i = 0; i < rover.length; i++) {
            rover[i] = new moveRover();
            System.out.println("\n\nRover #" + (i + 1) + ": ");
            rover[i].roverInput();
            moveRover.move();
            roverStartingPosition += rover[i].printStartingPosition() + "\n";
            roverFinalPosition += rover[i].printFinalPosition() + "\n";
        }

        String plateauSize = rover[0].printPlateauSize();

        System.out.println("INPUT: \n\n"
                + plateauSize + "\n\n"
                + roverStartingPosition + "\n\n"
                + "OUTPUT \n\n"
                + roverFinalPosition + "\n\n");
        System.out.println("END.");

    }
}
