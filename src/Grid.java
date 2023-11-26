public class Grid {
    private int damage;
    private int defence;
    private int coins;
    private int xCoordinate;
    private int yCoordinate;
    private Player owner;

    public Grid(int damage, int defence, int coins, int xCoordinate, int yCoordinate) {
        this.damage = damage;
        this.defence = defence;
        this.coins = coins;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    @Override
    public String toString() {
        return "Grid{" +
                "damage=" + damage +
                ", defence=" + defence +
                ", coins=" + coins +
                ", xCoordinate=" + xCoordinate +
                ", yCoordinate=" + yCoordinate +
                ", owner=" + owner +
                '}';
    }
}
