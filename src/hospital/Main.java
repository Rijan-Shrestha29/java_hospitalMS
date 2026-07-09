package hospital;

import hospital.database.HospitalDatabase;
import hospital.service.*;
import hospital.util.InputHelper;
import java.util.Scanner;

public class Main {
    private static HospitalDatabase database;
    private static PatientService patientService;
    private static DoctorService doctorService;
    private static AppointmentService appointmentService;
    private static TreatmentService treatmentService;
    private static BillingService billingService;
    private static Scanner scanner;

    public static void main(String[] args) {
        initialize();
        showMainMenu();
    }

    private static void initialize() {
        database = new HospitalDatabase();
        patientService = new PatientService(database);
        doctorService = new DoctorService(database);
        appointmentService = new AppointmentService(database);
        treatmentService = new TreatmentService(database);
        billingService = new BillingService(database);
        scanner = new Scanner(System.in);

        // Add sample data
        initializeSampleData();
    }

    private static void initializeSampleData() {
        // Add sample doctors
        doctorService.addDoctor("Dr. Smith", "Cardiology", "1234567890", "smith@hospital.com");
        doctorService.addDoctor("Dr. Johnson", "Neurology", "9876543210", "johnson@hospital.com");
        doctorService.addDoctor("Dr. Williams", "Pediatrics", "5555555555", "williams@hospital.com");
    }

    private static void showMainMenu() {
        while (true) {
            System.out.println("\n╔════════════════════════════════════════╗");
            System.out.println("║    HOSPITAL MANAGEMENT SYSTEM         ║");
            System.out.println("╚════════════════════════════════════════╝");
            System.out.println("1. Patient Management");
            System.out.println("2. Doctor Management");
            System.out.println("3. Appointment Management");
            System.out.println("4. Treatment Management");
            System.out.println("5. Billing Management");
            System.out.println("6. Reports");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = InputHelper.getIntInput(scanner);

            switch (choice) {
                case 1: handlePatientMenu(); break;
                case 2: handleDoctorMenu(); break;
                case 3: handleAppointmentMenu(); break;
                case 4: handleTreatmentMenu(); break;
                case 5: handleBillingMenu(); break;
                case 6: showReports(); break;
                case 7:
                    System.out.println("Thank you for using Hospital Management System!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void handlePatientMenu() {
        while (true) {
            System.out.println("\n--- PATIENT MANAGEMENT ---");
            System.out.println("1. Add Patient");
            System.out.println("2. View All Patients");
            System.out.println("3. Search Patient");
            System.out.println("4. Update Patient");
            System.out.println("5. Delete Patient");
            System.out.println("6. Back to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = InputHelper.getIntInput(scanner);

            switch (choice) {
                case 1: addPatient(); break;
                case 2: patientService.viewAllPatients(); break;
                case 3: searchPatient(); break;
                case 4: updatePatient(); break;
                case 5: deletePatient(); break;
                case 6: return;
                default: System.out.println("Invalid choice.");
            }
        }
    }

    private static void addPatient() {
        System.out.print("Enter patient name: ");
        String name = scanner.nextLine();
        System.out.print("Enter age: ");
        int age = InputHelper.getIntInput(scanner);
        System.out.print("Enter gender (M/F): ");
        String gender = scanner.nextLine();
        System.out.print("Enter contact number: ");
        String contact = scanner.nextLine();
        System.out.print("Enter address: ");
        String address = scanner.nextLine();

        patientService.addPatient(name, age, gender, contact, address);
    }

    private static void searchPatient() {
        System.out.print("Enter patient ID or name: ");
        String searchTerm = scanner.nextLine();
        patientService.searchPatient(searchTerm);
    }

    private static void updatePatient() {
        System.out.print("Enter patient ID to update: ");
        String patientId = scanner.nextLine();
        System.out.print("Enter new name (or press enter to skip): ");
        String name = scanner.nextLine();
        System.out.print("Enter new age (or 0 to skip): ");
        int age = InputHelper.getIntInput(scanner);
        System.out.print("Enter new contact (or press enter to skip): ");
        String contact = scanner.nextLine();
        System.out.print("Enter new address (or press enter to skip): ");
        String address = scanner.nextLine();

        patientService.updatePatient(patientId, name, age, contact, address);
    }

    private static void deletePatient() {
        System.out.print("Enter patient ID to delete: ");
        String patientId = scanner.nextLine();
        patientService.deletePatient(patientId);
    }

    private static void handleDoctorMenu() {
        while (true) {
            System.out.println("\n--- DOCTOR MANAGEMENT ---");
            System.out.println("1. Add Doctor");
            System.out.println("2. View All Doctors");
            System.out.println("3. Search Doctor by Specialization");
            System.out.println("4. Update Doctor");
            System.out.println("5. Remove Doctor");
            System.out.println("6. Back to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = InputHelper.getIntInput(scanner);

            switch (choice) {
                case 1: addDoctor(); break;
                case 2: doctorService.viewAllDoctors(); break;
                case 3: searchDoctorBySpecialization(); break;
                case 4: updateDoctor(); break;
                case 5: removeDoctor(); break;
                case 6: return;
                default: System.out.println("Invalid choice.");
            }
        }
    }

    private static void addDoctor() {
        System.out.print("Enter doctor name: ");
        String name = scanner.nextLine();
        System.out.print("Enter specialization: ");
        String specialization = scanner.nextLine();
        System.out.print("Enter contact number: ");
        String contact = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        doctorService.addDoctor(name, specialization, contact, email);
    }

    private static void searchDoctorBySpecialization() {
        System.out.print("Enter specialization to search: ");
        String specialization = scanner.nextLine();
        doctorService.searchDoctorBySpecialization(specialization);
    }

    private static void updateDoctor() {
        System.out.print("Enter doctor ID to update: ");
        String doctorId = scanner.nextLine();
        System.out.print("Enter new name (or press enter to skip): ");
        String name = scanner.nextLine();
        System.out.print("Enter new specialization (or press enter to skip): ");
        String specialization = scanner.nextLine();
        System.out.print("Enter new contact (or press enter to skip): ");
        String contact = scanner.nextLine();

        doctorService.updateDoctor(doctorId, name, specialization, contact);
    }

    private static void removeDoctor() {
        System.out.print("Enter doctor ID to remove: ");
        String doctorId = scanner.nextLine();
        doctorService.removeDoctor(doctorId);
    }

    private static void handleAppointmentMenu() {
        while (true) {
            System.out.println("\n--- APPOINTMENT MANAGEMENT ---");
            System.out.println("1. Book Appointment");
            System.out.println("2. View All Appointments");
            System.out.println("3. Cancel Appointment");
            System.out.println("4. Search Appointment");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = InputHelper.getIntInput(scanner);

            switch (choice) {
                case 1: bookAppointment(); break;
                case 2: appointmentService.viewAllAppointments(); break;
                case 3: cancelAppointment(); break;
                case 4: searchAppointment(); break;
                case 5: return;
                default: System.out.println("Invalid choice.");
            }
        }
    }

    private static void bookAppointment() {
        System.out.print("Enter patient ID: ");
        String patientId = scanner.nextLine();
        System.out.print("Enter doctor ID: ");
        String doctorId = scanner.nextLine();
        System.out.print("Enter appointment date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        System.out.print("Enter appointment time (HH:MM): ");
        String time = scanner.nextLine();

        appointmentService.bookAppointment(patientId, doctorId, date, time);
    }

    private static void cancelAppointment() {
        System.out.print("Enter appointment ID to cancel: ");
        String appointmentId = scanner.nextLine();
        appointmentService.cancelAppointment(appointmentId);
    }

    private static void searchAppointment() {
        System.out.print("Enter appointment ID or patient name: ");
        String searchTerm = scanner.nextLine();
        appointmentService.searchAppointment(searchTerm);
    }

    private static void handleTreatmentMenu() {
        while (true) {
            System.out.println("\n--- TREATMENT MANAGEMENT ---");
            System.out.println("1. Assign Treatment");
            System.out.println("2. View Treatment History");
            System.out.println("3. Add Diagnosis");
            System.out.println("4. Prescribe Medicine");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = InputHelper.getIntInput(scanner);

            switch (choice) {
                case 1: assignTreatment(); break;
                case 2: viewTreatmentHistory(); break;
                case 3: addDiagnosis(); break;
                case 4: prescribeMedicine(); break;
                case 5: return;
                default: System.out.println("Invalid choice.");
            }
        }
    }

    private static void assignTreatment() {
        System.out.print("Enter appointment ID: ");
        String appointmentId = scanner.nextLine();
        System.out.print("Enter treatment description: ");
        String description = scanner.nextLine();

        treatmentService.assignTreatment(appointmentId, description);
    }

    private static void viewTreatmentHistory() {
        System.out.print("Enter patient ID: ");
        String patientId = scanner.nextLine();
        treatmentService.viewTreatmentHistory(patientId);
    }

    private static void addDiagnosis() {
        System.out.print("Enter treatment ID: ");
        String treatmentId = scanner.nextLine();
        System.out.print("Enter diagnosis: ");
        String diagnosis = scanner.nextLine();

        treatmentService.addDiagnosis(treatmentId, diagnosis);
    }

    private static void prescribeMedicine() {
        System.out.print("Enter treatment ID: ");
        String treatmentId = scanner.nextLine();
        System.out.print("Enter medicine name: ");
        String medicineName = scanner.nextLine();
        System.out.print("Enter dosage: ");
        String dosage = scanner.nextLine();
        System.out.print("Enter duration: ");
        String duration = scanner.nextLine();
        System.out.print("Enter price: $");
        double price = Double.parseDouble(scanner.nextLine());

        treatmentService.prescribeMedicine(treatmentId, medicineName, dosage, duration, price);
    }

    private static void handleBillingMenu() {
        while (true) {
            System.out.println("\n--- BILLING MANAGEMENT ---");
            System.out.println("1. Generate Bill");
            System.out.println("2. View All Bills");
            System.out.println("3. View Bill Details");
            System.out.println("4. Process Payment");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = InputHelper.getIntInput(scanner);

            switch (choice) {
                case 1: generateBill(); break;
                case 2: billingService.viewAllBills(); break;
                case 3: viewBillDetails(); break;
                case 4: processPayment(); break;
                case 5: return;
                default: System.out.println("Invalid choice.");
            }
        }
    }

    private static void generateBill() {
        System.out.print("Enter treatment ID: ");
        String treatmentId = scanner.nextLine();
        System.out.print("Enter consultation fee: $");
        double consultationFee = Double.parseDouble(scanner.nextLine());
        System.out.print("Enter room charges: $");
        double roomCharges = Double.parseDouble(scanner.nextLine());
        System.out.print("Enter laboratory charges: $");
        double labCharges = Double.parseDouble(scanner.nextLine());

        billingService.generateBill(treatmentId, consultationFee, roomCharges, labCharges);
    }

    private static void viewBillDetails() {
        System.out.print("Enter bill ID: ");
        String billId = scanner.nextLine();
        billingService.viewBillDetails(billId);
    }

    private static void processPayment() {
        System.out.print("Enter bill ID: ");
        String billId = scanner.nextLine();

        System.out.println("Select payment method:");
        System.out.println("1. Cash");
        System.out.println("2. Card");
        System.out.println("3. Digital Wallet");
        System.out.print("Enter choice: ");

        int choice = InputHelper.getIntInput(scanner);
        billingService.processPayment(billId, choice, scanner);
    }

    private static void showReports() {
        System.out.println("\n--- REPORTS ---");
        System.out.println("Total Patients: " + database.getAllPatients().size());
        System.out.println("Total Doctors: " + database.getAllDoctors().size());
        System.out.println("Total Appointments: " + database.getAllAppointments().size());
        System.out.println("Total Bills: " + database.getAllBills().size());

        double totalRevenue = database.getAllBills().stream()
                .filter(bill -> bill.isPaid())
                .mapToDouble(bill -> bill.getTotalAmount())
                .sum();
        System.out.println("Total Revenue: $" + String.format("%.2f", totalRevenue));
    }
}