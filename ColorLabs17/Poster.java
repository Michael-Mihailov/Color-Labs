
/**
 * Write a description of class Poster here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.awt.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

public class Poster
{
    public static void main (String[] args)
    {
        ArrayList<Circle> circles = new CirclePacker(10).getCircles(); // get the list of circles
        
        
        
        Picture canvas = new Picture("images\\Canvas.png");
        Picture pig = new Picture("images\\pig.png");
        
        clearPicture(canvas); // reset the canvas and pig in case there is anything on them
        transparentBackground(pig);
        
        
        //rotate(pig, canvas, Math.random() * Math.PI * 2);
        //changeColorRandom(pig, canvas);
        //mirror(pig, canvas);
        //greyScale(pig, canvas);
        //scale(pig, canvas, 0.025);
        //recusiveSnout(pig, canvas, 6, 6, 0, 0);
        //edge(pig, canvas);
        
        
        
        for (int i = 0; i < circles.size(); i++) // add each circle onto the canvas
        {
            System.out.println(i + " out of " + circles.size());
            pasteCircle(pig, canvas, circles.get(i), i);
        }
        
        canvas.write(canvas.getFileName()); // save the image
        
        pig.explore(); // explore the images
        canvas.explore();
    }
    
    /**
     * edge detection method
     */    
    public static void edge(Picture sourcePic, Picture targetPic)
    {
        Pixel sourcePixel;
        Pixel targetPixel;
        
        for (int x = 1; x < sourcePic.getWidth(); x++)
        {
            for (int y = 1; y < sourcePic.getHeight(); y++)
            {
                sourcePixel = sourcePic.getPixel(x, y);
                targetPixel = targetPic.getPixel(x, y);
                if (false == (sourcePixel.getAverage() >= sourcePic.getPixel(x-1, y).getAverage() - 5 && 
                sourcePixel.getAverage() <= sourcePic.getPixel(x-1, y).getAverage() + 5)
                || false == (sourcePixel.getAverage() >= sourcePic.getPixel(x, y-1).getAverage() - 5 && 
                sourcePixel.getAverage() <= sourcePic.getPixel(x, y-1).getAverage() + 5))
                    targetPixel.setColor(Color.black);
            }
        }
    }
    
    /**
     * simple method to copy pixel values with a shift
     */
    public static void copyPic (Picture sourcePic, Picture targetPic, int xPos, int yPos)
    {
        Pixel sourcePixel;
        Pixel targetPixel;
        
        for (int x = 0; x < sourcePic.getWidth(); x++)
        {
            for (int y = 0; y < sourcePic.getHeight(); y++)
            {
                sourcePixel = sourcePic.getPixel(x, y);
                if (sourcePixel.getAverage() != 255 && sourcePixel.getAlpha() != 0 && x + xPos < targetPic.getWidth() && y + yPos < targetPic.getHeight()) 
                {
                    targetPixel = targetPic.getPixel(x + xPos, y + yPos);
                    targetPixel.setColor(sourcePixel.getColor());
                }
            }
        }
    }
    
    /**
     * Method to put a randomized image within a given circle
     */
    public static void pasteCircle(Picture sourcePic, Picture targetPic, Circle c, int index)
    {
        // scale pig useing radius
        // place circle and scaled pig on canvas
        
        Picture circlePic = new Picture("images\\Circle.png");
        Picture temp1 = new Picture("images\\Temp.png");
        Picture temp2 = new Picture("images\\Temp2.png");
        Picture temp3 = new Picture("images\\Temp3.png");
        
        clearPicture(circlePic); circlePic.write(circlePic.getFileName()); // clear temporary images
        clearPicture(temp1); temp1.write(temp1.getFileName());
        clearPicture(temp2); temp2.write(temp2.getFileName());
        clearPicture(temp3); temp3.write(temp3.getFileName());
        
        double r = c.getRadius(); // get the circle's info
        double xPos = c.getXPos();
        double yPos = c.getYPos();
        
        if (index == 0); // nothing should be in the outermost circle
        else if (index == 1) // the first inner circle should be the default image
        {
            double factor = (r * 2) / sourcePic.getWidth();
            factor *= 0.9;
            
            scale(sourcePic, temp3, factor);
            copyPic(temp3, circlePic, 80, 65); // make image more centered
        }
        else
        {
            if (Math.random() > 0.75) // chance of haveing a recursiveSnout pig
            {
                recusiveSnout(sourcePic, temp2, (int)(r / 200) + 1, (int)(r / 200) + 1, 0, 0);
            }
            else
            {
                copyPic(sourcePic, temp2, 0, 0);
            }
            
            changeColorRandom(temp2, temp1); // random color
            
            clearPicture(temp2);
            
            rotate(temp1, temp2, Math.random() * Math.PI * 2); // random rotation 
            
            clearPicture(temp1);
            
            if (Math.random() > 0.75) // 25% chance to mirror the image
            {
                mirror(temp2, temp1);
            }
            else
            {
                copyPic(temp2, temp1, 0, 0);
            }
            
            clearPicture(temp2);
            
            if (Math.random() > 0.9) // chance of grayscale
            {
                greyScale(temp1, temp2);
            }
            else if (Math.random() > 0.85) // chance of edge detection
            {
                edge(temp1, temp2);
            }
            else
            {
                copyPic(temp1, temp2, 0, 0);
            }
            
            
            scale(temp2, circlePic, (r * 2) / temp1.getWidth()); // scale to fit in circle
        }
        
        
        final int boarderWidth = (int)(Math.sqrt(r)/1.5) + 2; // the width of the circle boarder
        
        for (int x = 0; x < circlePic.getWidth(); x++) // add circle boarder
        {
            for (int y = 0; y < circlePic.getHeight(); y++)
            {                
                Pixel sourcePix = circlePic.getPixel(x,y);
                
                if (Math.pow(x - r, 2) + Math.pow(y - r, 2) >= Math.pow(r, 2)) // crop outside of circle
                {
                    sourcePix.setColor(new Color(255, 255, 255, 0));
                }
                else if (Math.pow(x - r, 2) + Math.pow(y - r, 2) >= Math.pow(r - boarderWidth, 2)) // fill circle boarder black
                {
                    sourcePix.setColor(Color.black);
                }
                else if (sourcePix.getAverage() == 255 && sourcePix.getAlpha() == 0) // fill circle a certain color
                {
                    // TODO: decide if I actualy want to fill the circle. White looks okay to me.
                }
            }
        }
        
        copyPic(circlePic, targetPic, (int)(xPos - r), (int)(yPos - r)); // place circle on canvas
    }
    
    
    /**
     * recursively replace the pig's nose with another pig
     */
    public static void recusiveSnout(Picture sourcePic, Picture targetPic, int depth, int start, int xPos, int yPos) // TODO: fix later
    {
        //System.out.println(depth);
        
        Picture temp2 = new Picture("images\\Temp2.png"); 
        if (depth > 0)
        {
            Picture temp1 = new Picture("images\\Temp.png");
            
            
            
            scale(sourcePic, temp1, Math.pow(0.55, start - depth)); // scale down
            copyPic(temp1, temp2, xPos, yPos); // add on to image
            
            temp2.write(temp2.getFileName());
            
            
            xPos += (198.0 / sourcePic.getWidth()) * (sourcePic.getWidth() * Math.pow(0.535, start - depth));
            yPos += (292.0 / sourcePic.getHeight()) * (sourcePic.getHeight() * Math.pow(0.563, start - depth));
            
            
            recusiveSnout(sourcePic, targetPic, depth - 1, start, xPos, yPos); // do it again
        }
        else // base case depth == 0
        {
            copyPic(temp2, targetPic, 0, 0);
            
            //temp2.explore();
            
            clearPicture(temp2);
        }
    }
    
    /**
     * scales the image by any factor (up or down)
     */
    public static void scale(Picture sourcePic, Picture targetPic, double magnification)
    {
        Pixel sourcePixel;
        Pixel targetPixel;
        
        
        int width = (int)(sourcePic.getWidth() * magnification);
        int height = (int)(sourcePic.getHeight() * magnification);
        
        for (int x = 0; x < width; x++)
        {
            for (int y = 0; y < height; y++)
            {
                sourcePixel = sourcePic.getPixel((int)(x / magnification), (int)(y / magnification));
                targetPixel = targetPic.getPixel(x, y);
                targetPixel.setColor(sourcePixel.getColor());
            }
        }
    }
    
    /**
     * makes the image grey by useing average pixel color values
     */
    public static void greyScale(Picture sourcePic, Picture targetPic)
    {
        Pixel sourcePix;
        Pixel targetPix;
        
        for (int x = 0; x < sourcePic.getWidth(); x++)
        {
            for (int y = 0; y < sourcePic.getHeight(); y++)
            {                
                sourcePix = sourcePic.getPixel(x,y);
                targetPix = targetPic.getPixel(x, y);
                
                Color setColor = sourcePix.getColor();
                
                int num = (setColor.getRed() + setColor.getBlue() + setColor.getGreen()) / 3;
                
                targetPix.setColor(new Color(num, num, num));
            }
        }
    }
    
    /**
     * mirrors the image across the y-axis
     */
    public static void mirror(Picture sourcePic, Picture targetPic)
    {
        Pixel sourcePix = null;
        Pixel targetPix = null;
        
        for (int x = 0; x < sourcePic.getWidth(); x++)
        {
            for (int y = 0; y < sourcePic.getHeight(); y++)
            {                
                sourcePix = sourcePic.getPixel(x,y);
                targetPix = targetPic.getPixel(sourcePic.getWidth() - 1 - x, y);
                targetPix.setColor(sourcePix.getColor());
            }
        }
    }
    
    /**
     * changes the color to a random color from an array
     */
    public static void changeColorRandom(Picture sourcePic, Picture targetPic)
    {
        // 108 226 73
        
        Pixel sourcePix = null;
        Pixel targetPix = null;
        
        Color[] colors = {Color.red, Color.yellow, Color.blue, Color.pink, Color.orange, Color.magenta};
        
        Color setColor = new Color(108, 226, 73);
        
        if (Math.random() > 0.25) // 25% chance to keep color
        {
            setColor = colors[(int) (Math.random() * colors.length)]; 
        }
        
        
        Picture temp = new Picture("images\\Temp.png");
        greyScale(sourcePic, temp);
        
        
        for (int y = 0; y < temp.getHeight(); y++)
        {
            for (int x = 0; x < temp.getWidth(); x++)
            {
                sourcePix = temp.getPixel(x,y);
                targetPix = targetPic.getPixel(x, y);
                
                double value = sourcePix.getAverage();
                
                Color daColor;
                if (value == 0 || value >= 245)
                    daColor = sourcePix.getColor();
                else
                    daColor = new Color((int)(setColor.getRed() * (value / 255)), (int)(setColor.getGreen() * (value / 255)), (int)(setColor.getBlue() * (value / 255)));
                
                targetPix.setColor(daColor);
            }
        }
    }
    
    /**
     * rotates the pig any number of degrees
     */
    public static void rotate(Picture sourcePic, Picture targetPic, double angleAdj)
    {
        Pixel sourcePixel;
        Pixel targetPixel;
        
        int xCen = sourcePic.getWidth() / 2;
        int yCen = sourcePic.getHeight() / 2;
        
        for (int y = 0; y < sourcePic.getHeight(); y++)
        {
            for (int x = 0; x < sourcePic.getWidth(); x++)
            {                
                double r = Math.sqrt(Math.pow(x - xCen, 2) + Math.pow(y - yCen, 2));
                double angle = Math.atan((double)(yCen - y) / (x - xCen));
                
                if (y > yCen)
                {
                    angle += Math.PI;
                }
                
                if ((y > yCen && x < xCen) || (y < yCen && x > xCen))
                {
                    angle += Math.PI;
                }
                
                if (y == yCen && x >= xCen)
                {
                    angle -= Math.PI;
                }
                if (x == xCen && y <= yCen)
                {
                    angle += Math.PI;
                }
                
                
                angle += angleAdj;
                
                int xSource = xCen + (int)(Math.cos(angle) * r);
                int ySource = yCen + (int)(Math.sin(angle) * r);
                
                
                Color daColor;
                
                if (xSource < 0 || xSource >= sourcePic.getWidth() || ySource < 0 || ySource >= sourcePic.getHeight())
                {
                    daColor = new Color(255, 255, 255, 0);
                }
                else
                {
                    daColor = sourcePic.getPixel(xSource, ySource).getColor();
                }
                
                targetPixel = targetPic.getPixel(x, y);
                
                targetPixel.setColor(daColor);
            }
        }
    }
    
    
    /**
     * Make the image of the pig have a transparent background
     * (only needs to be run once and then saved)
     */
    public static void transparentBackground(Picture sourcePic)
    {
        Pixel sourcePix = null;
        
        for (int x = 0; x < sourcePic.getWidth(); x++)
        {
            for (int y = 0; y < sourcePic.getHeight(); y++)
            {                
                sourcePix = sourcePic.getPixel(x,y);
                if (sourcePix.getAverage() == 255) // replace white with transparent (excludeing eyes)
                {
                    if ((x > 30 && x < 860) && (y > 430 && y < 660))
                    {
                        sourcePix.setColor(new Color(254, 254, 254, 255));
                    }
                    else
                    {
                        sourcePix.setColor(new Color(255, 255, 255, 0));
                    }
                }
            }
        }
        
        sourcePic.write(sourcePic.getFileName());
    }
    
    
    /**
     * turn picture into a transparent white image
     * used to reset temporary images
     */
    public static void clearPicture(Picture sourcePic)
    {
        for (int x = 0; x < sourcePic.getWidth(); x++)
        {
            for (int y = 0; y < sourcePic.getHeight(); y++)
            {                
                sourcePic.getPixel(x, y).setColor(new Color(255, 255, 255, 0));
            }
        }
        
        sourcePic.write(sourcePic.getFileName());
    }
}
