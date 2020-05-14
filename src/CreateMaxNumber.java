import base.ArrayUtils;

import java.util.*;

/*
LC#321
Given two arrays of length m and n with digits 0-9 representing two numbers. Create the maximum number of length k <= m + n from digits of the two. The relative order of the digits from the same array must be preserved. Return an array of the k digits.

Note: You should try to optimize your time and space complexity.

Example 1:

Input:
nums1 = [3, 4, 6, 5]
nums2 = [9, 1, 2, 5, 8, 3]
k = 5
Output:
[9, 8, 6, 5, 3]
Example 2:

Input:
nums1 = [6, 7]
nums2 = [6, 0, 4]
k = 5
Output:
[6, 7, 6, 0, 4]
Example 3:

Input:
nums1 = [3, 9]
nums2 = [8, 9]
k = 3
Output:
[9, 8, 9]
 */
public class CreateMaxNumber {
    // O((m+n)^3).
    // 1. enumerate single list i, k-i numbers
    // 2. merge. note we must look ahead in order to merge
    public int[] maxNumber(int[] a, int[] b, int k) {
        List<Integer> max = null;
        // must check min max to avoid illegal i
        for (int i = Math.max(0, k - b.length); i <= Math.min(a.length, k); i++) {
            List<Integer> la = pick(a, i);
            List<Integer> lb = pick(b, k - i);
            List<Integer> merged = merge(la, lb);
            if (max == null || bigger(merged, max)) {
                max = merged;
            }
        }
        int[] r = new int[k];
        for (int i = 0; i < k; i++) {
            r[i] = max.get(i);
        }
        return r;
    }

    // pick k numbers from a to make it biggest. similar to "remove duplicate letters" we use a stack
    // difference is here we need to make sure it's of length k while in that problem it's to make string unique
    private List<Integer> pick(int[] a, int k) {
        ArrayList<Integer> r = new ArrayList<>();
        if (k == 0) {
            return r;
        }
        Deque<Integer> st = new ArrayDeque<>();
        int n = a.length;
        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && st.peek() < a[i] && st.size() - 1 + n - i >= k) {
                st.pop();
            }
            if (st.size() < k) {
                st.push(a[i]);
            }
        }
        while (!st.isEmpty()) {
            r.add(st.pop());
        }
        Collections.reverse(r);
        return r;
    }

    private boolean bigger(List<Integer> merged, List<Integer> max) {
        for (int i = 0; i < merged.size(); i++) {
            if (merged.get(i) > max.get(i)) {
                return true;
            } else if (merged.get(i) < max.get(i)) {
                return false;
            }
        }
        return false;
    }

    private List<Integer> merge(List<Integer> la, List<Integer> lb) {
        List<Integer> m = new ArrayList<>();
        int i = 0;
        int j = 0;
        while (i < la.size() || j < lb.size()) {
            int vi = i == la.size() ? -1 : la.get(i);
            int vj = j == lb.size() ? -1 : lb.get(j);
            if (vi > vj) {
                m.add(vi);
                i++;
            } else if (vi < vj) {
                m.add(vj);
                j++;
            } else {
                // both are 0 for example:
                // 0,1,3
                // 0,1,5
                // we have to see who will meet a bigger number eventually.
                // good thing is all numbers are included, so the big number we see will be counted
                // another example: 7,8,9,4 vs 7,8,9,3
                int ni = i;
                int nj = j;
                int nvi = vi;
                int nvj = vj;
                while ((ni < la.size() || nj < lb.size()) && nvi == nvj) {
                    nvi = ni == la.size() ? -1 : la.get(ni);
                    nvj = nj == lb.size() ? -1 : lb.get(nj);
                    ni++;
                    nj++;
                }
                if (nvi >= nvj) {
                    m.add(vi);
                    i++;
                } else {
                    m.add(vj);
                    j++;
                }
            }
        }
        return m;
    }

    public static void main(String[] args) {

        //          System.out.println(Arrays.toString(new CreateMaxNumber().maxNumber(ArrayUtils.read1d("[3, 4, 6, 5]"), ArrayUtils.read1d("[9, 1, 2, 5, 8, 3]"), 5)));
        //      System.out.println(Arrays.toString(new CreateMaxNumber().maxNumber(ArrayUtils.read1d("[6,7]"), ArrayUtils.read1d("[6,0,4]"), 5)));
        System.out.println(Arrays.toString(new CreateMaxNumber().maxNumber(ArrayUtils.read1d("[2,5,6,4,4,0]"),
                ArrayUtils.read1d("[7,3,8,0,6,5,7,6,2]"), 15)));


        //    System.out.println(Arrays.toString(new CreateMaxNumber().maxNumber(ArrayUtils.read1d("[1,0,1,0,3,9,1,2,8,0,9,8,1,4,7,3]"),
        //          ArrayUtils.read1d("[1,0,1,0,5,6,0,5,0]"), 25)));
    }
}


class CreateMaxNumberOn4 {
    // slower, dp solution. O(m*n*(m+n)^2) in the worst case though we prune heavily
    // store best choice in ijk for later replay
    Map<Long, int[]> dp = new HashMap<>();

    public int[] maxNumber(int[] n1, int[] n2, int k) {
        dom(0, 0, n1, n2, k);
        int[] r = new int[k];
        dfs(0, 0, k, r, 0, n1, n2);
        return r;
    }

    void dfs(int i, int j, int k, int[] r, int ri, int[] n1, int[] n2) {
        if (k == 0) {
            return;
        }
        int[] top = dp.get(code(i, j, k));
        r[ri] = top[0];
        dfs(top[1], top[2], k - 1, r, ri + 1, n1, n2);
    }

    void dom(int i, int j, int[] n1, int[] n2, int k) {
        if (k == 0) {
            return;
        }
        int[] ch = dp.get(code(i, j, k));
        if (ch != null) {
            return;
        }
        int n1n = n1.length;
        int n2n = n2.length;
        int remi = n1n - i;
        int remj = n2n - j;
        int mi = -1;
        int mii = -1;
        for (int ik = i; ik < n1n && (n1n - (ik + 1) + remj) >= k - 1; ik++) {
            if (n1[ik] > mi) {
                mi = n1[ik];
                mii = ik;
            }
        }
        int mj = -1;
        int mjj = -1;
        for (int jk = j; jk < n2n && (n2n - (jk + 1) + remi) >= k - 1; jk++) {
            if (n2[jk] > mj) {
                mj = n2[jk];
                mjj = jk;
            }
        }
        int[] cr1 = {mi, mii + 1, j};
        int[] cr2 = {mj, i, mjj + 1};
        if (mi > mj) {
            dom(mii + 1, j, n1, n2, k - 1);
            dp.put(code(i, j, k), cr1);
        } else if (mj > mi) {
            dom(i, mjj + 1, n1, n2, k - 1);
            dp.put(code(i, j, k), cr2);
        } else {
            dom(mii + 1, j, n1, n2, k - 1);
            dom(i, mjj + 1, n1, n2, k - 1);
            if (bg(mii + 1, j, i, mjj + 1, k - 1, n1, n2)) {
                dp.put(code(i, j, k), cr1);
            } else {
                dp.put(code(i, j, k), cr2);
            }
        }
    }

    List<Integer> bg(List<Integer> l1, List<Integer> l2) {
        for (int i = l1.size() - 1; i >= 0; i--) {
            if (l1.get(i) > l2.get(i)) {
                return l1;
            } else if (l1.get(i) < l2.get(i)) {
                return l2;
            }
        }
        return l1;
    }

    long code(int i, int j, int k) {
        return 1000000000000L * i + 1000000L * j + k;
    }

    boolean bg(int i1, int j1, int i2, int j2, int k, int[] n1, int[] n2) {
        if (k == 0) {
            return true;
        }
        int[] top1 = dp.get(code(i1, j1, k));
        int[] top2 = dp.get(code(i2, j2, k));

        if (top1[0] > top2[0]) {
            return true;
        } else if (top1[0] < top2[0]) {
            return false;
        } else {
            return bg(top1[1], top1[2], top2[1], top2[2], k - 1, n1, n2);
        }
    }
}