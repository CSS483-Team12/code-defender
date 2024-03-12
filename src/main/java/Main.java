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

        // Input file name validation and existence check
        String inputFileName;
        do {
            System.out.println("Enter the input file name (without specifying '.txt'):");
            inputFileName = scanner.nextLine();
            if (!fileProcessor.isValidFileName(inputFileName)) {
                System.out.println("Invalid file name. Please enter a valid name (alphanumeric, underscores, hyphens):");
                continue;
            }
            inputFileName += ".txt"; // Append .txt extension for processing
            File inputFile = new File(inputFileName);
            if (!inputFile.exists()) {
                System.out.println("The input file does not exist: " + inputFileName + ". Please enter a different file name.");
                inputFileName = null; // Reset to null to trigger reprompt
            }
        } while (inputFileName == null);

        // Output file name validation and existence check
        String outputFileName;
        do {
            System.out.println("Enter the output file name (without specifying '.txt'):");
            outputFileName = scanner.nextLine();
            if (!fileProcessor.isValidFileName(outputFileName)) {
                System.out.println("Invalid file name. Please enter a valid name (alphanumeric, underscores, hyphens):");
                continue;
            }
            outputFileName += ".txt"; // Append .txt extension for processing
            File outputFile = new File(outputFileName);
            if (outputFile.exists()) {
                System.out.println("The output file already exists. Please choose a different file name.");
                outputFileName = null; // Reset for reprompting
            }
        } while (outputFileName == null);

        // Save input file content to output file
        fileProcessor.saveInputToFile(inputFileName, outputFileName);

        // Password setup and verification
        passwordManager.handlePasswordSetup(scanner);

        System.out.println("All operations completed successfully.");

        scanner.close();
    }
}
