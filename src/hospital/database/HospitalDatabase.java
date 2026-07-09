package hospital.database;

import hospital.model.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Demonstrates SRP - Single Responsibility for data storage
public class HospitalDatabase {
    private List<Patient> patients;
    private List<Doctor> doctors;
    private List<Appointment> appointments;
    private List<Treatment> treatments;
    private List<Bill> bills;
    private int patientCounter;
    private int doctorCounter;
    private int appointmentCounter;
    private int treatmentCounter;
    private int billCounter;
    private int medicineCounter;

    public HospitalDatabase() {
        this.patients = new ArrayList<>();
        this.doctors = new ArrayList<>();
        this.appointments = new ArrayList<>();
        this.treatments = new ArrayList<>();
        this.bills = new ArrayList<>();
        this.patientCounter = 1000;
        this.doctorCounter = 2000;
        this.appointmentCounter = 3000;
        this.treatmentCounter = 4000;
        this.billCounter = 5000;
        this.medicineCounter = 6000;
    }

    // Patient operations
    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    public Optional<Patient> findPatientById(String id) {
        return patients.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    public Optional<Patient> findPatientByName(String name) {
        return patients.stream()
                .filter(p -> p.getName().toLowerCase().contains(name.toLowerCase()))
                .findFirst();
    }

    public List<Patient> getAllPatients() {
        return new ArrayList<>(patients);
    }

    public boolean removePatient(String id) {
        return patients.removeIf(p -> p.getId().equals(id));
    }

    // Doctor operations
    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
    }

    public Optional<Doctor> findDoctorById(String id) {
        return doctors.stream()
                .filter(d -> d.getId().equals(id))
                .findFirst();
    }

    public List<Doctor> findDoctorsBySpecialization(String specialization) {
        return doctors.stream()
                .filter(d -> d.getSpecialization().toLowerCase()
                        .contains(specialization.toLowerCase()))
                .toList();
    }

    public List<Doctor> getAllDoctors() {
        return new ArrayList<>(doctors);
    }

    public boolean removeDoctor(String id) {
        return doctors.removeIf(d -> d.getId().equals(id));
    }

    // Appointment operations
    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    public Optional<Appointment> findAppointmentById(String id) {
        return appointments.stream()
                .filter(a -> a.getAppointmentId().equals(id))
                .findFirst();
    }

    public List<Appointment> getAllAppointments() {
        return new ArrayList<>(appointments);
    }

    public List<Appointment> getAppointmentsByPatient(String patientId) {
        return appointments.stream()
                .filter(a -> a.getPatient().getId().equals(patientId))
                .toList();
    }

    // Treatment operations
    public void addTreatment(Treatment treatment) {
        treatments.add(treatment);
    }

    public Optional<Treatment> findTreatmentById(String id) {
        return treatments.stream()
                .filter(t -> t.getTreatmentId().equals(id))
                .findFirst();
    }

    public List<Treatment> getTreatmentsByPatient(String patientId) {
        return treatments.stream()
                .filter(t -> t.getAppointment().getPatient().getId().equals(patientId))
                .toList();
    }

    // Bill operations
    public void addBill(Bill bill) {
        bills.add(bill);
    }

    public Optional<Bill> findBillById(String id) {
        return bills.stream()
                .filter(b -> b.getBillId().equals(id))
                .findFirst();
    }

    public List<Bill> getAllBills() {
        return new ArrayList<>(bills);
    }

    // Counter generators
    public String generatePatientId() {
        return "P" + (++patientCounter);
    }

    public String generateDoctorId() {
        return "D" + (++doctorCounter);
    }

    public String generateAppointmentId() {
        return "APT" + (++appointmentCounter);
    }

    public String generateTreatmentId() {
        return "TRT" + (++treatmentCounter);
    }

    public String generateBillId() {
        return "BIL" + (++billCounter);
    }

    public String generateMedicineId() {
        return "MED" + (++medicineCounter);
    }
}