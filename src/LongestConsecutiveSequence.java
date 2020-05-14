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
    int max = 0;
    Map<Integer, Integer> map = new HashMap<>();
    Map<Integer, Integer> sm = new HashMap<>();

    public int longestConsecutive(int[] a) {
        // ai-1<-> ai<->ai+1 are connected so this is finding biggest component
        for (int ai : a) {
            union(ai - 1, ai);
            union(ai + 1, ai);
        }
        return max;
    }

    // j could be new
    void union(int i, int j) {
        Integer gi = find(i);
        Integer gj = find(j);
        int sj = 0;
        if (gj == null) {
            gj = j;
            map.put(j, j);
            sm.put(j, 1);
            sj = 1;
        } else {
            sj = sm.get(gj);
        }
        max = Math.max(max, sj);

        int si = 0;
        if (gi == null) {
            return;
        } else {
            si = sm.get(gi);
        }
        if (gi.equals(gj)) {
            return;
        }
        if (si < sj) {
            map.put(gi, gj);
            sm.put(gj, si + sj);
        } else {
            map.put(gj, gi);
            sm.put(gi, si + sj);
        }
        max = Math.max(max, si + sj);
    }

    Integer find(int t) {
        Integer pt = map.get(t);
        if (pt == null) {
            // null means never existed
            return null;
        }
        // no null on the path
        if (pt == t) {
            return t;
        }
        int gt = find(pt);
        map.put(t, gt);
        return gt;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 0, 1};
        System.out.println(new LongestConsecutiveSequenceJustHashMap().longestConsecutive(nums));
    }
}

class LongestConsecutiveSequenceJustHashMap {
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
