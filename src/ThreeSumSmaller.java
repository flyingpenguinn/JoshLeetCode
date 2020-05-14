import java.util.Arrays;

/*
LC#259
Given an array of n integers nums and a target, find the number of index triplets i, j, k with 0 <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target.

Example:

Input: nums = [-2,0,1,3], and target = 2
Output: 2
Explanation: Because there are two triplets which sums are less than 2:
             [-2,0,1]
             [-2,0,3]
Follow up: Could you solve it in O(n2) runtime?
 */
public class ThreeSumSmaller {
    public int threeSumSmaller(int[] a, int target) {
        Arrays.sort(a);
        int n = a.length;
        int r = 0;
        for (int i = 0; i < n; i++) {
            int t = target - a[i];
            //i+1...n-1 sorted find num of two nums' sum <=t
            int j = i + 1;
            int k = n - 1;
            while (j < k) {
                int sum = a[j] + a[k];
                if (sum < t) {
                    // j...k k can be anyone in j+1...k so += k-j. note we fix j, so not (k-j)*(k-j-1)/2
                    // System.out.println(i+","+j+","+k);
                    r += k - j;
                    j++;
                } else {
                    k--;
                }
            }
        }
        return r;
    }
}
