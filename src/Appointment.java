import java.io.Serializable;
import java.time.LocalDateTime;

public class Appointment implements Serializable {
    private static final long serialVersionUID = 1L;

    private String appointmentType;   // Vet Visit, Vaccination, Grooming
    private LocalDateTime dateTime;   // full date and time
    private String notes;             // optional

    // Constructor
    public Appointment(String appointmentType, LocalDateTime dateTime, String notes) {
        this.appointmentType = appointmentType;
        this.dateTime = dateTime;
        this.notes = notes;
    }

    // Getters
    public String getAppointmentType() { return appointmentType; }
    public LocalDateTime getDateTime() { return dateTime; }
    public String getNotes() { return notes; }

    // Setters (optional)
    public void setAppointmentType(String appointmentType) { this.appointmentType = appointmentType; }
    public void setDateTime(LocalDateTime dateTime) { this.dateTime = dateTime; }
    public void setNotes(String notes) { this.notes = notes; }

    @Override
    public String toString() {
        return appointmentType + " | " + dateTime + " | " + (notes == null || notes.isEmpty() ? "No notes" : notes);
    }
}
