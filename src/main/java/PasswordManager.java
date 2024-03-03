import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Scanner;

public class PasswordManager {
    private static final String PASSWORD_FILE = "password.bin";

    public void handlePasswordFlow() throws NoSuchAlgorithmException, IOException {
        Scanner scanner = new Scanner(System.in);
        boolean verified = false;

        while (!verified) {
            System.out.println("Please enter a new password:");
            String password = scanner.nextLine();

            // Hashing the password with a salt
            String[] hashedPasswordAndSalt = hashPassword(password);

            // Storing the hashed password and salt
            storePassword(hashedPasswordAndSalt);

            System.out.println("Please re-enter your password for verification:");
            String verifyPassword = scanner.nextLine();

            // Verifying the password
            verified = verifyPassword(verifyPassword);
            if (!verified) {
                System.out.println("Passwords do not match. Please try again.");
            } else {
                System.out.println("Password verified successfully.");
            }
        }
    }

    private String[] hashPassword(String password) throws NoSuchAlgorithmException {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(salt);
        byte[] hashedPassword = md.digest(password.getBytes());
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String encodedHash = Base64.getEncoder().encodeToString(hashedPassword);
        return new String[]{encodedHash, encodedSalt};
    }

    private void storePassword(String[] passwordAndSalt) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(PASSWORD_FILE))) {
            dos.writeUTF(passwordAndSalt[0]); // Write the hashed password
            dos.writeUTF(passwordAndSalt[1]); // Write the salt
        }
    }

    private boolean verifyPassword(String inputPassword) throws NoSuchAlgorithmException, IOException {
        try (DataInputStream dis = new DataInputStream(new FileInputStream(PASSWORD_FILE))) {
            String storedHash = dis.readUTF();
            String storedSalt = dis.readUTF();
            byte[] salt = Base64.getDecoder().decode(storedSalt);
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] hashedInputPassword = md.digest(inputPassword.getBytes());
            String encodedInputHash = Base64.getEncoder().encodeToString(hashedInputPassword);
            return storedHash.equals(encodedInputHash);
        }
    }
}
