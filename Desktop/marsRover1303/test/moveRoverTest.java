/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import marsrover1303.MarsRover1303;
import marsrover1303.moveRover;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mockito;

/**
 *
 * @author kjsouribio
 */
public class moveRoverTest {
    
    public moveRoverTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    int maxX = 0;
    int maxY = 0;
    
    int startingPositionX = 0;
    int startingPositionY = 0;
    String startingCompassPoint = "N";
    String startingRoverInstructions = "LML";
            
    int rover1X = 1;
    int rover1Y = 2;
    String facing = "N";
    
    String printPlateau = maxX + " " + maxY;
    
    String printStarting = startingPositionX + " " + startingPositionY + " " + startingCompassPoint + "\n\n"
                + startingRoverInstructions + "\n";
    
    @Test
    public void plateauSizeTest(){
        
        moveRover printPlateau1 = new moveRover();
        
        String output = printPlateau1.printPlateauSize();
        assertEquals(printPlateau, output);
        
    }
    
    @Test
    public void startingPositionTest(){
        moveRover printStart = new moveRover();
        
        String output = printStart.printStartingPosition();
        assertEquals(printStarting, output);
    }
    
    //Methods can be called
    @Test
    public void plateauSizeCallTest(){
        moveRover rover1 = Mockito.mock(moveRover.class);
        moveRover.plateauSize();   
    }
    
    @Test
    public void roverInputTest(){
        moveRover rover2 = Mockito.mock(moveRover.class);
        rover2.roverInput();   
    }
    
    //Most variables are global and static. Not a lot of return type methods used. Cannot test a lot; except for making sure methods get called.
}
