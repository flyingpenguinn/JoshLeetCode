import base.ArrayUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
LC#1395
There are n soldiers standing in a line. Each soldier is assigned a unique rating value.

You have to form a team of 3 soldiers amongst them under the following rules:

Choose 3 soldiers with index (i, j, k) with rating (rating[i], rating[j], rating[k]).
A team is valid if:  (rating[i] < rating[j] < rating[k]) or (rating[i] > rating[j] > rating[k]) where (0 <= i < j < k < n).
Return the number of teams you can form given the conditions. (soldiers can be part of multiple teams).



Example 1:

Input: rating = [2,5,3,4,1]
Output: 3
Explanation: We can form three teams given the conditions. (2,3,4), (5,4,1), (5,3,1).
Example 2:

Input: rating = [2,1,3]
Output: 0
Explanation: We can't form any team given the conditions.
Example 3:

Input: rating = [1,2,3,4]
Output: 4


Constraints:

n == rating.length
1 <= n <= 200
1 <= rating[i] <= 10^5
 */
public class CountNumerOfTeams {
    // use bit to get bigger/smaller ones before/after current i
    public int numTeams(int[] a) {
        int n = a.length;
        if (n < 3) {
            return 0;
        }
        int[] ca = Arrays.copyOf(a, n);
        Arrays.sort(ca);
        Map<Integer, Integer> rank = genrank(ca, false);
        Map<Integer, Integer> rrank = genrank(ca, true);
        int[] bfb = new int[n];
        int[] bfs = new int[n];
        process(a, rank, rrank, bfb, bfs, false);
        int[] afb = new int[n];
        int[] afs = new int[n];
        process(a, rank, rrank, afb, afs, true);
        int r = 0;
        for (int i = 0; i < n; i++) {
            r += bfs[i] * afb[i];
            r += bfb[i] * afs[i];
        }
        return r;
    }

    private void process(int[] a, Map<Integer, Integer> rank, Map<Integer, Integer> rrank, int[] big, int[] small, boolean reverse) {
        int[] bit = new int[rank.keySet().size()];
        int[] bitr = new int[rank.keySet().size()];
        int n = a.length;
        int start = reverse ? n - 1 : 0;
        int end = reverse ? -1 : n;
        int delta = reverse ? -1 : 1;
        for (int i = start; i != end; i += delta) {
            int rk = rank.get(a[i]);
            small[i] = query(bit, rk - 1);
            update(bit, rk);
            int rrk = rrank.get(a[i]);
            big[i] = query(bitr, rrk - 1);
            update(bitr, rrk);
        }
    }

    private int query(int[] bit, int i) {
        int r = 0;
        while (i > 0) {
            r += bit[i];
            i -= i & (-i);
        }
        return r;
    }

    private void update(int[] bit, int i) {
        while (i < bit.length) {
            bit[i]++;
            i += i & (-i);
        }
    }

    private Map<Integer, Integer> genrank(int[] ca, boolean reverse) {
        Map<Integer, Integer> rank = new HashMap<>();
        int n = ca.length;
        int crank = 1;
        int start = reverse ? n - 1 : 0;
        int delta = reverse ? -1 : 1;
        int cur = ca[start];
        int end = reverse ? -1 : n;
        for (int i = start + delta; i != end + delta; i += delta) {
            if (i == end || ca[i] != ca[i - delta]) {
                rank.put(cur, crank);
                crank++;
                if (i != end) {
                    cur = ca[i];
                }
            }
        }
        return rank;
    }

    public static void main(String[] args) {
        System.out.println(new CountNumerOfTeams().numTeams(ArrayUtils.read1d("[2,5,3,4,1]")));
        System.out.println(new CountNumerOfTeams().numTeams(ArrayUtils.read1d("[2,1,3]")));
        System.out.println(new CountNumerOfTeams().numTeams(ArrayUtils.read1d("[1,2,3,4]")));
    }
}

class NumberOfTeamsOn2 {
    // enough in the contest...
    public int numTeams(int[] a) {
        int n = a.length;
        int r = 0;
        for (int j = 0; j < n; j++) {
            int bfb = 0;
            int bfs = 0;
            for (int i = j - 1; i >= 0; i--) {
                if (a[i] < a[j]) {
                    bfs++;
                } else if (a[i] > a[j]) {
                    bfb++;
                }
            }
            int afb = 0;
            int afs = 0;
            for (int k = j + 1; k < n; k++) {
                if (a[k] > a[j]) {
                    afb++;
                } else if (a[k] < a[j]) {
                    afs++;
                }
            }
            r += afb * bfs;
            r += afs * bfb;
        }
        return r;
    }
}