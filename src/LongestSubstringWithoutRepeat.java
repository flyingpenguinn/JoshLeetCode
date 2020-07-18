import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
LC#3
Given a string, find the length of the longest substring without repeating characters.

Example 1:

Input: "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.
Example 2:

Input: "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
Example 3:

Input: "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
             Note that the answer must be a substring, "pwke" is a subsequence and not a substring.

https://leetcode.com/problems/longest-substring-without-repeating-characters/
 */
public class LongestSubstringWithoutRepeat {
    // formulaic two pointers solution
    public int lengthOfLongestSubstring(String s) {
        int h = -1;
        int l = 0;
        Map<Character, Integer> map = new HashMap<>();
        int max = 0;
        while (true) {
            if (map.size() == h - l + 1) {
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

    public static void main(String[] args) {
        System.out.println(new LongestSubstringWithoutRepeat().lengthOfLongestSubstring("cdd"));
        System.out.println(new LongestSubstringWithoutRepeat().lengthOfLongestSubstring(" "));
        System.out.println(new LongestSubstringWithoutRepeat().lengthOfLongestSubstring("abcabcbb"));
    }
}

class LongestSubstringWithoutRepeatEeasier {
    // think from the point of every i, the substrings ending at i
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        int start = 0;
        Map<Character, Integer> last = new HashMap<>();
        int max = 0;
        // invariant: start...i-1 no repeat
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            Integer pre = last.get(c);
            if (pre != null && pre + 1 > start) {
                start = pre + 1;
            }
            // now start...i has no repeat
            int cur = i - start + 1;
            max = Math.max(max, cur);
            last.put(c, i);
        }
        return max;
    }
}
