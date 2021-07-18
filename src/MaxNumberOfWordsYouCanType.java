import java.util.HashSet;
import java.util.Set;

public class MaxNumberOfWordsYouCanType {
    public int canBeTypedWords(String text, String brokenLetters) {
        String[] ss = text.split(" ");
        Set<Character> set = new HashSet<>();
        for(char bc: brokenLetters.toCharArray()){
            set.add(bc);
        }
        int res = 0;
        for(String s: ss){
            boolean bad = false;
            for(char sc: set){
                if(s.indexOf(sc) != -1){
                    bad = true;
                    break;
                }
            }
            if(!bad){
                res++;
            }
        }
        return res;
    }
}
