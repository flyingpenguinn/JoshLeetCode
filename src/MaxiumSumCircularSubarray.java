import base.ArrayUtils;

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
        int n = a.length;
        int maxe = 0;
        int mine = 0;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            maxe = Math.max(a[i], maxe + a[i]);
            mine = Math.min(a[i], mine + a[i]);
            max = Math.max(max, maxe);
            min = Math.min(min, mine);
            sum += a[i];
        }
        if (sum != min) {
            // all neg, just return max, becaues empty is not allowed
            return Math.max(max, sum - min);
        } else {
            return max;
        }
    }
}
