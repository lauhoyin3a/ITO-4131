import java.io.FileNotFoundException;
import java.util.*;

public class Field {
    private Computer computerPlayer;

    private ArrayList<Integer> coinList;
    private ArrayList<Integer> damageList;
    private ArrayList<Integer> defenceList;
    private ArrayList<Grid> grids;
    private Human humanPlayer;

    public Field(){

    this.coinList=new ArrayList<>();
    this.damageList=new ArrayList<>();
    this.defenceList=new ArrayList<>();
    this.grids=new ArrayList<>();
    this.computerPlayer=new Computer();
    this.humanPlayer=new Human();
    }
    public Field(Computer computerPlayer, ArrayList<Grid> grids, Human humanPlayer) {
        this.computerPlayer = computerPlayer;
        this.grids = grids;
        this.humanPlayer = humanPlayer;
    }

    public void addGrid(Grid grid){
        this.grids.add(grid);
    }
    public void displayGrid(){
        for (int i=0;i<this.grids.size();i++) {

            System.out.println(this.grids.get(i).toString());
        }
    }

    public int[] inputGridSize(){
        Validation validation= new Validation();
        Scanner scanner= new Scanner(System.in);
        //System.out.println(human.toString());
        System.out.println("row size for gaming: ");
        int rowSize;
        while(true) {
            try {
                System.out.print("Enter row size: ");
                rowSize = Integer.parseInt(scanner.nextLine());
                if(validation.numberWithinRange(rowSize,3,10)) {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Input must be a number");
            }
            System.out.println("Input invalid, must be 3-10");
        }
        int colSize;
        while(true) {

            try {
                System.out.print("Enter col size: ");
                colSize = Integer.parseInt(scanner.nextLine());

                if(validation.numberWithinRange(colSize,3,10)) {
                    break;
                }

            } catch (NumberFormatException e) {
                System.out.println("Input must be a number");

            }

            System.out.println("Input invalid, must be 3-10");

        }
        int[] rowCol= new int[2];
        rowCol[0]=rowSize;
        rowCol[1]=colSize;
        return rowCol;
    }
    public void inputUserName(){
        Validation validation=new Validation();
        Scanner scanner= new Scanner(System.in);
        System.out.print("Please enter your name: ");
        String userName= scanner.nextLine();
        while(!validation.lengthWithinRange(userName,3,12)){
            System.out.print("Name length must be within 3 to 12!\nPlease enter your name: ");
            userName = scanner.nextLine();
        }
        this.humanPlayer.setName(userName);
    }

    public void readFile(){
        FileIO fileReader = new FileIO();
        String fileContent="";
        try {
            fileContent=fileReader.readFileContents();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileReader.getFileName());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        String[] lineContents=fileContent.split("\n");
        ArrayList<Integer> damageList= new ArrayList<>();
        ArrayList<Integer> defenceList= new ArrayList<>();
        ArrayList<Integer> coinList= new ArrayList<>();
        for (String lineContent: lineContents){
            String[] status=lineContent.split(",");
            int damage=Integer.parseInt(status[0]);
            int defence=Integer.parseInt(status[1]);
            int coins=Integer.parseInt(status[2]);
            this.damageList.add(damage);
            this.defenceList.add(defence);
            this.coinList.add(coins);
        }
    }


    public ArrayList<Integer> getCoinList() {
        return coinList;
    }

    public void setCoinList(ArrayList<Integer> coinList) {
        this.coinList = coinList;
    }

    public ArrayList<Integer> getDamageList() {
        return damageList;
    }

    public void setDamageList(ArrayList<Integer> damageList) {
        this.damageList = damageList;
    }

    public ArrayList<Integer> getDefenceList() {
        return defenceList;
    }

    public void setDefenceList(ArrayList<Integer> defenceList) {
        this.defenceList = defenceList;
    }

    public int getGridLength(){
        return this.grids.size();
    }


    public ArrayList<Grid> getGrids() {
        return grids;
    }

    public void setGrids(ArrayList<Grid> grids) {
        this.grids = grids;
    }

    public static void main(String[] args) {
        Field field= new Field();
        Input input= new Input();
        Validation validation = new Validation();
        RandomGenerator random=new RandomGenerator();
        FileIO fileReader = new FileIO();
        Scanner scanner= new Scanner(System.in);
        //System.out.println(human.toString());
        field.inputUserName();

        int[] rowCol=field.inputGridSize();
        int rowSize=rowCol[0];
        int colSize=rowCol[1];
        field.readFile();

        for (int i=0;i<rowSize;i++){
            for(int j=0;j<colSize;j++){
                int randomNum= random.generateNumberBetween(0,8);

                Grid grid=new Grid(field.getDamageList().get(randomNum),field.getDefenceList().get(randomNum),field.getCoinList().get(randomNum),i,j);
                field.addGrid(grid);

            }
        }
        field.displayGrid();




    }


}
