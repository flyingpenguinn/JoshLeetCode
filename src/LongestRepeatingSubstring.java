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
    // binary search and rolling hash. rolling hash template with double hash!
    private long base = 31;
    private long mod1 = 1_000_000_007L;
    private long mod2 = 1_000_000_009L;

    public int longestRepeatingSubstring(String s) {
        int n = s.length();
        int l = 1;
        int u = n;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            Map<Long, List<Integer>> m = new HashMap<>();
            long cur1 = 0;
            long cur2 = 0;
            boolean found = false;
            long multibase1 = 1;
            long multibase2 = 1;
            for (int i = 0; i < n; ++i) {
                int cind = s.charAt(i) - 'a' + 1;
                cur1 = cur1 * base + cind;
                cur1 %= mod1;
                cur2 = cur2 * base + cind;
                cur2 %= mod2;
                long key = (cur1 << 32) ^ cur2;
                if (i - mid + 1 < 0) {
                    // or just do
                    // long multibase1 = pow(base, mid - 1, mod1);
                    // long multibase2 = pow(base, mid - 1, mod2);
                    multibase1 *= base;
                    multibase1 %= mod1;

                    multibase2 *= base;
                    multibase2 %= mod2;
                    continue;
                }
                if (m.containsKey(key)) {
                    List<Integer> pre = m.get(key);
                    for (int start1 : pre) {
                        if (s.regionMatches(i-mid+1, s, start1, mid)) {
                            found = true;
                            break;
                        }

                    }
                }
                if(found){
                    break;
                }
                m.computeIfAbsent(key, k -> new ArrayList<>()).add(i - mid + 1);
                int head = i - mid + 1;
                int headind = s.charAt(head) - 'a' + 1;
                cur1 -=  multibase1*headind;
                cur1 %= mod1;
                if (cur1 < 0) {
                    cur1 += mod1;
                }
                cur2 -=  multibase2*headind;
                cur2 %= mod2;
                if (cur2 < 0) {
                    cur2 += mod2;
                }
            }
            if (found) {
                l = mid + 1;
            } else {
                u = mid - 1;
            }
        }
        return u;
    }
}