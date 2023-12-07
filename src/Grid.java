
public class Grid {

  private int coins;
  private int damage;
  private Player owner;
  private int defence;
  private int xCoordinate;
  private int yCoordinate;

  public Grid() {

  }

  public Grid(int damage, int defence, int coins, int xCoordinate, int yCoordinate) {
    this.damage = damage;
    this.defence = defence;
    this.coins = coins;
    this.xCoordinate = xCoordinate;
    this.yCoordinate = yCoordinate;
  }

  public Grid(int damage, int defence, int coins, int xCoordinate, int yCoordinate, Player owner) {
    this.damage = damage;
    this.defence = defence;
    this.coins = coins;
    this.xCoordinate = xCoordinate;
    this.yCoordinate = yCoordinate;
    this.owner = owner;
  }

  public int getCoins() {
    return coins;
  }

  public int getDamage() {
    return damage;
  }
    public int getDefence() {
    return defence;
  }

  public Player getOwner() {
    return owner;
  }

  public int getxCoordinate() {
    return xCoordinate;
  }

  public int getyCoordinate() {
    return yCoordinate;
  }


  public boolean isOwnByComputer(){
    try{
    if (this.getOwner() instanceof Computer){
      return true;
    }
    }
    catch(NullPointerException e){
      return false;
    }
    return false;
  }

  public boolean isOwnByHuman(){
    try{
    if (this.getOwner() instanceof Human){
      return true;
    }
    }
    catch(NullPointerException e){
      return false;
    }
    return false;
  }

  public void setCoins(int coins) {
    this.coins = coins;
  }

  public void setDamage(int damage) {
    this.damage = damage;
  }


  public void setDefence(int defence) {
    this.defence = defence;
  }

  public void setOwner(Player owner) {
    this.owner = owner;
  }

  public void setxCoordinate(int xCoordinate) {
    this.xCoordinate = xCoordinate;
  }

  public void setyCoordinate(int yCoordinate) {
    this.yCoordinate = yCoordinate;
  }


  public String toString() {
    String owner;
    try{
      boolean ownByComputer = this.getOwner() instanceof Computer;
      boolean ownByHuman = this.getOwner() instanceof Human;
      if (ownByComputer == true){
        owner = "C";
      }
      else if (ownByHuman == true){
        owner = "P";
      }
      else{
        owner = " ";
      }
    }
    catch(NullPointerException e){
      owner=" ";
    }
    String damage = String.format("%2d",this.damage);
    String coins = String.format("%5d", this.coins);

    return "(" + damage +
        "," + defence +
        "," + coins +
        "," + owner +
        ')';
  }
}
