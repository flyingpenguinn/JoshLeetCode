import java.util.*;

/*

LC#1062
Given a string S, find out the length of the longest repeating substring(s). Return 0 if no repeating substring exists.



Example 1:

Input: "abcd"
Output: 0
Explanation: There is no repeating substring.
Example 2:

Input: "abbaba"
Output: 2
Explanation: The longest repeating substrings are "ab" and "ba", each of which occurs twice.
Example 3:

Input: "aabcaabdaab"
Output: 3
Explanation: The longest repeating substring is "aab", which occurs 3 times.
Example 4:

Input: "aaaaa"
Output: 4
Explanation: The longest repeating substring is "aaaa", which occurs twice.


Note:

The string S consists of only lowercase English letters from 'a' - 'z'.
1 <= S.length <= 1500
 */
public class LongestRepeatingSubstring {

    // On2 suffix array... could have used hashset + rolling hash for On
    public int longestRepeatingSubstring(String s) {

        int n = s.length();
        String[] ss = new String[n];

        for (int i = 0; i < n; i++) {
            ss[i] = s.substring(i);
        }

        Arrays.sort(ss);
        int max = 0;
        for (int i = 0; i < n - 1; i++) {
            if (ss[i].length() <= max || ss[i + 1].length() <= max) {
                continue;
            }
            max = Math.max(max, comlen(ss[i], ss[i + 1]));
        }
        return max;
    }

    int comlen(String s1, String s2) {
        int i = 0;
        for (; i < s1.length() && i < s2.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                break;
            }
        }
        return i;
    }

    public static void main(String[] args) {
        System.out.println(new LrsRollingHash().longestRepeatingSubstring("banana"));
    }
}

class LrsDp {
    // on2
    // dpij means the longest common substring for starting point i and j
    // note we scan i backward!  scan i backward because we depend on i+1. j 's order doesnt really matter...
    public int longestRepeatingSubstring(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        int max = 0;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i - 1; j >= 0; j--) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (i + 1 < n && j + 1 < n) {
                        dp[i][j] = dp[i + 1][j + 1] + 1;
                    } else {
                        dp[i][j] = 1;
                    }
                } else {
                    dp[i][j] = 0;
                }
                max = Math.max(max, dp[i][j]);
            }
        }
        return max;
    }
}

class LrsBinarySearch {
    // O n^2logn
    public int longestRepeatingSubstring(String s) {
        int l = 1;
        int u = s.length();
        while(l<=u){
            int mid = l+(u-l)/2;
            if(repeat(s, mid)){
                l = mid+1;
            }else{
                u = mid-1;
            }
        }
        return u;
    }

    private boolean repeat(String s, int m){
        Set<String> set = new HashSet<>();
        for(int i=0; i+m-1<s.length(); i++){
            String sub = s.substring(i, i+m);
            if(set.contains(sub)){
                return true;
            }
            set.add(sub);
        }
        return false;
    }
}

class LrsRollingHash {
    public int longestRepeatingSubstring(String s) {
        int l = 1;
        int u = s.length();
        while(l<=u){
            int mid = l+(u-l)/2;
            if(repeat(s, mid)){
                l = mid+1;
            }else{
                u = mid-1;
            }
        }
        return u;
    }

    private int toint(char c){
        return c-'a'+1;
    }
    private int magic = 31;
    private long mod = 1000000007;
    private boolean repeat(String s, int m){
        long base = 1L;
        long hash = 0L;
        Map<Long, List<Integer>> map = new HashMap<>();
        for(int i=0; i<s.length(); i++){
            hash = hash*magic + toint(s.charAt(i)); // 1 to 26
            hash %= mod;
            int head = i-m+1;
            if(head>=0){
                List<Integer> cur = map.get(hash);
                if(cur != null){
                    for(int e: cur){
                        if(s.substring(head, i+1).equals(s.substring(e-m+1, e+1))){
                            return true;
                        }
                    }
                }
                map.computeIfAbsent(hash, k-> new ArrayList<> ()).add(i);
                hash -= base * toint(s.charAt(head));
                hash %= mod;
                if(hash<0){
                    hash += mod;
                }
            }else{
                base *= magic;
                base %= mod;
            }
        }
        return false;
    }
}