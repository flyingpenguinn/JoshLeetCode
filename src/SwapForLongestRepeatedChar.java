import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/*
LC#1156
Given a string text, we are allowed to swap two of the characters in the string. Find the length of the longest substring with repeated characters.



Example 1:

Input: text = "ababa"
Output: 3
Explanation: We can swap the first 'b' with the last 'a', or the last 'b' with the first 'a'. Then, the longest repeated character substring is "aaa", which its length is 3.
Example 2:

Input: text = "aaabaaa"
Output: 6
Explanation: Swap 'b' with the last 'a' (or the first 'a'), and we get longest repeated character substring "aaaaaa", which its length is 6.
Example 3:

Input: text = "aaabbaaa"
Output: 4
Example 4:

Input: text = "aaaaa"
Output: 5
Explanation: No need to swap, longest repeated character substring is "aaaaa", length is 5.
Example 5:

Input: text = "abcdef"
Output: 1


Constraints:

1 <= text.length <= 20000
text consist of lowercase English characters only.
 */
public class SwapForLongestRepeatedChar {
    // find max window where max char happens all the time, or gap-1 time and we can find a good swap target outside current window
    public int maxRepOpt1(String str) {
        char[] s = str.toCharArray();
        int n = s.length;
        int[] count = new int[26];
        for(int i=0; i<n; i++){
            count[s[i]-'a']++;
        }
        int[] wc = new int[26];
        int start = 0;
        int res = 0;
        for(int i=0; i<n; i++){
            wc[s[i]-'a']++;
            while(!doable(s, i-start+1, wc, count)){
                wc[s[start]-'a']--;
                start++;
            }
            res = Math.max(res, i-start+1);
        }
        return res;
    }

    private boolean doable(char[] s, int len, int[] wc, int[] count){
        int n = s.length;
        for(int j=0; j<26; j++){
            if(wc[j]==len){
                return true;
            }
            if(wc[j] +1 == len && count[j] - wc[j]>=1 ){
                return true;
            }
        }
        return false;
    }
}
