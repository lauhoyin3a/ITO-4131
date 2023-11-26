public class Player {
    private int attack;
    private int defence;
    private int coins;

    public Player(){
        attack=5;
        defence=7;
        coins=3000;
    }
    public Player(int attack, int defence, int coins) {
        this.attack = attack;
        this.defence = defence;
        this.coins = coins;
    }
    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    @Override
    public String toString() {
        return "Player{" +
                "attack=" + attack +
                ", defence=" + defence +
                ", coins=" + coins +
                '}';
    }

}
