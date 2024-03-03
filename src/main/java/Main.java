import java.io.*;
import java.util.Scanner;
// Import any necessary libraries for hashing

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Assuming proper imports and error handling are implemented elsewhere
        String firstName = promptForName("first");
        String lastName = promptForName("last");
        int firstInt = promptForInt();
        int secondInt = promptForInt();
        String inputFile = promptForFileName("input");
        String outputFile = promptForFileName("output");
        // Implement password handling logic here
        // Writing to output file
        try (PrintWriter writer = new PrintWriter(outputFile)) {
            writer.println("First name: " + firstName);
            writer.println("Last name: " + lastName);
            writer.println("First Integer: " + firstInt);
            writer.println("Second Integer: " + secondInt);
            writer.println("Sum: " + (firstInt + secondInt));
            writer.println("Product: " + ((long) firstInt * secondInt));
            // Read from input file and write its contents
            writeInputFileContents(writer, inputFile);
        } catch (FileNotFoundException e) {
            System.err.println("Error writing to file: " + e.getMessage());
            // Log to error file
        }
    }

    private static String promptForName(String whichName) {
        System.out.println("Enter your " + whichName + " name (letters only, up to 50 characters): ");
        String name = scanner.nextLine();
        // Add validation logic
        return name;
    }

    private static int promptForInt() {
        System.out.println("Enter an integer value (-2,147,483,648 to 2,147,483,647): ");
        int value = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over
        // Add validation logic
        return value;
    }

    private static String promptForFileName(String whichFile) {
        System.out.println("Enter the name of the " + whichFile + " file: ");
        return scanner.nextLine();
        // Add validation logic
    }

    private static void writeInputFileContents(PrintWriter writer, String inputFile) {
        try (Scanner fileScanner = new Scanner(new File(inputFile))) {
            writer.println("Input file contents:");
            while (fileScanner.hasNextLine()) {
                writer.println(fileScanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.err.println("Input file not found: " + e.getMessage());
            // Log to error file
        }
    }

    // I will need to implement password handling and hashing logic here
}
