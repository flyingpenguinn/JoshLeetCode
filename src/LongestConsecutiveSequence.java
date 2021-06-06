import java.util.HashMap;
import java.util.Map;

/*
LC#128
Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

Your algorithm should run in O(n) complexity.

Example:

Input: [100, 4, 200, 1, 3, 2]
Output: 4
Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
 */
// just like number of islands....
public class LongestConsecutiveSequence {

    public int longestConsecutive(int[] a) {
        if (a == null) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        int max = 0;
        for (int i = 0; i < a.length; i++) {
            if (map.containsKey(a[i])) {
                // eject duplicates
                continue;
            }
            int left = map.getOrDefault(a[i] - 1, 0);
            int right = map.getOrDefault(a[i] + 1, 0);
            int size = left + right + 1;
            // we only care about the boundary. it's ok if some middle element has wrong size
            map.put(a[i], size);
            map.put(a[i] - left, size);
            map.put(a[i] + right, size);
            max = Math.max(max, size);
        }
        return max;
    }
}
