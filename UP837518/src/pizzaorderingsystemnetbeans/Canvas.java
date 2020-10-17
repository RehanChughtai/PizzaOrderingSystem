package pizzaorderingsystemnetbeans;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

/**
 * Class Canvas - a class to allow for simple graphical 
 * drawing on a canvas.
 * 
 * @author Michael Kölling (mik)
 * @author Bruce Quig
 *
 * @version 2011.07.31
 */

public class Canvas
{
    private JFrame frame;
    private CanvasPane canvas;
    private Graphics2D graphic;
    private Color backgroundColor;
    private Image canvasImage;

    /**
     * Create a Canvas with default height, width and background color 
     * (300, 300, white).
     * @param title  title to appear in Canvas Frame     
     */
    public Canvas(String title)
    {
        this(title, 300, 300, Color.white);
    }

    /**
     * Create a Canvas with default background color (white).
     * @param title  title to appear in Canvas Frame
     * @param width  the desired width for the canvas
     * @param height  the desired height for the canvas
     */
    public Canvas(String title, int width, int height)
    {
        this(title, width, height, Color.white);
    }

    /**
     * Create a Canvas.
     * @param title  title to appear in Canvas Frame
     * @param width  the desired width for the canvas
     * @param height  the desired height for the canvas
     * @param bgColor  the desired background color of the canvas
     */
    public Canvas(String title, int width, int height, Color bgColor)
    {
        frame = new JFrame();
        canvas = new CanvasPane();
        frame.setContentPane(canvas);
        frame.setTitle(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        canvas.setPreferredSize(new Dimension(width, height));
        backgroundColor = bgColor;
        frame.pack();
        setVisible(true);
    }

    /**
     * Set the canvas visibility and brings canvas to the front of screen
     * when made visible. This method can also be used to bring an already
     * visible canvas to the front of other windows.
     * @param visible  boolean value representing the desired visibility of
     * the canvas (true or false) 
     */
    public void setVisible(boolean visible)
    {
        if(graphic == null) {
            // first time: instantiate the offscreen image and fill it with
            // the background color
            Dimension size = canvas.getSize();
            canvasImage = canvas.createImage(size.width, size.height);
            graphic = (Graphics2D)canvasImage.getGraphics();
            graphic.setColor(backgroundColor);
            graphic.fillRect(0, 0, size.width, size.height);
            graphic.setColor(Color.black);
        }
        frame.setVisible(true);
    }

    /**
     * Provide information on visibility of the Canvas.
     * @return  true if canvas is visible, false otherwise
     */
    public boolean isVisible()
    {
        return frame.isVisible();
    }

    /**
     * Draw the outline of a given shape onto the canvas.
     * @param  shape  the shape object to be drawn on the canvas
     */
    public void draw(Shape shape)
    {
        graphic.draw(shape);
        canvas.repaint();
    }
 
    /**
     * Fill the internal dimensions of a given shape with the current 
     * foreground color of the canvas.
     * @param  shape  the shape object to be filled 
     */
    public void fill(Shape shape)
    {
        graphic.fill(shape);
        canvas.repaint();
    }

    /**
     * Fill the internal dimensions of the given circle with the current 
     * foreground color of the canvas.
     * @param  xPos  The x-coordinate of the circle center point
     * @param  yPos  The y-coordinate of the circle center point
     * @param  diameter  The diameter of the circle to be drawn
     */
    public void fillCircle(double xPos, double yPos, double diameter)
    {
        double xEdge = xPos - (diameter/2);
        double yEdge = yPos - (diameter/2);
        Ellipse2D.Double circle = new Ellipse2D.Double(xEdge, yEdge, diameter, diameter);
        fill(circle);
    }
   
    /**
     * Fill the internal dimensions of the given semicircle with the current
     * foreground color of the canvas.
     * @param xPos The x coordinate of the upper-left corner of the framing rectangle of the semicircle
     * @param yPos The y coordinate of the upper-left corner of the framing rectangle of the semicircle
     * @param width The overall width of the full circle of which this semicircle is a partial section
     * @param height The overall height of the full circle of which this semicircle is a partial section
     * @param verticalMidLine if true, the line bounding the semicircle will be vertical
     * @param leftFacing if true and vertical, arc will point left.  if true and horizontal, arc will point upwards
     */
    public void fillSemiCircle(double xPos, double yPos, double width, double height, boolean verticalMidLine, boolean leftFacing)
    {
        int startingAngle, extentAngle;
        
        if(verticalMidLine)
        {
            startingAngle = 90;
        }
        else
        {
            startingAngle = 0;
        }
        
        if(leftFacing)
        {
            extentAngle = 180;
        }
        else
        {
            extentAngle = -180;
        }
        
        fill(new Arc2D.Double(xPos, yPos, width, height, startingAngle, extentAngle, Arc2D.CHORD));
    }

    /**
     * Fill the internal dimensions of the given rectangle with the current 
     * foreground color of the canvas. This is a convenience method. A similar 
     * effect can be achieved with the "fill" method.
     * 
     * @param xPos The x coordinate of the upper left corner of the rectangle
     * @param yPos The y coordinate of the upper left corner of the rectangle
     * @param width The width of the rectangle
     * @param height The height of the rectangle
     */
    public void fillRectangle(double xPos, double yPos, double width, double height)
    {
        fill(new Rectangle2D.Double(xPos, yPos, width, height));
    }
    
    /**
     * Fill the internal dimensions of the given triangle with the current color
     * of the canvas.  The order of the points provided is not relevant.
     * 
     * @param xP1 The x coordinate of the first point of the triangle
     * @param yP1 The y coordinate of the first point of the triangle
     * @param xP2 The x coordinate of the second point of the triangle
     * @param yP2 The y coordinate of the second point of the triangle
     * @param xP3 The x coordinate of the third point of the triangle
     * @param yP3 The y coordinate of the third point of the triangle
     */
    public void fillTriangle(double xP1, double yP1, double xP2, double yP2, double xP3, double yP3)
    {
        Point2D.Double[] trianglePoints = new Point2D.Double[3];
        trianglePoints[0] = new Point2D.Double(xP1, yP1);
        trianglePoints[1] = new Point2D.Double(xP2, yP2);
        trianglePoints[2] = new Point2D.Double(xP3, yP3);
        
        fill(new PolyShape(trianglePoints));
    }
    
    /**
     * Fill the internal dimensions of the given polygon with the current color
     * of the canvas.  The points should be given in a cycle.
     * @param xPoints The x coordinates for all the points in the polygon
     * @param yPoints The y coordinates for all the points in the polygon
     */
    public void fillPolygon(double[] xPoints, double[] yPoints)
    {
        if(xPoints.length != yPoints.length)
        {
            System.out.println("The x and y coordinate lists must be the same length");
        }
        else
        {
            Point2D.Double[] polyPoints = new Point2D.Double[xPoints.length];
            
            for(int i = 0; i < xPoints.length; i++)
            {
                polyPoints[i] = new Point2D.Double(xPoints[i], yPoints[i]);
            }
            
            fill(new PolyShape(polyPoints));
        }
    }
    
    /**
     * Erase the whole canvas.
     */
    public void erase()
    {
        Color original = graphic.getColor();
        graphic.setColor(backgroundColor);
        Dimension size = canvas.getSize();
        graphic.fill(new Rectangle(0, 0, size.width, size.height));
        graphic.setColor(original);
        canvas.repaint();
    }

    /**
     * Erase the internal dimensions of the given circle. This is a 
     * convenience method. A similar effect can be achieved with
     * the "erase" method.
     * @param  xPos  The x-coordinate of the circle center point
     * @param  yPos  The y-coordinate of the circle center point
     * @param  diameter  The diameter of the circle to be drawn
     */
    public void eraseCircle(double xPos, double yPos, double diameter)
    {
        double xEdge = xPos - (diameter/2);
        double yEdge = yPos - (diameter/2);
        Ellipse2D.Double circle = new Ellipse2D.Double(xEdge, yEdge, diameter, diameter);
        erase(circle);
    }
    
    /**
     * Erase the internal dimensions of the given semicircle with the current
     * foreground color of the canvas.
     * @param xPos The x coordinate of the upper-left corner of the framing rectangle of the semicircle
     * @param yPos The y coordinate of the upper-left corner of the framing rectangle of the semicircle
     * @param width The overall width of the full circle of which this semicircle is a partial section
     * @param height The overall height of the full circle of which this semicircle is a partial section
     * @param verticalMidLine if true, the line bounding the semicircle will be vertical
     * @param leftFacing if true and vertical, arc will point left.  if true and horizontal, arc will point upwards
     */
    public void eraseSemiCircle(double xPos, double yPos, double width, double height, boolean verticalMidLine, boolean leftFacing)
    {
        int startingAngle, extentAngle;
        
        if(verticalMidLine)
        {
            startingAngle = 90;
        }
        else
        {
            startingAngle = 0;
        }
        
        if(leftFacing)
        {
            extentAngle = 180;
        }
        else
        {
            extentAngle = -180;
        }
        
        fill(new Arc2D.Double(xPos, yPos, width, height, startingAngle, extentAngle, Arc2D.CHORD));
    }

    /**
     * Erase the internal dimensions of the given rectangle. This is a 
     * convenience method. A similar effect can be achieved with
     * the "erase" method.
     * @param xPos The x coordinate of the upper left corner of the rectangle
     * @param yPos The y coordinate of the upper left corner of the rectangle
     * @param width The width of the rectangle
     * @param height The height of the rectangle
     */
    public void eraseRectangle(double xPos, double yPos, double width, double height)
    {
        erase(new Rectangle2D.Double(xPos, yPos, width, height));
    }
    
    /**
     * Erase the internal dimensions of the given triangle.  
     * The order of the points provided is not relevant.
     * 
     * @param xP1 The x coordinate of the first point of the triangle
     * @param yP1 The y coordinate of the first point of the triangle
     * @param xP2 The x coordinate of the second point of the triangle
     * @param yP2 The y coordinate of the second point of the triangle
     * @param xP3 The x coordinate of the third point of the triangle
     * @param yP3 The y coordinate of the third point of the triangle
     */
    public void eraseTriangle(double xP1, double yP1, double xP2, double yP2, double xP3, double yP3)
    {
        Point2D.Double[] trianglePoints = new Point2D.Double[3];
        trianglePoints[0] = new Point2D.Double(xP1, yP1);
        trianglePoints[1] = new Point2D.Double(xP2, yP2);
        trianglePoints[2] = new Point2D.Double(xP3, yP3);
        
        erase(new PolyShape(trianglePoints));
    }
    
    /**
     * Erase the internal dimensions of the given polygon 
     * The points should be given in a cycle.
     * @param xPoints The x coordinates for all the points in the polygon
     * @param yPoints The y coordinates for all the points in the polygon
     */
    public void erasePolygon(double[] xPoints, double[] yPoints)
    {
        if(xPoints.length != yPoints.length)
        {
            System.out.println("The x and y coordinate lists must be the same length");
        }
        else
        {
            Point2D.Double[] polyPoints = new Point2D.Double[xPoints.length];
            
            for(int i = 0; i < xPoints.length; i++)
            {
                polyPoints[i] = new Point2D.Double(xPoints[i], yPoints[i]);
            }
            
            erase(new PolyShape(polyPoints));
        }
    }

    /**
     * Erase a given shape's interior on the screen.
     * @param  shape  the shape object to be erased 
     */
    public void erase(Shape shape)
    {
        Color original = graphic.getColor();
        graphic.setColor(backgroundColor);
        graphic.fill(shape);              // erase by filling background color
        graphic.setColor(original);
        canvas.repaint();
    }

    /**
     * Erases a given shape's outline on the screen.
     * @param  shape  the shape object to be erased 
     */
    public void eraseOutline(Shape shape)
    {
        Color original = graphic.getColor();
        graphic.setColor(backgroundColor);
        graphic.draw(shape);  // erase by drawing background color
        graphic.setColor(original);
        canvas.repaint();
    }

    /**
     * Draws a String on the Canvas.
     * @param  text   the String to be displayed 
     * @param  x      x co-ordinate for text placement 
     * @param  y      y co-ordinate for text placement
     */
    public void drawString(String text, double x, double y)
    {
        float fX = (float) x;
        float fY = (float) y;
        graphic.drawString(text, fX, fY); 
        canvas.repaint();
    }

    /**
     * Erases a String on the Canvas.
     * @param  text     the String to be displayed 
     * @param  x        x co-ordinate for text placement 
     * @param  y        y co-ordinate for text placement
     */
    public void eraseString(String text, double x, double y)
    {
        float fX = (float) x;
        float fY = (float) y;
        
        Color original = graphic.getColor();
        graphic.setColor(backgroundColor);
        graphic.drawString(text, fX, fY);   
        graphic.setColor(original);
        canvas.repaint();
    }

    /**
     * Draws a line on the Canvas.
     * @param  x1   x co-ordinate of start of line 
     * @param  y1   y co-ordinate of start of line 
     * @param  x2   x co-ordinate of end of line 
     * @param  y2   y co-ordinate of end of line 
     */
    public void drawLine(double x1, double y1, double x2, double y2)
    {
        graphic.draw(new Line2D.Double(x1, y1, x2, y2));   
        canvas.repaint();
    }

    /**
     * Sets the foreground color of the Canvas.
     * @param  newColor   the new color for the foreground of the Canvas 
     */
    public void setForegroundColor(Color newColor)
    {
        graphic.setColor(newColor);
    }

    /**
     * Returns the current color of the foreground.
     * @return   the color of the foreground of the Canvas 
     */
    public Color getForegroundColor()
    {
        return graphic.getColor();
    }

    /**
     * Sets the background color of the Canvas.
     * @param  newColor   the new color for the background of the Canvas 
     */
    public void setBackgroundColor(Color newColor)
    {
        backgroundColor = newColor;   
        graphic.setBackground(newColor);
    }

    /**
     * Returns the current color of the background
     * @return   the color of the background of the Canvas 
     */
    public Color getBackgroundColor()
    {
        return backgroundColor;
    }

    /**
     * changes the current Font used on the Canvas
     * @param  newFont   new font to be used for String output
     */
    public void setFont(Font newFont)
    {
        graphic.setFont(newFont);
    }
    
    /**
     * changes the size of the font used on the Canvas
     * @param newFontSize the size of the new font
     */
    public void setFontSize(float newFontSize)
    {
        setFont(getFont().deriveFont(newFontSize));
    }

    /**
     * Returns the current font of the canvas.
     * @return     the font currently in use
     **/
    public Font getFont()
    {
        return graphic.getFont();
    }

    /**
     * Sets the size of the canvas.
     * @param  width    new width 
     * @param  height   new height 
     */
    public void setSize(int width, int height)
    {
        canvas.setPreferredSize(new Dimension(width, height));
        Image oldImage = canvasImage;
        canvasImage = canvas.createImage(width, height);
        graphic = (Graphics2D)canvasImage.getGraphics();
        graphic.setColor(backgroundColor);
        graphic.fillRect(0, 0, width, height);
        graphic.drawImage(oldImage, 0, 0, null);
        frame.pack();
    }

    /**
     * Returns the size of the canvas.
     * @return     The current dimension of the canvas
     */
    public Dimension getSize()
    {
        return canvas.getSize();
    }

    /**
     * Waits for a specified number of milliseconds before finishing.
     * This provides an easy way to specify a small delay which can be
     * used when producing animations.
     * @param  milliseconds  the number 
     */
    public void wait(int milliseconds)
    {
        try
        {
            Thread.sleep(milliseconds);
        } 
        catch (InterruptedException e)
        {
            // ignoring exception at the moment
        }
    }

    /************************************************************************
     * Inner class CanvasPane - the actual canvas component contained in the
     * Canvas frame. This is essentially a JPanel with added capability to
     * refresh the image drawn on it.
     */
    private class CanvasPane extends JPanel
    {
        public void paint(Graphics g)
        {
            g.drawImage(canvasImage, 0, 0, null);
        }
    }
    
    /************************************************************************
     * Inner class PolyShape - this class is used to draw polygons with any 
     * number of points.  
     */
    private class PolyShape extends Path2D.Double
    {
        // code adapted from:
        // http://www.java2s.com/Tutorials/Java/Graphics_How_to/Shape/Draw_a_triangle.htm
        public PolyShape(Point2D.Double[] points)
        {
            moveTo(points[0].getX(), points[0].getY());
            
            int ptsSize = points.length;
            
            for(int i = 1; i < ptsSize; i++)
            {
                lineTo(points[i].getX(), points[i].getY());
            }
            
            
            closePath();
        }
    }
}
