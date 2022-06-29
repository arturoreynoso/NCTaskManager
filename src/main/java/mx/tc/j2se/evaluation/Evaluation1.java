package mx.tc.j2se.evaluation;
/**
 * <p>Class for Evaluation 1.</p>
 *
 * <p>The class has biggestCircle() method that can retrieve
 * the biggest circle received in an array. It also has a main method
 * to practice catching exception for invalid circles and get the
 * radius of the largest circle received in the parameter array of
 * biggestCircle method.</p>
 *
 * @version     1.0 29  June 2022
 * @author      Arturo Yitzack Reynoso Sánchez
 */
public class Evaluation1 {

    /**
     * Returns the index of the largest circle in an array.
     * @param circles an array of circles.
     * @return the index of the largest circle in the array.
     */
    public static int biggestCircle(Circle[] circles) {
        double biggestArea = 0;
        int indexOfLargest = 0;

        for (int i = 0; i < circles.length; i++) {
            if ( biggestArea < circles[i].getArea()) {
                biggestArea = circles[i].getArea();
                indexOfLargest = i;
            }
        }
        return indexOfLargest;
    }

    public static void main(String[] args) {
        try {
            Circle circleInvalid = new Circle(-2);
        } catch(IllegalArgumentException iae) {
            System.out.println("Cannot create a circle with non-positive radius.");
        }
        Circle[] circles = new Circle[3];
        circles[0] = new Circle(2);
        circles[1] = new Circle(1);
        circles[2] = new Circle(5);

        int indexOfLargest = biggestCircle(circles);
        String message = String.format("The radius of the largest circle is %d.",
                                       circles[indexOfLargest].getRadius());
        System.out.println(message);
    }
}
