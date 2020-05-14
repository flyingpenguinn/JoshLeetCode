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
    // pay attention to the dealing of >=limit (n-k numbers total) ! once we reached limit, cut the number short
    public String removeKdigits(String num, int k) {
        char[] s = num.toCharArray();
        int n = s.length;
        if (k >= n) {
            return "0";
        }
        Deque<Integer> st = new ArrayDeque<>();
        int oldk = k;
        for (int i = 0; i < n; i++) {
            int v = s[i] - '0';
            while (!st.isEmpty() && k > 0 && st.peekLast() > v) {
                // must be >, otherwise we throw away good eq numbers and give the bigger numbers after v another chance
                st.pollLast();
                k--;
            }
            st.offerLast(s[i] - '0');
        }
        StringBuilder sb = new StringBuilder();
        int len = Math.min(n - oldk, st.size());
        while (!st.isEmpty() && st.peekFirst() == 0) {
            st.pollFirst();
        }
        while (!st.isEmpty() && sb.length() < len) {
            sb.append(st.pollFirst());
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }

    public static void main(String[] args) {

        System.out.println(new RemoveKDigits().removeKdigits("9999999999991", 8));
        System.out.println(new RemoveKDigits().removeKdigits("43214321", 4));
        System.out.println(new RemoveKDigits().removeKdigits("10", 1));
        System.out.println(new RemoveKDigits().removeKdigits("5337", 2));

    }
}

