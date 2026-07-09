package hospital.payment;

public class CashPayment implements Payment {
    @Override
    public boolean processPayment(double amount) {
        System.out.println("\nProcessing Cash Payment...");
        System.out.println("Amount to pay: $" + String.format("%.2f", amount));
        System.out.println("Cash payment received successfully!");
        return true;
    }

    @Override
    public String getPaymentMethod() {
        return "Cash";
    }

    @Override
    public void displayPaymentInfo() {
        System.out.println("Payment Method: Cash");
        System.out.println("Please pay at the counter.");
    }
}