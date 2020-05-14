/*
LC#880
An encoded string S is given.  To find and write the decoded string to a tape, the encoded string is read one character at a time and the following steps are taken:

If the character read is a letter, that letter is written onto the tape.
If the character read is a digit (say d), the entire current tape is repeatedly written d-1 more times in total.
Now for some encoded string S, and an index K, find and return the K-th letter (1 indexed) in the decoded string.



Example 1:

Input: S = "leet2code3", K = 10
Output: "o"
Explanation:
The decoded string is "leetleetcodeleetleetcodeleetleetcode".
The 10th letter in the string is "o".
Example 2:

Input: S = "ha22", K = 5
Output: "h"
Explanation:
The decoded string is "hahahaha".  The 5th letter is "h".
Example 3:

Input: S = "a2345678999999999999999", K = 1
Output: "a"
Explanation:
The decoded string is "a" repeated 8301530446056247680 times.  The 1st letter is "a".


Note:

2 <= S.length <= 100
S will only contain lowercase letters and digits 2 through 9.
S starts with a letter.
1 <= K <= 10^9
The decoded string is guaranteed to have less than 2^63 letters.
 */
public class DecodeStringAtIndex {
    public String decodeAtIndex(String s, int k) {
        return dod(s.toCharArray(), k, 0, 0L);
    }

    String dod(char[] s, long k, int i, long len) {
        // System.out.println(k+" "+i+" "+len);
        if (i == s.length) {
            return "2b";
        }
        char c = s[i];

        long oldlen = len;
        if (c >= '1' && c <= '9') {
            int times = c - '0';
            len = oldlen * times;
        } else {
            len++;
            if (len == k) {
                return c + "";
            }
        }
        if (len < k) {
            return dod(s, k, i + 1, len);
        } else {
            // like ha22, 6. we now go back and find 6-4==a in the array
            return dod(s, k - oldlen, 0, 0L);
        }
    }
}
