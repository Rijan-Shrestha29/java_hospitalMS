package hospital.service;

import hospital.database.HospitalDatabase;
import hospital.model.*;
import java.util.List;
import java.util.Optional;

public class TreatmentService {
    private HospitalDatabase database;

    public TreatmentService(HospitalDatabase database) {
        this.database = database;
    }

    public void assignTreatment(String appointmentId, String description) {
        Optional<Appointment> appointment = database.findAppointmentById(appointmentId);

        if (appointment.isEmpty()) {
            System.out.println("Appointment not found.");
            return;
        }

        String treatmentId = database.generateTreatmentId();
        Treatment treatment = new Treatment(treatmentId, appointment.get());
        treatment.setTreatmentDescription(description);
        database.addTreatment(treatment);

        appointment.get().setStatus("Completed");
        System.out.println("Treatment assigned successfully!");
        System.out.println("Treatment ID: " + treatmentId);
    }

    public void viewTreatmentHistory(String patientId) {
        List<Treatment> treatments = database.getTreatmentsByPatient(patientId);

        if (treatments.isEmpty()) {
            System.out.println("No treatment history found for this patient.");
            return;
        }

        System.out.println("\n--- Treatment History ---");
        for (Treatment treatment : treatments) {
            treatment.displayTreatmentDetails();
            System.out.println("====================================");
        }
    }

    public void addDiagnosis(String treatmentId, String diagnosis) {
        Optional<Treatment> treatment = database.findTreatmentById(treatmentId);

        if (treatment.isEmpty()) {
            System.out.println("Treatment not found.");
            return;
        }

        treatment.get().setDiagnosis(diagnosis);
        System.out.println("Diagnosis added successfully.");
    }

    public void prescribeMedicine(String treatmentId, String medicineName,
                                  String dosage, String duration, double price) {
        Optional<Treatment> treatment = database.findTreatmentById(treatmentId);

        if (treatment.isEmpty()) {
            System.out.println("Treatment not found.");
            return;
        }

        String medicineId = database.generateMedicineId();
        Medicine medicine = new Medicine(medicineId, medicineName, dosage, duration, price);
        treatment.get().addMedicine(medicine);
        System.out.println("Medicine prescribed successfully.");
    }

    public Treatment findTreatmentById(String treatmentId) {
        return database.findTreatmentById(treatmentId).orElse(null);
    }
}