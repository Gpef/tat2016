package transport;

import route.Route;

/**
 * All moving means should implements this interface.
 * Provide methods to find spend time and money.
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
     * @return route's time for selected passing type
     */
    double calculateTime(Route route);

    /**
     * Calculates route's price for specified moving type
     * (vehicle, human etc.), that implements this interface.
     *
     * @param route route to calculate it's cost for selected
     *              passing type
     * @return route's cost for selected passing type
     */
    double calculateCost(Route route);
}
