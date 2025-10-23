package org.example.ParkingSpots;

import org.example.VehicleFactoryPattern.Vehicle;

public abstract class ParkingSpot {
    private int spotNumber;
    private boolean isOccupied;
    private Vehicle vehicle;
    private String spotType;

    public ParkingSpot(int spotNumber, String spotType) {
        this.spotNumber = spotNumber;
        this.isOccupied = false;
        this.spotType = spotType;
    }

    public boolean isOccupied() {
        return isOccupied;
    }
    // Abstract method to check if a vehicle can park in this spot(eg. if car dimensions fit in the spot etc.)
    public abstract boolean canParkVehicle(Vehicle vehicle);

    public void parkVehicle(Vehicle vehicle) {
        if(isOccupied) {
            throw new IllegalStateException("Spot is already occupied");
        }
        if(!canParkVehicle(vehicle)) {
            throw new IllegalArgumentException("This spot is not suitable for" + vehicle.getVehicleType());
        }

        this.vehicle = vehicle;
        this.isOccupied = true;
    }

    public void vacate() {
        if(!isOccupied) {
            throw new IllegalStateException("Spot is not occupied");
        }
        this.vehicle = null;
        this.isOccupied = false;
    }

    public int getSpotNumber() {
        return spotNumber;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public String getSpotType() {
        return spotType;
    }
}
