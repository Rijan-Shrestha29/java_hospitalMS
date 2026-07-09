package hospital.service;

import hospital.database.HospitalDatabase;
import hospital.model.*;
import hospital.payment.*;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class BillingService {
    private HospitalDatabase database;

    public BillingService(HospitalDatabase database) {
        this.database = database;
    }

    public void generateBill(String treatmentId, double consultationFee,
                             double roomCharges, double laboratoryCharges) {
        Optional<Treatment> treatment = database.findTreatmentById(treatmentId);

        if (treatment.isEmpty()) {
            System.out.println("Treatment not found.");
            return;
        }

        String billId = database.generateBillId();
        Bill bill = new Bill(billId, treatment.get(), consultationFee,
                roomCharges, laboratoryCharges);
        database.addBill(bill);

        System.out.println("Bill generated successfully!");
        bill.displayBillDetails();
    }

    public void viewAllBills() {
        List<Bill> bills = database.getAllBills();
        if (bills.isEmpty()) {
            System.out.println("No bills found.");
            return;
        }

        System.out.println("\n--- All Bills ---");
        for (Bill bill : bills) {
            System.out.println("Bill ID: " + bill.getBillId() +
                    " | Amount: $" + String.format("%.2f", bill.getTotalAmount()) +
                    " | Status: " + (bill.isPaid() ? "Paid" : "Unpaid"));
            System.out.println("----------------------------------------");
        }
    }

    public void viewBillDetails(String billId) {
        Optional<Bill> bill = database.findBillById(billId);

        if (bill.isEmpty()) {
            System.out.println("Bill not found.");
            return;
        }

        bill.get().displayBillDetails();
    }

    public void processPayment(String billId, int paymentChoice, Scanner scanner) {
        Optional<Bill> bill = database.findBillById(billId);

        if (bill.isEmpty()) {
            System.out.println("Bill not found.");
            return;
        }

        if (bill.get().isPaid()) {
            System.out.println("This bill has already been paid.");
            return;
        }

        // Demonstrates OCP and Polymorphism
        Payment payment;
        switch (paymentChoice) {
            case 1:
                payment = new CashPayment();
                break;
            case 2:
                payment = new CardPayment();
                break;
            case 3:
                payment = new DigitalWalletPayment();
                break;
            default:
                System.out.println("Invalid payment method.");
                return;
        }

        payment.displayPaymentInfo();

        if (payment.processPayment(bill.get().getTotalAmount())) {
            bill.get().setPaid(true);
            bill.get().setPaymentMethod(payment.getPaymentMethod());
            System.out.println("Payment processed successfully!");
            System.out.println("Thank you for your payment!");
        } else {
            System.out.println("Payment failed. Please try again.");
        }
    }
}