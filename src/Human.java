import java.util.Scanner;
import java.util.ArrayList;
public class Human extends Player {

  private String name;

  public Human() {
    super();
    this.name = "Human Player";
  }

  public Human(String name) {
    super();
    if(name == null || name.length() < 3 || name.length() > 12) {
    System.out.println("Name must be between 3-12 characters");
    System.out.println("Applying Default Name!");
    this.name = "Human Player";
    }
    
    else{
      this.name = name;
    }
  }

  public void action(Field field){
    Scanner scanner = new Scanner(System.in);
    int[] rowCol = new int[2];
    boolean hasCompletePath = this.hasCompletePath(field);
    Computer opponent = field.getComputerPlayer();
    int userChoice = Input.acceptIntegerInput(scanner.nextLine());
    boolean isValidChoice = false;
    if (hasCompletePath) {
      isValidChoice = Validation.numberWithinRange(userChoice, 1, 3);
      } else {
        isValidChoice = Validation.numberWithinRange(userChoice, 1, 2);
      }
    while (!isValidChoice) {
      if (userChoice == 3) {
        System.out.println("You dont have a Complete path!");

        }
        System.out.println("Please enter a valid number:");
        userChoice = Input.acceptIntegerInput(scanner.nextLine());
      if (hasCompletePath) {
          isValidChoice = Validation.numberWithinRange(userChoice, 1, 3);
        } else {
          isValidChoice = Validation.numberWithinRange(userChoice, 1, 2);
        }
      }
      if (userChoice == 1) {
        rowCol = Input.inputRowCol(field.getGridRow(), field.getGridCol());

        this.captureGrid(field, rowCol[0], rowCol[1]);
      } else if (userChoice == 2) {
        this.sabotageEnemy(field);
      } else if (userChoice == 3) {

        this.strikeHeart(opponent);
        System.out.println("Computer Heart: " + opponent.getHeart());

      }
  }

  public void decreaseOpponentAttack(Computer opponent) {
    Scanner scanner = new Scanner(System.in);
    int cost = RandomGenerator.generateNumberBetween(500, 1500);
    System.out.println("The Cost of decrement opponent's attack is " + cost + " coins.");
    if (!this.hasEnoughCoins(cost)){
      System.out.println("You dont have enough coins to do this operation!");
      return;
    }
    System.out.println("Press 1 to accept");
    System.out.println("Press 2 to reject");
    int choice = Input.acceptIntegerInput(scanner.nextLine());
    while (!Validation.numberWithinRange(choice, 1, 2)) {
      System.out.println("Please enter 1 or 2!");
      System.out.println("Press 1 to accept");
      System.out.println("Press 2 to reject");
      choice = Input.acceptIntegerInput(scanner.nextLine());

    }
    if (choice == 1) {
      this.decreaseCoins(cost);
      opponent.decreaseAttack(2);
      System.out.println("Computer Attack: " + opponent.getAttack());
    }
  }

  public void decreaseOpponentDefence(Computer opponent) {
    Scanner scanner = new Scanner(System.in);
    int cost = RandomGenerator.generateNumberBetween(500, 1500);
    System.out.println("The cost of decrease opponenet defence is " + cost);
    if (!this.hasEnoughCoins(cost)){
      System.out.println("You dont have enough coins to do this operation!");
      return;
    }
    System.out.println("Press 1 to accept");
    System.out.println("Press 2 to reject");
    int choice = Input.acceptIntegerInput(scanner.nextLine());
    while (!Validation.numberWithinRange(choice, 1, 2)) {
      System.out.println("Please enter 1 or 2!");
      System.out.println("Press 1 to accept");
      System.out.println("Press 2 to reject");
      choice = Input.acceptIntegerInput(scanner.nextLine());
    }
    if (choice == 1) {
      this.decreaseCoins(cost);
      opponent.decreaseDefence(2);
      System.out.println("Computer Defence: " + opponent.getDefence());
    }
  }

  public String getName() {
    return name;
  }

  public int getNumOfCapturedGrids(ArrayList<Grid> grids){
    int gridsWithOwner=0;
    for (Grid grid : grids){
      try{
        boolean ownByHuman = grid.getOwner() instanceof Human;
        if (ownByHuman == true){
          gridsWithOwner+=1;
        }
       
      }
      catch (NullPointerException e){
        continue;
      }
    }
    return gridsWithOwner;
  }

  public boolean hasCompletePath(Field field) {
    boolean isComplete = false;

    for (int i = 0; i < field.getGridRow(); i++) {
      isComplete = isComplete || this.isCompleteRow(field, i);
    }
    for (int j = 0; j < field.getGridCol(); j++) {
      isComplete = isComplete || this.isCompleteCol(field, j);
    }
    return isComplete;
  }

  public boolean isCompleteCol(Field field, int col) {
    boolean result = true;

    for (int i = 0; i < field.getGridRow(); i++) {

      boolean ownByHuman = false;
      try {
        ownByHuman = field.getSpecificGrid(i, col).getOwner() instanceof Human;
      } catch (NullPointerException e) {
        System.out.println("Grid no owner.");
      }
      result = result && ownByHuman;


    }
    return result;
  }

  public boolean isCompleteRow(Field field, int row) {
    boolean result = true;

    for (int i = 0; i < field.getGridCol(); i++) {

      boolean ownByHuman = false;
      try {
        ownByHuman = field.getSpecificGrid(row, i).getOwner() instanceof Human;
      } catch (NullPointerException e) {
        System.out.println("Grid no owner.");
      }
      result = result && ownByHuman;


    }
    return result;
  }

  public void sabotageEnemy(Field field) {
    Scanner scanner = new Scanner(System.in);
    Computer opponent = field.getComputerPlayer();
 
    System.out.println("Press 1 to decrement the opponent's attack by 2");
    System.out.println("Press 2 to decrement the opponent's defence by 2");
    System.out.println("Press 3 to sabotage an opponenet's grid square");
    int choice = Input.acceptIntegerInput(scanner.nextLine());
    while (!Validation.numberWithinRange(choice, 1, 3)) {
      System.out.println("Please enter 1-3");
      choice = Input.acceptIntegerInput(scanner.nextLine());
    }
    if (opponent.getNumOfCapturedGrids(field.getGrids()) == 0 && choice == 3){
      System.out.println("There are no grid captured by opponent!");
      return;
    }
    int cost = 0;
    if (choice == 1) {
      this.decreaseOpponentAttack(opponent);
    } else if (choice == 2) {
      this.decreaseOpponentDefence(opponent);
    } else if (choice == 3) {
      this.sabotageGrid(field);
    }

  }

  public void sabotageGrid(Field field) {
    Scanner scanner = new Scanner(System.in);
    Computer opponent = field.getComputerPlayer();
    if (opponent.getNumOfCapturedGrids(field.getGrids()) == 0){
      System.out.println("Computer didnot capture any grid yet!");
    }
    System.out.print("x coordinate of the target spot: ");
    
    int x = Input.acceptIntegerInput(scanner.nextLine());
    System.out.print("y coordinate of the target spot: ");
    int y = Input.acceptIntegerInput(scanner.nextLine());
    while (field.getSpecificGrid(y, x).getOwner() instanceof Computer == false) {
      System.out.println("Target spot must be captured by opponenet!");
      System.out.print("x coordinate of the target spot: ");
      x = Input.acceptIntegerInput(scanner.nextLine());
      System.out.print("y coordinate of the target spot: ");
      y = Input.acceptIntegerInput(scanner.nextLine());
    }
    int cost = RandomGenerator.generateNumberBetween(1000, 2500);
    System.out.println("The cost of sabotage the target grid is " + cost);
    if (!this.hasEnoughCoins(cost)){
      System.out.println("You dont have enough coins to do this operation!");
      return;
    }
    System.out.println("Press 1 to accept");
    System.out.println("Press 2 to reject");
    int choice = Input.acceptIntegerInput(scanner.nextLine());
    while (!Validation.numberWithinRange(choice, 1, 2)) {
      System.out.println("Please enter 1 or 2!");
      System.out.println("Press 1 to accept");
      System.out.println("Press 2 to reject");
      choice = Input.acceptIntegerInput(scanner.nextLine());
    }
    if (choice == 1) {
      this.decreaseCoins(cost);
      field.getSpecificGrid(y, x).setOwner(null);

    }
  }

  public void setName(String name) {
  
    this.name = name;
    if(name == null || name.length() < 3 || name.length() > 12) {
    System.out.println("Name must be between 3-12 characters");
    System.out.println("Applying Default Name!");
    this.name = "Human Player";
    }

  }

@Override
  public String toString() {
    return "Player Status: \n" +
        "name: " + name + ", " + super.toString();
  }


}
