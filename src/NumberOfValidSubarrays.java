import base.ArrayUtils;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
/*
LC#1063
Given an array A of integers, return the number of non-empty continuous subarrays that satisfy the following condition:

The leftmost element of the subarray is not larger than other elements in the subarray.



Example 1:

Input: [1,4,2,5,3]
Output: 11
Explanation: There are 11 valid subarrays: [1],[4],[2],[5],[3],[1,4],[2,5],[1,4,2],[2,5,3],[1,4,2,5],[1,4,2,5,3].
Example 2:

Input: [3,2,1]
Output: 3
Explanation: The 3 valid subarrays are: [3],[2],[1].
Example 3:

Input: [2,2,2]
Output: 6
Explanation: There are 6 valid subarrays: [2],[2],[2],[2,2],[2,2],[2,2,2].


Note:

1 <= A.length <= 50000
0 <= A[i] <= 100000
 */

public class NumberOfValidSubarrays {
    // keep the promising ones that can be the left of the subarrays in the stack
    public int validSubarrays(int[] a) {
        Deque<Integer> sm = new ArrayDeque<>();
        int n = a.length;
        sm.push(a[0]);
        int r = 1;
        for (int i = 1; i < n; i++) {
            while (!sm.isEmpty() && sm.peek() > a[i]) {
                sm.pop();
            }
            sm.push(a[i]);
            r += sm.size();
        }
        return r;
    }

    public static void main(String[] args) {
        System.out.println(new NumberOfValidSubarrays().validSubarrays(ArrayUtils.read1d("[3,2,1]")));
    }
}
