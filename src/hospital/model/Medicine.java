package hospital.model;

public class Medicine {
    private String medicineId;
    private String name;
    private String dosage;
    private String duration;
    private double price;

    public Medicine(String medicineId, String name, String dosage, String duration, double price) {
        this.medicineId = medicineId;
        this.name = name;
        this.dosage = dosage;
        this.duration = duration;
        this.price = price;
    }

    public void displayMedicineDetails() {
        System.out.println("Medicine: " + name);
        System.out.println("Dosage: " + dosage);
        System.out.println("Duration: " + duration);
        System.out.println("Price: $" + String.format("%.2f", price));
        System.out.println("--------------------");
    }

    // Getters
    public String getMedicineId() { return medicineId; }
    public String getName() { return name; }
    public String getDosage() { return dosage; }
    public String getDuration() { return duration; }
    public double getPrice() { return price; }
}