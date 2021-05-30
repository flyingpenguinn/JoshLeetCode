public class CheckifWordEqualsSummation {

    public boolean isSumEqual(String firstWord, String secondWord, String targetWord) {
        int v1 = value(firstWord);
        int v2 = value(secondWord);
        int v3 = value(targetWord);
        return v1+v2 == v3;
    }

    int value(String s){
        int res = 0;
        for(int i=0; i<s.length(); i++){
            res = res*10+(s.charAt(i)-'a');
        }
        return res;
    }
}
