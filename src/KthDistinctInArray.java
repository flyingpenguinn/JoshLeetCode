import java.util.HashMap;
import java.util.Map;

public class KthDistinctInArray {
    public String kthDistinct(String[] arr, int k) {
        Map<String, Integer> map =new HashMap<>();
        for(String a: arr){
            map.put(a, map.getOrDefault(a, 0)+1);
        }
        for(int i=0; i<arr.length; ++i){
            if(map.get(arr[i])==1){
                --k;
                if(k==0){
                    return arr[i];
                }
            }
        }
        return "";
    }
}
