public class TestAlphanumericsOnly {

    public static void main (String [] args){
        String str = "Emm App";
        System.out.println(str+" is all alpha ? : "+String.valueOf(isAlpha(str)));
    }

    public static boolean isAlpha(String str){
        for(int i = 0; i < str.length(); i++){
            if(Character.isLetter(str.charAt(i)))
                return true;
        }
        return false;
    }

}
