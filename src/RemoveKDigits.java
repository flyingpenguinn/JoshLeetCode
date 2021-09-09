import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/*
LC#402
Given a non-negative integer num represented as a string, remove k digits from the number so that the new number is the smallest possible.

Note:
The length of num is less than 10002 and will be ≥ k.
The given num does not contain any leading zero.
Example 1:

Input: num = "1432219", k = 3
Output: "1219"
Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
Example 2:

Input: num = "10200", k = 1
Output: "200"
Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
Example 3:

Input: num = "10", k = 2
Output: "0"
Explanation: Remove all the digits from the number and it is left with nothing which is 0.
 */
public class RemoveKDigits {
    // using the same template as createmaxnumbers
    public String removeKdigits(String s, int k) {
        int n = s.length();
        Deque<Character> st = new ArrayDeque<>();
        for(int i=0; i<n ; ++i){
            while(!st.isEmpty() && st.peekLast() > s.charAt(i) && k>0){
                // st increasing, get rid of the back
                st.pollLast();
                --k;
            }
            st.offerLast(s.charAt(i));
        }
        // st is increasing, so from the back
        while(k>0){
            st.pollLast();
            --k;
        }
        while(!st.isEmpty() && st.peekFirst()=='0'){
            st.pollFirst();
        }
        StringBuilder res = new StringBuilder();
        while(!st.isEmpty()){
            res .append(st.pollFirst());
        }
        return res.length()==0? "0": res.toString();
    }
}

