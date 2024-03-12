/**
 * code-defender
 * @author Griffin Ryan (glryan@uw.edu)
 * @author Tony Le ()
 **/
import java.util.Scanner;
import java.io.File;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InputHandler inputHandler = new InputHandler();
        PasswordManager passwordManager = new PasswordManager();
        FileProcessor fileProcessor = new FileProcessor();

        System.out.println("Welcome to code-defender, by Griffin Ryan and Tony Le!");
        System.out.println("Note: Please provide file names without extensions. Only alphanumeric characters, underscores, and hyphens are allowed. The application only processes .txt files.");

        // User input for first and last names, ensuring capitalization
        String firstName = inputHandler.readValidName(scanner, "first name");
        String lastName = inputHandler.readValidName(scanner, "last name");

        // User input for two integer values
        System.out.println("Enter the first integer (range -2,147,483,648 to 2,147,483,647):");
        int firstInt = inputHandler.readIntValue(scanner, "Enter the first integer: ");
        System.out.println("Enter the second integer (range -2,147,483,648 to 2,147,483,647):");
        int secondInt = inputHandler.readIntValue(scanner, "Enter the second integer: ");
        scanner.nextLine(); // consuming the lingering new line character.

        // Now when prompting for the file name, the input should be properly awaited
        String inputFileName;
        do {
            System.out.println("Enter the input file name (without specifying '.txt'):");
            inputFileName = scanner.nextLine().trim();
            if (!fileProcessor.isValidFileName(inputFileName)) {
                System.out.println("Invalid file name. Please enter a valid name (alphanumeric, underscores, hyphens):");
                continue;
            }
            inputFileName += ".txt"; // Append .txt extension for processing
            File inputFile = new File(inputFileName);
            if (!inputFile.exists()) {
                System.out.println("The input file does not exist: " + inputFileName + ". Please enter a different file name.");
                inputFileName = null; // Reset to null to trigger re-prompt
            }
        } while (inputFileName == null);

        String outputFileName;
        do {
            System.out.println("Enter the output file name (without specifying '.txt'), ensuring it does not already exist: ");
            outputFileName = scanner.nextLine().trim();

            // Check if the file name is valid and the file does not exist
            if (fileProcessor.isValidFileName(outputFileName)) {
                outputFileName += ".txt"; // Append .txt for the actual file name check
                File outputFile = new File(outputFileName);

                if (outputFile.exists()) {
                    System.out.println("The file '" + outputFileName + "' already exists. Please enter a different name.");
                    outputFileName = null; // Reset for the condition to re-prompt
                } else {
                    // Valid and does not exist, break the loop
                    break;
                }
            } else {
                System.out.println("Invalid file name. File names should only contain letters, numbers, underscores, or hyphens. Please try again: ");
                outputFileName = null; // Ensure the loop continues due to invalid format
            }
        } while (outputFileName == null);

        // Proceed with saving the input file content to the output file
        fileProcessor.saveInputToFile(inputFileName, outputFileName); // Use the validated and checked file name here


        // Password setup and verification
        passwordManager.handlePasswordSetup(scanner);

        System.out.println("All operations completed successfully.");

        scanner.close();
    }
}
