import java.util.*;

public class CirclePacker
{
    static final double ADJUST_SPEED = 10;
    final double MIN_RADIUS;
    
    
    Circle shell; // the circle containing the inner circles
    
    ArrayList<Circle> circles = new ArrayList(); // all of the created circles
    ArrayList<Circle[]> groups = new ArrayList(); // all of the groups of 3 circles that can have an circle packed in them
    
    public CirclePacker(double minRadius) // givin minimum circle radius
    {
        MIN_RADIUS = minRadius;
        // creat the outer circle
        shell = new Circle(1500 * 2, 1500 * 2, 1500 * 2, true);
        circles.add(shell);
        // add the 3 seed circles
        Circle c1 = new Circle(837.968 * 2, 1272.409 * 2, 800 * 2); circles.add(c1);
        Circle c2 = new Circle(1802.048 * 2, 2321.480 * 2, 624.75 * 2); circles.add(c2);
        Circle c3 = new Circle(2277.307 * 2, 1144.028 * 2,  645.06 * 2); circles.add(c3);
        // creat the 4 seed groups from the seed circles
        Circle[] s1 = {shell, c1, c2};
        Circle[] s2 = {shell, c2, c3};
        Circle[] s3 = {shell, c3, c1};
        Circle[] s4 = {c1, c2, c3};
        groups.add(s1); groups.add(s2); groups.add(s3); groups.add(s4);
        
        for (int i = 0; i < groups.size(); i++) // while there are still more potential circles...
        {
            Circle cNew = Circle.pack(groups.get(i)[0], groups.get(i)[1], groups.get(i)[2], ADJUST_SPEED, Double.MAX_VALUE, groups.get(i)[0]);
            if (cNew.getRadius() > MIN_RADIUS && cNew.getRadius() < groups.get(i)[0].getRadius() && cNew.getRadius() < groups.get(i)[1].getRadius() && cNew.getRadius() < groups.get(i)[2].getRadius())
            {
                circles.add(cNew); // add circle to list
                
                Circle[] g1 = {groups.get(i)[0], groups.get(i)[1], cNew}; // creat 3 groups for another 3 circles to be packed
                Circle[] g2 = {groups.get(i)[1], groups.get(i)[2], cNew};
                Circle[] g3 = {groups.get(i)[2], groups.get(i)[0], cNew};
                
                groups.add(g1); groups.add(g2); groups.add(g3);
            } // else if radius is too small (or the circle is somehow invalid), do not add the circle
            
            //System.out.println(i);
        }
    }
    
    /**
     * returns the list of all of the circles
     */
    public ArrayList<Circle> getCircles()
    {
        return circles;
    }
}
