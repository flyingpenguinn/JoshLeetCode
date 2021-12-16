import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RingsAndRods {
    public int countPoints(String s) {
        Map<Character, Set<Character>> m = new HashMap<>();
        int n = s.length();
        for(int i=0; i<n; i+=2){
            m.computeIfAbsent(s.charAt(i+1), k-> new HashSet<>()).add(s.charAt(i));
        }
        int res = 0;
        for(char k: m.keySet()){
            if(m.get(k).size()==3){
                ++res;
            }
        }
        return res;
    }
}
