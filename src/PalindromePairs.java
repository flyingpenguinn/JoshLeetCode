import java.util.*;

/*
LC#336
Given a list of unique words, find all pairs of distinct indices (i, j) in the given list, so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.

Example 1:

Input: ["abcd","dcba","lls","s","sssll"]
Output: [[0,1],[1,0],[3,2],[2,4]]
Explanation: The palindromes are ["dcbaabcd","abcddcba","slls","llssssll"]
Example 2:

Input: ["bat","tab","cat"]
Output: [[0,1],[1,0]]
Explanation: The palindromes are ["battab","tabbat"]
 */
public class PalindromePairs {
    public List<List<Integer>> palindromePairs(String[] w) {
        // palin + stub, store the reverses of the stub for later matching with a whole string
        // or stub+palin, find stub from a reverse map of whole string

        // rmd stores stubs without the starting palindromes, could be empty if whole string is palindrome, to match with a whole string
        Map<String, List<Integer>> rmd = new HashMap<>();
        // rm stores reversed full strings to match with a stub
        Map<String, Integer> rm = new HashMap<>();
        List<List<Integer>> r = new ArrayList<>();
        int[][][] dp = new int[w.length][][];
        for (int i = 0; i < w.length; i++) {
            int n = w[i].length();
            dp[i] = new int[n][n];
            paline(w[i], dp[i]);
            StringBuilder rsb = new StringBuilder(w[i]).reverse();
            rm.put(rsb.toString(), i);
            for (int j = 0; j < n; j++) {
                rsb.deleteCharAt(n - 1 - j);
                if (dp[i][0][j] == 1) {
                    String str = rsb.toString();
                    List<Integer> cur = rmd.getOrDefault(str, new ArrayList<>());
                    cur.add(i);
                    rmd.put(str, cur);
                }
            }
        }

        for (int i = 0; i < w.length; i++) {
            if (rmd.containsKey(w[i])) {
                for (int j : rmd.get(w[i])) {
                    tol(i, j, r);
                }
            }
            int n = w[i].length();
            StringBuilder sb = new StringBuilder(w[i]);
            for (int j = n; j >= 0; j--) {
                if (j < n) {
                    sb.deleteCharAt(j);
                }
                if (j == n || dp[i][j][n - 1] == 1) {
                    String str = sb.toString();
                    // str could be empty if the whole string is empty
                    if (rm.containsKey(str)) {
                        tol(i, rm.get(str), r);
                    }
                }
            }
        }
        return r;
    }

    private void paline(String s, int[][] dp) {
        int n = s.length();
        for (int len = 1; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                if (len == 1) {
                    dp[i][j] = 1;
                } else if (len == 2) {
                    dp[i][j] = s.charAt(i) == s.charAt(j) ? 1 : 0;
                } else if (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1] == 1) {
                    dp[i][j] = 1;
                }
            }
        }
    }


    void tol(Integer v1, Integer v2, List<List<Integer>> r) {
        if (!v1.equals(v2)) {
            List<Integer> cr = new ArrayList<>();
            cr.add(v1);
            cr.add(v2);
            r.add(cr);
        }
    }


    public static void main(String[] args) {
        PalindromePairs pp = new PalindromePairs();

        String[] input5 = {"ab", "bbba", "bba", "ba"};
        System.out.println(pp.palindromePairs(input5));


    }

}
