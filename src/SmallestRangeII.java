/*
LC#910
Given an array A of integers, for each integer A[i] we may choose any x with -K <= x <= K, and add x to A[i].

After this process, we have some array B.

Return the smallest possible difference between the maximum value of B and the minimum value of B.



Example 1:

Input: A = [1], K = 0
Output: 0
Explanation: B = [1]
Example 2:

Input: A = [0,10], K = 2
Output: 6
Explanation: B = [2,8]
Example 3:

Input: A = [1,3,6], K = 3
Output: 0
Explanation: B = [3,3,3] or B = [4,4,4]


Note:

1 <= A.length <= 10000
0 <= A[i] <= 10000
0 <= K <= 10000
 */

import java.util.Arrays;

public class SmallestRangeII {
    // it must be like +++++----  the + and - wont interleave. we enumerate the end point of +
    public int smallestRangeII(int[] a, int k) {        Arrays.sort(a);

        int n=a.length;
        int min= Integer.MAX_VALUE;
        int max= Integer.MIN_VALUE;
        for(int i=0;i<n;i++){
            a[i]-= k;
            min= Math.min(min,a[i]);
            max= Math.max(max,a[i]);
        }
        int mind= max-min;
        for(int i=0;i<n-1;i++){
            int nv= a[i]+2*k;
            // smallest must be among these two
            min= Math.min(a[0]+2*k,a[i+1]);
            // biggest must be among these two
            max=Math.max(a[n-1],nv);
            mind= Math.min(mind,max-min);
        }
        return mind;
    }
}
