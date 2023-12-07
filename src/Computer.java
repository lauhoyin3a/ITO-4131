import java.util.ArrayList;
public class Computer extends Player {

  public Computer() {
    super();
    super.setCoins(10000);
  }

  public Computer(int attack, int defence, int coins) {
    super(attack, defence, coins);
  }

  public void action(Field field) {
    int row = field.getGridRow();
    int col = field.getGridCol();
    int x = RandomGenerator.generateNumberBetween(0, col - 1);
    int y = RandomGenerator.generateNumberBetween(0, row - 1);
    while (field.getSpecificGrid(x,y).isOwnByComputer() == true){
      x = RandomGenerator.generateNumberBetween(0, col - 1);
      y = RandomGenerator.generateNumberBetween(0, row - 1);
    }
    int turns = field.getTurns();
    Human opponent = field.getHumanPlayer();
    System.out.println("Computer turn:");
    if (turns == 1) {

      this.captureGrid(field, x, y);
    } else {
      int computerOption = RandomGenerator.generateNumberBetween(1, 2);
      
      if (opponent.hasCompletePath(field) == true){
        this.sabotageEnemy(field);
      }
      else if (this.hasCompletePath(field) == true){
        System.out.println("The Computer Attack the Player!");
        this.strikeHeart(opponent);
      }
      else if (computerOption == 1) {
        this.captureGrid(field, x, y);
      } else if (computerOption == 2) {
        this.sabotageEnemy(field);
      } 

    }

  }

  public void decreaseOpponentAttack(Human opponent) {
    System.out.println("Decrease Player attack by 2!");
    int cost = RandomGenerator.generateNumberBetween(500, 1500);
    System.out.println("With Cost: " + cost + "coins");

    this.decreaseCoins(cost);
    opponent.decreaseAttack(2);
    System.out.println("Player Attack: " + opponent.getAttack());
  }

  public void decreaseOpponentDefence(Human opponent) {
    System.out.println("Decrease Player defence by 2!");
    int cost = RandomGenerator.generateNumberBetween(500, 1500);
    System.out.println("With Cost: " + cost + "coins");
    this.decreaseCoins(cost);
    opponent.decreaseDefence(2);
    System.out.println("Player Defence: " + opponent.getDefence());

  }

  public int getNumOfCapturedGrids(ArrayList<Grid> grids){
    int gridsWithOwner=0;
    for (Grid grid : grids){
      try{
        boolean ownByComputer = grid.getOwner() instanceof Computer;
        if (ownByComputer == true){
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

      boolean ownByComputer = false;
      try {
        ownByComputer = field.getSpecificGrid(i, col).getOwner() instanceof Computer;
      } catch (NullPointerException e) {
        System.out.println("Grid no owner.");
      }
      result = result && ownByComputer;


    }
    return result;
  }

  public boolean isCompleteRow(Field field, int row) {
    boolean result = true;

    for (int i = 0; i < field.getGridCol(); i++) {

      boolean ownByComputer = false;
      try {
        ownByComputer = field.getSpecificGrid(row, i).getOwner() instanceof Computer;
      } catch (NullPointerException e) {
        System.out.println("Grid no owner.");
      }
      result = result && ownByComputer;


    }
    return result;
  }

 public void sabotageEnemy(Field field) {
    System.out.println("Computer choose to sabotage");
    Human opponent = field.getHumanPlayer();
    int probability = RandomGenerator.generateNumberBetween(0, 100);
    if (opponent.hasCompletePath(field)) {
      this.sabotageGrid(field);
    } else if (probability >= 60) {
      this.decreaseOpponentAttack(opponent);
    } else if (probability >= 20) {
      this.decreaseOpponentDefence(opponent);
    } else {
      this.sabotageGrid(field);
    }
  }
  
  public void sabotageGrid(Field field) {
    System.out.println("Computer choose to sabotage Grid");
    Human opponent = field.getHumanPlayer();
    if (opponent.getNumOfCapturedGrids(field.getGrids()) == 0){
      System.out.println("Human didnot capture any grid yet!");
      System.out.println("Computer waste its turn.");
      return;
    }
    RandomGenerator rand = new RandomGenerator();

    int x = -1;
    int y = -1;
    for (Grid grid : field.getGrids()) {
      Player player;
      try {
        player = grid.getOwner();
      } catch (NullPointerException e) {
        continue;
      }
      if (player instanceof Human) {
        x = grid.getxCoordinate();
        y = grid.getyCoordinate();
        break;
      }
    }
    System.out.print("x coordinate of the target spot: " + x + "\n");
    System.out.print("y coordinate of the target spot: " + y + "\n");

    int cost = RandomGenerator.generateNumberBetween(1000, 2500);
    System.out.println("\nThe cost of sabotage the target grid is " + cost);
    if (this.hasEnoughCoins(cost)){
    this.decreaseCoins(cost);
    field.getSpecificGrid(x, y).setOwner(null);
    }
    else{
      System.out.println("Not Enough Coins to sabotage!");
    }

  }

  @Override
  public String toString() {
    return "Computer Status: \n" +
            super.toString();
  }

  public static void main(String[] args) {
    Computer c = new Computer();
    System.out.println(c.getAttack());
    System.out.println(c.getCoins());

  }

}
