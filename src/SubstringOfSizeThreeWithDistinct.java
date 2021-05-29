import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SubstringOfSizeThreeWithDistinct {
    public int countGoodSubstrings(String s) {
        int n = s.length();
        int res = 0;
        Map<Integer,Integer> m = new HashMap<>();
        for(int i=0; i<n; i++){
            int cind = s.charAt(i)-'a';
            update(m, cind, 1);
            if(i-2>=0) {
                if (m.size() == 3) {
                    res++;
                }
                update(m, s.charAt(i-2)-'a', -1);
            }
        }
        return res;
    }


    private void update(Map<Integer,Integer> m, int k, int d){
        int nv = m.getOrDefault(k, 0)+d;
        if(nv<=0){
            m.remove(k);
        }else{
            m.put(k, nv);
        }
    }
}
