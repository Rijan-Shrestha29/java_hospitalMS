package hospital.service;

import hospital.database.HospitalDatabase;
import hospital.model.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

public class AppointmentService {
    private HospitalDatabase database;

    public AppointmentService(HospitalDatabase database) {
        this.database = database;
    }

    public void bookAppointment(String patientId, String doctorId,
                                String dateStr, String timeStr) {
        Optional<Patient> patient = database.findPatientById(patientId);
        Optional<Doctor> doctor = database.findDoctorById(doctorId);

        if (patient.isEmpty()) {
            System.out.println("Patient not found.");
            return;
        }

        if (doctor.isEmpty()) {
            System.out.println("Doctor not found.");
            return;
        }

        try {
            LocalDate date = LocalDate.parse(dateStr);
            LocalTime time = LocalTime.parse(timeStr);

            String appointmentId = database.generateAppointmentId();
            Appointment appointment = new Appointment(appointmentId, patient.get(),
                    doctor.get(), date, time);
            database.addAppointment(appointment);
            System.out.println("Appointment booked successfully!");
            System.out.println("Appointment ID: " + appointmentId);
            System.out.println("Date: " + date + " Time: " + time);
            System.out.println("Doctor: Dr. " + doctor.get().getName());
        } catch (Exception e) {
            System.out.println("Error: Invalid date or time format. Use YYYY-MM-DD and HH:MM");
        }
    }

    public void viewAllAppointments() {
        List<Appointment> appointments = database.getAllAppointments();
        if (appointments.isEmpty()) {
            System.out.println("No appointments found.");
            return;
        }

        System.out.println("\n--- All Appointments ---");
        for (Appointment appointment : appointments) {
            appointment.displayAppointmentDetails();
            System.out.println("====================================");
        }
    }

    public void cancelAppointment(String appointmentId) {
        Optional<Appointment> appointment = database.findAppointmentById(appointmentId);

        if (appointment.isEmpty()) {
            System.out.println("Appointment not found.");
            return;
        }

        appointment.get().setStatus("Cancelled");
        System.out.println("Appointment cancelled successfully.");
    }

    public void searchAppointment(String searchTerm) {
        Optional<Appointment> appointment = database.findAppointmentById(searchTerm);

        if (appointment.isPresent()) {
            appointment.get().displayAppointmentDetails();
            return;
        }

        // Search by patient name
        List<Appointment> patientAppointments = database.getAllAppointments().stream()
                .filter(a -> a.getPatient().getName().toLowerCase()
                        .contains(searchTerm.toLowerCase()))
                .toList();

        if (!patientAppointments.isEmpty()) {
            System.out.println("\n--- Appointments for patient: " + searchTerm + " ---");
            for (Appointment apt : patientAppointments) {
                apt.displayAppointmentDetails();
                System.out.println("====================================");
            }
        } else {
            System.out.println("No appointments found.");
        }
    }

    public Appointment findAppointmentById(String appointmentId) {
        return database.findAppointmentById(appointmentId).orElse(null);
    }
}