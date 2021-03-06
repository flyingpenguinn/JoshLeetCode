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
    public List<Integer> majorityElement(int[] a) {
        int k = 2;
        Map<Integer, Integer> m = new HashMap<>();
        for (int i = 0; i < a.length; i++) {
            if (m.containsKey(a[i])) {
                update(m, a[i], 1);
            } else if (m.size() < k) {
                update(m, a[i], 1);
            } else {
                Set<Integer> set = new HashSet<>(m.keySet());
                for (int key : set) {
                    update(m, key, -1);
                }
            }
        }
        List<Integer> res = new ArrayList<>();
        for (int key : m.keySet()) {
            int count = 0;
            for (int i = 0; i < a.length; i++) {
                if (a[i] == key) {
                    count++;
                }
            }
            if (count > a.length / 3) {
                res.add(key);
            }
        }
        return res;
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