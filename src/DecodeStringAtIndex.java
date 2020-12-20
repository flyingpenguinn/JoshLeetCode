import java.util.ArrayDeque;
import java.util.Deque;

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
    public String decodeAtIndex(String s, long k) {
        int n = s.length();
        Deque<long[]> st = new ArrayDeque<>();
        // index of the digit, and the length at that digit
        st.push(new long[]{-1, 0});
        for(int i=0; i<=n; i++){
            char c = i==n?'1':s.charAt(i);
            if(!Character.isDigit(c)){
                continue;
            }
            long cd = c-'0';
            long curlen = i-st.peek()[0]-1;
            long lastlen = st.peek()[1];
            long newlen = (lastlen+curlen)*cd;
            st.push(new long[]{i, newlen});
            if(newlen>=k){
                break;
            }
        }
        while(!st.isEmpty()){
            long i = st.pop()[0];
            long lastlen = st.peek()[1];
            long lasti = st.peek()[0];
            long curlen = i-lasti-1;
            long seg = lastlen+curlen;
            k = scale(k, seg);
            // which part is k landing on: in lastlen, or after lastlen in curlen area
            if(k>lastlen){
                // here curlen wont be 0 because if so seg == lastlen and we know k <=seg
                return s.charAt((int)(lasti+k-lastlen))+"";
            }
            // otherwise, we need to find k in lastlen, so let it pop and continue
        }
        return "";
    }

    private long scale(long a, long b){
        long rt = a%b;
        return rt==0? b: rt;
    }
}
