import base.ArrayUtils;

/*
LC#918
Given a circular array C of integers represented by A, find the maximum possible sum of a non-empty subarray of C.

Here, a circular array means the end of the array connects to the beginning of the array.  (Formally, C[i] = A[i] when 0 <= i < A.length, and C[i+A.length] = C[i] when i >= 0.)

Also, a subarray may only include each element of the fixed buffer A at most once.  (Formally, for a subarray C[i], C[i+1], ..., C[j], there does not exist i <= k1, k2 <= j with k1 % A.length = k2 % A.length.)



Example 1:

Input: [1,-2,3,-2]
Output: 3
Explanation: Subarray [3] has maximum sum 3
Example 2:

Input: [5,-3,5]
Output: 10
Explanation: Subarray [5,5] has maximum sum 5 + 5 = 10
Example 3:

Input: [3,-1,2,-1]
Output: 4
Explanation: Subarray [2,-1,3] has maximum sum 2 + (-1) + 3 = 4
Example 4:

Input: [3,-2,2,-3]
Output: 3
Explanation: Subarray [3] and [3,-2,2] both have maximum sum 3
Example 5:

Input: [-2,-3,-1]
Output: -1
Explanation: Subarray [-1] has maximum sum -1


Note:

-30000 <= A[i] <= 30000
1 <= A.length <= 30000
 */
public class MaxiumSumCircularSubarray {

    // each circular subarray len must be <=n. note this is different from k concatenate subarray where we can freely concat prefix and suffix
    public int maxSubarraySumCircular(int[] a) {
        int kadane = Integer.MIN_VALUE;
        int maxe = 0;
        int n = a.length;
        int[] maxpref = new int[n];
        int pref = 0;
        for (int i = 0; i < n; i++) {
            maxe = Math.max(a[i], a[i] + maxe);
            kadane = Math.max(kadane, maxe);
            pref += a[i];
            maxpref[i] = i == 0 ? pref : Math.max(maxpref[i - 1], pref);
        }
        int suffix = 0;
        int maxsuff = Integer.MIN_VALUE;
        int maxcirc = Integer.MIN_VALUE;
        for (int i = n - 1; i >= 0; i--) {
            suffix += a[i];
            maxsuff = Math.max(suffix, maxsuff);
            int cmax = i == 0 ? maxsuff : maxsuff + maxpref[i - 1];
            maxcirc = Math.max(cmax, maxcirc);
        }
        return Math.max(kadane, maxcirc);
    }

    public static void main(String[] args) {
        System.out.println(new MaximumSumCircularSubarrayOnePass().maxSubarraySumCircular(ArrayUtils.read1d("[-2, -3, -1]")));
    }
}

// the folding case is sum - min subarray of the original one
class MaximumSumCircularSubarrayOnePass {
    public int maxSubarraySumCircular(int[] a) {
        if (a == null) {
            return 0;
        }
        int maxe = 0;
        int mine = 0;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int n = a.length;
        int all = 0;
        for (int i = 0; i < n; i++) {
            maxe = Math.max(maxe + a[i], a[i]);
            mine = Math.min(mine + a[i], a[i]);
            max = Math.max(max, maxe);
            min = Math.min(min, mine);
            all += a[i];
        }
        if (min == all) {
            // all <0, the only edge case
            return max;
        }
        return Math.max(max, all - min);
    }
}
