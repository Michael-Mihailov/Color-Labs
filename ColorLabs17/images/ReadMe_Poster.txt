Michael Mihailov, P7

The original image is titled "Pig"
The poster image is titled "Canvas_Final"

List of image manipulations:
- Rotation of image about it's center by any given number of degrees
- Changing the color of the pig from it's shade of gree to a random color from a preset array of colors
- Creating a grey-scaled version of the image
- mirroring the image about the y-axis
- scaleing the image useing any multiple
- edge detection
- recursively replaceing the pigs snout with a smaller pig (RECURSION)

All of the methods above were in the "Poster" class. Run the main method of the "Poster" class to generate the canvas.

Furthermore, there is a "Circle" and "CirclePacker" class which uses circle packing to pack a bunch of smaller circles into one large outer circle. (There is also a recursive method in the "Circle" class.)

The "CirclePacker" class is used to generate a list of "Circle" objects which is given to the "Poster" class in order to insert a manipulated image of a pig into each circle. One of the circles is given an unchanged image of the pig (though it may be cropped a little) and the rest of the circles have a randomly manipulated verion of the pig (e.g. random color, random rotation, etc.).

All of the 'Pig-Circles' are placed onto a canvas to creat the final image.


NOTE: Since the code is set the generate hundreds of circles in its current form, I would advice against running the code in its current form as it may take upwards of an hour to generate an image. The first line in "main" of the "Poster" class creats a new "CirclePacker" object. The parameter given is the minimum circle radius. Increase this to a few hundred if you want to test the code yourself. 