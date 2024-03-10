/**
 * code-defender
 * @author Griffin Ryan (glryan@uw.edu)
 * @author Tony Le ()
 **/
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Scanner;

public class PasswordManager {

    private static final String PASSWORD_FILE = "passwords.txt";

    // Generate a salt
    private byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    // Hash the password with a salt
    private String hashPassword(String password, byte[] salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hashedPassword);
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Failed to hash password: " + e.getMessage());
            return null;
        }
    }

    // Store the hashed password and salt
    private void storePassword(String hashedPassword, byte[] salt) {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(PASSWORD_FILE))) {
            dos.writeUTF(Base64.getEncoder().encodeToString(salt)); // Store salt
            dos.writeUTF(hashedPassword); // Store hashed password
        } catch (IOException e) {
            System.err.println("Error storing the password: " + e.getMessage());
        }
    }

    // Initiate the password setup and verification process
    public void handlePasswordSetup(Scanner scanner) {
        System.out.println("You need to set up a password.");
        String password = readPassword(scanner, "Enter a new password: ");
        byte[] salt = generateSalt();
        String hashedPassword = hashPassword(password, salt);
        storePassword(hashedPassword, salt);

        boolean verified = false;
        while (!verified) {
            String reenteredPassword = readPassword(scanner, "Re-enter your password for verification: ");
            String newHashedPassword = hashPassword(reenteredPassword, salt);
            verified = newHashedPassword.equals(hashedPassword);
            if (!verified) {
                System.out.println("Passwords do not match. Please try again.");
            }
        }
        System.out.println("Password verified and stored securely.");
    }

    // Read a password from the user
    private String readPassword(Scanner scanner, String prompt) {
        System.out.println(prompt);
        return scanner.nextLine();
    }
}
