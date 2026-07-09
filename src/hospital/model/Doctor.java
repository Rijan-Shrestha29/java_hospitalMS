package hospital.model;

public class Doctor extends Person {
    private String specialization;
    private double consultationFee;

    public Doctor(String id, String name, String specialization,
                  String contactNumber, String email) {
        super(id, name, contactNumber, email);
        this.specialization = specialization;
        this.consultationFee = 100.0; // Default fee
    }

    @Override
    public void displayDetails() {
        System.out.println("Doctor ID: " + id);
        System.out.println("Name: Dr. " + name);
        System.out.println("Specialization: " + specialization);
        System.out.println("Contact: " + contactNumber);
        System.out.println("Email: " + email);
        System.out.println("Consultation Fee: $" + consultationFee);
    }

    // Getters and Setters
    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    public double getConsultationFee() { return consultationFee; }
    public void setConsultationFee(double consultationFee) { this.consultationFee = consultationFee; }
}