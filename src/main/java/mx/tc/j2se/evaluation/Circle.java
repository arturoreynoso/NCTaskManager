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

    /* Constructor that receives a radius. */
    public Circle(int radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException("The radius must be positive.");
        }
        this.radius = radius;
    }

    public void setRadius(int radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException("The radius must be positive.");
        }
        this.radius = radius;
    }

    public int getRadius() {
        return this.radius;
    }

    public double getArea() {
        return Math.PI * this.radius * this.radius;
    }
}
