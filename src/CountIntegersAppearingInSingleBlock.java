import java.util.HashMap;
import java.util.Map;

public class CountIntegersAppearingInSingleBlock {
    public int countSpecialIntegers(int[] a) {
        int n = a.length;
        Map<Integer,Integer> blocks = new HashMap<>();
        int i = 0;
        while(i<n){
            int j = i;
            while(j<n && a[i]==a[j]){
                ++j;
            }
            blocks.put(a[i], blocks.getOrDefault(a[i], 0)+1);
            i = j;
        }
        int res = 0;
        for(int k: blocks.keySet()){
            if(blocks.get(k)==1){
                ++res;
            }
        }
        return res;
    }
}
