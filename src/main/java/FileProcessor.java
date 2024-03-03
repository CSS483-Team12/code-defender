import java.io.*;

public class FileProcessor {

    public void writeOutputFile(String fileName, String content) throws IOException {
        try (FileWriter fw = new FileWriter(fileName);
            BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(content);
        } catch (IOException e) {
            System.err.println("Error writing to output file: " + e.getMessage());
            throw e; // Rethrow exception to handle it in the calling method or log it
        }
    }

    public String readInputFile(String fileName) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (FileReader fr = new FileReader(fileName); BufferedReader br = new BufferedReader(fr)) {
            String line;
            while ((line = br.readLine()) != null) {
                contentBuilder.append(line).append(System.lineSeparator());
            }
        } catch (FileNotFoundException e) {
            System.err.println("Input file not found: " + e.getMessage());
            throw e;
        } catch (IOException e) {
            System.err.println("Error reading input file: " + e.getMessage());
            throw e;
        }
        return contentBuilder.toString();
    }

    public void storePassword(String fileName, String[] passwordAndSalt) throws IOException {
        try (FileWriter fw = new FileWriter(fileName, true); // Append mode
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write("Password Hash: " + passwordAndSalt[0] + " Salt: " + passwordAndSalt[1]);
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Error writing password to file: " + e.getMessage());
            throw e; // Rethrow exception for external handling
        }
    }
}
