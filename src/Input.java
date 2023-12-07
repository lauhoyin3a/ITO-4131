import java.util.*;

public class Input {

  public Input() {

  }

  public static int acceptIntegerInput(String string) {
    Scanner scanner = new Scanner(System.in);
    int result = 0;
    while (true){
    try{
      result = Integer.parseInt(string);
      break;
    }
    catch (NumberFormatException e){
      System.out.println("Please enter a number!");
      string = scanner.nextLine();
    }
    }
    
    return result;
  }

  public static int[] inputGridSize() {

    Scanner scanner = new Scanner(System.in);
    //System.out.println(human.toString());
    System.out.println("Enter the grid size for field game ");
    int rowSize;
    while (true) {
      try {
        System.out.print("Enter row size: ");
        rowSize = Input.acceptIntegerInput(scanner.nextLine());

        if (Validation.numberWithinRange(rowSize, 3, 10)) {
          break;
        }
      } catch (NumberFormatException e) {
        System.out.println("Input must be a number");
      }
      System.out.println("Input invalid, must be 3-10");
    }
    int colSize;
    while (true) {

      try {
        System.out.print("Enter col size: ");
        colSize = Input.acceptIntegerInput(scanner.nextLine());

        if (Validation.numberWithinRange(colSize, 3, 10)) {
          break;
        }

      } catch (NumberFormatException e) {
        System.out.println("Input must be a number");

      }

      System.out.println("Input invalid, must be 3-10");

    }
    int[] rowCol = new int[2];
    rowCol[0] = rowSize;
    rowCol[1] = colSize;
    return rowCol;
  }

  public static int[] inputRowCol(int currentRow, int currentColumn) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Please Enter the grid column number: ");
    int column = Input.acceptIntegerInput(scanner.nextLine());
    while (!Validation.numberWithinRange(column, 0, currentColumn - 1)) {
      System.out.println("Please enter a valid Column number");
      column = Input.acceptIntegerInput(scanner.nextLine());
    }
    System.out.println("Please Enter the grid row number: ");
    int row = Input.acceptIntegerInput(scanner.nextLine());
    while (!Validation.numberWithinRange(row, 0, currentRow - 1)) {
      System.out.println("Please enter a valid Row number");
      row = Input.acceptIntegerInput(scanner.nextLine());
    }
    int[] rowCol = new int[2];
    rowCol[0] = row;
    rowCol[1] = column;
    return rowCol;
  }

  public static String inputUserName() {

    Scanner scanner = new Scanner(System.in);
    System.out.print("Please enter your name: ");
    String userName = scanner.nextLine();
    while (!Validation.lengthWithinRange(userName, 3, 12)) {
      System.out.print("Name length must be within 3 to 12!\nPlease enter your name: ");
      userName = scanner.nextLine();
    }
    return userName;
  }

}
