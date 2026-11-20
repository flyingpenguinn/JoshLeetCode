import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MaximizeConsecutiveElmentsInArrayAfterModification {
    // LC 3041, daily 2026-11-20. Dp on value: a chain of consecutive values only cares about its last value.
    // dp[x] = longest chain ending at value x. Sorted, each element is used as v (extends chain ending at v-1)
    // or as v+1 (extends chain ending at v). Update dp[v+1] first, then dp[v]; the reverse order would use one element twice ([1] -> 2).
    // Earlier today: index dp(i, plusone) with a skip loop, AC but O(n^2) on all-equal input (in git history).
    public int maxSelectedElements(int[] a) {
        int n = a.length;
        Arrays.sort(a);
        Map<Integer,Integer> dp = new HashMap<>();
        int res =1;
        for(int v: a){
            dp.put(v+1, dp.getOrDefault(v, 0)+1);
            dp.put(v, dp.getOrDefault(v-1, 0)+1);
            int cur = Math.max(dp.get(v), dp.get(v+1));
            res = Math.max(res, cur);
        }
        return res;
    }
}
