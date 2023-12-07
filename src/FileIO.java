import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class FileIO {

  private final static String boostFileName = "boosts.txt";
  private final static String outcomeFileName = "outcome.txt";

  public FileIO() {

  }

  public String getBoostFileName() {
    return boostFileName;
  }

  public String getOutComeFileName(){
    return outcomeFileName;
  }

  public String readFileContents() throws FileNotFoundException {
    StringBuilder fileContent = new StringBuilder();
    try (FileReader reader = new FileReader(boostFileName);
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

  public void writeToFile(String content) throws IOException {
    try (FileWriter writer = new FileWriter(outcomeFileName)) {
      writer.write(content);
    } catch (IOException e) {
      throw e;
    } catch (Exception e) {
      throw new RuntimeException("Error in writing to file! Exiting...", e);
    }
  }

}
