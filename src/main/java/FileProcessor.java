/**
 * code-defender
 * @author Griffin Ryan (glryan@uw.edu)
 * @author Tony Le ()
 **/
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileProcessor {
    private PrintWriter writer;

    public boolean isValidFileName(String fileNameBase) {
        String regexPattern = "^[\\w-]+$";
        return fileNameBase.matches(regexPattern);
    }

    public void copyFileToNewFile(String inputBaseFilePath, String outputBaseFilePath) {
        if (!isValidFileName(inputBaseFilePath) || !isValidFileName(outputBaseFilePath)) {
            System.err.println("Invalid file name. File names should only contain letters, numbers, underscores, or hyphens.");
            return;
        }

        String inputFilePath = ensureTxtExtension(inputBaseFilePath);
        String outputFilePath = ensureTxtExtension(outputBaseFilePath);

        try {
            Files.copy(Paths.get(inputFilePath), Paths.get(outputFilePath), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File has been successfully copied to " + outputFilePath);
        } catch (IOException e) {
            System.err.println("An error occurred while copying the file: " + e.getMessage());
        }
    }

    private String ensureTxtExtension(String fileName) {
        return fileName.replaceAll("\\.txt$", "") + ".txt";
    }

    // Adding method to save content from input file to output file
    public void saveInputToFile(String inputBaseFilePath, String outputBaseFilePath) {
        if (!isValidFileName(inputBaseFilePath) || !isValidFileName(outputBaseFilePath)) {
            System.err.println("Invalid file name. Please ensure the file names contain only letters, numbers, underscores, or hyphens.");
            return;
        }

        String inputFilePath = ensureTxtExtension(inputBaseFilePath);
        String outputFilePath = ensureTxtExtension(outputBaseFilePath);

        try {
            // Ensuring the input file exists
            File inputFile = new File(inputFilePath);
            if (!inputFile.exists()) {
                System.err.println("The input file does not exist: " + inputFilePath);
                return;
            }

            // Reading content from the input file and writing it to the output file
            Files.copy(Paths.get(inputFilePath), Paths.get(outputFilePath), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Content from " + inputFilePath + " has been successfully saved to " + outputFilePath);
        } catch (IOException e) {
            System.err.println("An error occurred while saving content to the file: " + e.getMessage());
        }
    }

    // Other methods as needed...
}
