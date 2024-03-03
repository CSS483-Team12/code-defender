import java.io.*;

public class Main {
    public static void main(String[] args) {
        InputHandler inputHandler = new InputHandler();
        FileProcessor fileProcessor = new FileProcessor();
        PasswordManager passwordManager = new PasswordManager();

        try {
            // Prompt for and read the user's first and last names
            String firstName = inputHandler.readValidName("first name");
            String lastName = inputHandler.readValidName("last name");

            // Prompt for and read two int values
            int firstInt = inputHandler.readValidInt();
            int secondInt = inputHandler.readValidInt();

            // Prompt for and read the names of the input and output files
            String inputFileName = inputHandler.readValidFileName("input");
            String outputFileName = inputHandler.readValidFileName("output");

            // Prompt for and handle password input
            String[] passwordAndSalt = inputHandler.readAndVerifyPassword();

            // Read the contents of the input file
            String inputFileContents = fileProcessor.readInputFile(inputFileName);

            // Prepare content to write to the output file
            String outputContent = String.format("First Name: %s\nLast Name: %s\nFirst Integer: %d\nSecond Integer: %d\nSum: %d\nProduct: %d\nInput File Name: %s\nInput File Contents:\n%s",
                    firstName, lastName, firstInt, secondInt, firstInt + secondInt, firstInt * secondInt, inputFileName, inputFileContents);

            // Write to the output file
            fileProcessor.writeOutputFile(outputFileName, outputContent);

            // Securely store the hashed password
            fileProcessor.storePassword("passwords.txt", passwordAndSalt);

            System.out.println("Operation completed successfully.");
        } catch (Exception e) {
            Logger.logError(e.getMessage());
            System.err.println("An error occurred. Please check the error log for details.");
        }
    }
}
