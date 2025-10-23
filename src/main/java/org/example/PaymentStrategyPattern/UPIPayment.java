package org.example.PaymentStrategyPattern;

public class UPIPayment implements PaymentStrategy {
    public UPIPayment(double fee) {

    }
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing UPI payment of $" + amount);
    }
}
