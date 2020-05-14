import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
LC#1371
Given the string s, return the size of the longest substring containing each vowel an even number of times. That is, 'a', 'e', 'i', 'o', and 'u' must appear an even number of times.



Example 1:

Input: s = "eleetminicoworoep"
Output: 13
Explanation: The longest substring is "leetminicowor" which contains two each of the vowels: e, i and o and zero of the vowels: a and u.
Example 2:

Input: s = "leetcodeisgreat"
Output: 5
Explanation: The longest substring is "leetc" which contains two e's.
Example 3:

Input: s = "bcbcbc"
Output: 6
Explanation: In this case, the given string "bcbcbc" is the longest because all vowels: a, e, i, o and u appear zero times.


Constraints:

1 <= s.length <= 5 x 10^5
s contains only lowercase English letters.
 */
public class FindLongestSubstringContainingVowelsEven {
    // use bit masks to form a pattern for each vowel occurence: even = 0, odd =1. then the pattern is a number. map find this number
    String vow = "aeiou";
    int[] vm = new int[5];

    public int findTheLongestSubstring(String s) {
        int[] map = new int[64];
        Arrays.fill(map, -1);
        int max = 0;
        int cur = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int index = vow.indexOf(c);
            if (index != -1) {
                vm[index]++;
                if (vm[index] % 2 == 1) {
                    // was 0 before
                    cur |= (1 << offset(c));
                } else {
                    // was 1 before. now clear the bit up
                    cur ^= (1 << offset(c));
                }
            }
            if (cur == 0) {
                max = Math.max(max, i + 1);
            } else {
                int before = map[cur];
                if (before != -1) {
                    max = Math.max(max, i - before);
                } else {
                    map[cur] = i;
                }
            }
        }
        return max;
    }

    private int offset(char c) {
        return vow.indexOf(c);
    }
}
