import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Field {

    private Computer computerPlayer;
    private ArrayList<Integer> coinList;
    private ArrayList<Integer> damageList;
    private ArrayList<Integer> defenceList;
    private int gridCol;
    private int gridRow;
    private ArrayList<Grid> grids;
    private Human humanPlayer;
    private int turns;

    public Field() {
        this.coinList = new ArrayList<>();
        this.damageList = new ArrayList<>();
        this.defenceList = new ArrayList<>();
        this.grids = new ArrayList<>();
        this.computerPlayer = new Computer();
        this.humanPlayer = new Human();
        this.gridCol = 3;
        this.gridRow = 3;
        this.turns = 0;
    }

    public Field(Computer computerPlayer, ArrayList<Integer> coinList, ArrayList<Integer> damageList,
                 ArrayList<Integer> defenceList, int gridCol, int gridRow, ArrayList<Grid> grids,
                 Human humanPlayer) {
        this.computerPlayer = computerPlayer;
        this.coinList = coinList;
        this.damageList = damageList;
        this.defenceList = defenceList;
        this.gridCol = gridCol;
        this.gridRow = gridRow;
        this.grids = grids;
        this.humanPlayer = humanPlayer;
        this.turns = 0;
    }

    public void addGrid(Grid grid) {
        this.grids.add(grid);
    }

    public void checkGameStatus(){
        if (this.isGameOver() == true){
            this.gameOver();
            System.exit(0);
            }
    }

    public void displayGrid() {
        int gridCount = 0;
        for (int i = 0; i < gridRow; i++) {
            StringBuilder displayString = new StringBuilder();
            for (int j = 0; j < gridCol; j++) {
                Grid grid = this.getSpecificGrid(i, j);
                String temp = grid.toString();

                displayString.append(temp + " ");

            }
            System.out.println(displayString);
        }
    }

    public void gameOver(){
        FileIO fileWriter = new FileIO();
        System.out.println(this.toString());
        try {
            fileWriter.writeToFile(this.toString());
            } 
        catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileWriter.getBoostFileName());
            } 
        catch (Exception e) {
            System.out.println(e.getMessage());
            }
    }

    public String gameResult() {
        Human player = this.getHumanPlayer();
        Computer computer = this.getComputerPlayer();
        String playerName = player.getName();
        int turns = this.getTurns();
        int playerAttack = player.getAttack();
        int playerDefence = player.getDefence();
        int computerAttack = computer.getAttack();
        int computerDefence = computer.getAttack();
        return playerName + "turns: " + turns + "Player Attack: " + playerAttack + "Player Defence"
                + playerDefence + "Computer Attack: " + computerAttack + "Computer Defence"
                + computerDefence;
    }

    public ArrayList<Integer> getCoinList() {
        return coinList;
    }

    public ArrayList<Integer> getDamageList() {
        return damageList;
    }

    public ArrayList<Integer> getDefenceList() {
        return defenceList;
    }

    public Computer getComputerPlayer() {
        return this.computerPlayer;
    }

    public int getGridCol() {
        return gridCol;
    }

    public int getGridLength() {
        return this.grids.size();
    }

    public int getGridRow() {
        return gridRow;
    }

    public ArrayList<Grid> getGrids() {
        return grids;
    }

    public Human getHumanPlayer() {
        return this.humanPlayer;
    }

    public Grid getSpecificGrid(int x, int y) {
        Grid resultGrid = null;

        for (Grid grid : this.grids) {
            if (grid.getxCoordinate() == x && grid.getyCoordinate() == y) {
                resultGrid = grid;
                break;
            }
        }

        if (resultGrid == null) {

            System.out.println("Grid with coordinates (" + x + ", " + y + ") not found.");
        }

        return resultGrid;
    }

    public int getTurns() {
        return this.turns;
    }

    public void initializeField(){
        RandomGenerator random = new RandomGenerator();
        String userName = Input.inputUserName();
        this.getHumanPlayer().setName(userName);
        int[] rowCol = Input.inputGridSize();
        this.setGridCol(rowCol[1]);
        this.setGridRow(rowCol[0]);
        this.readFile();

        for (int i = 0; i < this.getGridRow(); i++) {
            for (int j = 0; j < this.getGridCol(); j++) {
                int randomNum = random.generateNumberBetween(0, 8);
                Grid grid = new Grid(this.getDamageList().get(randomNum),
                        this.getDefenceList().get(randomNum), this.getCoinList().get(randomNum), j, i);
                this.addGrid(grid);
            }
        }
    }

    public boolean isGameOver() {
        if (this.computerPlayer.gameOver()) {
            return true;
        }
        return this.humanPlayer.gameOver();
    }


    public void readFile() {
        FileIO fileReader = new FileIO();
        String fileContent = "";
        try {
            fileContent = fileReader.readFileContents();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileReader.getBoostFileName());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        String[] lineContents = fileContent.split("\n");
        for (String lineContent : lineContents) {
            String[] status = lineContent.split(",");
            int damage = Integer.parseInt(status[0]);
            int defence = Integer.parseInt(status[1]);
            int coins = Integer.parseInt(status[2]);
            this.damageList.add(damage);
            this.defenceList.add(defence);
            this.coinList.add(coins);
        }
    }

    public void runTurn(boolean coinFlip){
            this.turnCounter();
            System.out.println("\n\nTurn " + this.getTurns() + ":");
            Human player = this.getHumanPlayer();
            Computer computer = this.getComputerPlayer();
            if (coinFlip == true){
                Display.displayPlayerStatus(this);
                Display.displayGrid(this);
                Display.displayMenu();
                player.action(this);
                this.checkGameStatus();
                computer.action(this);
                this.checkGameStatus();
            }
            else{
                computer.action(this);
                this.checkGameStatus();
                Display.displayPlayerStatus(this);
                Display.displayGrid(this);
                Display.displayMenu();
                player.action(this);
                this.checkGameStatus();
            }
            
    }

    public void setCoinList(ArrayList<Integer> coinList) {
        this.coinList = coinList;
    }

    public void setComputerPlayer(Computer computerPlayer){
        this.computerPlayer = computerPlayer;
    }

    public void setDamageList(ArrayList<Integer> damageList) {
        this.damageList = damageList;
    }
    
    public void setDefenceList(ArrayList<Integer> defenceList) {
        this.defenceList = defenceList;
    }


    public void setGridCol(int gridCol) {
        this.gridCol = gridCol;
    }


    public void setGridRow(int gridRow) {
        this.gridRow = gridRow;
    }


    public void setGrids(ArrayList<Grid> grids) {
        this.grids = grids;
    }

    public void setHumanPlayer(Human humanPlayer){
        this.humanPlayer = humanPlayer;
    }

    public void setTurns(int turns) {
        this.turns = turns;
    }

    public String toString() {
        String winner;
        if (humanPlayer.getHeart() == 0){
            winner = "Computer";
        }
        else{
            winner = "Player";
        }
        return "Final Status of Player "+ humanPlayer.getName() +
                ":\nAttack Status: " + humanPlayer.getAttack() +
                " and Defence Status: " + humanPlayer.getDefence() +
                "\nPlayer Heart: " + humanPlayer.getHeart() +
                "\n\nFinal Status of Computer: " +
                "\nAttack Status: " + computerPlayer.getAttack() +
                " and Defence Status: " + computerPlayer.getDefence() +
                "\nComputer Heart: " + computerPlayer.getHeart() +
                "\n\n" + winner + " wins the game!" +
                "\nTurns of game: " + this.getTurns() +
                "\nCaptured Grids: " + humanPlayer.getNumOfCapturedGrids(this.grids) +
                "\nLost Grids: " + computerPlayer.getNumOfCapturedGrids(this.grids);
    }

    public void turnCounter() {
        this.turns += 1;
    }

    public static void main(String[] args) {
        Field field = new Field();
        field.initializeField();
        boolean coinFlip = RandomGenerator.coinFlip();
        if (coinFlip == true){
            System.out.println("Player will start first this time!");
        }
        else{
            System.out.println("Computer will start first this time!");
        }

        while (true) {
            field.runTurn(coinFlip);
            }
        }
    }



