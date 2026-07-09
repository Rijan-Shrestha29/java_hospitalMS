package hospital.model;

public class Patient extends Person {
    private int age;
    private String gender;
    private String address;
    private String bloodGroup;

    public Patient(String id, String name, int age, String gender,
                   String contactNumber, String address) {
        super(id, name, contactNumber, "");
        this.age = age;
        this.gender = gender;
        this.address = address;
    }

    @Override
    public void displayDetails() {
        System.out.println("Patient ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Gender: " + gender);
        System.out.println("Contact: " + contactNumber);
        System.out.println("Address: " + address);
        System.out.println("Blood Group: " + (bloodGroup != null ? bloodGroup : "Not specified"));
    }

    // Getters and Setters
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getBloodGroup() { return bloodGroup; }
    public void setBloodGroup(String bloodGroup) { this.bloodGroup = bloodGroup; }
}