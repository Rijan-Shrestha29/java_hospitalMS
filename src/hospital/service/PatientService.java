package hospital.service;

import hospital.database.HospitalDatabase;
import hospital.model.Patient;
import hospital.util.Validator;
import java.util.List;
import java.util.Optional;

// Demonstrates SRP - Single Responsibility for patient operations
public class PatientService {
    private HospitalDatabase database;

    public PatientService(HospitalDatabase database) {
        this.database = database;
    }

    public void addPatient(String name, int age, String gender,
                           String contactNumber, String address) {
        if (!Validator.isValidName(name)) {
            System.out.println("Error: Invalid name format.");
            return;
        }

        if (!Validator.isValidAge(age)) {
            System.out.println("Error: Invalid age. Age must be between 0 and 150.");
            return;
        }

        String patientId = database.generatePatientId();
        Patient patient = new Patient(patientId, name, age, gender, contactNumber, address);
        database.addPatient(patient);
        System.out.println("Patient added successfully! Patient ID: " + patientId);
    }

    public void viewAllPatients() {
        List<Patient> patients = database.getAllPatients();
        if (patients.isEmpty()) {
            System.out.println("No patients found.");
            return;
        }

        System.out.println("\n--- All Patients ---");
        System.out.println("====================");
        for (Patient patient : patients) {
            patient.displayDetails();
            System.out.println("--------------------");
        }
    }

    public void searchPatient(String searchTerm) {
        Optional<Patient> patient = database.findPatientById(searchTerm);

        if (patient.isEmpty()) {
            patient = database.findPatientByName(searchTerm);
        }

        if (patient.isPresent()) {
            System.out.println("\nPatient Found:");
            patient.get().displayDetails();
        } else {
            System.out.println("Patient not found.");
        }
    }

    public void updatePatient(String patientId, String name, int age,
                              String contactNumber, String address) {
        Optional<Patient> optionalPatient = database.findPatientById(patientId);

        if (optionalPatient.isEmpty()) {
            System.out.println("Patient not found.");
            return;
        }

        Patient patient = optionalPatient.get();

        if (name != null && !name.trim().isEmpty()) {
            patient.setName(name);
        }
        if (age > 0) {
            patient.setAge(age);
        }
        if (contactNumber != null && !contactNumber.trim().isEmpty()) {
            patient.setContactNumber(contactNumber);
        }
        if (address != null && !address.trim().isEmpty()) {
            patient.setAddress(address);
        }

        System.out.println("Patient updated successfully!");
    }

    public void deletePatient(String patientId) {
        if (database.removePatient(patientId)) {
            System.out.println("Patient deleted successfully.");
        } else {
            System.out.println("Patient not found.");
        }
    }

    public Patient findPatientById(String patientId) {
        return database.findPatientById(patientId).orElse(null);
    }
}