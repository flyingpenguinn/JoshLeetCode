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
        int n = s.length();
        Map<Character,Integer> m = new HashMap<>();
        int t = 2;
        int j = 0;
        int res = 0;
        // j...i-1 good, now trying i
        for(int i=0; i<n; ++i){
            char c = s.charAt(i);
            update(m, c, 1);
            while(m.size()>t){
                update(m, s.charAt(j++), -1);
            }
            // now j...i good
            int cur = i-j+1;
            res = Math.max(cur, res);
        }
        return res;
    }

    void update(Map<Character, Integer> map, char k, int v) {
        if (v == 0) {
            return;
        }
        int nv = map.getOrDefault(k, 0) + v;
        if (nv <= 0) {
            map.remove(k);
        } else {
            map.put(k, nv);
        }
    }
}
