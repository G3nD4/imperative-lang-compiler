package Helpers;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class RunJasmin {

    public static final String HEADER =
            ".class public MyProgram\n" +
                    ".super java/lang/Object\n" +
                    "\n" +
                    "; Main method\n" +
                    ".method public static main([Ljava/lang/String;)V\n" +
                    "    getstatic java/lang/System/out Ljava/io/PrintStream;\n" +
                    "    .limit stack 100                ; Limit the stack usage\n" +
                    "    .limit locals 100               ; Limit the local variables\n" +
                    "\n";

    public static final String FOOTER =
            ".end method\n";

    public static void runJasminProgram(String jasminFileName) {
        // Compile the .j file to .class using Jasmin assembler
        try {
            // Compile Jasmin file
            ProcessBuilder generateClass = new ProcessBuilder
                    ("java", "-jar",
                            "C:\\Users\\HUAWEI\\Downloads\\jasmin-2.4\\jasmin.jar", "C:\\Users\\HUAWEI\\Downloads\\jasmin-2.4\\" + jasminFileName);
            Process p = generateClass.start();
            p.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Run the generated .class file using Java command and capture the output
        try {
            // Run the compiled program
            ProcessBuilder byteCodeGeneration = new ProcessBuilder("java", "C:\\Users\\HUAWEI\\Downloads\\jasmin-2.4\\MyProgram");
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
