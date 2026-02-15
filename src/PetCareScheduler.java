import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PetCareScheduler {

    private static List<Pet> pets = new ArrayList<>();
    private static List<Appointment> appointments = new ArrayList<>();

    private static final String PET_FILE = "pets.dat";
    private static final String APPOINTMENT_FILE = "appointments.dat";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        loadData(); // Load pets and appointments from serialized files

        boolean running = true;

        while (running) {
            try {
                System.out.println("\n--- Pet Care Scheduler ---");
                System.out.println("1. Register Pet");
                System.out.println("2. Schedule Appointment");
                System.out.println("3. Display Records");
                System.out.println("4. Generate Reports");
                System.out.println("5. Store Data");
                System.out.println("6. Exit");
                System.out.print("Enter choice: ");
                

                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        registerPet(scanner);
                        break;
                    case 2:
                        scheduleAppointmentForPet(scanner);
                        break;
                    case 3:
                        displayRecords(scanner);
                        break;
                    case 4:
                        generatePetReport();
                        break;
                    case 5:
                        storeData();
                        break;
                    case 6:
                        storeData();
                        running = false;
                        System.out.println("Exiting application...");
                        break;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }

        scanner.close();
    }

    // ------------------ Register Pet ------------------
    private static void registerPet(Scanner scanner) {
        try {
            System.out.print("Enter Pet ID: ");
            String petId = scanner.nextLine().trim();
            if (petId.isEmpty()) { System.out.println("Pet ID cannot be empty."); return; }

            System.out.print("Enter Pet Name: ");
            String name = scanner.nextLine().trim();
            if (name.isEmpty()) { System.out.println("Pet name cannot be empty."); return; }

            System.out.print("Enter Species/Breed: ");
            String breed = scanner.nextLine().trim();
            if (breed.isEmpty()) { System.out.println("Species/Breed cannot be empty."); return; }

            System.out.print("Enter Age: ");
            int age = Integer.parseInt(scanner.nextLine());
            if (age < 0) { System.out.println("Age cannot be less than zero."); return; }

            System.out.print("Enter Owner Name: ");
            String ownerName = scanner.nextLine().trim();
            if (ownerName.isEmpty()) { System.out.println("Owner name cannot be empty."); return; }

            System.out.print("Enter Contact Info: ");
            String contact = scanner.nextLine().trim();
            if (contact.isEmpty()) { System.out.println("Contact info cannot be empty."); return; }

            Pet pet = new Pet(petId, name, breed, age, ownerName, contact);
            pets.add(pet);

            System.out.println("Pet registered successfully.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid age. Must be a number.");
        }
    }

    // ------------------ Schedule Appointment ------------------
    private static void scheduleAppointmentForPet(Scanner scanner) {
        try {
            System.out.print("Enter Pet ID: ");
            String petId = scanner.nextLine().trim();

            Pet selectedPet = null;
            for (Pet pet : pets) {
                if (pet.getPetId().equalsIgnoreCase(petId)) {
                    selectedPet = pet;
                    break;
                }
            }
            if (selectedPet == null) {
                System.out.println("Pet ID not found.");
                return;
            }

            System.out.print("Enter Appointment Type (Vet Visit / Vaccination / Grooming): ");
            String appointmentType = scanner.nextLine().trim();
            if (!(appointmentType.equalsIgnoreCase("Vet Visit")
                    || appointmentType.equalsIgnoreCase("Vaccination")
                    || appointmentType.equalsIgnoreCase("Grooming"))) {
                System.out.println("Invalid appointment type.");
                return;
            }

            System.out.print("Enter Appointment Date & Time (yyyy-MM-ddTHH:mm): ");
            String inputDateTime = scanner.nextLine().trim();
            LocalDateTime appointmentDateTime;
            try {
                appointmentDateTime = LocalDateTime.parse(inputDateTime);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date/time format.");
                return;
            }

            if (appointmentDateTime.isBefore(LocalDateTime.now())) {
                System.out.println("Appointment must be in the future.");
                return;
            }

        boolean duplicate = false;
        for (Appointment appt : selectedPet.getAppointments()) {
            if (appt.getDateTime().equals(appointmentDateTime)
                    && appt.getAppointmentType().equalsIgnoreCase(appointmentType)) {
                duplicate = true;
                break;
            }
        }
        if (duplicate) {
            System.out.println("Duplicate appointment detected for this pet at the same time.");
            return;
        }

            System.out.print("Enter Notes (optional): ");
            String notes = scanner.nextLine().trim();
            if (notes.length() > 200) {
            System.out.println("Notes too long. Maximum 200 characters allowed.");
            return;
        }

            Appointment appointment = new Appointment(appointmentType, appointmentDateTime, notes);
            selectedPet.addAppointment(appointment);
            appointments.add(appointment); // Add to global appointments list

            System.out.println("Appointment scheduled successfully for " + selectedPet.getName());
        } catch (Exception e) {
            System.out.println("Error scheduling appointment: " + e.getMessage());
        }
    }

    // ------------------ Display Records ------------------
    private static void displayRecords(Scanner scanner) {
        System.out.println("\n--- Registered Pets ---");
        if (pets.isEmpty()) {
            System.out.println("No pets registered.");
        } else {
            for (Pet pet : pets) {
                System.out.println(pet);
            }
        }

        System.out.print("\nEnter Pet ID to view appointments (or press Enter to skip): ");
        String petId = scanner.nextLine().trim();

        if (!petId.isEmpty()) {
            Pet selectedPet = null;
            for (Pet pet : pets) {
                if (pet.getPetId().equalsIgnoreCase(petId)) {
                    selectedPet = pet;
                    break;
                }
            }

            if (selectedPet != null) {
                System.out.println("\nAppointments for " + selectedPet.getName());
                LocalDateTime now = LocalDateTime.now();

                System.out.println("Upcoming Appointments:");
                boolean upcomingFound = false;
                for (Appointment appt : selectedPet.getAppointments()) {
                    if (appt.getDateTime().isAfter(now)) {
                        System.out.println(appt);
                        upcomingFound = true;
                    }
                }
                if (!upcomingFound) System.out.println("None");

                System.out.println("\nPast Appointments:");
                boolean pastFound = false;
                for (Appointment appt : selectedPet.getAppointments()) {
                    if (appt.getDateTime().isBefore(now)) {
                        System.out.println(appt);
                        pastFound = true;
                    }
                }
                if (!pastFound) System.out.println("None");
            } else {
                System.out.println("Pet ID not found.");
            }
        }

        // Display upcoming appointments for all pets
        System.out.println("\n--- Upcoming Appointments for All Pets ---");
        LocalDateTime now = LocalDateTime.now();
        boolean anyUpcoming = false;
        for (Pet pet : pets) {
            for (Appointment appt : pet.getAppointments()) {
                if (appt.getDateTime().isAfter(now)) {
                    System.out.println("Pet: " + pet.getName() + " | " + appt);
                    anyUpcoming = true;
                }
            }
        }
        if (!anyUpcoming) System.out.println("No upcoming appointments.");

        // Display past appointments for all pets
        System.out.println("\n--- Past Appointments for All Pets ---");
        boolean anyPast = false;
        for (Pet pet : pets) {
            for (Appointment appt : pet.getAppointments()) {
                if (appt.getDateTime().isBefore(now)) {
                    System.out.println("Pet: " + pet.getName() + " | " + appt);
                    anyPast = true;
                }
            }
        }
        if (!anyPast) System.out.println("No past appointments.");
    }

    // ------------------ Generate Report ------------------
    private static void generatePetReport() {
        System.out.println("\n--- Pet Report ---");

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nextWeek = now.plusWeeks(1);

        System.out.println("\nPets with upcoming appointments in the next week:");
        boolean upcomingFound = false;
        for (Pet pet : pets) {
            for (Appointment appt : pet.getAppointments()) {
                if (appt.getDateTime().isAfter(now) && appt.getDateTime().isBefore(nextWeek)) {
                    System.out.println("Pet: " + pet.getName() + " | " + appt);
                    upcomingFound = true;
                }
            }
        }
        if (!upcomingFound) System.out.println("None");

        System.out.println("\nPets overdue for a vet visit (no vet visit in last 6 months):");
        boolean overdueFound = false;
        LocalDate sixMonthsAgo = LocalDate.now().minusMonths(6);

        for (Pet pet : pets) {
            boolean vetVisitedRecently = false;
            for (Appointment appt : pet.getAppointments()) {
                if (appt.getAppointmentType().equalsIgnoreCase("Vet Visit")
                        && appt.getDateTime().toLocalDate().isAfter(sixMonthsAgo)) {
                    vetVisitedRecently = true;
                    break;
                }
            }
            if (!vetVisitedRecently) {
                System.out.println("Pet: " + pet.getName() + " | Last vet visit: more than 6 months ago or none");
                overdueFound = true;
            }
        }
        if (!overdueFound) System.out.println("None");
    }

    // ------------------ Store Data ------------------
    private static void storeData() {
        try (ObjectOutputStream petOut = new ObjectOutputStream(new FileOutputStream(PET_FILE));
             ObjectOutputStream apptOut = new ObjectOutputStream(new FileOutputStream(APPOINTMENT_FILE))) {

            petOut.writeObject(pets);
            apptOut.writeObject(appointments);

            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    // ------------------ Load Data ------------------
   @SuppressWarnings("unchecked")
private static void loadData() {
    File file = new File(PET_FILE); // e.g., pets.dat
    if (!file.exists()) return;

    try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
        pets = (List<Pet>) in.readObject(); // appointments are already inside pets
        System.out.println("Existing data loaded successfully.");
    } catch (IOException | ClassNotFoundException e) {
        System.out.println("Error loading data: " + e.getMessage());
    }
}

}