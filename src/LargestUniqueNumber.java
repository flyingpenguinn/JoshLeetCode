import java.util.Arrays;

/*
LC#1133
Given an array of integers A, return the largest integer that only occurs once.

If no integer occurs once, return -1.



Example 1:

Input: [5,7,3,9,4,9,8,3,1]
Output: 8
Explanation:
The maximum integer in the array is 9 but it is repeated. The number 8 occurs only once, so it's the answer.
Example 2:

Input: [9,9,8,8]
Output: -1
Explanation:
There is no number that occurs only once.


Note:

1 <= A.length <= 2000
0 <= A[i] <= 1000
 */
public class LargestUniqueNumber {
    // can also do hash map
    public int largestUniqueNumber(int[] a) {
        Arrays.sort(a);
        int i = 0;
        int max = Integer.MIN_VALUE;
        while (i < a.length) {
            int j = i + 1;
            while (j < a.length && a[j] == a[i]) {
                j++;
            }
            // i..j-1 is the same element
            if (j == i + 1) {
                max = Math.max(max, a[i]);
            }
            i = j;
        }
        return max == Integer.MIN_VALUE ? -1 : max;
    }
}
