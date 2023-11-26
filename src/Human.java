public class Human extends Player{



    private String name;

    public Human() {
        super();
        this.name="Human Player";
    }

    public Human( String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Human{" +
                "name='" + name + '\'' + super.toString()+
                '}';
    }

    public static void main(String[] args) {
        Human h=new Human();
        System.out.println(h.getAttack());
        System.out.println(h.getName());

    }

}
