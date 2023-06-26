import java.util.HashMap;
import java.util.Map;

public class FindMaxNumberOfStringPairs {
    public int maximumNumberOfStringPairs(String[] words) {
        Map<String,Integer> m = new HashMap<>();
        for(String w: words){
            m.put(w, m.getOrDefault(w, 0)+1);
            String rev = new StringBuilder(w).reverse().toString();
            if(!rev.equals(w)){
                m.put(rev, m.getOrDefault(rev, 0)+1);
            }
        }
        int res = 0;
        for(String k: m.keySet()){
            int v = m.get(k);
            res += v*(v-1)/2;
        }
        return res/2;
    }
}
