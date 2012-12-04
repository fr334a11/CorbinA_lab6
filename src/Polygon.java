import javax.swing.*;
import java.awt.*;
/**
 * Write a description of class Polygon here.
 * 
 * @author Sharon Dereamer
 * @version (a version number or a date)
 */
public class Polygon extends Container
{
    // instance variables - replace the example below with your own
    private int numSides;
    private int startX;
    private int startY;
    private int size;
    private double startAngle;
    private Color color = Color.cyan;
    /**
     * Constructor for objects of class Polygon
     * The parameters are:
     *  - x and y for position
     *  - the size is the length of a side in pixels
     *  - n is the number of sides the polygon will have
     *  - startAngle is the angle at which the bottom line of the polygon 
     *    is drawn as the lower left corner stays and the right corner
     *    moves up as specified in radians 
     */
    public Polygon(int x, int y, int size, int n, double startAngle)
    {
        // initialise instance variables
        setBounds (0, 0, 800, 800);
        numSides = n;
        startX = x;
        startY = y;
        this.size = size;
        this.startAngle = startAngle;
        makePolygon ();
    }
 /**
     * Constructor for objects of class Polygon
     *  The parameters are:
     *  - x and y for position
     *  - the size is the length of a side in pixels
     *  - n is the number of sides the polygon will have
     *  - startAngle is the angle at which the bottom line of the polygon
     *    is drawn as the lower left corner stays and the right corner 
     *    moves up as specified in radians
     *  - c is the color of the polygon
     */
    public Polygon(int x, int y, int size, int n, double startAngle, Color c)
    {
        // initialise instance variables
        setBounds (0, 0, 800, 800);
        numSides = n;
        startX = x;
        startY = y;
        this.size = size;
        this.startAngle = startAngle;
        this.color = c;
        makePolygon ();
    }

    double getAngle() { return startAngle; }

    private void makePolygon ()
    {
        int newX = startX;
        int newY = startY;
        double deltaAngle = (Math.PI * (numSides - 2.0)) / numSides;
        double newAngle = deltaAngle + startAngle;
        int counter = 0;
        while (counter < numSides)
        {
            int deltaX = (int) (Math.cos (newAngle) * size);
            int deltaY = (int) (Math.sin (newAngle) * size);
            Line line = new Line (newX, newY, newX + deltaX, newY - deltaY);
            line.setBackground(color);
         this.add (line, 0);
            newX = newX + deltaX;
            newY = newY - deltaY;
            counter++;
            int ratio = counter%2==0?0:1;
            newAngle = (deltaAngle * (counter + 1)) + Math.PI * ratio + startAngle;
        }
    }
   
}
