package org.example;

import org.example.CommonEnum.DurationType;
import org.example.FareStrategyPattern.BasicHourlyRateStrategy;
import org.example.FareStrategyPattern.ParkingFeeStrategy;
import org.example.FareStrategyPattern.PremiumRateStrategy;
import org.example.ParkingLotController.ParkingLot;
import org.example.ParkingLotController.ParkingLotBuilder;
import org.example.ParkingSpots.BikeParkingSpot;
import org.example.ParkingSpots.CarParkingSpot;
import org.example.ParkingSpots.ParkingSpot;
import org.example.PaymentStrategyPattern.CashPayment;
import org.example.PaymentStrategyPattern.CreditCardPayment;
import org.example.PaymentStrategyPattern.PaymentStrategy;
import org.example.PaymentStrategyPattern.UPIPayment;
import org.example.VehicleFactoryPattern.Vehicle;
import org.example.VehicleFactoryPattern.VehicleFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // Initialize parking spots
        List<ParkingSpot> parkingSpots = new ArrayList<>();
        parkingSpots.add(new CarParkingSpot(1, "Car"));
        parkingSpots.add(new CarParkingSpot(2,"Car"));
        parkingSpots.add(new BikeParkingSpot(3, "Bike"));
        parkingSpots.add(new BikeParkingSpot(4, "Bike"));
        // Initialize parking lot
        ParkingLot parkingLot = new ParkingLotBuilder().createFloor(1, 2, 2)
                .createFloor(2, 3, 1, 1).build();
        // Create fee strategies
        ParkingFeeStrategy basicHourlyRateStrategy = new BasicHourlyRateStrategy();
        ParkingFeeStrategy premiumRateStrategy = new PremiumRateStrategy();
        // Create vehicles using Factory Pattern with fee strategies
        Vehicle car1 = VehicleFactory.createVehicle("Car", "CAR123", basicHourlyRateStrategy);
        Vehicle car2 = VehicleFactory.createVehicle("Car", "CAR345", basicHourlyRateStrategy);

        Vehicle bike1 = VehicleFactory.createVehicle("Bike", "BIKE456", premiumRateStrategy);
        Vehicle bike2 = VehicleFactory.createVehicle("Bike", "BIKE123", premiumRateStrategy);


        // Park vehicles
        ParkingSpot carSpot = parkingLot.parkVehicle(car1);
        ParkingSpot bikeSpot = parkingLot.parkVehicle(bike1);

        ParkingSpot carSpot2 = parkingLot.parkVehicle(car2);
        ParkingSpot bikeSpot2 = parkingLot.parkVehicle(bike2);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Select payment method for your vehicle:");
        System.out.println("1. Credit Card");
        System.out.println("2. Cash");
        System.out.println("3. UPI");
        int paymentMethod = scanner.nextInt();
        // Process payments using Strategy Patterns
        if (carSpot != null) {
            // Calculate fee using the specific strategy for the vehicle
            double carFee = car1.calculateFee(2, DurationType.HOURS);
            PaymentStrategy carPaymentStrategy =
                    getPaymentStrategy(paymentMethod, carFee);
            carPaymentStrategy.processPayment(carFee);
            parkingLot.vacateSpot(carSpot, car1);
        }
        if (bikeSpot != null) {
            // Calculate fee using the specific strategy for the vehicle
            double bikeFee = bike1.calculateFee(3, DurationType.HOURS);
            PaymentStrategy bikePaymentStrategy =
                    getPaymentStrategy(paymentMethod, bikeFee);
            bikePaymentStrategy.processPayment(bikeFee);
            parkingLot.vacateSpot(bikeSpot, bike1);
        }
        scanner.close();
    }

    private static PaymentStrategy getPaymentStrategy(
            int paymentMethod, double fee) {
        switch (paymentMethod) {
            case 1:
                return new CreditCardPayment(fee);
            case 2:
                return new CashPayment(fee);
            case 3:
                return new UPIPayment(fee);
            default:
                System.out.println("Invalid choice! Default to Credit card payment.");
                return new CreditCardPayment(fee);
        }
    }
}