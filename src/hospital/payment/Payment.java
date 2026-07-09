package hospital.payment;

public interface Payment {
    boolean processPayment(double amount);
    String getPaymentMethod();
    void displayPaymentInfo();
}