/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marsrover1302;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.*;
import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author kjsouribio
 */
public class moveRover extends MarsRover1302 {

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

    static JLabel roverPositionMessage;

    static String roverPositionMessageString = "";

    /**
     *
     * // * @param args the command line arguments
     *
     */
    public static void plateauSize() {

//        System.out.println("LET'S ESTABLISH THE SIZE OF THE PLATEAU:");
        JOptionPane.showMessageDialog(null, "Let's establish the size of the plateau.", "Plateau", JOptionPane.INFORMATION_MESSAGE);

        while (true) {
//            Scanner maximumXInput = new Scanner(System.in);
//            System.out.println("Enter the maximum X Axis");
            String maximumXInput = JOptionPane.showInputDialog("Enter the maximum X-axis of the plateau.");
            cancel.JOptionCancel(maximumXInput);
            try {

                maximumX = Integer.parseInt(maximumXInput);
                if (maximumX == 0 || maximumX < 0) {
//                    System.out.println("It cannot be 0 or less than 0.");
                    error.zeroOrLessThanZero();

                }
            } catch (NumberFormatException e) {
//                System.out.println("Please enter a number.");
                error.pleaseEnterANumber();

            }

//            Scanner maximumYInput = new Scanner(System.in);
//            System.out.println("Enter the maximum Y Axis");
            String maximumYInput = JOptionPane.showInputDialog("Enter the maximum Y-axis of the plateau.");
            cancel.JOptionCancel(maximumYInput);

            try {
                maximumY = Integer.parseInt(maximumYInput);
                if (maximumY == 0 || maximumY < 0) {
                    error.zeroOrLessThanZero();
                }
            } catch (NumberFormatException e) {
                error.pleaseEnterANumber();

            }

            if (maximumX == 0 || maximumY == 0) {
//                System.out.println("You had wrong inputs. Please try again.");
                JOptionPane.showMessageDialog(null, "Let's start again.", "Warning", JOptionPane.INFORMATION_MESSAGE);

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
        plateau.getPlateauSize((maximumX + 1), (maximumY + 1));
        marsRoverFrame.add(plateau);
        JPanel panel = (JPanel) marsRoverFrame.getContentPane();
        panel.setLayout(null);
        roverPositionMessage = new JLabel("Messages Incoming...");
        panel.add(roverPositionMessage);
        Dimension size = roverPositionMessage.getPreferredSize();
        roverPositionMessage.setBounds(0, (maximumY * 100) + 85, (maximumX + 1) * 100, 100);
        marsRoverFrame.setVisible(true);

    }

    public void roverInput() {

        // TODO code application logic here
//        System.out.println("\n\nWHERE WOULD YOU LIKE THE ROVER TO START?");
        JOptionPane.showMessageDialog(null, "First, let's establish the starting points of the rover.", "Starting Point", JOptionPane.INFORMATION_MESSAGE);

        while (true) {
            boolean pass = false;

//            Scanner positionXInput = new Scanner(System.in);
//            System.out.println("Enter the starting X Position of the Rover");
            String positionXInput = JOptionPane.showInputDialog("Enter the starting X Position of the Rover");
            cancel.JOptionCancel(positionXInput);

            try {

                positionX = Integer.parseInt(positionXInput);
                if (positionX > maximumX || positionX < 0) {
//                    System.out.println("It cannot be more than the size of the plateau or less than 0.");
                    error.plateauSizeError();
                    pass = false;

                } else {
                    startingPositionX = positionX;
                    pass = true;

                }
            } catch (NumberFormatException e) {
//                System.out.println("Please enter a number.");
                error.pleaseEnterANumber();
                pass = false;
            }

//            Scanner positionYInput = new Scanner(System.in);
//            System.out.println("Enter the starting Y Position of the Rover");
            String positionYInput = JOptionPane.showInputDialog("Enter the starting Y Position of the Rover");
            cancel.JOptionCancel(positionYInput);

            try {

                positionY = Integer.parseInt(positionYInput);
                if (positionY > maximumY || positionY < 0) {
//                    System.out.println("It cannot be more than the size of the plateau or less than 0.");
                    error.plateauSizeError();
                    pass = false;

                } else {
                    startingPositionY = positionY;
                    pass = true;
                }

            } catch (NumberFormatException e) {
//                System.out.println("Please enter a number.");
                error.pleaseEnterANumber();
                pass = false;
            }

            if (positionX > maximumX || positionY > maximumY || pass == false) {
//                System.out.println("You entered wrong inputs. Please try again.");
                error.pleaseEnterANumber();

            } else {
                break;
            }

        }

        plateau.getRoverPosition(startingPositionX, startingPositionY);
//        marsRoverFrame.add(plateau);

        while (true) {
//            Scanner compassPointInput = new Scanner(System.in);
//            System.out.println("Enter the Direction the Rover is Facing: N for North, E for East, W for West, S for South");
            String compassPointInput = JOptionPane.showInputDialog("Enter the Direction the Rover is Facing:\nN for North\nE for East\nW for West\nS for South");
            compassPoint = compassPointInput.toUpperCase();

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
//                System.out.println("Wrong Input. It cannot be more than 1 character. Please only choose between N,E,W,");
                JOptionPane.showMessageDialog(null, "It cannot be more than 1 character.\nPlease only choose between N,E,S,W.", "Warning", JOptionPane.INFORMATION_MESSAGE);

            }
        }

        boolean instructionsCorrect = true;
        while (instructionsCorrect) {
//            Scanner roverInstructionsInput = new Scanner(System.in);
//            System.out.println("Enter Your Instructions: L for Left, R for Right, M for Move i.e. i.e. LMLRMLMRL");
            String roverInstructionsInput = JOptionPane.showInputDialog("Enter your instructions in a series of letters:\nL for Left\nR for Right\nM for Move\ni.e. LMLRMLMRL");
            roverInstructions = roverInstructionsInput.toUpperCase();

            roverInstructionsArray = roverInstructions.toCharArray();

            for (int i = 0; i < roverInstructionsArray.length; i++) {

                if (roverInstructionsArray[i] == 'L' || roverInstructionsArray[i] == 'R' || roverInstructionsArray[i] == 'M') {
                    if (i == roverInstructionsArray.length - 1) {
//                        System.out.println("Finish");
                        instructionsCorrect = false;
                        startingRoverInstructions = roverInstructions;

                    }
                } else {
                    System.out.println("Please Enter\nL for Left\nR for Right\nM for Move\ni.e. LMLRMLMRL");
                    JOptionPane.showMessageDialog(null, "Please Enter\nL for Left\nR for Right\nM for Move\ni.e. LMLRMLMRL", "Warning", JOptionPane.INFORMATION_MESSAGE);

                    instructionsCorrect = true;
                    break;
                }
            }

        }

    }

    public static void move() throws InterruptedException {

        //Starting X Position
        //Starting Y Position
//        //Starting facing Direction
//        System.out.println("\n\nPlateau's Size: " + maximumX + ", " + maximumY);
//        System.out.println("Rover Current Position: " + positionX + ", " + positionY + ", " + compassPoint);
//        System.out.println("Rover Instructions: " + roverInstructions + "\n\n\n");
        roverPositionMessage.setText("<html>Plateau's Size: " + maximumX + ", " + maximumY
                + "<br/>Rover Current Position: " + positionX + ", " + positionY + ", " + compassPoint
                + "<br/>Rover Instructions: " + roverInstructions + "</html>");
        TimeUnit.SECONDS.sleep(3);

        roverInstructionsArray = roverInstructions.toCharArray();

        for (int i = 0; i < roverInstructionsArray.length; i++) {
//            System.out.println("INSTRUCTIONS RECEIVED: ");
            if (roverInstructionsArray[i] == 'M') {

                switch (compassPoint) {
                    case "N":
                        roverPositionMessageString
                                += "<html>Moving Towards North <br/>"
                                + "Current Compass Point: " + compassPoint + "<br/>";
//                        System.out.println("Moving Towards North");
//                        System.out.println("Current Compass Point: " + compassPoint);
                        positionY += 1;
                        if (positionY > maximumY || positionY < 0) {
                            roverPositionMessageString += "Rover is out of the plateau area. Staying in place.<br/>";
//                            System.out.println("Rover is out of the plateau area. Staying in place.");
                            positionY -= 1;
                        }
//                        System.out.println("Current Position: " + positionX + ", " + positionY + "\n");
                        roverPositionMessageString += "Current Position: " + positionX + ", " + positionY + "</html>";
                        roverPositionMessage.setText(roverPositionMessageString);
                        roverPositionMessageString = "";
                        plateau.getRoverPosition(positionX, positionY);
                        TimeUnit.SECONDS.sleep(3);
                        break;
                    case "E":
                        roverPositionMessageString
                                += "<html>Moving Towards East <br/>"
                                + "Current Compass Point: " + compassPoint + "<br/>";
//                        System.out.println("Moving Towards East");
//                        System.out.println("Current Compass Point: " + compassPoint);
                        positionX += 1;
                        if (positionX > maximumX || positionX < 0) {
//                            System.out.println("Rover is out of the plateau area. Staying in place.");
                            roverPositionMessageString += "Rover is out of the plateau area. Staying in place.<br/>";

                            positionX -= 1;
                        }
//                        System.out.println("Current Position: " + positionX + ", " + positionY + "\n");
                        roverPositionMessageString += "Current Position: " + positionX + ", " + positionY + "</html>";
                        roverPositionMessage.setText(roverPositionMessageString);
                        roverPositionMessageString = "";
                        plateau.getRoverPosition(positionX, positionY);
                        TimeUnit.SECONDS.sleep(3);

                        break;
                    case "W":
//                        System.out.println("Moving Towards West");
                        roverPositionMessageString
                                += "<html>Moving Towards West <br/>"
                                + "Current Compass Point: " + compassPoint + "<br/>";
//                        System.out.println("Current Compass Point: " + compassPoint);
                        positionX -= 1;
                        if (positionX > maximumX || positionX < 0) {
//                            System.out.println("Rover is out of the plateau area. Staying in place.");
                            roverPositionMessageString += "Rover is out of the plateau area. Staying in place.<br/>";

                            positionX += 1;
                        }
//                        System.out.println("Current Position: " + positionX + ", " + positionY + "\n");
                        roverPositionMessageString += "Current Position: " + positionX + ", " + positionY + "</html>";
                        roverPositionMessage.setText(roverPositionMessageString);
                        roverPositionMessageString = "";
                        plateau.getRoverPosition(positionX, positionY);
                        TimeUnit.SECONDS.sleep(3);

                        break;
                    case "S":
//                        System.out.println("Moving Towards South");
//                        System.out.println("Current Compass Point: " + compassPoint);
                        roverPositionMessageString
                                += "<html>Moving Towards South <br/>"
                                + "Current Compass Point: " + compassPoint + "<br/>";
                        positionY -= 1;
                        if (positionY > maximumY || positionY < 0) {
//                            System.out.println("Rover is out of the plateau area. Staying in place.");
                            roverPositionMessageString += "Rover is out of the plateau area. Staying in place.<br/>";

                            positionY += 1;
                        }
//                        System.out.println("Current Position: " + positionX + ", " + positionY + "\n");
                        roverPositionMessageString += "Current Position: " + positionX + ", " + positionY + "</html>";
                        roverPositionMessage.setText(roverPositionMessageString);
                        roverPositionMessageString = "";
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
                        compassPoint = "W";
//                        System.out.println("Turning Left to West");
//                        System.out.println("Current Compass Point: " + compassPoint);
//                        System.out.println("Current Position: " + positionX + ", " + positionY + "\n");
                        roverPositionMessageString
                                += "<html>Turning Left to West<br/>"
                                + "Current Compass Point: " + compassPoint  + "<br/>"
                                + "Current Position: " + positionX + ", " + positionY + "</html>";
                        roverPositionMessage.setText(roverPositionMessageString);
                        roverPositionMessageString = "";
                        plateau.getRoverSize(50, 100, "W");
                        TimeUnit.SECONDS.sleep(3);

                        break;
                    case "E":
                        compassPoint = "N";
//                        System.out.println("Turning Left to North");
//                        System.out.println("Current Compass Point: " + compassPoint);
//                        System.out.println("Current Position: " + positionX + ", " + positionY + "\n");
                        
                        roverPositionMessageString
                                += "<html>Turning Left to North<br/>"
                                + "Current Compass Point: " + compassPoint  + "<br/>"
                                + "Current Position: " + positionX + ", " + positionY + "</html>";
                        roverPositionMessage.setText(roverPositionMessageString);
                        roverPositionMessageString = "";
                        
                        plateau.getRoverSize(100, 50, "N");
                        TimeUnit.SECONDS.sleep(3);
                        break;
                    case "S":
//                        System.out.println("Turning Left to East");
                        compassPoint = "E";
//                        System.out.println("Current Compass Point: " + compassPoint);
//                        System.out.println("Current Position: " + positionX + ", " + positionY + "\n");
                        roverPositionMessageString
                                += "<html>Turning Left to East<br/>"
                                + "Current Compass Point: " + compassPoint  + "<br/>"
                                + "Current Position: " + positionX + ", " + positionY + "</html>";
                        roverPositionMessage.setText(roverPositionMessageString);
                        roverPositionMessageString = "";
                        plateau.getRoverSize(50, 100, "E");
                        TimeUnit.SECONDS.sleep(3);
                        break;
                    case "W":
//                        System.out.println("Turning Left to South");
                        compassPoint = "S";
//                        System.out.println("Current Compass Point: " + compassPoint);
//                        System.out.println("Current Position: " + positionX + ", " + positionY + "\n");
                        roverPositionMessageString
                                += "<html>Turning Left to South<br/>"
                                + "Current Compass Point: " + compassPoint  + "<br/>"
                                + "Current Position: " + positionX + ", " + positionY + "</html>";
                        roverPositionMessage.setText(roverPositionMessageString);
                        roverPositionMessageString = "";
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
//                        System.out.println("Turning Right to East");
                        compassPoint = "E";
//                        System.out.println("Current Compass Point: " + compassPoint);
//                        System.out.println("Current Position: " + positionX + ", " + positionY + "\n");
                        
                        roverPositionMessageString
                                += "<html>Turning Right to East<br/>"
                                + "Current Compass Point: " + compassPoint  + "<br/>"
                                + "Current Position: " + positionX + ", " + positionY + "</html>";
                        roverPositionMessage.setText(roverPositionMessageString);
                        roverPositionMessageString = "";
                        
                        plateau.getRoverSize(50, 100, "E");
                        TimeUnit.SECONDS.sleep(3);
                        break;
                    case "E":
//                        System.out.println("Turning Right to South");
                        compassPoint = "S";
//                        System.out.println("Current Compass Point: " + compassPoint);
//                        System.out.println("Current Position: " + positionX + ", " + positionY + "\n");
                        
                        roverPositionMessageString
                                += "<html>Turning Right to South<br/>"
                                + "Current Compass Point: " + compassPoint  + "<br/>"
                                + "Current Position: " + positionX + ", " + positionY + "</html>";
                        roverPositionMessage.setText(roverPositionMessageString);
                        roverPositionMessageString = "";
                        
                        plateau.getRoverSize(100, 50, "S");
                        TimeUnit.SECONDS.sleep(3);
                        break;
                    case "S":
//                        System.out.println("Turning Right to West");
                        compassPoint = "W";
//                        System.out.println("Current Compass Point: " + compassPoint);
//                        System.out.println("Current Position: " + positionX + ", " + positionY + "\n");
                        
                        roverPositionMessageString
                                += "<html>Turning Right to West<br/>"
                                + "Current Compass Point: " + compassPoint  + "<br/>"
                                + "Current Position: " + positionX + ", " + positionY + "</html>";
                        roverPositionMessage.setText(roverPositionMessageString);
                        roverPositionMessageString = "";
                        
                        plateau.getRoverSize(50, 100, "W");
                        TimeUnit.SECONDS.sleep(3);
                        break;
                    case "W":
//                        System.out.println("Turning Right to North");
                        compassPoint = "N";
                        
                        roverPositionMessageString
                                += "<html>Turning Right to North<br/>"
                                + "Current Compass Point: " + compassPoint  + "<br/>"
                                + "Current Position: " + positionX + ", " + positionY + "</html>";
                        roverPositionMessage.setText(roverPositionMessageString);
                        roverPositionMessageString = "";
                        
//                        System.out.println("Current Compass Point: " + compassPoint);
//                        System.out.println("Current Position: " + positionX + ", " + positionY + "\n");
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

    public static void JOptionCancel(String input) {

    }

}
