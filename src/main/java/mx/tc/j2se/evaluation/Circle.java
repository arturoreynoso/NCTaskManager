package mx.tc.j2se.evaluation;

/**
 * <p>Class for creating circles.</p>
 *
 * <p>The user can create a circle with a given radius,
 * can set and get its radius and get its area.</p>
 *
 * @version     1.0 29  June 2022
 * @author      Arturo Yitzack Reynoso Sánchez
 */
public class Circle {
    private int radius;

    /* Default constructor with radius set to 1. */
    public Circle() {
        this.radius = 1;
    }

    /** Constructor that receives a radius.
     * @param radius the radius of the circle.
     * @throws IllegalArgumentException if the radius is non-positive.*/
    public Circle(int radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException("The radius must be positive.");
        }
        this.radius = radius;
    }

    /** Sets the radius of the circle.
     * @param radius the radius of the circle.
     * @throws IllegalArgumentException if the radius is non-positive.*/
    public void setRadius(int radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException("The radius must be positive.");
        }
        this.radius = radius;
    }

    /* Returns the radius of the circle. */
    public int getRadius() {
        return this.radius;
    }

    /* Returns the area of the circle. */
    public double getArea() {
        return Math.PI * this.radius * this.radius;
    }
}
