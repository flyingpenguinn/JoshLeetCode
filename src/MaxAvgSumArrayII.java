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
    // note if certain double is good, anything <= it will be ok too, as we check if there is a subarray length>-k that can do it
    public double findMaxAverage(int[] a, int k) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int n = a.length;
        for (int i = 0; i < n; i++) {
            max = Math.max(a[i], max);
            min = Math.min(a[i], min);
        }
        double l = min;
        double u = max;
        double tol = 0.0000001;
        while (u - l > tol) {
            double mid = (l + u) / 2.0;
            if (good(a, mid, k)) {
                l = mid;
            } else {
                u = mid;
            }
        }
        return u;
    }

    // whether there is a subarray length>=k and whose avg >=avg
    private boolean good(int[] a, double avg, int k) {
        double min = 0.0;
        int minindex = -1;
        int n = a.length;
        double[] sum = new double[n];
        for (int i = 0; i < n; i++) {
            // only put i-k into consideration. only need the min
            if (i - k >= 0 && sum[i - k] < min) {
                min = sum[i - k];
                minindex = i - k;
            }
            sum[i] = (i == 0 ? 0 : sum[i - 1]) + a[i] - avg;
            if (sum[i] - min >= 0 && i - minindex >= k) {
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        System.out.println(new MaxAvgSumArrayII().findMaxAverage(ArrayUtils.read1d("8,9,3,1,8,3,0,6,9,2"), 8));
    }
}

