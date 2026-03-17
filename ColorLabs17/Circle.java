


public class Circle
{
    private double xPos;
    private double yPos;
    
    private double radius;
    
    
    private boolean outerCircle = false; // whether or not this is the outermost circle
    
    public static void main(String[] args) // Used for testing while creating the circle class. DISREGARD THIS METHOD
    {
        double a1 = Math.atan2(5, -3); // the angle of circle 2 relative to circle 1
        System.out.println(a1);
        if (a1 < 0) a1 = (2 * Math.PI) + a1;
        System.out.println(a1);
        if (5 < 0) a1 += Math.PI;
        System.out.println(a1);
        
        
    }
    
    /**
     * Creats a new circle at the givent position with the given radius
     */
    public Circle(double x, double y, double r)
    {
        xPos = x;
        yPos = y;
        radius = r;
    }
    public Circle(double x, double y, double r, boolean outer)
    {
        xPos = x;
        yPos = y;
        radius = r;
        outerCircle = outer;
    }
    public Circle(Circle other, double radiusAdjust)
    {
        xPos = other.getXPos();
        yPos = other.getYPos();
        
        outerCircle = other.isOuter();
        
        if (outerCircle)
            radius = other.getRadius() - radiusAdjust;
        else
            radius = other.getRadius() + radiusAdjust;
    }
    
    
    // varius getter methods
    public double getXPos()
    {
        return xPos;
    }
    
    public double getYPos()
    {
        return yPos;
    }
    
    public double getRadius()
    {
        return radius;
    }
    
    public boolean isOuter()
    {
        return outerCircle;
    }

    
    
    /**
     * find the 2 intercepts of 2 given circles
     */ 
    public static double[] intercepts(Circle c1, Circle c2)
    {
        double r1 = c1.getRadius(); // The radius, x position, and y position of each circle
        double r2 = c2.getRadius(); 
        
        double x1 = c1.getXPos();
        double x2 = c2.getXPos();
        
        double y1 = c1.getYPos();
        double y2 = c2.getYPos();
        
        
        
        
        
        double d = Math.hypot(x2 - x1, y2 - y1); // distance between circle centers useing distance formula
        //System.out.println(d);
        
        double a1 = Math.atan((y2-y1)/(x2-x1)); // the angle of circle 2 relative to circle 1
        if (x2 - x1 < 0 && y2 - y1 > 0) a1 = Math.PI + a1; // if in quadrant 2
        else if (x2 - x1 < 0 && y2 - y1 < 0) a1 = Math.PI + a1; // if in quadrant 3
        else if (x2 - x1 > 0 && y2 - y1 < 0) a1 = (2 * Math.PI) + a1; // if in quadrant 4
        a1 = a1 % (Math.PI * 2);
        if (a1 < 0) a1 = (Math.PI * 2) + a1;
        
        
        double a2 = Math.acos((Math.pow(r2, 2) - Math.pow(r1, 2) - Math.pow(d, 2)) / (-2 * r1 * d)); // the angle made by circle 1 between d and each intersect point relative to the line d
        
        //System.out.println(Math.toDegrees(a1));
        //System.out.println(Math.toDegrees(a2));
        
        double xAns1 = (Math.cos(a2 + a1) * r1) + x1; // the intercept coordinates
        double xAns2 = (Math.cos((-1 * a2) + a1) * r1) + x1;
        
        double yAns1 = (Math.sin(a2 + a1) * r1) + y1;
        double yAns2 = (Math.sin((-1 * a2) + a1) * r1) + y1; 
        
        double[] answer = {xAns1, yAns1, xAns2, yAns2}; // returns the 2 intercept coordinates
        
        return answer;
    }
    
    /**
     * Recursive method to pack a circle in between 3 circles
     */
    static final double acceptableError = 0.01;
    public static Circle pack(Circle c1, Circle c2, Circle c3, double adjust, double previous, Circle OGc1)
    {
        //System.out.println(c1);
        //System.out.println(c2);
        //System.out.println(c3);
        
        double min = Double.MAX_VALUE; // find the smallest perimeter made by the intersecting points
        double xPos = 0; double yPos = 0;
        for (int a = 0; a <= 2; a+=2)
        {
            double x1 = intercepts(c1, c2)[a];
            double y1 = intercepts(c1, c2)[a + 1];
            for (int b = 0; b <= 2; b+=2)
            {
                double x2 = intercepts(c2, c3)[b];
                double y2 = intercepts(c2, c3)[b + 1];
                for (int c = 0; c <= 2; c+=2)
                {
                    double x3 = intercepts(c3, c1)[c];
                    double y3 = intercepts(c3, c1)[c + 1];
                    
                    double p = Math.hypot(x1 - x2, y1 - y2) + Math.hypot(x2 - x3, y2 - y3) + Math.hypot(x3 - x1, y3 - y1);
                    
                    if (p < min)
                    {
                        min = p;
                        xPos = (x1 + x2 + x3) / 3; // the estimated position of the packed circle
                        yPos = (y1 + y2 + y3) / 3;
                        //System.out.println("YES");
                        //System.out.println(x1 + ", " + y1);
                        //System.out.println(x2 + ", " + y2);
                        //System.out.println(x3 + ", " + y3);
                    }
                }
            }
        }
        
        //System.out.println(min + " " + adjust + " " + (OGc1.getRadius() - c1.getRadius()));
        //System.out.println(intercepts(c1, c2));
        //System.out.println(intercepts(c2, c3));
        //System.out.println(intercepts(c3, c1));
        
        if (min < acceptableError) // if the circle is approximated within acceptable error...
            return new Circle(xPos, yPos, Math.abs(OGc1.getRadius() - c1.getRadius())); // once within acceptable error, return the circle
        else
        {
            double adj;
            if (min == Double.MAX_VALUE) // if not all circles are intercepting
            {
                adj = -1.25 * adjust;
            }
            else if (min > previous) // if getting farther away from actual position, change direction and move slower
            {
                adj = -0.5 * adjust;
            }
            else // else keep going
            {
                adj = adjust;
            }
            
            return pack(new Circle(c1, adj), new Circle(c2, adj), new Circle(c3, adj), adj, min, OGc1); // recursivly try again
        }
    }
    
    public String toString() // toString that was used in deBugging
    {
        return "radius: " + getRadius() + " | Coordinate: (" + getXPos() + ", " + getYPos() + ")";
    }
}
