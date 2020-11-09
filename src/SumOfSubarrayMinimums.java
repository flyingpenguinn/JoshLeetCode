import java.util.*;

/*
LC#907
Given an array of integers A, find the sum of min(B), where B ranges over every (contiguous) subarray of A.

Since the answer may be large, return the answer modulo 10^9 + 7.



Example 1:

Input: [3,1,2,4]
Output: 17
Explanation: Subarrays are [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [1,2,4], [3,1,2,4].
Minimums are 3, 1, 2, 4, 1, 1, 2, 1, 1, 1.  Sum is 17.


Note:

1 <= A.length <= 30000
1 <= A[i] <= 30000

 */
public class SumOfSubarrayMinimums {
    private int mod = 1000000007;

    // each i has contribution from 2 segments: 0...j..i, j<i.
    // contri= dp[j]+(i-j)*a[i]
    public int sumSubarrayMins(int[] a) {
        int n = a.length;
        long sum = 0;
        Deque<Integer> st = new ArrayDeque<>();
        int[] dp = new int[n];
        Deque<Integer> dq = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && a[st.peek()] >= a[i]) {
                st.pop();
            }
            int j = st.isEmpty() ? -1 : st.peek();

            dp[i] = (j == -1 ? 0 : dp[j]) + (i - j) * a[i];
            sum += dp[i];
            sum %= mod;
            st.push(i);
        }
        return (int) sum;
    }

    public static void main(String[] args) {
        int[] a = {71, 55, 82, 55};
        System.out.println(new SumOfSubarrayMinimums().sumSubarrayMins(a));
    }
}
