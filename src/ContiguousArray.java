import java.util.HashMap;
import java.util.Map;

/*
LC#525
Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.

Example 1:
Input: [0,1]
Output: 2
Explanation: [0, 1] is the longest contiguous subarray with equal number of 0 and 1.
Example 2:
Input: [0,1,0]
Output: 2
Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
Note: The length of the given binary array will not exceed 50,000.
 */
public class ContiguousArray {
    // 2*(si-sj) == (i-j) => 2*si-i == 2*sj-j
    // i, j are lengths, so i+1
    public int findMaxLength(int[] a) {
        int n = a.length;
        Map<Integer, Integer> m = new HashMap<>();
        int sum = 0;
        int max = 0;
        m.put(0, -1);
        for (int i = 0; i < n; i++) {
            sum += a[i];
            Integer old = m.get(2 * sum - i - 1);
            if (old != null) {
                max = Math.max(max, i - old);
            }
            m.putIfAbsent(2 * sum - i - 1, i);
        }
        return max;
    }
}

class ContiguousArrayAlternative {
    // get the same diff appeared before
    public int findMaxLength(int[] a) {
        int c0 = 0;
        int c1 = 0;
        int max = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int n = a.length;
        for (int i = 0; i < n; i++) {
            if (a[i] == 0) {
                c0++;
            } else {
                c1++;
            }
            int diff = c1 - c0;
            Integer before = map.get(diff);
            if (before != null) {
                max = Math.max(max, i - before);
            } else {
                map.put(diff, i);
            }

        }
        return max;
    }
}
