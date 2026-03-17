
/**
 * Michael Mihailov
 * 11/11/2022
 * Shepar Fairey Lab
 * Edit images into a small amount of colors
 */
import java.awt.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

public class SheparFaireyLab
{
    /**
     * main method, to test the picture
     *  
     */
    public static void main(String[] args)
    {
        
         //opens selfie picture 
          /**/
         //String fileName = FileChooser.pickAFile();
         //Picture pictObj = new Picture(fileName);
         //pictObj.explore();
         
         //relative path
         Picture apic = new Picture("images\\home.jpg");
         apic.explore();
         //change with selfie picture
         Picture me1 = new Picture("images/home.jpg");
         Picture me2 = new Picture("images/home.jpg");
         Picture me3 = new Picture("images/home.jpg");
         
         /**
          * method 1 change
          * 
          */
         
         method1(me1);
         me1.explore(); // how we explore the images
         
         
         
         /**
          * method 2 change
          * 
          */
         
         method2(me2);
         me2.explore();
         
         
         /**
          * custom color palette
          */

         method3(me3);
         me3.explore();
         // me3.write("images/SFtry5.jpg"); // how i saved images
    }//main   
    
    public static void method1(Picture img)
    {
        Pixel[] pixels = img.getPixels();
        for (Pixel spot : pixels) // split the 256 possible averages into even groups of 64-ish
        {
            int avg = (spot.getRed() + spot.getGreen() + spot.getBlue()) / 3;
            if (avg < 64)
            {
                spot.setRed(224);
                spot.setGreen(24);
                spot.setBlue(37);
            }
            else if (avg < 128)
            {
                spot.setRed(116);
                spot.setGreen(152);
                spot.setBlue(166);
            }
            else if (avg < 198)
            {
                spot.setRed(254);
                spot.setGreen(228);
                spot.setBlue(169);
            }
            else
            {
                spot.setRed(0);
                spot.setGreen(49);
                spot.setBlue(79);
            }
        }
    }
    public static void method2(Picture img)
    {
        int min = 255, max = 0;
        Pixel[] pixels1 = img.getPixels();
        for (Pixel spot : pixels1) // find the min and max pixel values
        {
            int avg = (spot.getRed() + spot.getGreen() + spot.getBlue()) / 3;
            if(avg < min) min = avg;
            if(avg > max) max = avg;
        }
        
        
        // go through the pixels and split them into 4 even sections
        
        Pixel[] pixels2 = img.getPixels();
        for (Pixel spot : pixels2)
        {
            int avg = (spot.getRed() + spot.getGreen() + spot.getBlue()) / 3;
            if (avg < ((max - min) / 4) + min) // EXPLANATION: you have to add min because if you didn't then there is a chance that no pixels will go in this section
            {                                  // e.g. if max = 200 and min = 100, then (max-min)/4 = 25 and pixels from 0-25 will go into this group, however there are no pixels valued below 100, so no pixels would print of this color
                spot.setRed(224);
                spot.setGreen(24);
                spot.setBlue(37);
            }
            else if (avg < ((max - min) / 2) + min)
            {
                spot.setRed(116);
                spot.setGreen(152);
                spot.setBlue(166);
            }
            else if ( avg < (((max - min) / 4) * 3) + min)
            {
                spot.setRed(254);
                spot.setGreen(228);
                spot.setBlue(169);
            }
            else
            {
                spot.setRed(0);
                spot.setGreen(49);
                spot.setBlue(79);
            }
        }
    }
    public static void method3(Picture img) // this method is like method 2 but with more groups and different colors
    {
        int min = 255, max = 0;
        Pixel[] pixels1 = img.getPixels();
        for (Pixel spot : pixels1)
        {
            int avg = (spot.getRed() + spot.getGreen() + spot.getBlue()) / 3;
            if(avg < min) min = avg;
            if(avg > max) max = avg;
        }
        Pixel[] pixels2 = img.getPixels();
        for (Pixel spot : pixels2)
        {
            int avg = (spot.getRed() + spot.getGreen() + spot.getBlue()) / 3;
            if (avg < ((max - min) / 6) + min)
            {
                spot.setColor(new Color(152, 251, 152));
            }
            else if (avg < (((max - min) / 6)* 2) + min)
            {
                spot.setColor(new Color(144, 238, 144));
            }
            else if (avg < (((max - min) / 6)* 3) + min)
            {
                spot.setColor(new Color(154, 205, 50));
            }
            else if (avg < (((max - min) / 6)* 4) + min)
            {
                spot.setColor(new Color(102, 205, 170));
            }
            else if (avg < (((max - min) / 6)* 5) + min)
            {
                spot.setColor(new Color(123, 104, 238));
            }
            else
            {
                spot.setColor(new Color(0, 0, 128));
            }
        }
    }
}//class
