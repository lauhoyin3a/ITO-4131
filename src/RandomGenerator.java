import java.util.Random;

public class RandomGenerator {

    private Random rand;

    public RandomGenerator() {
        rand = new Random();
    }

    public int generateNumberBetween(int lowerBound, int upperBound) {

        int randomNumber = rand.nextInt(upperBound - lowerBound + 1) + lowerBound;
        return randomNumber;

    }

}