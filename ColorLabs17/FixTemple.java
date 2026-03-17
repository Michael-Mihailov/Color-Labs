import java.awt.*;
import java.util.*;
import java.util.List; 
/**
 * Write a description of class FixTemple here.
 * 1 Copyed orginal]
 * 2 Mirror
 * 
 * @author (Mr. Hayes) 
 * @version (Poster Project May 23)
 */
public class FixTemple
{
  public static void main(String[] args)
  {
      
     Picture apic = new Picture("images\\temple.jpg");
     Picture canvas = new Picture("images\\poster5.jpg");
      //makes an array of pixels--GIVEN YOU NEED THIS
     Pixel[] pixels;
     //gets pixels from picture and assigns to pixels array
     pixels = apic.getPixels();//GET ALL THE PIXELS
     
    
    apic.explore();//method - does something
    
    copy(apic, canvas);
    mirror(apic,canvas, 0, 100, 0, 550);
    canvas.explore();
   }

   public static void copy( Picture sourcePic, Picture targetPic)
   {
       Pixel sourcePix = null;
       Pixel targetPix = null;
       
       
       for (int x = 0; x < sourcePic.getWidth(); x++)
        {
            for (int y = 0; y < sourcePic.getHeight(); y++)
            {
                sourcePix = sourcePic.getPixel(x,y);
                targetPix = targetPic.getPixel(x, y);
                targetPix.setColor(sourcePix.getColor());
            }
        }
       
       
    }//end of copyKatie
    
    public static void mirror(Picture sourcePic, Picture targetPic, int y1, int y2, int x1, int x2)
    {
        Pixel sourcePix = null;
        Pixel targetPix = null;
        
        for (int x = x1; x < x2 / 2; x++)
        {
            for (int y = y1; y < y2; y++)
            {
                sourcePix = sourcePic.getPixel(x,y);
                targetPix = targetPic.getPixel(x2 - x, y);
                targetPix.setColor(sourcePix.getColor());
            }
        }
    }
   
   
   
   
}
