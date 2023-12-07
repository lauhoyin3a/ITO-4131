public class Display {

  public Display() {

  }

  public static void displayGrid(Field field) {
    int gridCount = 0;
    StringBuilder colNumber = new StringBuilder();
    for (int k = 0; k < field.getGridCol(); k++){
      colNumber.append("        " + k + "      ");
    }
    System.out.println(colNumber);
    for (int i = 0; i < field.getGridRow(); i++) {
        StringBuilder displayString = new StringBuilder();
        displayString.append(i + " ");
        for (int j = 0; j < field.getGridCol(); j++) {
            Grid grid = field.getSpecificGrid(i, j);
            String temp = grid.toString();
            displayString.append(temp + " ");
        }
        System.out.println(displayString);
    }
  }

  public static void displayMenu() {
    System.out.println("Press 1 to capture a grid spot");
    System.out.println("Press 2 to sabotage the enemy");
    System.out.println("Press 3 to direct strike a heart");
    System.out.print("Please Enter 1-3: ");
  }

  public static void displayPlayerStatus(Field field){
    Computer computer = field.getComputerPlayer();
    Human player = field.getHumanPlayer();
    System.out.println(player.toString());
    System.out.println(computer.toString());
  }

}
