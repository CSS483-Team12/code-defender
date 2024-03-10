/**
 * code-defender
 * @author Griffin Ryan (glryan@uw.edu)
 * @author Tony Le ()
 **/    
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InputHandler inputHandler = new InputHandler();
        PasswordManager passwordManager = new PasswordManager();
        FileProcessor fileProcessor = new FileProcessor();

        System.out.println("Welcome to the Secure App!");

        // User input collection process
        String firstName = inputHandler.readValidName(scanner, "first name");
        String lastName = inputHandler.readValidName(scanner, "last name");
        int firstInt = inputHandler.readIntValue(scanner, "Enter the first integer: ");
        int secondInt = inputHandler.readIntValue(scanner, "Enter the second integer: ");
        String inputFileName = inputHandler.readValidFileName(scanner, "input");
        String outputFileName = inputHandler.readValidFileName(scanner, "output");

        // Password setup and verification
        passwordManager.handlePasswordSetup(scanner);

        // Assuming handlePasswordSetup includes the entire process:
        // 1. User sets a password, which is then hashed and stored with a salt.
        // 2. User is prompted to re-enter the password for verification.

        // File operations
        if (fileProcessor.canReadFile(inputFileName)) {
            fileProcessor.openFile(outputFileName);
            fileProcessor.writeLine("First Name: " + firstName);
            fileProcessor.writeLine("Last Name: " + lastName);
            fileProcessor.writeResults(firstInt, secondInt);
            fileProcessor.copyInputFileContents(inputFileName);
            fileProcessor.closeFile();
            System.out.println("All operations completed successfully.");
        } else {
            System.out.println("Input file cannot be read. Ensure the file exists and is accessible.");
        }

        scanner.close();
    }
}
