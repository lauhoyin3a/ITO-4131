import java.io.FileReader;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileIO {

    private final static String fileName = "boosts.txt";

    public FileIO() {

    }

    public String readFileContents() throws FileNotFoundException {
        StringBuilder fileContent = new StringBuilder();
        try (FileReader reader = new FileReader(fileName);
             Scanner fileInput = new Scanner(reader)) {

            while (fileInput.hasNextLine()) {
                fileContent.append(fileInput.nextLine()).append("\n");
            }
        } catch (FileNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error in reading from file! Exiting...", e);
        }
        return fileContent.toString();
    }

    public String getFileName() {
        return fileName;
    }

    public static void main(String[] args) {
        FileIO fileReader = new FileIO();
        try {
            System.out.println(fileReader.readFileContents());
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileReader.getFileName());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}