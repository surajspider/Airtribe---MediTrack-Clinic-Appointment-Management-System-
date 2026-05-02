# MediTrack Setup Instructions

Follow these steps to set up and run the MediTrack Clinic Management System.

## Prerequisites
- **JDK 8 or higher**: You need the Java Development Kit installed.

## 1. Verify Java Installation
Open your terminal or command prompt and run:
```bash
java -version
javac -version
```
*Screenshot Placeholder: Insert screenshot showing Java version output here.*

If Java is not installed, download it from the [Oracle Website](https://www.oracle.com/java/technologies/downloads/) and add the `bin` directory to your system's `PATH`.

## 2. Compile the Project
Navigate to the root directory of the project. Then, move to the `src/main/java` directory and compile all Java files:
```bash
cd src/main/java
javac com/airtribe/meditrack/**/*.java com/airtribe/meditrack/*.java
```

## 3. Run the Application
You can run the interactive Console UI by executing the `Main` class.

**Start fresh:**
```bash
java com.airtribe.meditrack.Main
```

**Load existing data (if saved previously):**
```bash
java com.airtribe.meditrack.Main --loadData
```

## 4. Run Manual Tests
To execute the manual tests (verifying Cloning, Serialization, etc.), run:
```bash
java com.airtribe.meditrack.test.TestRunner
```
*Screenshot Placeholder: Insert screenshot showing the TestRunner console output.*
