package route;

import java.util.ArrayList;

/**
 * Provides useful methods to work with routes and
 * thir lengths.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 26.10.2016
 */
public abstract class RouteUtils {

    /**
     * Calculates distance between two points, using
     * vector's rule.
     *
     * @param point1 from this point
     * @param point2 to this point
     * @return distance between point1 and point2
     */
    public static double calculateDistance(Checkpoint point1, Checkpoint point2) {
        return Math.sqrt(Math.pow((point2.x - point1.x), 2) +
                Math.pow((point2.y - point1.y), 2));
    }

    /**
     * Calculates whole route length point by point.
     *
     * @param route route needed to find length
     * @return
     */
    public static double calculateRouteLength(Route route) {
        double routeLength = 0;
        ArrayList<Checkpoint> points = route.getCheckpoints();
        for (int i = 1; i < points.size() - 1; i++) {
            routeLength += calculateDistance(points.get(i - 1), points.get(i));
        }
        return routeLength;
    }
}
