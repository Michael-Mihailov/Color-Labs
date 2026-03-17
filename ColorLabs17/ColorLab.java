
/**
 * Write a description of class ColorLab here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.awt.*;
import java.util.*;
import java.util.List;

public class ColorLab
{
    public static void main(String[] args)
    {
        Picture beach = new Picture("images\\beach.jpg");
        beach.explore();
        
        Picture p1 = new Picture("images\\beach.jpg");
        maxBlue(p1);
        p1.explore();
        
        Picture p2 = new Picture("images\\beach.jpg");
        negate(p2);
        p2.explore();
        
        Picture p3 = new Picture("images\\beach.jpg");
        adjustBlue(p3, 0.5);
        p3.explore();
        
        Picture p4 = new Picture("images\\beach.jpg");
        greyscale(p4);
        p4.explore();
        
        Picture p5 = new Picture("images\\beach.jpg");
        lighten(p5, 50);
        p5.explore();
        
        Picture p6 = new Picture("images\\beach.jpg");
        changeColor(p6, 50, -30, 20);
        p6.explore();
        
        Picture p7 = new Picture("images\\beach.jpg");
        swap2(p7);
        p7.explore();
        
        Picture p8 = new Picture("images\\beach.jpg");
        swap3(p8);
        p8.explore();
    }
    
    
    public static void maxBlue(Picture img)
    {
        Pixel[] pixels = img.getPixels();
        for (Pixel spot : pixels)
        {
            spot.setBlue(255);
        }
    }
    
    public static void negate(Picture img)
    {
        Pixel[] pixels = img.getPixels();
        for (Pixel spot : pixels)
        {
            spot.setRed(255 - spot.getRed());
            spot.setGreen(255 - spot.getGreen());
            spot.setBlue(255 - spot.getBlue());
        }
    }
    
    public static void adjustBlue(Picture img, double factor)
    {
        Pixel[] pixels = img.getPixels();
        for (Pixel spot : pixels)
        {
            spot.setBlue((int)(spot.getBlue() * factor));
        }
    }
    
    public static void greyscale(Picture img)
    {
        Pixel[] pixels = img.getPixels();
        for (Pixel spot : pixels)
        {
            int avg = (spot.getRed() + spot.getGreen() + spot.getBlue()) / 3;
            spot.setRed(avg);
            spot.setGreen(avg);
            spot.setBlue(avg);
        }
    }
    
    public static void lighten(Picture img, int factor)
    {
        Pixel[] pixels = img.getPixels();
        for (Pixel spot : pixels)
        {
            spot.setRed(spot.getRed() + factor);
            spot.setGreen(spot.getGreen() + factor);
            spot.setBlue(spot.getBlue() + factor);
        }
    }
    
    public static void changeColor(Picture img, int red, int green, int blue)
    {
        Pixel[] pixels = img.getPixels();
        for (Pixel spot : pixels)
        {
            if(spot.getBlue() > 200 && spot.getRed() > 100 && spot.getGreen() > 100)
            {
                spot.setRed(red + spot.getRed());
                spot.setGreen(green + spot.getGreen());
                spot.setBlue(blue + spot.getBlue());
            }
        }
    }
    
    public static void swap2(Picture img)
    {
        Pixel[] pixels = img.getPixels();
        for (Pixel spot : pixels)
        {
            int green = spot.getGreen(), red = spot.getRed();
            spot.setRed(green);
            spot.setGreen(red);
        }
    }
    
    public static void swap3(Picture img)
    {
        Pixel[] pixels = img.getPixels();
        for (Pixel spot : pixels)
        {
            int green = spot.getGreen(), red = spot.getRed(), blue = spot.getBlue();
            spot.setRed(green);
            spot.setGreen(blue);
            spot.setBlue(red);
        }
    }
}
