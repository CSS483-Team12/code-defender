public class InputValidator {

    public static boolean isValidName(String name) {
        // Names should only contain letters, can include hyphens or apostrophes for compound names.
        // Length must be 50 characters or less.
        return name.matches("[a-zA-Z-' ]{1,50}");
    }

    public static boolean isValidInteger(String input) {
        try {
            // This will throw an exception if the input is not an integer or out of range
            int value = Integer.parseInt(input);
            // No need to check range here as parseInt ensures it's within int range
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isValidFileName(String fileName) {
        // Basic validation for file names. You might want to extend this depending on your requirements.
        return fileName.matches("[\\w,\\s-]+\\.[A-Za-z]{3}");
    }
    
}
