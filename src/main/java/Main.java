/**
 * code-defender
 * @author Griffin Ryan (glryan@uw.edu)
 * @author Tony Le ()
 **/
import java.io.File;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InputHandler inputHandler = new InputHandler();
        PasswordManager passwordManager = new PasswordManager();
        FileProcessor fileProcessor = new FileProcessor();

        System.out.println("Welcome to code-defender, by Griffin Ryan and Tony Le!");

        // User input collection process for names and integers
        String firstName = inputHandler.readValidName(scanner, "first name");
        String lastName = inputHandler.readValidName(scanner, "last name");
        int firstInt = inputHandler.readIntValue(scanner, "Enter the first integer: ");
        int secondInt = inputHandler.readIntValue(scanner, "Enter the second integer: ");

        scanner.nextLine(); // Consume any leftover newline

        // Informing about the default input file and asking for the input file name
        System.out.println("A default 'input.txt' file is available for use. You may also specify another file.");
        System.out.println("Enter the name of the input file you wish to use:");
        String inputFileName = scanner.nextLine();
        String inputFileExtension = getFileExtension(inputFileName);

        // Ensuring the output file has the same extension as the input file
        String outputFileName;
        do {
            System.out.println("Enter the name of the output file (must have the same extension '" + inputFileExtension + "'):");
            outputFileName = scanner.nextLine();
            if (!outputFileName.endsWith(inputFileExtension)) {
                System.out.println("The output file must have the same extension ('" + inputFileExtension + "') as the input file.");
            } else if (new File(outputFileName).exists()) {
                System.out.println("This file already exists. Please enter a different file name.");
            }
        } while (!outputFileName.endsWith(inputFileExtension) || new File(outputFileName).exists());

        fileProcessor.copyFileToNewFile(inputFileName, outputFileName);

        // Password setup and verification
        passwordManager.handlePasswordSetup(scanner);

        System.out.println("All operations completed successfully.");

        scanner.close();
    }

    // Utility method to extract the file extension from a file name
    private static String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
            return fileName.substring(dotIndex); // includes the dot
        }
        return ""; // No extension found
    }
}
