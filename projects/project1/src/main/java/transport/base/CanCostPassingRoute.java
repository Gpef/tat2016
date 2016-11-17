package transport.base;

import exceptions.RoutePassingException;
import route.Route;

/**
 * All moving means moving of that can cost something,
 * should implements this interface.
 * Provide methods to find route passing price (in USD).
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 01.11.2016
 */
public interface CanCostPassingRoute {
    /**
     * Calculates route's price for specified moving mean
     * (vehicle, human etc.), that implements this interface.
     *
     * @param route route to calculate it's cost for selected
     *              passing type
     * @return route's cost for selected passing type in <b>USD</b>
     * @throws RoutePassingException if error occurred while processing
     *                               cost calculation, like too big values
     *                               that causes return of infinite values
     */
    double calculateCost(Route route) throws RoutePassingException;
}
