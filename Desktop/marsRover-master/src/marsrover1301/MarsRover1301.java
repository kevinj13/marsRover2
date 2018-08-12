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

    public static void main(String[] args) {

        // TODO code application logic here
        moveRover.plateauSize();

        Scanner roverInput = new Scanner(System.in);
        System.out.println("How many rovers would you like to add?");
        int roverCount = Integer.parseInt(roverInput.next());

        moveRover[] rover = new moveRover[roverCount];

        for (int i = 0; i < rover.length; i++) {
            rover[i] = new moveRover();
            System.out.println("\n\nRover #" + (i + 1) + ": ");
            rover[i].roverInput();
            moveRover.move();
            roverStartingPosition += rover[i].printStartingPosition() + "\n";
            roverFinalPosition += rover[i].printFinalPosition() + "\n";
        }

        String plateauSize = rover[1].printPlateauSize();

        System.out.println("INPUT: \n\n"
                + plateauSize + "\n\n"
                + roverStartingPosition + "\n\n"
                + "OUTPUT \n\n"
                + roverFinalPosition + "\n\n");
        System.out.println("END.");

    }
}
