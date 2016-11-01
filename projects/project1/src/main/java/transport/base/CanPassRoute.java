package transport.base;

import exceptions.RoutePassingException;
import route.Route;

/**
 * All moving means should implements this interface.
 * Provide methods to find spend time on route passing.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 26.10.2016
 */
public interface CanPassRoute {

    /**
     * Calculates time that will be spend to pass
     * {@code Route} route with moving type (vehicle,
     * human etc.) that implements this interface.
     *
     * @param route route to calculate spend time for
     *              selected passing type
     * @return route's time for selected passing type in <b>hours</b>
     * @throws RoutePassingException if error occurred while processing
     *                               cost calculation, like too big values
     *                               that causes return of infinite values
     */
    double calculateTime(Route route) throws RoutePassingException;
}
