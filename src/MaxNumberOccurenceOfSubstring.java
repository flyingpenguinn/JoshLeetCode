import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
LC#1297
Given a string s, return the maximum number of ocurrences of any substring under the following rules:

The number of unique characters in the substring must be less than or equal to maxLetters.
The substring size must be between minSize and maxSize inclusive.


Example 1:

Input: s = "aababcaab", maxLetters = 2, minSize = 3, maxSize = 4
Output: 2
Explanation: Substring "aab" has 2 ocurrences in the original string.
It satisfies the conditions, 2 unique letters and size 3 (between minSize and maxSize).
Example 2:

Input: s = "aaaa", maxLetters = 1, minSize = 3, maxSize = 3
Output: 2
Explanation: Substring "aaa" occur 2 times in the string. It can overlap.
Example 3:

Input: s = "aabcabcab", maxLetters = 2, minSize = 2, maxSize = 3
Output: 3
Example 4:

Input: s = "abcde", maxLetters = 2, minSize = 3, maxSize = 3
Output: 0


Constraints:

1 <= s.length <= 10^5
1 <= maxLetters <= 26
1 <= minSize <= maxSize <= min(26, s.length)
s only contains lowercase English letters.
 */
public class MaxNumberOccurenceOfSubstring {

    // never need to consider maxsize, just minsize is enough because we want the max occur substring...
    public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
        int n = s.length();
        int k = minSize;
        Map<String, Integer> m = new HashMap<>();
        int res = 0;
        Map<Character,Integer> cm= new HashMap<>();
        // sliding window, ENDING at i
        for(int i=0; i<n; i++){
            update(cm, s.charAt(i),1);
            if(i-k+1>=0 && cm.keySet().size()<=maxLetters){
                String str = s.substring(i-k+1, i+1);
                int nv = m.getOrDefault(str, 0)+1;
                m.put(str, nv);
                res = Math.max(res, nv);
            }
            if(i-k+1>=0){
                update(cm, s.charAt(i-k+1), -1);
            }
        }
        //   System.out.println(m);
        return res;
    }

    private void update(Map<Character,Integer> m, char k,int d){
        int nv = m.getOrDefault(k, 0)+d;
        if(nv<=0){
            m.remove(k);
        }else{
            m.put(k, nv);
        }
    }

    public static void main(String[] args) {
        System.out.println(new MaxNumberOccurenceOfSubstring().maxFreq("aababcaab", 2, 3, 4));
    }
}

class MaxNumberOccurrenceRollingHash {
    private int mod = 1000000007;

    public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
        int n = s.length();
        Map<Long, Integer> m = new HashMap<>();
        int max = 0;
        int len = minSize;
        Map<Integer, Integer> cm = new HashMap<>();
        long hash = 0;
        long minusbase = 1;
        for (int i = 0; i < n; i++) {
            int c = toint(s.charAt(i));
            update(cm, c, 1);
            hash = hash * 26 + c;
            // System.out.println(i+" "+hash);
            hash %= mod;
            if (i - len + 1 >= 0) {
                int nv = m.getOrDefault(hash, 0) + 1;
                if (cm.size() <= maxLetters) {
                    max = Math.max(max, nv);
                }
                m.put(hash, nv);
                int hc = toint(s.charAt(i - len + 1));
                hash -= minusbase * hc;
                hash %= mod;
                if (hash < 0) {
                    hash += mod;
                }
                update(cm, hc, -1);
            } else {
                minusbase *= 26;
                minusbase %= mod;
            }
        }

        return max;
    }

    private void update(Map<Integer, Integer> m, int k, int delta) {
        int nv = m.getOrDefault(k, 0) + delta;
        if (nv <= 0) {
            m.remove(k);
        } else {
            m.put(k, nv);
        }
    }

    private int toint(char c) {
        return c - 'a' + 1;
    }
}
