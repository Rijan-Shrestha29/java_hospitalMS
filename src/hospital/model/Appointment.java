package hospital.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Appointment {
    private String appointmentId;
    private Patient patient;
    private Doctor doctor;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
    private String status; // Scheduled, Completed, Cancelled

    public Appointment(String appointmentId, Patient patient, Doctor doctor,
                       LocalDate appointmentDate, LocalTime appointmentTime) {
        this.appointmentId = appointmentId;
        this.patient = patient;
        this.doctor = doctor;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.status = "Scheduled";
    }

    public void displayAppointmentDetails() {
        System.out.println("\n--- Appointment Details ---");
        System.out.println("Appointment ID: " + appointmentId);
        System.out.println("Date: " + appointmentDate);
        System.out.println("Time: " + appointmentTime);
        System.out.println("Status: " + status);
        System.out.println("\nPatient Information:");
        patient.displayDetails();
        System.out.println("\nDoctor Information:");
        doctor.displayDetails();
    }

    // Getters and Setters
    public String getAppointmentId() { return appointmentId; }
    public Patient getPatient() { return patient; }
    public Doctor getDoctor() { return doctor; }
    public LocalDate getAppointmentDate() { return appointmentDate; }
    public LocalTime getAppointmentTime() { return appointmentTime; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}