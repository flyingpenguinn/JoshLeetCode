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
        int j = 0;
        int n = s.length();
        int[] counter = new int[256];
        // j..i-1 is good
        int res = 0;
        for(int i=0; i<n; ++i){
            char c = s.charAt(i);
            ++counter[c];
            // only possible violator is c
            while(counter[c]>1){
                --counter[s.charAt(j++)];
            }
            res = Math.max(res, i-j+1);
        }
        return res;
    }



    public static void main(String[] args) {
        System.out.println(new LongestSubstringWithoutRepeat().lengthOfLongestSubstring("cdd"));
        System.out.println(new LongestSubstringWithoutRepeat().lengthOfLongestSubstring(" "));
        System.out.println(new LongestSubstringWithoutRepeat().lengthOfLongestSubstring("abcabcbb"));
    }
}

class LongestSubstringWithoutRepeatEeasier {
    // think from the point of every i, the substrings ending at i. the pre that we can start from must be after the last appearance of char of i
    public int lengthOfLongestSubstring(String s) {
        int[] last = new int[255];
        Arrays.fill(last, -1);
        int pre = -1; // we start after -1 to begin with
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            pre = Math.max(pre, last[c]);
            max = Math.max(max, i - pre);
            last[c] = i;
        }
        return max;
    }
}
