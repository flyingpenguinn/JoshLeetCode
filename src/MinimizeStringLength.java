import java.util.HashSet;
import java.util.Set;

public class MinimizeStringLength {
    public int minimizedStringLength(String s) {
        char[] c = s.toCharArray();
        Set<Character> set = new HashSet<>();
        for (char ci: c){
            set.add(ci);
        }
        return set.size();
    }
}
