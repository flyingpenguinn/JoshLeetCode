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
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < a.length; i++) {
            set.add(a[i]);
        }
        Set<Integer> r = new HashSet<>();
        for (int i = 0; i < b.length; i++) {
            if (set.contains(b[i])) {
                r.add(b[i]);
            }
        }
        int[] rr = new int[r.size()];
        int ri = 0;
        for (int k : r) {
            rr[ri++] = k;
        }
        return rr;
    }
}

class IntersetionOfTwoArraysSorted {
    public int[] intersection(int[] a, int[] b) {
        Arrays.sort(a);
        Arrays.sort(b);
        int i = 0;
        int j = 0;
        List<Integer> r = new ArrayList<>();
        while (i < a.length && j < b.length) {
            while (i < a.length && i > 0 && a[i] == a[i - 1]) {
                i++;
            }
            while (j < b.length && j > 0 && b[j] == b[j - 1]) {
                j++;
            }
            if (i >= a.length || j >= b.length) {
                break;
            }
            if (a[i] < b[j]) {
                i++;
            } else if (a[i] > b[j]) {
                j++;
            } else {
                r.add(a[i]);
                i++;
                j++;
            }
        }
        int[] rr = new int[r.size()];
        for (i = 0; i < r.size(); i++) {
            rr[i] = r.get(i);
        }
        return rr;
    }
}
