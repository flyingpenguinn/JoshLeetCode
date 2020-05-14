/* LC#5
Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

Example 1:

Input: "babad"
Output: "bab"
Note: "aba" is also a valid answer.
Example 2:

Input: "cbbd"
Output: "bb"

https://leetcode.com/problems/longest-palindromic-substring/
 */

import java.util.Arrays;

public class LongestPalindromeSubstring {
    // for each i check i as the center, or i, i+1 as center

    // single char is always palindrome
    int maxlen = 1;
    int maxstart = 0;
    int maxend = 0;


    public String longestPalindrome(String s) {
        int n = s.length();
        if (n == 0) {
            return "";
        }
        for (int i = 0; i < n; i++) {
            int j = i - 1;
            int k = i + 1;
            processjk(s, n, j, k);
            j = i;
            k = i + 1;
            processjk(s, n, j, k);
        }
        return s.substring(maxstart, maxend + 1);
    }

    private void processjk(String s, int n, int j, int k) {

        while (j >= 0 && k < n) {
            if (s.charAt(j) == s.charAt(k)) {
                j--;
                k++;
            } else {
                break;
            }
        }
        // j+1...k-1 is the palindrome
        int len = k - 1 - (j + 1) + 1;
        if (len > maxlen) {
            maxlen = len;
            maxstart = j + 1;
            maxend = k - 1;
        }
    }

    public static void main(String[] args) {
        System.out.println(new LongestPalindromeSubstringSuffixarray().longestPalindrome("babad"));
        //  System.out.println(new LongestPalindromeSubstringSuffixarray().longestPalindrome(""));
    }
}

class LongestPalindromSubstringDp {
    // dpij = if i..j is palindrome
    public String longestPalindrome(String s) {
        int n = s.length();
        if (n == 0) {
            return "";
        }
        boolean[][] dp = new boolean[n][n];
        // need to bootstrap len ==1 and len==2
        int maxlen = 1;
        int maxstart = 0;
        int maxend = 0;
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }
        // we must boost maxlen here when we check len==2
        for (int i = 0; i + 1 < n; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = true;
                if (maxlen < 2) {
                    maxlen = 2;
                    maxstart = i;
                    maxend = i + 1;
                }
            }
        }

        for (int len = 3; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                // this is substring. so i+1, j-1 must be palindrome too. compare with subsequence problem where we just get the len of it
                if (dp[i + 1][j - 1] && s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = true;
                    if (len > maxlen) {
                        maxlen = len;
                        maxstart = i;
                        maxend = j;
                    }
                }
            }
        }
        return s.substring(maxstart, maxend + 1);
    }
}

class LongestPalindromeSubstringSuffixarray {
    // concat string, get sa
    // then compute each i and j (2*len-i or 2*len-1-i)'s lcp

    // nlogn^2 to create sa, nlogn for lcp iteration
    public String longestPalindrome(String s) {
        String ns = s + "#" + new StringBuilder(s).reverse().toString();
        int len = ns.length();
        int originallen = s.length();
        int[][] sa = createSa(ns);
        int maxl = 0;
        int maxi = 0;
        // odd len, iterate the middle
        for (int i = 0; i < originallen; i++) {
            int j = len - 1 - i;
            int lcp = getlcp(i, j, sa, originallen);
            if (lcp + lcp - 1 > maxl) {
                maxl = lcp + lcp - 1;
                maxi = i - (lcp - 1);
            }
        }
        // even len, iterate the right start
        for (int i = 1; i < originallen; i++) {
            int j = len - i;
            // logn
            int lcp = getlcp(i, j, sa, originallen);
            if (2 * lcp > maxl) {
                maxl = 2 * lcp;
                maxi = i - lcp;
            }
        }
        return s.substring(maxi, maxi + maxl);
    }

    private int getlcp(int i, int j, int[][] sa, int n) {
        int step = sa.length - 1;
        if (i == j) {
            return n - i;
        }
        int len = 0;
        while (step >= 0) {
            if (1 << step <= n) {
                // only check len <=n otherwise we are crossing the boundary
                if (i < n && j < 2 * n + 1 && sa[step][i] == sa[step][j]) {
                    int curlen = 1 << step;
                    len += curlen;
                    i += curlen;
                    j += curlen;
                }
            }
            step--;
        }
        return len;
    }

    // find biggest suffix. use suffix array
    class Entry implements Comparable<Entry> {
        int v1;
        int v2;
        // record the "i" in the algo, i.e. the starting point
        int start;

        public Entry(int v1, int v2, int start) {
            this.v1 = v1;
            this.v2 = v2;
            this.start = start;
        }

        @Override
        public int compareTo(Entry o) {
            if (v1 != o.v1) {
                return Integer.compare(v1, o.v1);
            } else {
                return Integer.compare(v2, o.v2);
            }
        }
    }

    public int[][] createSa(String s) {
        int n = s.length();
        int m = (int) (Math.log(n) / Math.log(2) + 1.5);
        int[][] p = new int[m + 1][n];
        Entry[] l = new Entry[n];

        for (int i = 0; i < n; i++) {
            int v = s.charAt(i) - 'a';
            //allocate initial rank to each char
            p[0][i] = v;
        }
        int step = 1;
        for (int len = 1; len <= n; len *= 2, step++) {
            for (int i = 0; i < n; i++) {
                // we have results for i..i+len-1 and i+len...i+2len-1 for previous step. now merge the scores for sorting
                // if overflow then minimal value -1
                l[i] = new Entry(p[step - 1][i], i + len < n ? p[step - 1][i + len] : -1, i);
            }
            Arrays.sort(l);
            for (int i = 0; i < n; i++) {
                boolean same = i > 0 && l[i].compareTo(l[i - 1]) == 0;
                // either stay with before or a new one. start here can be inconsecutive but it's fine
                int index = l[i].start;
                p[step][index] = same ? p[step][l[i - 1].start] : i;
            }
        }
        return p;
    }


}