/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marsrover1303;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author kjsouribio
 */
public class moveRover extends MarsRover1303 {

    //static variables used for easier changes with the GUI
    
    static int maximumX = 0;
    static int maximumY = 0;
    
    static int startingPositionX = 0;
    static int startingPositionY = 0;
    
    static String startingCompassPoint = "N";
    static String startingRoverInstructions = "LML";
    
    static int positionX = 0;
    static int positionY = 0;
    
    static String compassPoint = "N";
    
    static String roverInstructions = "LML";
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
    //Method to ask user to input the size of the plateau.
    //Gives the user an option to have the size they wanted, instead of just the 5, 5 provided in the task
    
    //Many of the error handling is the same for all inputs. Created a separate class - ErrorMessages to print the 
    //error messages depending on the error that occured
    public static void plateauSize() {

        JOptionPane.showMessageDialog(null, "Let's establish the size of the plateau.", "Plateau", JOptionPane.INFORMATION_MESSAGE);

        while (true) {
            String maximumXInput = JOptionPane.showInputDialog("Enter the maximum X-axis of the plateau.");
            cancel.JOptionCancel(maximumXInput);
            try {

                maximumX = Integer.parseInt(maximumXInput);
                if (maximumX == 0 || maximumX < 0) {
                    error.zeroOrLessThanZero();

                }
            } catch (NumberFormatException e) {
                error.pleaseEnterANumber();

            }

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
                JOptionPane.showMessageDialog(null, "Let's start again.", "Warning", JOptionPane.INFORMATION_MESSAGE);

            } else {
                break;
            }

        }

        //Interface for the rover and the plateau - Plateau.java
        
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
        roverPositionMessage.setBounds(0, (maximumY * 100) + 85, (maximumX + 1) * 100, 100);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        marsRoverFrame.setLocation(dim.width / 2 - marsRoverFrame.getSize().width / 2, dim.height / 2 - marsRoverFrame.getSize().height / 2);
        marsRoverFrame.setVisible(true);
        
    }

    //User input for the instructions for the rover. Error handling the same for most - integers/String comparison.
    public void roverInput() {

        JOptionPane.showMessageDialog(null, "First, let's establish the starting points of the rover.", "Starting Point", JOptionPane.INFORMATION_MESSAGE);

        while (true) {
            boolean pass = false;

            String positionXInput = JOptionPane.showInputDialog("Enter the starting X Position of the Rover");
            cancel.JOptionCancel(positionXInput);

            try {

                positionX = Integer.parseInt(positionXInput);
                if (positionX > maximumX || positionX < 0) {
                    error.plateauSizeError();
                    pass = false;

                } else {
                    startingPositionX = positionX;
                    pass = true;

                }
            } catch (NumberFormatException e) {
                error.pleaseEnterANumber();
                pass = false;
            }

            String positionYInput = JOptionPane.showInputDialog("Enter the starting Y Position of the Rover");
            cancel.JOptionCancel(positionYInput);

            try {

                positionY = Integer.parseInt(positionYInput);
                if (positionY > maximumY || positionY < 0) {
                    error.plateauSizeError();
                    pass = false;

                } else {
                    startingPositionY = positionY;
                    pass = true;
                }

            } catch (NumberFormatException e) {
                error.pleaseEnterANumber();
                pass = false;
            }

            if (positionX > maximumX || positionY > maximumY || pass == false) {
                error.pleaseEnterANumber();

            } else {
                break;
            }

        }

        plateau.getRoverPosition(startingPositionX, startingPositionY);

        //Input for the starting compass point for the rover
        while (true) {
            String compassPointInput = JOptionPane.showInputDialog("Enter the Direction the Rover is Facing:\nN for North\nE for East\nW for West\nS for South");
            compassPoint = compassPointInput.toUpperCase();

            if (compassPoint.matches("[a-zA-Z]{1}")
                    && (compassPoint.equals("N")
                    || compassPoint.equals("E")
                    || compassPoint.equals("W")
                    || compassPoint.equals("S"))) {
                startingCompassPoint = compassPoint;
                plateau.getRoverSize(100, 100, compassPoint);
                //This is mainly used for the design of the rover on the JFrame
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
                JOptionPane.showMessageDialog(null, "It cannot be more than 1 character.\nPlease only choose between N,E,S,W.", "Warning", JOptionPane.INFORMATION_MESSAGE);

            }
        }

        //input for the instructions. Divided the string into array, each character then used to compare and execute the right code for the rover to work
        boolean instructionsCorrect = true;
        while (instructionsCorrect) {
            String roverInstructionsInput = JOptionPane.showInputDialog("Enter your instructions in a series of letters:\nL for Left\nR for Right\nM for Move\ni.e. LMLRMLMRL");
            roverInstructions = roverInstructionsInput.toUpperCase();

            roverInstructionsArray = roverInstructions.toCharArray();

            for (int i = 0; i < roverInstructionsArray.length; i++) {

                if (roverInstructionsArray[i] == 'L' || roverInstructionsArray[i] == 'R' || roverInstructionsArray[i] == 'M') {
                    if (i == roverInstructionsArray.length - 1) {
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

    //This method starts the rover on moving. It changes depending on the instructions given by the user. 
    public static void move() throws InterruptedException {

        //Label used visible in the JFrame for the user to get updates on the current position of the rover.
        //The change changes depending on roverPositionMessageString
        roverPositionMessage.setText("<html>Plateau's Size: " + maximumX + ", " + maximumY
                + "<br/>Rover Current Position: " + positionX + ", " + positionY + ", " + compassPoint
                + "<br/>Rover Instructions: " + roverInstructions + "</html>");
        TimeUnit.SECONDS.sleep(3);

        roverInstructionsArray = roverInstructions.toCharArray();

        for (int i = 0; i < roverInstructionsArray.length; i++) {
            if (roverInstructionsArray[i] == 'M') {

                switch (compassPoint) {
                    case "N":
                        roverPositionMessageString
                                += "<html>Moving Towards North <br/>"
                                + "Current Compass Point: " + compassPoint + "<br/>";
                        positionY += 1;
                        if (positionY > maximumY || positionY < 0) {
                            roverPositionMessageString += "Rover is out of the plateau area. Staying in place.<br/>";
                            positionY -= 1;
                        }
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
                        positionX += 1;
                        if (positionX > maximumX || positionX < 0) {
                            roverPositionMessageString += "Rover is out of the plateau area. Staying in place.<br/>";

                            positionX -= 1;
                        }
                        roverPositionMessageString += "Current Position: " + positionX + ", " + positionY + "</html>";
                        roverPositionMessage.setText(roverPositionMessageString);
                        roverPositionMessageString = "";
                        plateau.getRoverPosition(positionX, positionY);
                        TimeUnit.SECONDS.sleep(3);
                        break;
                    case "W":
                        roverPositionMessageString
                                += "<html>Moving Towards West <br/>"
                                + "Current Compass Point: " + compassPoint + "<br/>";
                        positionX -= 1;
                        if (positionX > maximumX || positionX < 0) {
                            roverPositionMessageString += "Rover is out of the plateau area. Staying in place.<br/>";

                            positionX += 1;
                        }
                        roverPositionMessageString += "Current Position: " + positionX + ", " + positionY + "</html>";
                        roverPositionMessage.setText(roverPositionMessageString);
                        roverPositionMessageString = "";
                        plateau.getRoverPosition(positionX, positionY);
                        TimeUnit.SECONDS.sleep(3);

                        break;
                    case "S":
                        roverPositionMessageString
                                += "<html>Moving Towards South <br/>"
                                + "Current Compass Point: " + compassPoint + "<br/>";
                        positionY -= 1;
                        if (positionY > maximumY || positionY < 0) {
                            roverPositionMessageString += "Rover is out of the plateau area. Staying in place.<br/>";

                            positionY += 1;
                        }
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
                        roverPositionMessageString
                                += "<html>Turning Left to West<br/>"
                                + "Current Compass Point: " + compassPoint + "<br/>"
                                + "Current Position: " + positionX + ", " + positionY + "</html>";
                        roverPositionMessage.setText(roverPositionMessageString);
                        roverPositionMessageString = "";
                        plateau.getRoverSize(50, 100, "W");
                        TimeUnit.SECONDS.sleep(3);

                        break;
                    case "E":
                        compassPoint = "N";
                        roverPositionMessageString
                                += "<html>Turning Left to North<br/>"
                                + "Current Compass Point: " + compassPoint + "<br/>"
                                + "Current Position: " + positionX + ", " + positionY + "</html>";
                        roverPositionMessage.setText(roverPositionMessageString);
                        roverPositionMessageString = "";

                        plateau.getRoverSize(100, 50, "N");
                        TimeUnit.SECONDS.sleep(3);
                        break;
                    case "S":
                        compassPoint = "E";
                        roverPositionMessageString
                                += "<html>Turning Left to East<br/>"
                                + "Current Compass Point: " + compassPoint + "<br/>"
                                + "Current Position: " + positionX + ", " + positionY + "</html>";
                        roverPositionMessage.setText(roverPositionMessageString);
                        roverPositionMessageString = "";
                        plateau.getRoverSize(50, 100, "E");
                        TimeUnit.SECONDS.sleep(3);
                        break;
                    case "W":
                        compassPoint = "S";
                        roverPositionMessageString
                                += "<html>Turning Left to South<br/>"
                                + "Current Compass Point: " + compassPoint + "<br/>"
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
                        compassPoint = "E";
                        roverPositionMessageString
                                += "<html>Turning Right to East<br/>"
                                + "Current Compass Point: " + compassPoint + "<br/>"
                                + "Current Position: " + positionX + ", " + positionY + "</html>";
                        roverPositionMessage.setText(roverPositionMessageString);
                        roverPositionMessageString = "";

                        plateau.getRoverSize(50, 100, "E");
                        TimeUnit.SECONDS.sleep(3);
                        break;
                    case "E":
                        compassPoint = "S";
                        roverPositionMessageString
                                += "<html>Turning Right to South<br/>"
                                + "Current Compass Point: " + compassPoint + "<br/>"
                                + "Current Position: " + positionX + ", " + positionY + "</html>";
                        roverPositionMessage.setText(roverPositionMessageString);
                        roverPositionMessageString = "";

                        plateau.getRoverSize(100, 50, "S");
                        TimeUnit.SECONDS.sleep(3);
                        break;
                    case "S":
                        compassPoint = "W";

                        roverPositionMessageString
                                += "<html>Turning Right to West<br/>"
                                + "Current Compass Point: " + compassPoint + "<br/>"
                                + "Current Position: " + positionX + ", " + positionY + "</html>";
                        roverPositionMessage.setText(roverPositionMessageString);
                        roverPositionMessageString = "";

                        plateau.getRoverSize(50, 100, "W");
                        TimeUnit.SECONDS.sleep(3);
                        break;
                    case "W":
                        compassPoint = "N";
                        roverPositionMessageString
                                += "<html>Turning Right to North<br/>"
                                + "Current Compass Point: " + compassPoint + "<br/>"
                                + "Current Position: " + positionX + ", " + positionY + "</html>";
                        roverPositionMessage.setText(roverPositionMessageString);
                        roverPositionMessageString = "";
                        plateau.getRoverSize(100, 50, "N");
                        TimeUnit.SECONDS.sleep(3);
                        break;

                    default:
                        break;

                }

            } //R END
        }
    }

    //Final Screen results - input/output - returning them as Strings
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
