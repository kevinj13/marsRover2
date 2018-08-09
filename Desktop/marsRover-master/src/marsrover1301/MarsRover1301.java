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
     * @param args the command line arguments
     */
    static int maximumX;

    static int maximumY;

    static int positionX;

    static int positionY;

    static String compassPoint;

    static String roverInstructions;

    /**
     *
     * @param args the command line arguments
     *
     */
    public static void main(String[] args) {

        // TODO code application logic here
        System.out.println("LET'S ESTABLISH THE SIZE OF THE PLATEAU:");
        Scanner maximumXInput = new Scanner(System.in);

        System.out.println("Enter the maximum X Axis");
        maximumX = Integer.parseInt(maximumXInput.next());

        Scanner maximumYInput = new Scanner(System.in);
        System.out.println("Enter the maximum Y Axis");

        maximumY = Integer.parseInt(maximumYInput.next());
        System.out.println("WHERE WOULD YOU LIKE THE ROVER TO START?");

        Scanner positionXInput = new Scanner(System.in);
        System.out.println("Enter the starting X Position of the Rover");
        positionX = Integer.parseInt(positionXInput.next());

        Scanner positionYInput = new Scanner(System.in);
        System.out.println("Enter the X Position of the Rover");
        positionY = Integer.parseInt(positionYInput.next());

        boolean correct = true;
        while (correct) {
            Scanner compassPointInput = new Scanner(System.in);
            System.out.println("Enter the Direction the Rover is Facing: N for North, E for East, W for West, S for South");
            compassPoint = compassPointInput.next().toUpperCase();
            correct = !compassPoint.matches("[a-zA-Z]{1}");
        }

        Scanner roverInstructionsInput = new Scanner(System.in);
        System.out.println("Enter Your Instructions");
        roverInstructions = roverInstructionsInput.next().toUpperCase();

        move();

    }

    public static void move() {

        char[] roverInstructionsArray = roverInstructions.toCharArray();

        System.out.println(roverInstructionsArray[0]);

        for (int i = 0; i < roverInstructionsArray.length; i++) {

            System.out.print(roverInstructionsArray[i]);

            if (roverInstructionsArray[i] == 'L') {

            }

        }

    }
}
