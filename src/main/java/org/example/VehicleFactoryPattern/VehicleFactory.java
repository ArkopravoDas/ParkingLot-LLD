package org.example.VehicleFactoryPattern;

import org.example.FareStrategyPattern.ParkingFeeStrategy;
import org.example.VehicleFactoryPattern.VehicleStrategies.BikeVehicle;
import org.example.VehicleFactoryPattern.VehicleStrategies.CarVehicle;
import org.example.VehicleFactoryPattern.VehicleStrategies.OtherVehicle;

public class VehicleFactory {
    public static Vehicle createVehicle(String vehicleType, String licensePlate, ParkingFeeStrategy feeStrategy) {
        if(vehicleType.equalsIgnoreCase("Car")) {
            return new CarVehicle(licensePlate, vehicleType, feeStrategy);
        }
        else if(vehicleType.equalsIgnoreCase("Bike")) {
            return new BikeVehicle(licensePlate, vehicleType, feeStrategy);
        }
        return new OtherVehicle(licensePlate, vehicleType, feeStrategy);
    }
}
