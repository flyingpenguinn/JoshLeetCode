import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
lc#1004
Given an array A of 0s and 1s, we may change up to K values from 0 to 1.

Return the length of the longest (contiguous) subarray that contains only 1s.



Example 1:

Input: A = [1,1,1,0,0,0,1,1,1,1,0], K = 2
Output: 6
Explanation:
[1,1,1,0,0,1,1,1,1,1,1]
Bolded numbers were flipped from 0 to 1.  The longest subarray is underlined.
Example 2:

Input: A = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3
Output: 10
Explanation:
[0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
Bolded numbers were flipped from 0 to 1.  The longest subarray is underlined.


Note:

1 <= A.length <= 20000
0 <= K <= A.length
A[i] is 0 or 1
 */
public class MaxConsecutiveOnesIII {
    // sliding window on numbers of 1. similar to "MaxSwapsToMakeAllOnesTogether" but here window size is not fixed
    // maintain a window of at most k zeros. same as question "max substring with at most k zeros..."
    public int longestOnes(int[] a, int k) {
        int n = a.length;
        int low = 0;
        int high = -1;
        int zeros = 0;
        int max = 0;
        while(true){
            if(zeros<=k){
                max = Math.max(max, high-low+1);
                high++;
                if(high==n){
                    break;
                }
                if(a[high]==0){
                    zeros++;
                }
            }else{
                if(a[low]==0){
                    zeros--;
                }
                low++;
            }
        }
        return max;
    }
}
