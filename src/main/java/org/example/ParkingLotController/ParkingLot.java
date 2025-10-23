package org.example.ParkingLotController;

import org.example.ParkingFloor.ParkingFloor;
import org.example.ParkingSpots.ParkingSpot;
import org.example.VehicleFactoryPattern.Vehicle;

import java.util.List;

public class ParkingLot {
    private List<ParkingFloor> floors;

    public ParkingLot(List<ParkingFloor> floors) {
        this.floors = floors;
    }

    public ParkingSpot findAvailableSpot(String vehicleType) {
        for (ParkingFloor floor : floors) {
            ParkingSpot spot = floor.findAvailableSpot(vehicleType);
            if (spot != null) {
                return spot; // Return the first available spot found
            }
        }
        return null; // Return null if no spot is available
    }
    public ParkingSpot parkVehicle(Vehicle vehicle) {
        ParkingSpot spot = findAvailableSpot(vehicle.getVehicleType());
        if (spot != null) {
            spot.parkVehicle(vehicle); // Park the vehicle in the found spot
            System.out.println(
                    "Vehicle parked successfully in spot: " + spot.getSpotNumber());
            return spot;
        }
        // If no spot is available, notify the user
        System.out.println(
                "No parking spots available for " + vehicle.getVehicleType() + "!");
        return null;
    }

    public void vacateSpot(ParkingSpot spot, Vehicle vehicle) {
        // Ensure the spot is occupied and the vehicle matches before vacating
        if (spot != null && spot.isOccupied()
                && spot.getVehicle().equals(vehicle)) {
            spot.vacate(); // Free up the parking spot
            System.out.println(vehicle.getVehicleType()
                    + " vacated the spot: " + spot.getSpotNumber());
        } else {
            System.out.println("Invalid operation! Either the spot is already vacant "
                    + "or the vehicle does not match.");
        }
    }

    public ParkingSpot getSpotByNumber(int spotNumber) {
        for (ParkingFloor floor : floors) {
            for (ParkingSpot spot : floor.getParkingSpots()) {
                if (spot.getSpotNumber() == spotNumber) {
                    return spot; // Return the parking spot if found
                }
            }
        }
        return null; // Return null if no spot with the given number exists
    }

    public List<ParkingFloor> getFloors() {
        return floors;
    }
}
