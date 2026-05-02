# MediTrack - Clinic & Appointment Management System

MediTrack is a modular, object-oriented Clinic Management System built in Core Java. It models the core entities of a medical facility, such as Patients, Doctors, Appointments, and Bills, providing a menu-driven console interface for interaction.

## Features Included
- **Core OOP Principles**: Inheritence, Encapsulation, Polymorphism, Abstraction.
- **Advanced OOP**: Deep Cloning, Immutable classes (`BillSummary`), Enums.
- **Design Patterns**: Singleton (`IdGenerator`), Factory (`BillFactory`), Observer (`ConsoleNotificationObserver`).
- **Java 8+ Streams**: Complex analytics and filtering.
- **Persistence**: File I/O with Java Serialization to save and load state.

## Project Structure
- `src/main/java/com/airtribe/meditrack/` - The base package containing the codebase.
- `docs/` - Contains the JVM report, Setup Instructions, and Design Decisions.

## How to Run
Navigate to `src/main/java` and compile:
```bash
javac com/airtribe/meditrack/**/*.java com/airtribe/meditrack/*.java
```

Start the application:
```bash
java com.airtribe.meditrack.Main
```

Load with existing data:
```bash
java com.airtribe.meditrack.Main --loadData
```

Run manual tests:
```bash
java com.airtribe.meditrack.test.TestRunner
```

For more comprehensive setup details, please review `docs/Setup_Instructions.md`.
