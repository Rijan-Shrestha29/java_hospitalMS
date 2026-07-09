package hospital.model;

import java.util.ArrayList;
import java.util.List;

public class Treatment {
    private String treatmentId;
    private Appointment appointment;
    private String diagnosis;
    private String treatmentDescription;
    private List<Medicine> prescribedMedicines;

    public Treatment(String treatmentId, Appointment appointment) {
        this.treatmentId = treatmentId;
        this.appointment = appointment;
        this.prescribedMedicines = new ArrayList<>();
    }

    public void addMedicine(Medicine medicine) {
        prescribedMedicines.add(medicine);
    }

    public void displayTreatmentDetails() {
        System.out.println("\n--- Treatment Details ---");
        System.out.println("Treatment ID: " + treatmentId);
        System.out.println("Diagnosis: " + (diagnosis != null ? diagnosis : "Pending"));
        System.out.println("Treatment: " + (treatmentDescription != null ? treatmentDescription : "Pending"));

        if (!prescribedMedicines.isEmpty()) {
            System.out.println("\nPrescribed Medicines:");
            System.out.println("--------------------");
            double totalMedicineCost = 0;
            for (Medicine medicine : prescribedMedicines) {
                medicine.displayMedicineDetails();
                totalMedicineCost += medicine.getPrice();
            }
            System.out.println("Total Medicine Cost: $" + String.format("%.2f", totalMedicineCost));
        }
    }

    // Getters and Setters
    public String getTreatmentId() { return treatmentId; }
    public Appointment getAppointment() { return appointment; }
    public String getDiagnosis() { return diagnosis; }
    public void setDiagnosis(String diagnosis) { this.diagnosis = diagnosis; }
    public String getTreatmentDescription() { return treatmentDescription; }
    public void setTreatmentDescription(String treatmentDescription) {
        this.treatmentDescription = treatmentDescription;
    }
    public List<Medicine> getPrescribedMedicines() { return prescribedMedicines; }
}