package Helpers;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class RunJasmin {

    public static void runJasminProgram(String jasminFileName) {
        // Compile the .j file to .class using Jasmin assembler
        try {
            // Compile Jasmin file
            ProcessBuilder generateClass = new ProcessBuilder(
                    "java", "-jar", "/path/to/jasmin.jar", "/path/to/" + jasminFileName);
            Process p = generateClass.start();
            p.waitFor();

            // Check for compilation errors
            if (p.exitValue() != 0) {
                System.err.println("Error compiling Jasmin file: " + jasminFileName);
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        // Run the generated .class file using Java command and capture the output
        try {
            // Run the compiled program
            ProcessBuilder byteCodeGeneration = new ProcessBuilder("java", "-cp", "/path/to/", "MyProgram");
            System.out.println("command: " + byteCodeGeneration.command());
            Process runProcess = byteCodeGeneration.start();

            // Capture and print the output from the Java process
            BufferedReader reader = new BufferedReader(new InputStreamReader(runProcess.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line); // Print the output from MyProgram
            }

            // Wait for the Java process to exit
            runProcess.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        RunJasmin.runJasminProgram("MyProgram.j");
    }
}