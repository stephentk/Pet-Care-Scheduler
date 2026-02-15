import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;


public class Pet implements Serializable {
private static final long serialVersionUID = 1L;
    private String petId;                     // Unique Pet ID
    private String name;
    private String speciesOrBreed;
    private int age;
    private String ownerName;
    private String contactInfo;
    private LocalDate registrationDate;
    private List<Appointment> appointments;

    // Constructor
    public Pet(String petId, String name, String speciesOrBreed, int age,
               String ownerName, String contactInfo) {
        this.petId = petId;
        this.name = name;
        this.speciesOrBreed = speciesOrBreed;
        this.age = age;
        this.ownerName = ownerName;
        this.contactInfo = contactInfo;
        this.registrationDate = LocalDate.now();
        this.appointments = new ArrayList<>();
    }

    // Getters
    public String getPetId() {
        return petId;
    }

    public String getName() {
        return name;
    }

    public String getSpeciesOrBreed() {
        return speciesOrBreed;
    }

    public int getAge() {
        return age;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setSpeciesOrBreed(String speciesOrBreed) {
        this.speciesOrBreed = speciesOrBreed;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    // Appointment management
    public void addAppointment(Appointment appointment) {
        this.appointments.add(appointment);
    }

    // Override toString
    @Override
    public String toString() {
        return "Pet{" +
                "petId='" + petId + '\'' +
                ", name='" + name + '\'' +
                ", speciesOrBreed='" + speciesOrBreed + '\'' +
                ", age=" + age +
                ", ownerName='" + ownerName + '\'' +
                ", contactInfo='" + contactInfo + '\'' +
                ", registrationDate=" + registrationDate +
                ", appointments=" + appointments +
                '}';
    }
}
