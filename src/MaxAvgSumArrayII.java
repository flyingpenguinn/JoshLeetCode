import base.ArrayUtils;

/*
LC#644
Given an array consisting of n integers, find the contiguous subarray whose length is greater than or equal to k that has the maximum average value. And you need to output the maximum average value.

Example 1:
Input: [1,12,-5,-6,50,3], k = 4
Output: 12.75
Explanation:
when length is 5, maximum average value is 10.8,
when length is 6, maximum average value is 9.16667.
Thus return 12.75.
Note:
1 <= k <= n <= 10,000.
Elements of the given array will be in range [-10,000, 10,000].
The answer with the calculation error less than 10-5 will be accepted.
 */
public class MaxAvgSumArrayII {
    // from avg to subarray sum: (a[i]+...a[j])/(j-i+1) >= m => (ai-m) + ...+(a[j]-m)>=0
    // hence converting to whether there is an suabarray sum >=0. doable in O(n) time
    // note if certain double is good, anything <= it will be ok too, as we check if there is a subarray length>=k that can do it.
    // so we binary search on the mid value
    public double findMaxAverage(int[] a, int k) {
        int n = a.length;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            min = Math.min(min, a[i]);
            max = Math.max(max, a[i]);
        }
        double l = min;
        double u = max;
        while ((u - l) >= 0.000001) {
            double m = (l + u) / 2.0;
            if (isgood(a, m, k)) {
                l = m;
            } else {
                u = m;
            }
        }
        return l;
    }

    // there is a subarray whose length >=k that has avg >= m
    private boolean isgood(int[] a, double m, int k) {
        int n = a.length;
        double[] sums = new double[n];
        double min = Integer.MAX_VALUE;
        double sum = 0.0;
        for (int i = 0; i < n; i++) {
            sum += a[i] - m;
            // start from 0
            if (sum >= 0 && i + 1 >= k) {
                return true;
            }
            // just need min and just put those <= i-k+1 into it
            if (sum - min >= 0) {
                return true;
            }
            sums[i] = sum;
            if (i - k + 1 >= 0) {
                min = Math.min(min, sums[i - k + 1]);
            }
        }
        return false;
    }


    public static void main(String[] args) {
        System.out.println(new MaxAvgSumArrayII().findMaxAverage(ArrayUtils.read1d("8,9,3,1,8,3,0,6,9,2"), 8));
    }
}

