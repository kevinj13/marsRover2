/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marsrover1302;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author kjsouribio
 */
public class Plateau extends JPanel implements ActionListener {

    int plateauX;
    int plateauY;

    int roverPositionX = 0;
    int roverPositionY = 0;
    String compassPoint = "N";
    int roverWidth = 100;
    int roverHeight = 50;
    int widthIndent = 0;
    int heightIndent = 25;

    public Plateau() {

    }

    public void getPlateauSize(int x, int y) {
        plateauX = x;
        plateauY = y;
    }

    public void getRoverPosition(int x, int y) {
        roverPositionX = x;
        roverPositionY = y;

        System.out.println(x + " " + y);

    }

    public void getRoverSize(int w, int h, String compass) {
        roverWidth = w;
        roverHeight = h;
        compassPoint = compass;
        System.out.println(roverPositionX+" " + roverPositionY + "\n"+compass + ((roverPositionX * 100) + widthIndent)+" "+ (((plateauX * 100) - ((roverPositionY + 1) * 100)) + heightIndent));

        

    }

    public void paint(Graphics plateau) {

        for (int i = 0; i < plateauX; i++) {
            for (int j = 0; j < plateauX; j++) {
                plateau.setColor(Color.red);
                plateau.fillRect(i * 100, (plateauX * 100) - ((j + 1) * 100), 100, 100);
                plateau.setColor(Color.white);
                String plateauCoordinates = Integer.toString(i) + ", " + Integer.toString(j);
                plateau.drawString(plateauCoordinates, (i * 100) + 40, (((plateauX * 100) - ((j + 1) * 100)) + 50));
            }

        }
        
        switch (compassPoint) {
            case "N":
                heightIndent = 25;
                widthIndent = 0;
                break;
            case "E":
                heightIndent = 0;
                widthIndent = 25;
                break;
            case "S":
                heightIndent = 25;
                widthIndent = 0;
                break;
            case "W":
                heightIndent = 0;
                widthIndent = 25;
                break;
            default:
                break;
        }
        
        //rover
        plateau.setColor(Color.black);
        plateau.fillRect((roverPositionX * 100) + widthIndent, ((plateauX * 100) - ((roverPositionY + 1) * 100)) + heightIndent, roverWidth, roverHeight);
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}
