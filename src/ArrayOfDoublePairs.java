import java.util.TreeMap;

/*
LC#954
Given an array of integers A with even length, return true if and only if it is possible to reorder it such that A[2 * i + 1] = 2 * A[2 * i] for every 0 <= i < len(A) / 2.



Example 1:

Input: [3,1,3,6]
Output: false
Example 2:

Input: [2,1,2,6]
Output: false
Example 3:

Input: [4,-2,2,-4]
Output: true
Explanation: We can take two groups, [-2,-4] and [2,4] to form [-2,-4,2,4] or [2,4,-2,-4].
Example 4:

Input: [1,2,4,16,8,4]
Output: false


Note:

0 <= A.length <= 30000
A.length is even
-100000 <= A[i] <= 100000
 */
public class ArrayOfDoublePairs {

    // try to fill smallest number first. can use two pointers too
    public boolean canReorderDoubled(int[] a) {
        int n = a.length;
        TreeMap<Integer, Integer> pos = new TreeMap<>();
        TreeMap<Integer, Integer> neg = new TreeMap<>();
        int zeros = 0;
        for (int i = 0; i < n; i++) {
            if (a[i] > 0) {
                pos.put(a[i], pos.getOrDefault(a[i], 0) + 1);
            } else if (a[i] < 0) {
                neg.put(a[i], neg.getOrDefault(a[i], 0) + 1);
            } else {
                zeros++;
            }
        }
        // need special logic for 0!
        if (zeros % 2 == 1) {
            return false;
        }
        while (!pos.isEmpty()) {
            int t = pos.firstKey();

            if (!hastwotimes(t, pos)) {
                return false;
            }
            reduce(t, pos);
            reduce(2 * t, pos);
        }
        while (!neg.isEmpty()) {
            int t = neg.lastKey();

            if (!hastwotimes(t, neg)) {
                return false;
            }
            reduce(t, neg);
            reduce(2 * t, neg);
        }
        // must be all emptied
        return neg.isEmpty() && pos.isEmpty();

    }

    boolean hastwotimes(int t, TreeMap<Integer, Integer> m) {
        return m.containsKey(2 * t);
    }

    void reduce(int t, TreeMap<Integer, Integer> m) {
        int cc = m.get(t);
        if (cc == 1) {
            m.remove(t);
        } else {
            m.put(t, cc - 1);
        }
    }
}
