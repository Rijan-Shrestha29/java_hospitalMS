# 🏥 Hospital Management System

A comprehensive Java console-based Hospital Management System that demonstrates Object-Oriented Programming principles, design patterns, and clean code practices.

![Java](https://img.shields.io/badge/Java-17+-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![License](https://img.shields.io/badge/License-MIT-blue.svg)
![Build](https://img.shields.io/badge/Build-Passing-brightgreen.svg)

## 📋 Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Project Structure](#project-structure)
- [OOP Concepts Demonstrated](#oop-concepts-demonstrated)
- [SOLID Principles](#solid-principles)
- [UML Relationships](#uml-relationships)
- [System Architecture](#system-architecture)
- [Installation & Setup](#installation--setup)
- [Usage Guide](#usage-guide)
- [Module Description](#module-description)
- [Class Diagram](#class-diagram)
- [Design Patterns](#design-patterns)
- [Refactoring Examples](#refactoring-examples)
- [Clean Code Practices](#clean-code-practices)
- [Future Enhancements](#future-enhancements)

## 🎯 Overview

The Hospital Management System is a robust Java application designed to streamline hospital operations including patient management, doctor scheduling, appointment booking, treatment tracking, billing, and payment processing. This project serves as a demonstration of advanced Java programming concepts and software design principles.

### Project Statistics

| Metric | Count |
|--------|-------|
| Total Classes | 18 |
| Interfaces | 1 |
| Abstract Classes | 1 |
| Packages | 6 |
| Methods | 100+ |
| Lines of Code | 1,800+ |

## ✨ Features

### Core Modules
- **👨‍⚕️ Patient Management**
  - Register new patients
  - Update patient information
  - Search patients by ID or name
  - View all patients
  - Delete patient records

- **👨‍⚕️ Doctor Management**
  - Add new doctors
  - Update doctor profiles
  - Search doctors by specialization
  - View all doctors
  - Remove doctors

- **📅 Appointment Management**
  - Book appointments
  - View appointment schedules
  - Cancel appointments
  - Search appointments
  - Automatic status tracking

- **💊 Treatment Management**
  - Assign treatments
  - View treatment history
  - Add diagnoses
  - Prescribe medicines
  - Track medicine costs

- **💰 Billing System**
  - Generate detailed bills
  - Calculate consultation fees
  - Track medicine charges
  - Room and laboratory charges
  - Print professional invoices

- **💳 Payment Processing**
  - Multiple payment methods
  - Cash payments
  - Card payments
  - Digital wallet payments
  - Payment status tracking

## 📁 Project Structure

```java
HospitalManagementSystem/
│
├── src/
│ └── hospital/
│ ├── Main.java # Application entry point
│ │
│ ├── model/ # Data models
│ │ ├── Person.java # Abstract base class
│ │ ├── Patient.java # Patient entity
│ │ ├── Doctor.java # Doctor entity
│ │ ├── Appointment.java # Appointment entity
│ │ ├── Treatment.java # Treatment entity
│ │ ├── Medicine.java # Medicine entity
│ │ └── Bill.java # Bill entity
│ │
│ ├── service/ # Business logic
│ │ ├── PatientService.java # Patient operations
│ │ ├── DoctorService.java # Doctor operations
│ │ ├── AppointmentService.java # Appointment operations
│ │ ├── TreatmentService.java # Treatment operations
│ │ └── BillingService.java # Billing operations
│ │
│ ├── payment/ # Payment strategy
│ │ ├── Payment.java # Payment interface
│ │ ├── CashPayment.java # Cash payment implementation
│ │ ├── CardPayment.java # Card payment implementation
│ │ └── DigitalWalletPayment.java # Wallet payment implementation
│ │
│ ├── database/ # Data storage
│ │ └── HospitalDatabase.java # In-memory database
│ │
│ └── util/ # Utilities
│ ├── InputHelper.java # Input validation helper
│ └── Validator.java # Data validation
│
├── README.md # Project documentation
└── .gitignore # Git ignore file
```

## 🔧 OOP Concepts Demonstrated

### 1. Encapsulation
```java
// Private fields with public getters/setters
private String patientName;
private int age;
private String disease;

public String getPatientName() { return patientName; }
public void setPatientName(String patientName) { this.patientName = patientName; }
```

### 2. Inheritance
```java
          Person (Abstract)
             ▲
    -------------------
    |        |        |
 Patient   Doctor   Staff
```

### 3. Polymorphism
```java
// Payment interface with multiple implementations
Payment payment;
payment = new CashPayment();        // Different behavior
payment.processPayment(amount);

payment = new CardPayment();        // Same interface, different behavior
payment.processPayment(amount);
```

### 4. Abstraction
```java
// Abstract class forces implementation
public abstract class Person {
    public abstract void displayDetails();
}

// Interface defines contract
public interface Payment {
    boolean processPayment(double amount);
    String getPaymentMethod();
}
```

## 🏗️ SOLID Principles
### Single Responsibility Principle (SRP)

Each class has one, and only one, reason to change:

*   PatientService - Only handles patient operations
    
*   BillingService - Only handles billing logic
    
*   HospitalDatabase - Only handles data storage
    

### Open/Closed Principle (OCP)

Classes are open for extension but closed for modification:
```java
// Adding new payment method doesn't modify existing code
public class InsurancePayment implements Payment {
    @Override
    public boolean processPayment(double amount) {
        // New implementation
    }
}
```
### Liskov Substitution Principle (LSP)
Subtypes must be substitutable for their base types:

```java
Person person = new Doctor();  // Works correctly
Person person = new Patient(); // Also works correctly
person.displayDetails();       // Both behave properly
```

### Interface Segregation Principle (ISP)
Clients shouldn't depend on methods they don't use:
```java
// Instead of one large interface
interface HospitalOperations { ... }

// We have focused interfaces
interface Treatable { ... }
interface Billable { ... }
interface Schedulable { ... }
```

### Dependency Inversion Principle (DIP)
High-level modules don't depend on low-level modules:
```java
// Depends on abstraction, not concrete implementation
public class BillingService {
    private Payment payment; // Interface, not concrete class
}
```

## 📊 UML Relationships
```
Relationship	Type	Example
Association	Doctor -------- Patient	Doctor treats Patient
Aggregation	Hospital ◇------ Doctor	Hospital has Doctors (independent lifecycle)
Aggregation	Hospital ◇------ Patient	Hospital has Patients (independent lifecycle)
Composition	Appointment ◆------ Treatment	Appointment owns Treatment (dependent lifecycle)
Composition	Bill ◆------ BillItem	Bill owns BillItems (dependent lifecycle)
Inheritance	Person <|-- Patient	Patient is a Person
Inheritance	Person <|-- Doctor	Doctor is a Person
Realization	Payment <|.. CashPayment	CashPayment implements Payment
```

### 🏛️ System Architecture
Architecture Pattern: Layered Architecture
```
┌─────────────────────────────────────────────────┐
│                 Presentation Layer               │
│              (Console Menu System)               │
├─────────────────────────────────────────────────┤
│                  Service Layer                   │
│    (Business Logic & Service Coordination)       │
├─────────────────────────────────────────────────┤
│                   Domain Layer                   │
│    (Models, Entities & Business Objects)         │
├─────────────────────────────────────────────────┤
│               Data Access Layer                  │
│    (Database Operations & Data Management)       │
└─────────────────────────────────────────────────┘
```

Data Flow
```
User Input → Main Menu → Service Layer → Database → Response → Console Output
```

🚀 Installation & Setup

### Prerequisites

*   **Java JDK 17 or higher**
    
*   **Any Java IDE** (IntelliJ IDEA, Eclipse, VS Code recommended)
    
*   **Git** (optional, for version control)
    

### Installation Steps

1.  **Clone the repository**
```bash
git clone https://github.com/yourusername/hospital-management-system.git
cd hospital-management-system
```
2. ** Compile the project **
```bash
cd src
javac hospital/Main.java
```
3. Run the application
```nbash
java hospital.Main
```

### DE Setup (IntelliJ IDEA)

1.  Open IntelliJ IDEA
2.  Click File → Open
3.  Select the project directory
4.  Mark src as Sources Root
5.  Run Main.java
    

📖 Usage Guide

### Main Menu Navigation

```
╔════════════════════════════════════════╗
║    HOSPITAL MANAGEMENT SYSTEM         ║
╚════════════════════════════════════════╝
1. Patient Management
2. Doctor Management
3. Appointment Management
4. Treatment Management
5. Billing Management
6. Reports
7. Exit
```

### Quick Start Guide

#### 1\. Add a Doctor
```
→ Doctor Management → Add Doctor
→ Enter: Dr. Smith, Cardiology, 1234567890, smith@hospital.com
```
#### 2\. Register a Patient
```
→ Patient Management → Add Patient
→ Enter: John Doe, 35, M, 9876543210, 123 Main St
```
#### 3\. Book an Appointment
```
→ Appointment Management → Book Appointment
→ Enter patient ID, doctor ID, date (YYYY-MM-DD), time (HH:MM)
```
#### 4\. Create Treatment & Bill
```
→ Treatment Management → Assign Treatment
→ Billing Management → Generate Bill
```

📦 Module Description
---------------------

### Patient Management Module

Handles all patient-related operations with validation:

*   Name validation (minimum 2 characters)
    
*   Age validation (0-150 years)
    
*   Contact number formatting
    
*   Address management
    

### Doctor Management Module

Manages doctor profiles and specializations:

*   Specialization-based search
    
*   Contact information management
    
*   Email validation
    
*   Profile updates
    

### Appointment Management Module

Coordinates patient-doctor scheduling:

*   Date and time validation
    
*   Conflict checking
    
*   Status tracking (Scheduled/Completed/Cancelled)
    
*   Appointment history
    

### Treatment Management Module

Handles medical treatment tracking:

*   Diagnosis recording
    
*   Medicine prescription
    
*   Treatment history
    
*   Medicine cost calculation
    

### Billing Module

Manages financial transactions:

*   Automatic charge calculation
    
*   Professional invoice generation
    
*   Payment tracking
    
*   Revenue reporting
    

### Payment Module

Processes payments through multiple channels:

*   **Cash**: Direct payment processing
    
*   **Card**: Card validation simulation
    
*   **Digital Wallet**: Multiple wallet support
    

## 📐 Class Diagram
```
                    ┌─────────────┐
                    │   Person    │ (Abstract)
                    │-------------│
                    │ - id        │
                    │ - name      │
                    │ - contact   │
                    │ + display() │
                    └─────────────┘
                           ▲
                 ┌─────────┴─────────┐
                 │                   │
           ┌─────────┐         ┌─────────┐
           │ Patient │         │ Doctor  │
           │---------│         │---------│
           │ - age   │         │ - spec  │
           │ - gender│         │ - fee   │
           └─────────┘         └─────────┘
                │                    │
                │    ┌──────────┐   │
                └────┤Appointment├───┘
                     │----------│
                     │ - date   │
                     │ - time   │
                     └──────────┘
                          │
                     ┌──────────┐
                     │Treatment │
                     │----------│
                     │-diagnosis│
                     │-medicine │
                     └──────────┘
                          │
                     ┌──────────┐
                     │   Bill   │
                     │----------│
                     │-total    │
                     │-status   │
                     └──────────┘
```
🎨 Design Patterns
------------------

### 1\. Repository Pattern

*   HospitalDatabase acts as a repository for all entities
    
*   Provides a clean API for data access
    
*   Centralizes data management
    

### 2\. Service Layer Pattern

*   PatientService, DoctorService, etc.
    
*   Encapsulates business logic
    
*   Provides transaction management
    

### 3\. Strategy Pattern

*   Payment interface with multiple strategies
    
*   CashPayment, CardPayment, DigitalWalletPayment
    
*   Easy to add new payment methods
    

### 4\. Factory Pattern (Implicit)

*   Service classes create appropriate objects
    
*   ID generation centralized in database
    

### 5\. Facade Pattern

*   Main class acts as a facade
    
*   Simplifies complex subsystem interactions
    

## 🔄 Refactoring Examples

### Before Refactoring
```java
// Long method with multiple responsibilities
public void processPatientAndBill() {
    // 200 lines of mixed code
}
```

### After Refactoring
```java
// Extract Method - Single Responsibility
private void validatePatientData() { ... }
private void savePatient() { ... }
private void generateBill() { ... }
private void processPayment() { ... }

// Replace Magic Numbers with Constants
private static final int MAX_AGE = 150;
private static final double DEFAULT_CONSULTATION_FEE = 100.0;

// Extract Class
public class PatientValidator {
    public boolean validate(Patient patient) { ... }
}
```

## ✨ Clean Code Practices

*   ✅ **Meaningful Names**: findPatientById() instead of find()
    
*   ✅ **Small Methods**: Each method does one thing well
    
*   ✅ **No Magic Numbers**: All constants are named
    
*   ✅ **Consistent Formatting**: Uniform indentation and spacing
    
*   ✅ **DRY Principle**: No duplicated code
    
*   ✅ **Self-Documenting Code**: Code explains itself
    
*   ✅ **Proper Comments**: Only where necessary for complex logic
    
*   ✅ **Error Handling**: Comprehensive input validation
  

## 🚀 Future Enhancements
### Short-term Improvements

*   Database persistence (MySQL/PostgreSQL)
    
*   Graphical User Interface (JavaFX/Swing)
    
*   Authentication and authorization system
    
*   Email notifications for appointments
    
*   Medical history tracking
    

### Long-term Vision

*   REST API development
    
*   Web-based interface
    
*   Mobile application
    
*   Integration with laboratory systems
    
*   Telemedicine support
    
*   AI-powered diagnosis assistance
    
*   Real-time bed management
    
*   Insurance claim processing
    

### Coding Standards

*   Follow Java naming conventions
    
*   Write unit tests for new features
    
*   Update documentation as needed
    
*   Maintain SOLID principles
    
*   Keep methods focused and small
    

## 📝 Testing

### Manual Testing Scenarios

1.  **Patient Registration Flow**
    
    *   Test with valid data
        
    *   Test with invalid age/name
        
    *   Verify duplicate handling
        
2.  **Appointment Booking**
    
    *   Book with valid patient/doctor
        
    *   Test with non-existent IDs
        
    *   Verify date/time validation
        
3.  **Payment Processing**
    
    *   Test all payment methods
        
    *   Verify bill status updates
        
    *   Check amount calculations
        

## 🐛 Known Issues

*   In-memory database loses data on application restart
    
*   No concurrent user support
    
*   Limited input validation for some edge cases
    
*   Console clearing not implemented for all menus
    

## 📚 Learning Resources

This project demonstrates concepts from:

*   **Object-Oriented Analysis and Design**
    
*   **Design Patterns: Elements of Reusable Object-Oriented Software**
    
*   **Clean Code: A Handbook of Agile Software Craftsmanship**
    
*   **SOLID Principles of Object-Oriented Design**



## 📸 Screenshots
## Main Menu
<img width="397" height="282" alt="image" src="https://github.com/user-attachments/assets/05697b03-7184-4f66-a7c8-73ecedb903d7" />

### Bill Invoice Example 
<img width="332" height="396" alt="image" src="https://github.com/user-attachments/assets/1a39e5c1-24c4-41ba-88f8-a263193be074" />

## 🎯 Project Objectives Achieved

This project successfully demonstrates:

*   ✅ Object-Oriented Programming concepts
    
*   ✅ UML relationships implementation
    
*   ✅ SOLID principles application
    
*   ✅ Design patterns usage
    
*   ✅ Clean code practices
    
*   ✅ Refactoring techniques
    
*   ✅ Modular architecture
    
*   ✅ Comprehensive error handling
    
*   ✅ User-friendly console interface
    
*   ✅ Professional documentation
