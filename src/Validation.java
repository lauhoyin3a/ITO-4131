public class Validation{
    public Validation(){

    }


    public boolean numberWithinRange(int number,int minimum, int maximum){
        if (number>=minimum && number<=maximum){
            return true;

        }
        return false;
    }
    public boolean isBlank(String string){
        if (string.length()==0){
            return true;
        }
        return false;
    }
    public boolean lengthWithinRange(String string, int index1, int index2){
        int stringLength=string.length();
        if ((stringLength >= index1 && stringLength <=index2) || (stringLength >= index2 && stringLength <=index1))
        {
            return true;
        }
        return false;

    }

}