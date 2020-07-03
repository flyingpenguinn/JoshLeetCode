import java.util.*;

/*
LC#349
Given two arrays, write a function to compute their intersection.

Example 1:

Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2]
Example 2:

Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [9,4]
Note:

Each element in the result must be unique.
The result can be in any order.
 */
public class IntersectionOfTwoArrays {
    public int[] intersection(int[] a, int[] b) {
        // check null
        if (a.length > b.length) {
            return intersection(b, a);
        }
        Set<Integer> sa = new HashSet<>();
        for (int ai : a) {
            sa.add(ai);
        }
        // what if b has duplicates? must use a set
        Set<Integer> res = new HashSet<>();
        for (int bi : b) {
            if (sa.contains(bi)) {
                res.add(bi);
            }
        }
        int[] arrayRes = new int[res.size()];
        int ri = 0;
        for (int k : res) {
            arrayRes[ri++] = k;
        }
        return arrayRes;
    }
}

class IntersetionOfTwoArraysSorted {
    // traps! 1. no dup, so keep moving if the same value.
    // 2. no final two while loops! this is intersection not merging
    public int[] intersection(int[] a, int[] b) {
        Arrays.sort(a);
        Arrays.sort(b);
        int ia = 0;
        int ib = 0;
        List<Integer> r = new ArrayList<>();
        while (ia < a.length && ib < b.length) {
            if (a[ia] < b[ib]) {
                ia = move(a, ia);
            } else if (a[ia] > b[ib]) {
                ib = move(b, ib);
            } else {
                r.add(a[ia]);
                ia = move(a, ia);
                ib = move(b, ib);
            }
        }
        int[] rr = new int[r.size()];
        for (int i = 0; i < r.size(); i++) {
            rr[i] = r.get(i);
        }
        return rr;
    }

    int move(int[] a, int i) {
        int j = i;
        while (j < a.length && a[j] == a[i]) {
            j++;
        }
        return j;
    }
}
