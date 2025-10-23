package org.example.ParkingLotController;

import org.example.FareStrategyPattern.BasicHourlyRateStrategy;
import org.example.ParkingFloor.ParkingFloor;
import org.example.ParkingSpots.BikeParkingSpot;
import org.example.ParkingSpots.CarParkingSpot;
import org.example.VehicleFactoryPattern.VehicleStrategies.OtherVehicle;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotBuilder {
    private List<ParkingFloor> floors;
    public ParkingLotBuilder() {
        this.floors = new ArrayList<>();
    }
    public ParkingLotBuilder addFloor(ParkingFloor floor) {
        floors.add(floor);
        return this;
    }

    public ParkingLotBuilder createFloor(int floorNumber, int numOfCarSpots, int numOfBikeSpots, int... otherSpotCounts) {
        ParkingFloor floor = new ParkingFloor(floorNumber);
        for(int i=0; i<numOfCarSpots; i++) {
            floor.addParkingSpot(new CarParkingSpot(i+1, "Car"));
        }
        for(int i=0; i<numOfBikeSpots; i++) {
            floor.addParkingSpot(new BikeParkingSpot(numOfCarSpots + i + 1, "Bike"));
        }

//        // Add other types of spots if provided
//        int spotOffset = numOfCarSpots + numOfBikeSpots;
//        for (int i = 0; i < otherSpotCounts.length; i++) {
//            for (int j = 0; j < otherSpotCounts[i]; j++) {
//                // Dynamically add other vehicle type spots
//                // Note: This uses OtherVehicle as a placeholder. In a real system,
//                // you might want a more robust way to handle different vehicle types
//                floor.addParkingSpot(new OtherVehicle(
//                        spotOffset + j + 1, new BasicHourlyRateStrategy()));
//            }
//            // Update the spot offset for the next type of vehicle
//            spotOffset += otherSpotCounts[i];
//        }
        floors.add(floor);
        return this;
    }
    public ParkingLot build() {
        return new ParkingLot(floors);
    }
}
