import base.ArrayUtils;
import base.Lists;

import java.util.*;

/*
LC#952
Given a non-empty array of unique positive integers A, consider the following graph:

There are A.length nodes, labelled A[0] to A[A.length - 1];
There is an edge between A[i] and A[j] if and only if A[i] and A[j] share a common factor greater than 1.
Return the size of the largest connected component in the graph.



Example 1:

Input: [4,6,15,35]
Output: 4

Example 2:

Input: [20,50,9,63]
Output: 2

Example 3:

Input: [2,3,6,7,4,12,21,39]
Output: 8

Note:

1 <= A.length <= 20000
1 <= A[i] <= 100000
 */
public class LargestComponentSizeByCommonFactor {
    // don't really need to deal with prime factors only: we can process every a[i] and skip the prime check i on j and other
    Map<Integer, Integer> pm = new HashMap<>();// prime factor to its representative
    Map<Integer, Integer> parent = new HashMap<>();
    Map<Integer, Integer> size = new HashMap<>();
    boolean[] isp;

    public int largestComponentSize(int[] a) {
        int n = a.length;
        int max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, a[i]);
        }
        getprimes(max);
        for (int i = 0; i < n; i++) {
            parent.put(a[i], a[i]);
            size.put(a[i], 1);
            if (pm.keySet().contains(a[i])) {
                processprime(a[i], a[i]);
            } else {
                for (int j = 2; j * j <= a[i]; j++) {
                    // instead of continuous / of prime numbers this is sqrt(w) and faster....
                    // alternatively we can keep / primes and then move to the next, i.e. factor the number
                    if (isp[j] && a[i] % j == 0) {
                        processprime(a[i], j);
                    }
                    // note this trick so that we only need sqrt(max) steps here
                    int other = a[i] / j;
                    if (isp[other] && other != j && a[i] % other == 0) {
                        processprime(a[i], other);
                    }
                }
            }
        }
        int r = 0;
        for (int k : parent.keySet()) {
            int cur = size.get(k);
            r = Math.max(r, cur);
        }
        return r;
    }

    protected void processprime(int v, int prime) {
        int pre = pm.get(prime);
        if (pre == -1) {
            pm.put(prime, v);
        } else {
            union(v, pre);
        }
    }

    private void getprimes(int n) {
        isp = new boolean[n + 1];
        Arrays.fill(isp, true);
        isp[0] = isp[1] = false;
        for (int i = 4; i <= n; i += 2) {
            isp[i] = false;
        }
        for (int i = 3; i * i <= n; i++) {
            if (isp[i]) {
                for (int j = i * i; j <= n; j += i) {
                    isp[j] = false;
                }
            }
        }
        for (int i = 2; i <= n; i++) {
            if (isp[i]) {
                pm.put(i, -1);
            }
        }
    }

    void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        if (pa != pb) {
            if (size.get(pa) < size.get(pb)) {
                parent.put(pa, pb);
                size.put(pb, size.get(pb) + size.get(pa));
            } else {
                parent.put(pb, pa);
                size.put(pa, size.get(pa) + size.get(pb));
            }
        }
    }

    private int find(int a) {
        Integer pa = parent.get(a);
        if (pa == a) {
            return a;
        } else {
            int rt = find(pa);
            parent.put(a, rt);
            return rt;
        }
    }

    public static void main(String[] args) {
        System.out.println(new LargestComponentSizeByCommonFactor().largestComponentSize(ArrayUtils.read1d("[83,99,39,11,19,30,31]")));
    }
}