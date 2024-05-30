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
        int[] left = findlefts(a);
        long[] dp = new long[n];
        long res = 0;
        for (int i = 0; i < n; i++) {
            if (left[i] == -1) {
                dp[i] = (i + 1) * a[i];
            } else {
                dp[i] = dp[left[i]] + (i - left[i]) * a[i];
            }
            dp[i] %= mod;
            res += dp[i];
            res %= mod;
        }
        return (int) res;
    }

    // nearest small element on the left. the popped one is going to be assigned to i
    // can also go from left to right and merge this stack stuff with dp assignment
    private int[] findlefts(int[] a) {
        Deque<Integer> st = new ArrayDeque<>();
        int n = a.length;
        int[] res = new int[n];
        Arrays.fill(res, -1);
        for (int i = n - 1; i >= 0; i--) {
            while (!st.isEmpty() && a[st.peek()] > a[i]) {
                res[st.pop()] = i;
            }
            st.push(i);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] a = {71, 55, 82, 55};
        System.out.println(new SumOfSubarrayMinimums().sumSubarrayMins(a));
    }
}

class SumSubarrayMinsAnotherWay {
    // first strictly less on the left, and first <= on the right. note can't be both strict, and can't be both <=
    public int sumSubarrayMins(int[] a) {
        int n = a.length;
        Deque<Integer> st = new ArrayDeque<>();
        int[] left = new int[n];
        int[] right = new int[n];

        for (int i = 0; i < n; ++i) {
            left[i] = -1;
            right[i] = n;
        }

        for (int i = 0; i < n; ++i) {
            while (!st.isEmpty() && a[st.peek()] >= a[i]) {
                st.pop();
            }
            if (!st.isEmpty()) {
                left[i] = st.peek();
            }
            st.push(i);
        }

        st.clear();

        for (int i = n - 1; i >= 0; --i) {
            while (!st.isEmpty() && a[st.peek()] > a[i]) {
                st.pop();
            }
            if (!st.isEmpty()) {
                right[i] = st.peek();
            }
            st.push(i);
        }

        long res = 0;
        long Mod = (long) 1e9 + 7;
        for (int i = 0; i < n; ++i) {
            long ln = i - left[i];
            long rn = right[i] - i;
            long cur = ln * rn * a[i];
            res += cur;
            res %= Mod;
        }
        return (int) res;
    }
}
