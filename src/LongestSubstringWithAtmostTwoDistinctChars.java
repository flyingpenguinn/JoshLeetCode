import java.util.HashMap;
import java.util.Map;

/*
LC#159
Given a string s , find the length of the longest substring t  that contains at most 2 distinct characters.

Example 1:

Input: "eceba"
Output: 3
Explanation: t is "ece" which its length is 3.
Example 2:

Input: "ccaabbb"
Output: 5
Explanation: t is "aabbb" which its length is 5.
 */
public class LongestSubstringWithAtmostTwoDistinctChars {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int h = -1;
        int l = 0;
        Map<Character, Integer> map = new HashMap<>();
        int max = 0;
        while (true) {
            if (map.size() <= 2) {
                max = Math.max(max, h - l + 1);

                h++;
                if (h == s.length()) {
                    break;
                }
                update(map, s.charAt(h), 1);
            } else {
                update(map, s.charAt(l++), -1);
            }

        }
        return max;
    }

    void update(Map<Character, Integer> map, char k, int v) {
        if (v == 0) {
            return;
        }
        int nv = map.getOrDefault(k, 0) + v;
        ;
        if (nv <= 0) {
            map.remove(k);
        } else {
            map.put(k, nv);
        }
    }
}
