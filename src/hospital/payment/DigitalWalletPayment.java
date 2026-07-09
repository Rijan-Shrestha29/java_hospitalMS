package hospital.payment;

import java.util.Scanner;

public class DigitalWalletPayment implements Payment {
    @Override
    public boolean processPayment(double amount) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nProcessing Digital Wallet Payment...");
        System.out.println("Amount to pay: $" + String.format("%.2f", amount));

        System.out.println("Select wallet:");
        System.out.println("1. PayPal");
        System.out.println("2. Google Pay");
        System.out.println("3. Apple Pay");
        System.out.print("Enter choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter wallet ID/email: ");
        String walletId = scanner.nextLine();

        // Simulate wallet payment processing
        System.out.println("Processing...");
        System.out.println("Digital wallet payment processed successfully!");
        return true;
    }

    @Override
    public String getPaymentMethod() {
        return "Digital Wallet";
    }

    @Override
    public void displayPaymentInfo() {
        System.out.println("Payment Method: Digital Wallet");
        System.out.println("Supported wallets: PayPal, Google Pay, Apple Pay");
    }
}