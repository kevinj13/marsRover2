/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marsrover1302;

import java.util.*;
import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;

/**
 *
 * @author kjsouribio
 */
public class moveRover {

    static int maximumX;

    static int maximumY;

    static int startingPositionX;

    static int startingPositionY;

    static String startingCompassPoint;

    static String startingRoverInstructions;

    static int positionX;

    static int positionY;

    static String compassPoint;

    static String roverInstructions;

    static char[] roverInstructionsArray;

    static Plateau plateau;

    static JFrame marsRoverFrame;

    /**
     *
     * // * @param args the command line arguments
     *
     */
    public static void plateauSize() {
        System.out.println("LET'S ESTABLISH THE SIZE OF THE PLATEAU:");

        while (true) {
            Scanner maximumXInput = new Scanner(System.in);
            System.out.println("Enter the maximum X Axis");
            try {

                maximumX = Integer.parseInt(maximumXInput.next());
                if (maximumX == 0 || maximumX < 0) {
                    System.out.println("It cannot be 0 or less than 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
            }

            Scanner maximumYInput = new Scanner(System.in);
            System.out.println("Enter the maximum Y Axis");
            try {
                maximumY = Integer.parseInt(maximumYInput.next());
                if (maximumY == 0 || maximumY < 0) {
                    System.out.println("It cannot be 0 or less than 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");

            }

            if (maximumX == 0 || maximumY == 0) {
                System.out.println("You had wrong inputs. Please try again.");
            } else {
                break;
            }
        }

        marsRoverFrame = new JFrame();
        plateau = new Plateau();
        marsRoverFrame.setSize(((maximumX + 1) * 100), (maximumY * 100) + 200);
        marsRoverFrame.setTitle("Mars Rover");
        marsRoverFrame.setResizable(true);
        marsRoverFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        plateau.setBounds(0, 0, (maximumX + 1) * 100, (maximumY + 1) * 100);
        marsRoverFrame.add(plateau);
        plateau.getPlateauSize((maximumX + 1), (maximumY + 1));
        marsRoverFrame.setVisible(true);

    }

    public void roverInput() {

        // TODO code application logic here
        System.out.println("\n\nWHERE WOULD YOU LIKE THE ROVER TO START?");

        while (true) {
            boolean pass = false;

            Scanner positionXInput = new Scanner(System.in);
            System.out.println("Enter the starting X Position of the Rover");
            try {

                positionX = Integer.parseInt(positionXInput.next());
                if (positionX > maximumX || positionX < 0) {
                    System.out.println("It cannot be more than the size of the plateau or less than 0.");
                } else {
                    startingPositionX = positionX;
                    pass = true;

                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
                pass = false;
            }

            Scanner positionYInput = new Scanner(System.in);
            System.out.println("Enter the starting Y Position of the Rover");
            try {

                positionY = Integer.parseInt(positionYInput.next());
                if (positionY > maximumY || positionY < 0) {
                    System.out.println("It cannot be more than the size of the plateau or less than 0.");
                } else {
                    startingPositionY = positionY;
                    pass = true;
                }

            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
                pass = false;
            }

            if (positionX > maximumX || positionY > maximumY || pass == false) {
                System.out.println("You entered wrong inputs. Please try again.");
            } else {
                break;
            }

        }

        plateau.getRoverPosition(startingPositionX, startingPositionY);
//        marsRoverFrame.add(plateau);

        while (true) {
            Scanner compassPointInput = new Scanner(System.in);
            System.out.println("Enter the Direction the Rover is Facing: N for North, E for East, W for West, S for South");
            compassPoint = compassPointInput.next().toUpperCase();

            if (compassPoint.matches("[a-zA-Z]{1}")
                    && (compassPoint.equals("N")
                    || compassPoint.equals("E")
                    || compassPoint.equals("W")
                    || compassPoint.equals("S"))) {
                startingCompassPoint = compassPoint;
               plateau.getRoverSize(100, 100, compassPoint);
               switch (compassPoint) {
                    case "N":
                        plateau.getRoverSize(100, 50, "N");
                        break;
                    case "E":
                        plateau.getRoverSize(50, 100, "E");
                        break;
                    case "S":
                        plateau.getRoverSize(100, 50, "S");
                        break;
                    case "W":
                        plateau.getRoverSize(50, 100, "W");
                        break;        
               }

                break;
            } else {
                System.out.println("Wrong Input. It cannot be more than 1 character. Please only choose between N,E,W,");

            }
        }

        boolean instructionsCorrect = true;
        while (instructionsCorrect) {
            Scanner roverInstructionsInput = new Scanner(System.in);
            System.out.println("Enter Your Instructions: L for Left, R for Right, M for Move i.e. i.e. LMLRMLMRL");
            roverInstructions = roverInstructionsInput.next().toUpperCase();

            roverInstructionsArray = roverInstructions.toCharArray();

            for (int i = 0; i < roverInstructionsArray.length; i++) {

                if (roverInstructionsArray[i] == 'L' || roverInstructionsArray[i] == 'R' || roverInstructionsArray[i] == 'M') {
                    if (i == roverInstructionsArray.length - 1) {
//                        System.out.println("Finish");
                        instructionsCorrect = false;
                        startingRoverInstructions = roverInstructions;

                    }
                } else {
                    System.out.println("Please Enter L for Left,  R for Right, M for Move. i.e. LMLRMLMRL");
                    instructionsCorrect = true;
                    break;
                }
            }

        }

    }

    public static void move() throws InterruptedException {

        //Starting X Position
        //Starting Y Position
        //Starting facing Direction
        System.out.println("\n\nPlateau's Size: " + maximumX + ", " + maximumY);
        System.out.println("Rover Current Position: " + positionX + ", " + positionY + ", " + compassPoint);
        System.out.println("Rover Instructions: " + roverInstructions + "\n\n\n");

        roverInstructionsArray = roverInstructions.toCharArray();

        for (int i = 0; i < roverInstructionsArray.length; i++) {
//            System.out.println("INSTRUCTIONS RECEIVED: ");
            if (roverInstructionsArray[i] == 'M') {

                switch (compassPoint) {
                    case "N":
                        System.out.println("Moving Towards North");
                        System.out.println("Current Compass Point: " + compassPoint);
                        positionY += 1;
                        if (positionY > maximumY || positionY < 0) {
                            System.out.println("Rover is out of the plateau area. Staying in place.");
                            positionY -= 1;
                        }
                        System.out.println("Current Position: " + positionX + ", " + positionY + "\n");
                        plateau.getRoverPosition(positionX, positionY);
                        TimeUnit.SECONDS.sleep(3);
                        break;
                    case "E":
                        System.out.println("Moving Towards East");
                        System.out.println("Current Compass Point: " + compassPoint);
                        positionX += 1;
                        if (positionX > maximumX || positionX < 0) {
                            System.out.println("Rover is out of the plateau area. Staying in place.");
                            positionX -= 1;
                        }
                        System.out.println("Current Position: " + positionX + ", " + positionY + "\n");
                        plateau.getRoverPosition(positionX, positionY);
                        TimeUnit.SECONDS.sleep(3);

                        break;
                    case "W":
                        System.out.println("Moving Towards West");
                        System.out.println("Current Compass Point: " + compassPoint);
                        positionX -= 1;
                        if (positionX > maximumX || positionX < 0) {
                            System.out.println("Rover is out of the plateau area. Staying in place.");
                            positionX += 1;
                        }
                        System.out.println("Current Position: " + positionX + ", " + positionY + "\n");
                        plateau.getRoverPosition(positionX, positionY);
                        TimeUnit.SECONDS.sleep(3);

                        break;
                    case "S":
                        System.out.println("Moving Towards South");
                        System.out.println("Current Compass Point: " + compassPoint);
                        positionY -= 1;
                        if (positionY > maximumY || positionY < 0) {
                            System.out.println("Rover is out of the plateau area. Staying in place.");
                            positionY += 1;
                        }
                        System.out.println("Current Position: " + positionX + ", " + positionY + "\n");
                        plateau.getRoverPosition(positionX, positionY);
                        TimeUnit.SECONDS.sleep(3);

                        break;
                    default:
                        break;

                }

            }
            if (roverInstructionsArray[i] == 'L') {

                switch (compassPoint) {
                    case "N":
                        System.out.println("Turning Left to West");
                        compassPoint = "W";
                        System.out.println("Current Compass Point: " + compassPoint);
                        System.out.println("Current Position: " + positionX + ", " + positionY + "\n");
                        plateau.getRoverSize(50, 100, "W");
                        TimeUnit.SECONDS.sleep(3);

                        break;
                    case "E":
                        System.out.println("Turning Left to North");
                        compassPoint = "N";
                        System.out.println("Current Compass Point: " + compassPoint);
                        System.out.println("Current Position: " + positionX + ", " + positionY + "\n");
                        plateau.getRoverSize(100, 50, "N");
                        TimeUnit.SECONDS.sleep(3);
                        break;
                    case "S":
                        System.out.println("Turning Left to East");
                        compassPoint = "E";
                        System.out.println("Current Compass Point: " + compassPoint);
                        System.out.println("Current Position: " + positionX + ", " + positionY + "\n");
                        plateau.getRoverSize(50, 100, "E");
                        TimeUnit.SECONDS.sleep(3);
                        break;
                    case "W":
                        System.out.println("Turning Left to South");
                        compassPoint = "S";
                        System.out.println("Current Compass Point: " + compassPoint);
                        System.out.println("Current Position: " + positionX + ", " + positionY + "\n");
                        plateau.getRoverSize(100, 50, "S");
                        TimeUnit.SECONDS.sleep(3);
                        break;

                    default:
                        break;

                }

            }// L END

            if (roverInstructionsArray[i] == 'R') {

                switch (compassPoint) {
                    case "N":
                        System.out.println("Turning Left to East");
                        compassPoint = "E";
                        System.out.println("Current Compass Point: " + compassPoint);
                        System.out.println("Current Position: " + positionX + ", " + positionY + "\n");
                        plateau.getRoverSize(50, 100, "E");
                        TimeUnit.SECONDS.sleep(3);
                        break;
                    case "E":
                        System.out.println("Turning Left to South");
                        compassPoint = "S";
                        System.out.println("Current Compass Point: " + compassPoint);
                        System.out.println("Current Position: " + positionX + ", " + positionY + "\n");
                        plateau.getRoverSize(100, 50, "S");
                        TimeUnit.SECONDS.sleep(3);
                        break;
                    case "S":
                        System.out.println("Turning Left to West");
                        compassPoint = "W";
                        System.out.println("Current Compass Point: " + compassPoint);
                        System.out.println("Current Position: " + positionX + ", " + positionY + "\n");
                        plateau.getRoverSize(50, 100, "W");
                        TimeUnit.SECONDS.sleep(3);
                        break;
                    case "W":
                        System.out.println("Turning Left to North");
                        compassPoint = "N";
                        System.out.println("Current Compass Point: " + compassPoint);
                        System.out.println("Current Position: " + positionX + ", " + positionY + "\n");
                        plateau.getRoverSize(100, 50, "N");
                        TimeUnit.SECONDS.sleep(3);
                        break;

                    default:
                        break;

                }

            } //R END
        }
    }

    public String printPlateauSize() {
        String PlateauSize = maximumX + " " + maximumY;
        return PlateauSize;
    }

    public String printStartingPosition() {
        String startingPosition = startingPositionX + " " + startingPositionY + " " + startingCompassPoint + "\n\n"
                + startingRoverInstructions + "\n";
        return startingPosition;
    }

    public String printFinalPosition() {
        String finalPosition = positionX + " " + positionY + " " + compassPoint + "\n";
        return finalPosition;
    }

}
