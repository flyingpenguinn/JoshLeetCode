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
    int Mod = 1000000007;

    // key is to get the nearest smaller on the left
    // each i has contribution from 2 segments: 0...j..i, j<i.
    // contri= dp[j]+(i-j)*a[i]
    public int sumSubarrayMins(int[] a) {
        // store index,mono decrease
        Deque<Integer> stack = new ArrayDeque<>();
        int n = a.length;
        // sum of mins ending at i
        long[] dp = new long[n];
        long r = 0L;
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && a[stack.peek()] > a[i]) {
                stack.pop();
            }
            int left = stack.isEmpty() ? -1 : stack.peek();
            long cur = (i - left) * a[i];
            long bf = left == -1 ? 0 : dp[left];
            dp[i] = (cur + bf) % Mod;
            r = (r + dp[i]) % Mod;
            stack.push(i);
        }
        return (int) r;
    }

    public static void main(String[] args) {
        int[] a = {71, 55, 82, 55};
        System.out.println(new SumOfSubarrayMinimums().sumSubarrayMins(a));
    }
}
