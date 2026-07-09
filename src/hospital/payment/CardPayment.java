package hospital.payment;

import java.util.Scanner;

public class CardPayment implements Payment {
    @Override
    public boolean processPayment(double amount) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nProcessing Card Payment...");
        System.out.println("Amount to pay: $" + String.format("%.2f", amount));

        System.out.print("Enter card number: ");
        String cardNumber = scanner.nextLine();
        System.out.print("Enter card holder name: ");
        String holderName = scanner.nextLine();
        System.out.print("Enter CVV: ");
        String cvv = scanner.nextLine();

        // Simulate card payment processing
        System.out.println("Processing...");
        System.out.println("Card payment processed successfully!");
        return true;
    }

    @Override
    public String getPaymentMethod() {
        return "Card";
    }

    @Override
    public void displayPaymentInfo() {
        System.out.println("Payment Method: Card");
        System.out.println("We accept Visa, MasterCard, and American Express.");
    }
}