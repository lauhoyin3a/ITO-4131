import java.util.ArrayList;
public abstract class Player {

  private int attack;
  private int defence;
  private int coins;
  private int heart;

  protected Player() {
    attack = 5;
    defence = 7;
    coins = 3000;
    heart = 3;
  }

  protected Player(int attack, int defence, int coins) {
    this.attack = attack;
    this.defence = defence;
    this.coins = coins;
  }

  public abstract void action(Field field);

  public void addCoins(int coins) {
    this.coins += coins;
  }

  public void captureGrid(Field field, int x, int y) {
    Player opponent;
    if(this instanceof Human) {
      opponent = field.getComputerPlayer();
    } else {
      opponent = field.getHumanPlayer();  
    }
    int playerDices = RandomGenerator.rollDicesSum(3);
    int opponentDices = RandomGenerator.rollDicesSum(2);
    int attackStatus = this.getAttack() + playerDices;
    int defenceStatus = opponent.getDefence() + opponentDices;
    System.out.println("Trying to capture grid at (" + y + "," + x +")");
    System.out.println("Attack Status is :" + attackStatus);
    System.out.println("Defence Status is :" + defenceStatus);
    if (attackStatus > defenceStatus) {
      Grid capturedGrid = field.getSpecificGrid(x, y);
      int coins = capturedGrid.getCoins();
      int attack = capturedGrid.getDamage();
      int defence = capturedGrid.getDefence();
      this.addCoins(coins);
      this.setAttack(this.attack + attack);
      this.setDefence(this.defence + defence);
      capturedGrid.setCoins(0);
      capturedGrid.setOwner(this);
      System.out.println("Captured grid!");
  
    }
    else{
      System.out.println("Capture grid failed!");
    }

  }

  public void decreaseAttack(int attack) {
     
      this.attack -= attack;
      if (this.attack < 0){
        this.attack = 0;
      }
   
    }

  public void decreaseDefence(int defence) {
      this.defence -= defence;
   
    if (this.defence < 0){
        this.defence = 0;
      }
  }

  public void decreaseCoins(int coins) {
    this.coins -= coins;
  }

  public void decreaseHeart() {
    this.heart -= 1;
  }

  public boolean gameOver() {
    return this.heart == 0;
  }

  public int getAttack() {
    return attack;
  }

  public int getCoins() {
    return coins;
  }

  public int getDefence() {
      return defence;
    }

  public int getHeart() {
    return this.heart;
  }

  public abstract int getNumOfCapturedGrids(ArrayList<Grid> grids);

  public abstract boolean hasCompletePath(Field field);
 
  public boolean hasEnoughCoins(int coins){
    if (this.coins >= coins){
      return true;
    }
    return false;
  }

  public abstract boolean isCompleteCol(Field field, int col);

  public abstract boolean isCompleteRow(Field field, int row);

  public void setAttack(int attack) {
    this.attack = attack;
    if (attack < 0){
      System.out.println("Attack cant be less than 0!");
      System.out.println("Set attack to 0.");
      this.attack = 0;
    }
  }

  public void setCoins(int coins) {
      this.coins = coins;
  }

  public void setDefence(int defence) {
    this.defence = defence;
    if (defence < 0){
      System.out.println("Defence cant be less than 0!");
      System.out.println("Set defence to 0.");
      this.defence = 0;
    }
  }

  public void setHeart(int heart){
    this.heart = heart;
    if (heart < 0){
      System.out.println("Heart cant be less than 0!");
      System.out.println("Set heart to 0.");
      this.heart = 0;
    }
  }

  public void strikeHeart(Player opponent) {
    opponent.decreaseHeart();
  }

  
  public String toString() {
    return "attack=" + attack +
        ", defence=" + defence +
        ", coins=" + coins +
        ", heart=" + heart;
  }

}
