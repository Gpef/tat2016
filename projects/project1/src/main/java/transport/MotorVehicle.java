package transport;

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

}
