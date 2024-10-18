import java.util.ArrayDeque;
import java.util.Deque;

/*
LC#1405
A string is called happy if it does not have any of the strings 'aaa', 'bbb' or 'ccc' as a substring.

Given three integers a, b and c, return any string s, which satisfies following conditions:

s is happy and longest possible.
s contains at most a occurrences of the letter 'a', at most b occurrences of the letter 'b' and at most c occurrences of the letter 'c'.
s will only contain 'a', 'b' and 'c' letters.
If there is no such string s return the empty string "".



Example 1:

Input: a = 1, b = 1, c = 7
Output: "ccaccbcc"
Explanation: "ccbccacc" would also be a correct answer.
Example 2:

Input: a = 2, b = 2, c = 1
Output: "aabbc"
Example 3:

Input: a = 7, b = 1, c = 0
Output: "aabaa"
Explanation: It's the only correct answer in this case.


Constraints:

0 <= a, b, c <= 100
a + b + c > 0
 */
public class LongestHappyString {
    // always picked the most frequent one if we can.
    // if current max - put < 2nd max, then dont pick 2nd max, use it as the max in next round
    private StringBuilder res = new StringBuilder();
    public String longestDiverseString(int a, int b, int c) {
        dfs(a, b, c, 'a', 'b', 'c');
        return res.toString();
    }

    private void dfs(int a, int b, int c, char ca, char cb, char cc){

        if(a<b){
            dfs(b, a, c, cb, ca, cc);
            return;
        }
        if(a<c){
            dfs(c, b, a, cc, cb, ca);
            return;
        }
        if(b<c){
            dfs(a, c, b, ca, cc, cb);
            return;
        }
        // System.out.println(a+" "+b+" "+c);
        if(b==0){
            int puta = Math.min(2, a);
            while(puta>0){
                res.append(ca);
                --puta;
            }
            return;
        }
        int puta = Math.min(2, a);
        int putb = a-puta>=b? 1: 0; // we will use b as dominant one next time
        a -= puta;
        b -= putb;
        while(puta>0){
            res.append(ca);
            --puta;
        }
        while(putb>0){
            res.append(cb);
            --putb;
        }
        dfs(a, b, c, ca, cb, cc);
    }
}
