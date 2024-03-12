/**
 * code-defender
 * @author Griffin Ryan (glryan@uw.edu)
 * @author Tony Le ()
 **/
import java.util.Scanner;

public class InputHandler {

    // Read and validate a name
    public String readValidName(Scanner scanner, String nameType) {
        System.out.println("Enter your " + nameType + " (1-50 characters, must start each word with a capital letter, letters, apostrophes, and hyphens allowed): ");
        String input = scanner.nextLine().trim();

        // Update the regex pattern to enforce starting each word with an uppercase letter
        // This pattern matches names that start with an uppercase letter and may contain lowercase letters,
        // apostrophes, and hyphens afterwards. Each segment (word) of the name must also start with an uppercase letter.
        String pattern = "(?U)\\b[A-Z][A-Za-z'-]*\\b(\\s+[A-Z][A-Za-z'-]*)*";

        while (!input.matches(pattern)) {
            System.out.println("Invalid input. Names must start with a capital letter and be 1-50 characters long. Please try again: ");
            input = scanner.nextLine().trim();
        }
        return input;
    }

    // Read and validate an integer value
    public int readIntValue(Scanner scanner, String prompt) {
        System.out.println(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("That's not a valid integer. Please try again: ");
            scanner.next(); // consume the non-integer input
        }
        return scanner.nextInt();
    }

    // Read and validate a file name
    public String readValidFileName(Scanner scanner, String fileType) {
        System.out.println("Enter the name of the " + fileType + " file (must end in .txt): ");
        String fileName = scanner.next().trim();
        while (!fileName.matches("^[\\w,\\s-]+\\.txt$")) {
            System.out.println("Invalid file name. Please ensure the file name ends with .txt and try again: ");
            fileName = scanner.next().trim();
        }
        return fileName;
    }
}
