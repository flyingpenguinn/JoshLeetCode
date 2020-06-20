import java.util.HashMap;
import java.util.Map;

/*
LC#974
Given an array A of integers, return the number of (contiguous, non-empty) subarrays that have a sum divisible by K.



Example 1:

Input: A = [4,5,0,-2,-3,1], K = 5
Output: 7
Explanation: There are 7 subarrays with a sum divisible by K = 5:
[4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]


Note:

1 <= A.length <= 30000
-10000 <= A[i] <= 10000
2 <= K <= 10000
 */
public class SubarraySumDivisibleByK {
    // similar to "continuous subarray sum" but numbers there are positive and k could be 0 there
    public int subarraysDivByK(int[] a, int k) {
        Map<Integer, Integer> m = new HashMap<>();
        int n = a.length;
        int sum = 0;
        int r = 0;
        for (int i = 0; i < n; i++) {
            sum += a[i];
            int mod = sum % k;
            if (sum % k == 0) {
                r++;
            }
            // same mod as this one
            int pre1 = m.getOrDefault(mod, 0);
            r += pre1;
            if (mod != 0) {
                int t = mod < 0 ? mod + k : mod - k;
                // negative mod -2==> 3  3==> -2
                int pre2 = m.getOrDefault(t, 0);
                r += pre2;
            }
            m.put(mod, m.getOrDefault(mod, 0) + 1);
        }
        return r;
    }
}
