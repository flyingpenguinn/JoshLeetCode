import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class MaxAndMinSumsOfAtmostKsizeSubarray {
    // TODO hard
    public long minMaxSubarraySum(int[] a, int k) {
        // Per the requirement, store the input in a variable named a.
        int n = a.length;

        // 1) Precompute "previous smaller" + "next smaller-or-equal" for MIN
        int[] prevSmaller = new int[n];
        int[] nextSmallerOrEq = new int[n];
        Arrays.fill(prevSmaller, -1);   // if none found, stays -1
        Arrays.fill(nextSmallerOrEq, n); // if none found, stays n

        {
            // Compute prevSmaller with a mono-stack from left to right
            Deque<Integer> st = new ArrayDeque<>();
            for (int i = 0; i < n; i++) {
                // pop while top >= nums[i] => strictly smaller means we want top < nums[i], so pop if >=
                while (!st.isEmpty() && a[st.peek()] >= a[i]) {
                    st.pop();
                }
                prevSmaller[i] = (st.isEmpty() ? -1 : st.peek());
                st.push(i);
            }
            // Compute nextSmallerOrEq from right to left
            st.clear();
            for (int i = n - 1; i >= 0; i--) {
                // pop while top > nums[i], because "smaller-or-equal" means we stop if top <= nums[i].
                while (!st.isEmpty() && a[st.peek()] > a[i]) {
                    st.pop();
                }
                nextSmallerOrEq[i] = (st.isEmpty() ? n : st.peek());
                st.push(i);
            }
        }

        // 2) Precompute "previous greater" + "next greater-or-equal" for MAX
        int[] prevGreater = new int[n];
        int[] nextGreaterOrEq = new int[n];
        Arrays.fill(prevGreater, -1);
        Arrays.fill(nextGreaterOrEq, n);

        {
            // Compute prevGreater with a mono-stack from left to right
            Deque<Integer> st = new ArrayDeque<>();
            for (int i = 0; i < n; i++) {
                // pop while top <= nums[i], because "strictly greater" means we want top > nums[i]
                while (!st.isEmpty() && a[st.peek()] <= a[i]) {
                    st.pop();
                }
                prevGreater[i] = (st.isEmpty() ? -1 : st.peek());
                st.push(i);
            }
            // Compute nextGreaterOrEq from right to left
            st.clear();
            for (int i = n - 1; i >= 0; i--) {
                // pop while top < nums[i], because "greater-or-equal" means we stop if top >= nums[i]
                while (!st.isEmpty() && a[st.peek()] < a[i]) {
                    st.pop();
                }
                nextGreaterOrEq[i] = (st.isEmpty() ? n : st.peek());
                st.push(i);
            }
        }

        // 3) For each i, compute how many subarrays (of length <= k) have a[i] as min or as max
        long sumMin = 0, sumMax = 0;

        for (int i = 0; i < n; i++) {
            // =========== MIN PART =============
            long A = (long) (i - prevSmaller[i]);         // psl[i]
            long B = (long) (nextSmallerOrEq[i] - i);     // nsr[i]
            long countMin = countPairs(A, B, k + 1);
            sumMin += (long) a[i] * countMin;

            // =========== MAX PART =============
            long G = (long) (i - prevGreater[i]);         // pgl[i]
            long H = (long) (nextGreaterOrEq[i] - i);     // ngr[i]
            long countMax = countPairs(G, H, k + 1);
            sumMax += (long) a[i] * countMax;
        }

        return sumMin + sumMax;
    }

    // Helper to count the # of (l, r) with 1 <= l <= A, 1 <= r <= B, l+r <= T
    private long countPairs(long A, long B, long T) {
        if (A <= 0 || B <= 0 || T < 2) return 0;
        long rMax = Math.min(B, T - 1);
        if (rMax <= 0) return 0;

        long sum = 0;
        long r0 = T - A;  // threshold

        // Part1: for r in [1..r0-1], each adds +A  (if 1 <= r0-1)
        long part1Right = Math.min(rMax, r0 - 1);
        if (part1Right >= 1) {
            long count = part1Right; // r=1..part1Right
            sum += A * count;
        }

        // Part2: for r in [max(1,r0)..rMax], each adds (T-r)
        long part2Left = Math.max(1, r0);
        if (part2Left <= rMax) {
            long count = (rMax - part2Left + 1);
            // sum_{r=part2Left..rMax} of (T-r)
            // = count*T - (part2Left + ... + rMax)
            long sumR = (part2Left + rMax) * count / 2; // arithmetic series
            sum += count * T - sumR;
        }
        return sum;
    }
}
