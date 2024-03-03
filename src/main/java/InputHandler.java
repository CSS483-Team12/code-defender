import java.util.Scanner;

public class InputHandler {
    private Scanner scanner;

    public InputHandler() {
        this.scanner = new Scanner(System.in);
    }

    public String readValidName(String nameType) {
        System.out.println("Please enter your " + nameType + " (at most 50 characters, letters only):");
        while (!scanner.hasNext("[A-Za-z]{1,50}")) {
            System.out.println("Invalid " + nameType + ". Please enter letters only, up to 50 characters:");
            scanner.next(); // Consume the invalid input
        }
        return scanner.next();
    }

    public int readValidInt() {
        System.out.println("Enter an integer value (range: " + Integer.MIN_VALUE + " to " + Integer.MAX_VALUE + "):");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid integer. Please enter a value between " + Integer.MIN_VALUE + " and " + Integer.MAX_VALUE + ":");
            scanner.next(); // Consume the invalid input
        }
        return scanner.nextInt();
    }

    public String readValidFileName(String fileType) {
        System.out.println("Please enter the name of the " + fileType + " file (use \".\" for file extensions):");
        while (true) {
            String fileName = scanner.nextLine();
            // Validate that the file name contains only valid characters and has at least one character before the dot and an extension
            if (fileName.matches("[a-zA-Z0-9-_]+\\.?[a-zA-Z0-9-_]*") && !fileName.endsWith(".")) {
                return fileName;
            } else {
                System.out.println("Invalid file name. Please make sure the file name does not end with a dot and contains only letters, numbers, hyphens, or underscores:");
            }
        }
    }

    public String[] readAndVerifyPassword() {
        System.out.println("Please enter a password:");
        String password = scanner.next();
        // Simulate hashing and salting
        String salt = "randomSalt"; // In practice, generate a real, random salt
        String hashedPassword = hashPassword(password, salt);

        // Store the hashed password and salt (in reality, you'd write these to a secure storage)
        // For demonstration, we're just returning these values
        System.out.println("Please re-enter your password for verification:");
        String verifyPassword = scanner.next();
        String verifyHashedPassword = hashPassword(verifyPassword, salt);

        // Verify the hashed passwords match
        while (!hashedPassword.equals(verifyHashedPassword)) {
            System.out.println("Passwords do not match. Please try again.");
            System.out.println("Please enter a password:");
            password = scanner.next();
            // Hash and salt again for comparison
            hashedPassword = hashPassword(password, salt);

            System.out.println("Please re-enter your password for verification:");
            verifyPassword = scanner.next();
            verifyHashedPassword = hashPassword(verifyPassword, salt);
        }

        // Return the hashed password and salt
        return new String[]{hashedPassword, salt};
    }

    private String hashPassword(String password, String salt) {
        // Placeholder for hashing logic
        // In practice, use a secure hashing algorithm like SHA-256, with the password and salt
        return password + salt; // Simplified for demonstration
    }

}