import java.util.*;

/*
LC#943
Given an array A of strings, find any smallest string that contains each string in A as a substring.

We may assume that no string in A is substring of another string in A.


Example 1:

Input: ["alex","loves","leetcode"]
Output: "alexlovesleetcode"
Explanation: All permutations of "alex","loves","leetcode" would also be accepted.
Example 2:

Input: ["catg","ctaagt","gcta","ttca","atgcatc"]
Output: "gctaagttcatgcatc"


Note:

1 <= A.length <= 12
1 <= A[i].length <= 20
 */
public class FindShortestSuperstring {
    // tsp without fixed start
    // cache match results between i and j dont need to calc over and over
    // when concat subtract the overlapping part and the remaining would be the "edge length"
    String[][] dp;

    public String shortestSuperstring(String[] a) {
        int n = a.length;
        int[][] overlap = new int[n][n];
        dp = new String[n][1 << n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                overlap[i][j] = calcoverlap(a[i], a[j]);
                overlap[j][i] = calcoverlap(a[j], a[i]);
            }
        }
        String min = null;
        for (int i = 0; i < n; i++) {
            String cur = dos(i, (1 << i), overlap, a);
            if (min == null || cur.length() < min.length()) {
                min = cur;
            }
        }
        return min;
    }

    private int calcoverlap(String s, String t) {
        int overlap = 0;
        int sn = s.length();
        for (int i = sn - 1; i >= 0; i--) {
            String stub = s.substring(i, sn);
            if (t.startsWith(stub)) {
                overlap = sn - i;
            }
        }
        return overlap;
    }

    // last string was i, st is the status: 1 means used already, 0 means we can use
    private String dos(int i, int st, int[][] overlap, String[] a) {
        int n = a.length;
        int all = (1 << n) - 1;
        if (st == all) {
            return a[i];
        }
        if (dp[i][st] != null) {
            return dp[i][st];
        }
        String min = null;
        for (int j = 0; j < n; j++) {
            if (j == i) {
                continue;
            }
            if (((st >> j) & 1) == 0) {
                // can still use j
                int nst = (st | (1 << j));
                String later = dos(j, nst, overlap, a);
                String cur = a[i].substring(0, a[i].length() - overlap[i][j]) + later;
                if (min == null || cur.length() < min.length()) {
                    min = cur;
                }
            }
        }
        dp[i][st] = min;
        return min;
    }


    public static void main(String[] args) {
        //  String[] strs = {"catg", "ctaagt", "gcta", "ttca", "atgcatc"};
        String[] strs = {"uhozqhxcqmkifljvcie", "epuhozqhxcqmkifljvci", "ugmqnepuhozqhxcqm", "iexdudtvurjkrovrhmpa", "rovrhmpaasblgaosw", "qmkifljvciexdudtv", "zsgtuowskyzphybeugm", "uowskyzphybeugmq", "qhxcqmkifljvciex"};
        // String[] strs = {"ymv", "lpkp", "ajelp", "kpx"};
        System.out.println(new FindShortestSuperstring().shortestSuperstring(strs));
    }
}
