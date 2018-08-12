/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marsrover1301;

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
    public static void main(String[] args) {

        // TODO code application logic here
        moveRover rover1 = new moveRover();
        moveRover.plateauSize();
        rover1.roverInput();
        moveRover.move();
        String rover1startingPosition = rover1.printStartingPosition();

        String rover1FinalPosition = rover1.printFinalPosition();

        moveRover rover2 = new moveRover();
        rover2.roverInput();
        moveRover.move();
        String rover2startingPosition = rover2.printStartingPosition();
        String rover2FinalPosition = rover2.printFinalPosition();

        String plateauSize = rover1.printPlateauSize();
        System.out.println("INPUT: \n");
        System.out.println(plateauSize + "\n");
        System.out.println(rover1startingPosition + "\n");
        System.out.println(rover2startingPosition + "\n");

        System.out.println("OUTPUT \n");
        System.out.println(rover1FinalPosition + "\n");
        System.out.println(rover2FinalPosition + "\n");

        System.out.println("END.");

    }
}
