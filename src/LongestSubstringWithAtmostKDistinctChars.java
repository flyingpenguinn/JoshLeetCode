import java.util.HashMap;
import java.util.Map;

/*
LC#340
Given a string, find the length of the longest substring T that contains at most k distinct characters.

Example 1:

Input: s = "eceba", k = 2
Output: 3
Explanation: T is "ece" which its length is 3.
Example 2:

Input: s = "aa", k = 1
Output: 2
Explanation: T is "aa" which its length is 2.
 */
public class LongestSubstringWithAtmostKDistinctChars {
    // typical two pointer template...
    public int lengthOfLongestSubstringKDistinct(String s, int t) {
        int n = s.length();
        Map<Character, Integer> m = new HashMap<>();
        int res = 0;
        int start = 0;
        for (int i = 0; i < n; i++) {
            update(m, s.charAt(i), 1);
            while (m.keySet().size() > t) {
                update(m, s.charAt(start++), -1);
            }
            res = Math.max(res, i - start + 1);
        }
        return res;
    }

    private void update(Map<Character, Integer> m, char k, int d) {
        int nv = m.getOrDefault(k, 0) + d;
        if (nv <= 0) {
            m.remove(k);
        } else {
            m.put(k, nv);
        }
    }

    public static void main(String[] args) {
        System.out.println(new LongestSubstringWithAtmostKDistinctChars().lengthOfLongestSubstringKDistinct("eceba", 3));
    }
}
