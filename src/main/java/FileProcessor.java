/**
 * code-defender
 * @author Griffin Ryan (glryan@uw.edu)
 * @author Tony Le ()
 **/
import java.io.*;

public class FileProcessor {
    private PrintWriter writer;

    // Open a file for writing
    public void openFile(String fileName) {
        try {
            writer = new PrintWriter(new FileWriter(fileName, true)); // Append mode
        } catch (IOException e) {
            System.err.println("An error occurred while opening the file: " + e.getMessage());
        }
    }

    // Write a single line to the file
    public void writeLine(String line) {
        if (writer != null) {
            writer.println(line);
        }
    }

    // Write calculation results
    public void writeResults(int firstInt, int secondInt) {
        writeLine("Sum: " + (firstInt + secondInt));
        writeLine("Product: " + (firstInt * secondInt));
    }

    // Copy input file contents to the output file
    public void copyInputFileContents(String inputFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                writeLine(line);
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading the input file: " + e.getMessage());
        }
    }

    // Close the file
    public void closeFile() {
        if (writer != null) {
            writer.close();
        }
    }

    // Check if a file can be read
    public boolean canReadFile(String fileName) {
        File file = new File(fileName);
        return file.exists() && file.canRead();
    }
}
