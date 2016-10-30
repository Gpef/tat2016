package route;

/**
 * Represents one peace of whole {@code Route} route.
 * Contains x and y coordinates as constants to make
 * access to them clean and short.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 25.10.2016
 */
public class Checkpoint {

    private double x;
    private double y;

    public Checkpoint(double coordinateX, double coordinateY) {
        x = coordinateX;
        y = coordinateY;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double newX) {
        x = newX;
    }

    public void setY(double newY) {
        y = newY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Checkpoint that = (Checkpoint) o;
        return Double.compare(that.x, x) == 0 && Double.compare(that.y, y) == 0;
    }
}
