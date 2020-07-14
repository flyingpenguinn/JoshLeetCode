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
    // similar to "continuous subarray sum" but numbers in a there are positive and k could be 0 there
    // convert negative number to positive to group them together with peers. -2 ==> 1
    public int subarraysDivByK(int[] a, int k) {
        Map<Integer, Integer> m = new HashMap<>();
        int n = a.length;
        int sum = 0;
        int r = 0;
        m.put(0, 1);
        for (int i = 0; i < n; i++) {
            sum += a[i];
            int mod = sum % k;
            if (mod < 0) {
                // k =5, -2 functions like 3: it would merge with 3 anyway
                mod += k;
            }
            // find myself is enough after neg conversion
            r += m.getOrDefault(mod, 0);
            m.put(mod, m.getOrDefault(mod, 0) + 1);
        }
        return r;
    }
}
