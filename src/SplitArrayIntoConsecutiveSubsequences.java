import base.ArrayUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

/*
LC#659
Given an array nums sorted in ascending order, return true if and only if you can split it into 1 or more subsequences such that each subsequence consists of consecutive integers and has length at least 3.



Example 1:

Input: [1,2,3,3,4,5]
Output: True
Explanation:
You can split them into two consecutive subsequences :
1, 2, 3
3, 4, 5

Example 2:

Input: [1,2,3,3,4,4,5,5]
Output: True
Explanation:
You can split them into two consecutive subsequences :
1, 2, 3, 4, 5
3, 4, 5

Example 3:

Input: [1,2,3,4,4,5]
Output: False


Constraints:

1 <= nums.length <= 10000
 */
public class SplitArrayIntoConsecutiveSubsequences {

    // can use i only when we have more of i than i-1
    // for example 10,10,11,11,11,12,12,12,[13],13,14,14,15,15. we can't "consume" 13 when we are first at 12 because 13 <12 if we we use this 13
    // then later some 12 is not going to be paired with any 13 and we are stuck
    // if there are enough 10,11 then we can pair this 13 with 12 at that time anyway. it's safer not to use that 13 now
    // we can burn i as long as it has >= i-1 nums
    // otherwise we will have prob with dangling i-1
    public boolean isPossible(int[] a) {
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        for (int i = 0; i < a.length; i++) {
            update(tm, a[i], 1);
        }

        while (!tm.isEmpty()) {
            int curlen = 0;
            int t = tm.firstKey();
            // at least 3, so have to do below
            while (curlen < 3 && tm.containsKey(t)) {
                update(tm, t, -1);
                t++;
                curlen++;
            }
            if (curlen < 3) {
                return false;
            }
            // high peak cutting- if t-1 is too much we stop at t and cut smallest....t-1 until it's <= t
            while (tm.getOrDefault(t, 0) - 1 >= tm.getOrDefault(t - 1, 0)) {
                update(tm, t, -1);
                t++;
            }
        }
        return true;
    }

    void update(TreeMap<Integer, Integer> tm, int k, int d) {
        int nv = tm.getOrDefault(k, 0) + d;
        if (nv <= 0) {
            tm.remove(k);
        } else {
            tm.put(k, nv);
        }
    }


    public static void main(String[] args) {
        System.out.println(new SplitArrayConsecutiveSubsOn().isPossible(ArrayUtils.read1d("[1,2,3,3,4,5]")));
    }
}

class SplitArrayConsecutiveSubsOn {
    public boolean isPossible(int[] a) {
        int n = a.length;
        Map<Integer, Integer> freq = new HashMap<>();
        for (int i = 0; i < n; i++) {
            freq.put(a[i], freq.getOrDefault(a[i], 0) + 1);
        }
        Map<Integer, Integer> tail = new HashMap<>();
        // how many ending at this number
        for (int i = 0; i < n; i++) {
            if (freq.get(a[i]) == null) {
                continue;
            } else if (tail.get(a[i] - 1) != null) {
                // we have played out the previous nums. so if we can concat, we continue. starting a new one may fail, not a better option
                update(tail, a[i] - 1, -1);
                update(tail, a[i], 1);
            } else {
                if (freq.get(a[i] + 1) != null && freq.get(a[i] + 2) != null) {
                    update(freq, a[i] + 1, -1);
                    update(freq, a[i] + 2, -1);
                    update(tail, a[i] + 2, 1);
                } else {
                    return false;
                }
            }
            update(freq, a[i], -1);
        }
        return true;
    }

    void update(Map<Integer, Integer> m, int k, int d) {
        int nv = m.getOrDefault(k, 0) + d;
        if (nv <= 0) {
            m.remove(k);
        } else {
            m.put(k, nv);
        }
    }
}
