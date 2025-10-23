package org.example.VehicleFactoryPattern.VehicleStrategies;

import org.example.FareStrategyPattern.ParkingFeeStrategy;
import org.example.VehicleFactoryPattern.Vehicle;

public class BikeVehicle extends Vehicle {
    public BikeVehicle(String licensePlate, String vehicleType, ParkingFeeStrategy feeStrategy) {
        super(licensePlate, vehicleType, feeStrategy);
    }
}
