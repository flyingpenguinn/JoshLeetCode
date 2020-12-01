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
    // when concat subtract the overlapping part and the remaining would be the "edge length". each time we just add the remaning part of a[j].
    // note for each starting point i we need to add a[i]
    private String[][] dp;

    public String shortestSuperstring(String[] a) {
        int n = a.length;
        String[][] g = new String[n + 1][n + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                // == a[i].length will get empty string out to cover "not sharing any prefix" case
                for (int k = 1; k <= a[i].length(); k++) {
                    String istub = a[i].substring(k);
                    if (a[j].startsWith(istub)) {
                        g[i][j] = a[j].substring(istub.length());
                        break;
                    }
                }
            }
        }
        // for the initial state we pass in n and can go to any node as starting point
        for (int i = 0; i < n; i++) {
            g[n][i] = a[i];
        }
        dp = new String[n + 1][1 << n];
        return travel(g, a, n, 0);
    }

    private String travel(String[][] g, String[] a, int i, int st) {
        int n = a.length;
        if (st + 1 == (1 << n)) {
            return "";
        }
        if (dp[i][st] != null) {
            return dp[i][st];
        }
        String res = null;
        for (int j = 0; j < n; j++) {
            if (((st >> j) & 1) == 0) {
                String cur = g[i][j] + travel(g, a, j, (st | (1 << j)));
                if (res == null || res.length() > cur.length()) {
                    res = cur;
                }
            }
        }
        dp[i][st] = res;
        return res;
    }


    public static void main(String[] args) {
        //  String[] strs = {"catg", "ctaagt", "gcta", "ttca", "atgcatc"};
        String[] strs = {"uhozqhxcqmkifljvcie", "epuhozqhxcqmkifljvci", "ugmqnepuhozqhxcqm", "iexdudtvurjkrovrhmpa", "rovrhmpaasblgaosw", "qmkifljvciexdudtv", "zsgtuowskyzphybeugm", "uowskyzphybeugmq", "qhxcqmkifljvciex"};
        // String[] strs = {"ymv", "lpkp", "ajelp", "kpx"};
        System.out.println(new FindShortestSuperstring().shortestSuperstring(strs));
    }
}
