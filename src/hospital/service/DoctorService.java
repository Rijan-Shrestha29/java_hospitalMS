package hospital.service;

import hospital.database.HospitalDatabase;
import hospital.model.Doctor;
import java.util.List;
import java.util.Optional;

public class DoctorService {
    private HospitalDatabase database;

    public DoctorService(HospitalDatabase database) {
        this.database = database;
    }

    public void addDoctor(String name, String specialization,
                          String contactNumber, String email) {
        String doctorId = database.generateDoctorId();
        Doctor doctor = new Doctor(doctorId, name, specialization, contactNumber, email);
        database.addDoctor(doctor);
        System.out.println("Doctor added successfully! Doctor ID: " + doctorId);
    }

    public void viewAllDoctors() {
        List<Doctor> doctors = database.getAllDoctors();
        if (doctors.isEmpty()) {
            System.out.println("No doctors found.");
            return;
        }

        System.out.println("\n--- All Doctors ---");
        System.out.println("===================");
        for (Doctor doctor : doctors) {
            doctor.displayDetails();
            System.out.println("--------------------");
        }
    }

    public void searchDoctorBySpecialization(String specialization) {
        List<Doctor> doctors = database.findDoctorsBySpecialization(specialization);

        if (doctors.isEmpty()) {
            System.out.println("No doctors found with specialization: " + specialization);
            return;
        }

        System.out.println("\n--- Doctors with specialization: " + specialization + " ---");
        for (Doctor doctor : doctors) {
            doctor.displayDetails();
            System.out.println("--------------------");
        }
    }

    public void updateDoctor(String doctorId, String name,
                             String specialization, String contactNumber) {
        Optional<Doctor> optionalDoctor = database.findDoctorById(doctorId);

        if (optionalDoctor.isEmpty()) {
            System.out.println("Doctor not found.");
            return;
        }

        Doctor doctor = optionalDoctor.get();

        if (name != null && !name.trim().isEmpty()) {
            doctor.setName(name);
        }
        if (specialization != null && !specialization.trim().isEmpty()) {
            doctor.setSpecialization(specialization);
        }
        if (contactNumber != null && !contactNumber.trim().isEmpty()) {
            doctor.setContactNumber(contactNumber);
        }

        System.out.println("Doctor updated successfully!");
    }

    public void removeDoctor(String doctorId) {
        if (database.removeDoctor(doctorId)) {
            System.out.println("Doctor removed successfully.");
        } else {
            System.out.println("Doctor not found.");
        }
    }

    public Doctor findDoctorById(String doctorId) {
        return database.findDoctorById(doctorId).orElse(null);
    }
}