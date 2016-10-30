package transport.base;

import exceptions.WrongParameterException;
import transport.fuel.Fuel;

/**
 * Base class for vehicles driven by motor, that
 * consumes fuel.
 *
 * @author Oleg Baslak
 * @version 1.0
 * @since 26.10.2016
 */
public abstract class MotorVehicle extends Vehicle {

    protected Fuel fuel;
    protected double fuelConsumption;   // Fuel consumption for 100 km
    protected int passengersCount;
    protected int passengersCapacity;

    public Fuel getFuel() {
        return fuel;
    }

    public double getFuelConsumption() {
        return fuelConsumption;
    }

    public double getPassengersCount() {
        return passengersCount;
    }

    public double getPassengersCapacity() {
        return passengersCapacity;
    }

    public void setFuel(Fuel fuel) {
        this.fuel = fuel;
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
     * @param validatePassengersCount    passengers count, can't be < 1 and > capacity
     * @param validatePassengersCapacity passengers capacity, can't be < 1
     * @throws WrongParameterException if passengers count or capacity
     *                                 has not valid value
     */
    protected void validatePassengers(int validatePassengersCount, int validatePassengersCapacity) throws WrongParameterException {
        if (validatePassengersCapacity < 1) {
            throw new WrongParameterException("Error creating new " + this.getClass().getSimpleName() +
                    ": passengers max capacity can't be < 1");
        }
        if (validatePassengersCount < 1) {
            throw new WrongParameterException("Error creating new " + this.getClass().getSimpleName() +
                    ": passengers count can't be < 1");
        }
        if (validatePassengersCount > validatePassengersCapacity) {
            throw new WrongParameterException("Error creating new " + this.getClass().getSimpleName() +
                    ": can't carry more passengers than capacity allows");
        }
    }

    /**
     * Validates fuel consumption parameter. Throws {@code WrongParameterException}
     * with message if it has not valid value.
     *
     * @param validateFuelConsumption fuel consumption ti validate
     * @throws WrongParameterException if fuel consumption parameter <= 0
     */
    protected void validateFuelConsumption(double validateFuelConsumption) throws WrongParameterException {
        if (Double.compare(validateFuelConsumption, 0) <= 0) {
            throw new WrongParameterException("Fuel consumption can't be <= 0");
        }
    }
}
