import java.util.HashMap;
import java.util.Map;

public class NumberOfPairsOfStringsConcatEqual {
    public int numOfPairs(String[] a, String t) {
        Map<String, Integer> m = new HashMap<>();
        for(String s: a){
            m.put(s, m.getOrDefault(s, 0)+1);
        }
        int n = a.length;
        int res = 0;
        for(String s: a){
            if(t.length() > s.length() && t.startsWith(s)){
                String rem = t.substring(s.length());
                res += m.getOrDefault(rem, 0);
                if(rem.equals(s)){
                    --res;
                }
            }
        }
        return res;
    }
}
