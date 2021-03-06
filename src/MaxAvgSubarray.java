/*
LC#643
Given an array consisting of n integers, find the contiguous subarray of given length k that has the maximum average value. And you need to output the maximum average value.

Example 1:

Input: [1,12,-5,-6,50,3], k = 4
Output: 12.75
Explanation: Maximum average is (12-5-6+50)/4 = 51/4 = 12.75


Note:

1 <= k <= n <= 30,000.
Elements of the given array will be in the range [-10,000, 10,000].
 */
public class MaxAvgSubarray {
    // sliding window...
    public double findMaxAverage(int[] a, int k) {
        int n = a.length;
        int sum = 0;
        double max = Integer.MIN_VALUE;
        for(int i=0; i<n;i++){
            sum += a[i];
            if(i-k+1>=0){
                double avg = sum*1.0/k;
                max = Math.max(max, avg);
                sum -= a[i-k+1];
            }
        }
        return max;
    }
}
