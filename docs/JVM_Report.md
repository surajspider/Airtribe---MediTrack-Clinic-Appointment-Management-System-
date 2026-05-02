# JVM Architecture & Working Principles

This report provides a concise overview of the Java Virtual Machine (JVM) internals and the concept of platform independence in Java.

## 1. Class Loader Subsystem
The Class Loader is responsible for loading, linking, and initializing class files (`.class`) when a Java program starts.
- **Loading:** Reads `.class` files and generates corresponding binary data in the Method Area.
- **Linking:** Verifies bytecode for security, allocates memory for static variables (Preparation), and replaces symbolic references with direct references (Resolution).
- **Initialization:** Assigns original values to static variables and executes static blocks (like the one demonstrated in `Constants.java`).

## 2. Runtime Data Areas
When a program executes, the JVM allocates memory in different areas:
- **Method Area:** Stores class-level data (method code, static variables, runtime constant pool).
- **Heap Area:** Stores all objects and their instance variables. It is subject to Garbage Collection.
- **Stack Area:** Every thread has a private JVM stack containing frames for method calls, local variables, and partial results.
- **PC Register:** Holds the address of the currently executing JVM instruction. Each thread has its own PC register.
- **Native Method Stack:** Used for native methods written in C/C++.

## 3. Execution Engine
The Execution Engine reads the bytecode from the Method Area and executes it. It contains:
- **Interpreter:** Reads bytecode instructions one by one and translates them into machine code. It's fast to start but slower to execute repetitive code.
- **JIT (Just-In-Time) Compiler:** Overcomes interpreter drawbacks by compiling entire methods into native machine code if they are executed frequently (hotspots), drastically improving execution speed.
- **Garbage Collector:** Automatically frees memory by destroying objects with no active references.

## 4. "Write Once, Run Anywhere" (WORA)
Java's platform independence is achieved through bytecode. The Java Compiler (`javac`) translates source code into intermediate bytecode (`.class`). This bytecode is platform-independent. Each operating system has a specific JVM implementation (Windows JVM, Mac JVM, Linux JVM) that translates this bytecode into the local machine's native code. Thus, the exact same `.class` file can be executed without recompilation on any platform equipped with a JRE.
