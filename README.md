# Pet Care Scheduler Console Application

## Overview
Paws & Whiskers is a console-based Java application designed to help pet owners keep track of their furry friends' health and care needs. The application allows users to register pets, schedule and track appointments such as vet visits, vaccinations, and grooming sessions, and maintain a history of past visits. All data is safely stored so it persists even after the program closes.

This application is ideal for small pet care services or individual pet owners who want a simple, organized way to manage multiple pets and their care schedules.

---

## Application Features
- Register pets with unique IDs and detailed profiles including:
  - Name, Species/Breed, Age
  - Owner name and contact info
  - Registration date
  - List of appointments
- Schedule appointments for pets:
  - Appointment types include vet visits, vaccinations, and grooming
  - Date and time of appointment
  - Optional notes
- Track upcoming appointments and view past appointment history
- Display pet records and appointment details
- Store data to files to ensure persistence across sessions
- Generate reports such as:
  - Pets with upcoming appointments in the next week
  - Pets overdue for a vet visit

---

## Core Concepts Used
- Object-Oriented Programming (OOP) and encapsulation
- Java Collections (`ArrayList`, `HashMap`) to manage pets and appointments
- Date and Time API for scheduling and tracking appointments
- File Input and Output (File I/O) for data persistence
- Exception handling for invalid inputs and file errors
- Console-based user interaction with menu-driven operations

---

## Project Structure
- `src/`
  - Java source files for Pet, Appointment, and main application logic
- `classes/`
  - Compiled `.class` files
- Data files
  - Stores pet profiles and appointment records

---

## How the Application Works
- Users interact with a menu-driven console interface
- Pets are registered with unique IDs and added to the system
- Appointments are scheduled and linked to the corresponding pet
- Upcoming and past appointments can be viewed at any time
- Reports provide insights into pets’ care schedules and overdue visits
- Data is persisted to files so that information is not lost when the application closes

---

## Requirements
- Java JDK 8 or higher
- Command-line or terminal access
- Any Java-compatible IDE or Cloud IDE

---

## How to Run

- Compile the project (output to `classes` folder):
- Run the application:
- Follow the on-screen menu options to register pets, schedule appointments, view records, and generate reports


