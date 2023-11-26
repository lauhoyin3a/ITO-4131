public class Computer extends Player{
    public Computer() {
        super();
        super.setCoins(10000);
    }

    public Computer(int attack, int defence, int coins) {
        super(attack, defence, coins);
    }

    public static void main(String[] args) {
        Computer c= new Computer();
        System.out.println(c.getAttack());
        System.out.println(c.getCoins());

    }

}
