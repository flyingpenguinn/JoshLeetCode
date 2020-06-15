import base.ArrayUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FindTwoNonOverlappingSubarraysSumToTarget {
    int Max = 10000000;

    public int minSumOfLengths(int[] a, int t) {
        int n = a.length;
        int[] sum = new int[n];
        Map<Integer, Integer> map1 = new HashMap<>();
        map1.put(0, -1);
        int[] minsofar = new int[n];
        int min = Max;
        for (int i = 0; i < n; i++) {
            sum[i] = (i == 0 ? 0 : sum[i - 1]) + a[i];
            int curend = Max;
            int lookfor = sum[i] - t;
            if (map1.containsKey(lookfor)) {
                int pre = map1.get(lookfor);
                curend = i - pre;
                if (pre >= 0) {
                    // 0... pre what's the min value
                    int cur = curend + minsofar[pre];
                    min = Math.min(min, cur);
                }
            }
            map1.put(sum[i], i);
            if (i == 0) {
                minsofar[i] = curend;
            } else {
                minsofar[i] = curend < minsofar[i - 1] ? curend : minsofar[i - 1];
            }
        }
        return min >= Max ? -1 : min;
    }

    public static void main(String[] args) {
        System.out.println(new FindTwoNonOverlappingSubarraysSumToTarget().minSumOfLengths(ArrayUtils.read1d("[64,5,20,9,1,39]"), 69));
        System.out.println(new FindTwoNonOverlappingSubarraysSumToTarget().minSumOfLengths(ArrayUtils.read1d("[3,2,2,4,3]"), 3));
    }
}
