public class FindMaxNonDecreasingArrayLength {
    /*
key is to observe that when we have i....j, then a future k will have
    acc[j] - acc[i] <= acc[k] - acc[j]
so that acc[j] * 2 - acc[i] <= acc[k].

so we will note that for k we need to sum up from j
     */
    public int findMaximumLength(int[] nums) {
        int n = nums.length;
        long[] pref = new long[n + 1];
        long[] pre = new long[n + 2];
        long[] dp = new long[n + 1];

        for (int i = 1; i <= n; i++) {
            pref[i] = pref[i - 1] + nums[i - 1];
        }

        long i = 0;
        for (int j = 1; j <= n; j++) {
            // if we know pre[j] before just use it. otherwise we start from 0
            i = Math.max(i, pre[j]);
            dp[j] = dp[(int) i] + 1;

            int k = findFirstBigger(pref, 2 * pref[j] - pref[(int) i]);
            pre[k] = j;
        }

        return (int) dp[n];
    }

    private int findFirstBigger(long[] arr, long target) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }
}
