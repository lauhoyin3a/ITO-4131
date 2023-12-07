public class Validation {

  public Validation() {

  }


  public static boolean numberWithinRange(int number, int minimum, int maximum) {
    return number >= minimum && number <= maximum;
  }

  public static boolean isBlank(String string) {
    return string.length() == 0;
  }

  public static boolean isComputer(Player player) {
    boolean isComputer = player instanceof Computer;
    return isComputer;
  }

  public static boolean isHuman(Player player) {
    boolean isHuman = player instanceof Human;
    return isHuman;
  }

  public static boolean lengthWithinRange(String string, int index1, int index2) {
    int stringLength = string.length();
    return (stringLength >= index1 && stringLength <= index2) || (stringLength >= index2
        && stringLength <= index1);

  }

}
