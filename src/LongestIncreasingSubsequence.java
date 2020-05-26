import java.util.ArrayList;
import java.util.List;

/*
LC#300
Given an unsorted array of integers, find the length of longest increasing subsequence.

Example:

Input: [10,9,2,5,3,7,101,18]
Output: 4
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
Note:

There may be more than one LIS combination, it is only necessary for you to return the length.
Your algorithm should run in O(n2) complexity.
Follow up: Could you improve it to O(n log n) time complexity?
 */
public class LongestIncreasingSubsequence {
    public int findLengthOfLCIS(int[] a) {
        int n = a.length;
        if(n==0){
            return 0;
        }
        int start = 0;
        int max = 1;
        for(int i=1; i<n;i++){
            if(a[i]>a[i-1]){
                max = Math.max(max, i-start+1);
            }else{
                start = i;
            }
        }
        return max;
    }
}
