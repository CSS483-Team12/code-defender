public class Main {
    public static void main(String[] args) {
        InputHandler inputHandler = new InputHandler();
        FileProcessor fileProcessor = new FileProcessor();
        PasswordManager passwordManager = new PasswordManager();

        try {
            // Collect user's first and last names
            String firstName = inputHandler.readValidName("first name");
            String lastName = inputHandler.readValidName("last name");

            // Prompt for and read the names of the input and output files
            String inputFileName = inputHandler.readValidFileName("input");
            String outputFileName = inputHandler.readValidFileName("output");

            // Reading input file contents
            String inputFileContents = "";
            try {
                inputFileContents = fileProcessor.readInputFile(inputFileName);
            } catch (Exception e) {
                System.err.println("Failed to read from input file: " + e.getMessage());
                // Handle error, possibly continue to password step or retry
            }

            // Handle the password input, hash, store, and verify at the end
            passwordManager.handlePasswordFlow();

            // Prompt for and read two int values
            int firstInt = inputHandler.readValidInt();
            int secondInt = inputHandler.readValidInt();

            // Preparing content for output file
            String outputContent = String.format("First Name: %s\nLast Name: %s\nFirst Integer: %d\nSecond Integer: %d\nSum: %d\nProduct: %d\nInput File Name: %s\nInput File Contents:\n%s",
                    firstName, lastName, firstInt, secondInt, firstInt + secondInt, (long) firstInt * secondInt, inputFileName, inputFileContents);

            // Writing to the output file
            fileProcessor.writeOutputFile(outputFileName, outputContent);

            System.out.println("Operation completed successfully.");
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            // Depending on your application's error handling policy, you might want to log this error or take specific actions.
        }
    }
}
