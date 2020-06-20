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
        int low = 0;
        int high = -1;
        Map<Integer, Integer> m = new HashMap<>();
        int r = 0;
        while (true) {
            if (m.size() <= t) {
                high++;
                if (high == s.length()) {
                    r = Math.max(r, high - low); // dont forget here when it extends to the end
                    break;
                }
                update(m, s.charAt(high), 1);
            } else {
                r = Math.max(r, high - low);
                update(m, s.charAt(low), -1);
                low++;
            }
        }
        return r;
    }

    void update(Map<Integer, Integer> m, int k, int d) {
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
