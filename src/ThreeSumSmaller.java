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
    // 0 <= i < j < k < n it just asks for 3 different numbers in the array instead of asking for any sequence there
    public int threeSumSmaller(int[] a, int t) {
        if (a == null || a.length == 0) {
            return 0;
        }
        Arrays.sort(a);
        int i = 0;
        int n = a.length;
        int res = 0;
        while (i < n) {
            int j = i + 1;
            int k = n - 1;
            while (j < k) {
                if (a[i] + a[j] + a[k] >= t) {
                    k--;
                } else {
                    res += k - j;
                    j++;
                }
            }
            i++;
        }
        return res;
    }
}
