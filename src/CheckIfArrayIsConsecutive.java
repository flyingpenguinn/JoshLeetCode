import java.util.HashSet;
import java.util.Set;

public class CheckIfArrayIsConsecutive {
    public boolean isConsecutive(int[] a) {
        Set<Integer> set = new HashSet<>();
        int min = (int)1e9;
        for(int ai: a){
            set.add(ai);
            min = Math.min(min, ai);
        }
        int t = min;
        for(int i=0; i<a.length; ++i){
            if(!set.contains(t++)){
                return false;
            }
        }
        return true;
    }
}
