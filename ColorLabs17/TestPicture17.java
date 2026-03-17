
/**
 * Kevin Hayes
 * Test Picture Classes
 *
 * @author (Kevin Hayes)
 * @version (10-19-2016)
 */
import java.awt.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List
public class TestPicture17
{

    /**
     * main method, to test the picture
     *
     */
  public static void main(String[] args)
  {
      //opens picture using a dialog box
      /**/
     // String fileName = FileChooser.pickAFile();
     // Picture pictObj = new Picture(fileName);
     // pictObj.explore();

     //opens a pictue using a path
     //Picture apic = new Picture("C:\\Users\\khayes\\Favorites\\Documents\APCS- Java\chap03\Curriclum 2013\Picture Color labs\images\\beach.jpg");
     
     //relative path
     //Class    id     memory     Constructor
     Picture    apic = new        Picture("images\\beach.jpg");
     Picture ferris1 = new Picture("images/2000 ferris wheel2.jpg");
     Picture cute = new Picture("images/ kitten2.jpg");
     Picture ferris3 = new Picture("images/2000 ferris wheel2.jpg");

     //apic.explore();
     ferris1.explore();
     
     //makes an array of pixels
     Pixel[] pixels;
     //gets pixels from picture and assigns to pixels array
     pixels = ferris1.getPixels();
     
     int count = 0;
     for(Pixel spot11 : pixels)
     {
         spot11.setBlue((int)(spot11.getBlue() * 0.3));
         if (count % 7 == 0)
         {
             spot11.setColor(new Color(200, 60, 180));
         }
         count++;
     }
     
     //how many pixels or how large array
    System.out.println("This is a large array"+pixels.length  );
    Pixel spot1 = ferris1.getPixel(100,100);
    System.out.println(spot1);
    spot1.setColor(Color.yellow);
    /**/
        //access each index
    System.out.println(pixels[17]);
    //access each pixel
    Pixel spot = ferris1.getPixel(100,100);
    
    System.out.println(pixels[17].getColor());
    System.out.println(spot);
    
    ferris1.explore();
    
    Pixel[] pixelsC;
    pixelsC = cute.getPixels();
    int r, g, b;
    for (Pixel spotC : pixelsC)
    {
        r = spotC.getRed();
        g = spotC.getGreen();
        b = spotC.getBlue();
        
        if (r <= 35 && g <= 35 && b <= 35)
        {
            spotC.setRed(240);
            spotC.setBlue(255);
            spotC.setGreen(240);
        }
    }
    
    cute.explore();
/*
    pixels[17].setColor(Color.blue);
    spot.setColor(new Color(252,252,252));
    pixels[500034].setColor(Color.blue);

    ferris1.explore();
/*
   // loop to access indexes of array or collection

    //for each loop spot  is a ?
    for (Pixel spot : pixels)
    System.out.println( spot );

    
   
 /**/

 /**
  * Method to clear red from picture
  * @param none
  * @return none
  */
 /*
    for (Pixel pixelObj : pixels)
        {
            //set the red value of the current pixel to the new value
           

        }
    ferris1.explore();
    
/**/
 /**
  * Method to reduce red from picture by a factor of n
  * @param none
  * @return none
  */

/*
int value;
final double  FACTOR = .5;
    for (Pixel pixelObj : pixels)
    {

        //get the redvalue
        value = pixelObj.getRed();
        //System.out.println(value);

        //decrease the red value by 50%
        
        //set the red value of the current pixel to the new value
        

    }
    // use new picture when changing or it will make changes to 
    // pic you already changed
    ferris1.explore();
    ferris2.explore();

  /**/ 
    //write/save a picture as a file
    ferris1.write("images/ferris11.jpg");

    /**/
  }//main
}//class
