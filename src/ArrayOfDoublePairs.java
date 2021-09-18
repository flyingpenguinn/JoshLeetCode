import java.util.*;

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
        Map<Integer, Integer> m = new HashMap<>();
        for (int i = 0; i < n; i++) {
            update(m, a[i], 1);
        }
        List<Integer> li = new ArrayList<>();
        for (int ai : a) {
            li.add(ai);
        }
        // sort by abs. can't make -1 -> 1 because -1 *2 1= 2
        Collections.sort(li, (x, y) -> Integer.compare(Math.abs(x.intValue()), Math.abs(y.intValue())));
        for (int i = 0; i < n; i++) {
            int cv = li.get(i);
            if (!m.containsKey(cv)) {
                continue;
            }
            update(m, cv, -1);
            int nv = 2 * cv;
            if (!m.containsKey(nv)) {
                return false;
            }
            update(m, nv, -1);
        }
        return true;
    }

    private void update(Map<Integer, Integer> m, int k, int d) {
        int nv = m.getOrDefault(k, 0) + d;
        if (nv <= 0) {
            m.remove(k);
        } else {
            m.put(k, nv);
        }
    }
}
