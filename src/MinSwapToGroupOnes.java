/*
LC#1151
Given a binary array data, return the minimum number of swaps required to group all 1’s present in the array together in any place in the array.



Example 1:

Input: [1,0,1,0,1]
Output: 1
Explanation:
There are 3 ways to group all 1's together:
[1,1,1,0,0] using 1 swap.
[0,1,1,1,0] using 2 swaps.
[0,0,1,1,1] using 1 swap.
The minimum is 1.
Example 2:

Input: [0,0,0,1,0]
Output: 0
Explanation:
Since there is only one 1 in the array, no swaps needed.
Example 3:

Input: [1,0,1,0,1,0,0,1,1,0,1]
Output: 3
Explanation:
One possible solution that uses 3 swaps is [0,0,0,0,0,1,1,1,1,1,1].


Note:

1 <= data.length <= 10^5
0 <= data[i] <= 1
 */
public class MinSwapToGroupOnes {
    // idea similar to consecutive stone problem!
    // similar to consecutive stonez ii,find a good window. here the window size is fixed
    // how many zeros each window = ones has? we need to swap them out
    public int minSwaps(int[] a) {
        int n = a.length;
        int window = 0;
        for (int i = 0; i < n; i++) {
            if (a[i] == 1) {
                window++;
            }
        }
        if (window == 0) {
            return 0;
        }
        int gaps = 0;
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (a[i] == 0) {
                gaps++;
            }
            if (i - window + 1 >= 0) {
                res = Math.min(res, gaps);
                if (a[i - window + 1] == 0) {
                    gaps--;
                }
            }
        }
        return res;
    }
}
