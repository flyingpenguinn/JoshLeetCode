import java.util.HashMap;
import java.util.Map;

/*
LC#930
        In an array A of 0s and 1s, how many non-empty subarrays have sum S?
        Example 1:

        Input: A = [1,0,1,0,1], S = 2
        Output: 4
        Explanation:
        The 4 subarrays are bolded below:
        [1,0,1,0,1]
        [1,0,1,0,1]
        [1,0,1,0,1]
        [1,0,1,0,1]


        Note:

        A.length <= 30000
        0 <= S <= A.length
        A[i] is either 0 or 1.
*/
public class BinarySubarraysWithSum {
    // using prefix sum
    public int numSubarraysWithSum(int[] a, int t) {
        int n = a.length;
        int ones = 0;
        Map<Integer, Integer> map = new HashMap<>();
        int r = 0;
        for (int i = 0; i < n; i++) {
            ones += a[i]; // ones is for prefix sum till i-1
            int cur = map.getOrDefault(ones - t, 0);
            r += cur;
            if (ones == t) {
                // 0 to i
                r++;
            }
            map.put(ones, map.getOrDefault(ones, 0) + 1);

        }
        return r;

    }
}
