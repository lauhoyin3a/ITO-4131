import java.util.Random;

public class RandomGenerator {

  private static Random rand;

  public RandomGenerator() {
    rand = new Random();
  }

  public static boolean coinFlip(){
    int coin = RandomGenerator.generateNumberBetween(0,1);
    if (coin == 0){
      return true;
    }
    return false;
  }

  public static int generateNumberBetween(int lowerBound, int upperBound) {

    int randomNumber = rand.nextInt(upperBound - lowerBound + 1) + lowerBound;
    return randomNumber;

  }

  public static int rollDicesSum(int times){
    int result=0;
    for(int i=0; i<times; i++){
      result += RandomGenerator.generateNumberBetween(1,6);
    }
    return result;
  }

}
