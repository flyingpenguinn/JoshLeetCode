import java.util.*;

/*
LC#229
Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.

Note: The algorithm should run in linear time and in O(1) space.

Example 1:

Input: [3,2,3]
Output: [3]
Example 2:

Input: [1,1,1,3,3,2,2,2]
Output: [1,2]
 */
public class MajorityElementII {
    // delete three different elements each time. in the end the two majority elements will still be there.
    // note we need to verify them because they might be phantom
    // note we are NOT putting v to anywhere when we count to 0 for n1 or n2. because v is the number that we have taken away..
    public List<Integer> majorityElement(int[] a) {
        int n = a.length;
        int n1 = 0;
        int c1 = 0;
        int n2 = 0;
        int c2 = 0;
        for (int v : a) {
            if (v == n1) {
                c1++;
            } else if (v == n2) {
                c2++;
            } else if (c1 == 0) {
                n1 = v;
                c1 = 1;
            } else if (c2 == 0) {
                n2 = v;
                c2 = 1;
            } else {
                c1--;
                c2--;
            }
        }
        c1 = 0;
        c2 = 0;
        for (int v : a) {
            if (v == n1) {
                c1++;
            }
            if (v == n2) {
                c2++;
            }
        }
        List<Integer> r = new ArrayList<>();
        if (c1 > n / 3) {
            r.add(n1);
        }
        if (c2 > n / 3 && n1 != n2) {
            r.add(n2);
        }
        return r;
    }
}


class MajorityElementIIGeneralized {
    // generalized way, take away k different values each time and majority must be among the ones left
    // note we wait for k different numbers to be there hence the check on <k then put
    // O(n) because we can't hide a vote more than once. we do the map operation only n/k times each costing k
    class Solution {
        // generalized solution for k majority elements....
        public List<Integer> majorityElement(int[] a) {
            int n = a.length;
            List<Integer> r = new ArrayList<>();
            if (a == null || n == 0) {
                return r;
            }
            Map<Integer, Integer> m = new HashMap<>();
            int k = 3;  // k must >=2. k means we look for elements > n/k
            // Ok in complexity... for k==3 it's O(3)
            // if requires >n/k,  wont be more than k-1 elements
            for (int i = 0; i < n; i++) {
                if (m.size() < k - 1) {
                    m.put(a[i], m.getOrDefault(a[i], 0) + 1);
                } else if (m.containsKey(a[i])) {
                    m.put(a[i], m.get(a[i]) + 1);
                } else {
                    List<Integer> toRemove = new ArrayList<>();
                    for (int key : m.keySet()) {
                        int ncount = m.get(key) - 1;
                        if (ncount == 0) {
                            toRemove.add(key);
                        } else {
                            m.put(key, ncount);
                        }
                    }
                    for (int key : toRemove) {
                        m.remove(key);
                    }
                }
            }
            for (int key : m.keySet()) {
                m.put(key, 0);
            }
            for (int i = 0; i < n; i++) {
                if (m.containsKey(a[i])) {
                    m.put(a[i], m.get(a[i]) + 1);
                }
            }
            for (int key : m.keySet()) {
                if (m.get(key) > n / 3) {
                    r.add(key);
                }
            }
            return r;
        }
    }
}