import java.util.HashMap;
import java.util.Map;

public class FindElementWithUniqueFreq {
    public int firstUniqueFreq(int[] a) {
        int n = a.length;
        Map<Integer, Integer> f = new HashMap<>();
        for(int ai: a){
            f.put(ai, f.getOrDefault(ai, 0)+1);
        }
        Map<Integer, Integer> fs = new HashMap<>();
        for(int k: f.keySet()){
            int cf = f.get(k);
            fs.put(cf, fs.getOrDefault(cf,0)+1);
        }

        for(int ai: a){
            int aif = f.get(ai);
            int aifc = fs.get(aif);
            if(aifc == 1){
                return ai;
            }
        }
        return -1;
    }
}
