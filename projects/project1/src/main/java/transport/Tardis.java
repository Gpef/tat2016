package transport;

import exceptions.WrongParameterException;
import route.Route;
import route.RouteUtils;
import transport.base.CanPassRoute;
import transport.fuel.Fuel;

/**
 * Represents police box for teleporting in space and time.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 26.10.2016
 */
public class Tardis implements CanPassRoute {

    private Fuel fuel;
    private double fuelConsumption;
    private int passengersCount;
    private int passengersCapacity;

    /**
     * Creates new tarids object. Performs parameters validating.
     *
     * @param fuelConsumption    fuel consumption (it uses Mercury and this can't
     *                           be changed)
     * @param passengersCount    passengers count. Can't be > max
     *                           passengers capacity
     * @param passengersCapacity max passengers capacity
     * @throws WrongParameterException if at least one parameter has
     *                                 not valid value and {@code Tardos} object
     *                                 can't be created
     */
    public Tardis(double fuelConsumption, int passengersCount, int passengersCapacity) throws WrongParameterException {
        validatePassengers(passengersCount, passengersCapacity);
        validateFuelConsumption(fuelConsumption);

        this.fuel = Fuel.MERCURY;
        this.fuelConsumption = fuelConsumption;
        this.passengersCapacity = passengersCapacity;
        this.passengersCount = passengersCount;
    }

    /**
     * @param route route to calculate spend time for
     *              selected passing type
     * @return 2 seconds, because tardis needs 2 seconds to
     * dissolve
     */
    @Override
    public double calculateTime(Route route) {
        return 5.5e-4;
    }

    @Override
    public double calculateCost(Route route) {
        return fuelConsumption / 100 * new RouteUtils().calculateEuclidRouteLength(route) * fuel.getPrice() / passengersCount;
    }

    public Fuel getFuel() {
        return fuel;
    }

    public double getFuelConsumption() {
        return fuelConsumption;
    }

    public int getPassengersCount() {
        return passengersCount;
    }

    public int getPassengersCapacity() {
        return passengersCapacity;
    }

    /**
     * Set new fuel consumption. Before setting validates
     * new value. If it's not valid - throws
     * {@code WrongParameterException}.
     *
     * @param fuelConsumption new fuel consumption
     * @throws WrongParameterException if fuel consumption < 0
     */
    public void setFuelConsumption(double fuelConsumption) throws WrongParameterException {
        validateFuelConsumption(fuelConsumption);
        this.fuelConsumption = fuelConsumption;
    }

    /**
     * Set new passengers count. Before setting validates
     * new value. If it's not valid - throws
     * {@code WrongParameterException}.
     *
     * @param passengersCount new passengers count
     * @throws WrongParameterException if passengers count < 1,
     *                                 or passengers count > max capacity
     */
    public void setPassengersCount(int passengersCount) throws WrongParameterException {
        validatePassengers(passengersCount, this.passengersCapacity);
        this.passengersCount = passengersCount;
    }

    /**
     * Set new passengers max capacity. Before setting validates
     * new value. If it's not valid - throws
     * {@code WrongParameterException}.
     *
     * @param passengersCapacity new passengers capacity
     * @throws WrongParameterException if passengers capacity < 1
     */
    public void setPassengersCapacity(int passengersCapacity) throws WrongParameterException {
        validatePassengers(this.passengersCount, passengersCapacity);
        this.passengersCapacity = passengersCapacity;
    }

    /**
     * Validates passengers count and capacity parameters.
     * If one of them is wrong it will throw
     * {@code WrongParameterException}.
     *
     * @param validatePassengersCount    passengers count, can't be < 0 and > capacity
     * @param validatePassengersCapacity passengers capacity, can't be < 0
     * @throws WrongParameterException if passengers count or capacity
     *                                 has not valid value
     */
    private void validatePassengers(int validatePassengersCount, int validatePassengersCapacity) throws WrongParameterException {
        if (validatePassengersCapacity < 1) {
            throw new WrongParameterException("Passengers max capacity can't be < 1");
        }
        if (validatePassengersCount < 1) {
            throw new WrongParameterException("Passengers count can't be < 1");
        }
        if (validatePassengersCount > validatePassengersCapacity) {
            throw new WrongParameterException("Can't carry more passengers than capacity allows");
        }
    }

    /**
     * Validates fuel consumption parameter. Throws {@code WrongParameterException}
     * with message if it has not valid value.
     *
     * @param validateFuelConsumption fuel consumption ti validate
     * @throws WrongParameterException if fuel consumption parameter <= 0
     */
    private void validateFuelConsumption(double validateFuelConsumption) throws WrongParameterException {
        if (Double.compare(validateFuelConsumption, 0) <= 0) {
            throw new WrongParameterException("Fuel consumption can't be <= 0");
        }
    }
}
