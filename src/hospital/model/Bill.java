package hospital.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Bill {
    private String billId;
    private Treatment treatment;
    private double consultationFee;
    private double medicineCharges;
    private double roomCharges;
    private double laboratoryCharges;
    private double totalAmount;
    private LocalDateTime billDate;
    private boolean isPaid;
    private String paymentMethod;

    public Bill(String billId, Treatment treatment, double consultationFee,
                double roomCharges, double laboratoryCharges) {
        this.billId = billId;
        this.treatment = treatment;
        this.consultationFee = consultationFee;
        this.roomCharges = roomCharges;
        this.laboratoryCharges = laboratoryCharges;
        this.billDate = LocalDateTime.now();
        this.isPaid = false;
        calculateTotalBill();
    }

    private void calculateTotalBill() {
        this.medicineCharges = treatment.getPrescribedMedicines().stream()
                .mapToDouble(Medicine::getPrice)
                .sum();
        this.totalAmount = consultationFee + medicineCharges + roomCharges + laboratoryCharges;
    }

    public void displayBillDetails() {
        System.out.println("\n╔════════════════════════════════════╗");
        System.out.println("║            BILL INVOICE            ║");
        System.out.println("╚════════════════════════════════════╝");
        System.out.println("Bill ID: " + billId);
        System.out.println("Date: " + billDate);
        System.out.println("\nPatient: " + treatment.getAppointment().getPatient().getName());
        System.out.println("Doctor: Dr. " + treatment.getAppointment().getDoctor().getName());
        System.out.println("\n--- Charges Breakdown ---");
        System.out.println("Consultation Fee: $" + String.format("%.2f", consultationFee));
        System.out.println("Medicine Charges: $" + String.format("%.2f", medicineCharges));
        System.out.println("Room Charges: $" + String.format("%.2f", roomCharges));
        System.out.println("Laboratory Charges: $" + String.format("%.2f", laboratoryCharges));
        System.out.println("─────────────────────────────");
        System.out.println("TOTAL AMOUNT: $" + String.format("%.2f", totalAmount));
        System.out.println("Status: " + (isPaid ? "PAID" : "UNPAID"));
        if (isPaid) {
            System.out.println("Payment Method: " + paymentMethod);
        }
        System.out.println("╚════════════════════════════════════╝");
    }

    // Getters and Setters
    public String getBillId() { return billId; }
    public Treatment getTreatment() { return treatment; }
    public double getTotalAmount() { return totalAmount; }
    public boolean isPaid() { return isPaid; }
    public void setPaid(boolean paid) { isPaid = paid; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
}