/*
LC#303
Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

Example:
Given nums = [-2, 0, 3, -5, 2, -1]

sumRange(0, 2) -> 1
sumRange(2, 5) -> -1
sumRange(0, 5) -> -3
Note:
You may assume that the array does not change.
There are many calls to sumRange function.
 */
/*
LC#303
Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

Example:
Given nums = [-2, 0, 3, -5, 2, -1]

sumRange(0, 2) -> 1
sumRange(2, 5) -> -1
sumRange(0, 5) -> -3
Note:
You may assume that the array does not change.
There are many calls to sumRange function.
 */
public class RangeSumQueryImmutable {
    class NumArray {
        int[] sum;

        public NumArray(int[] a) {
            int n = a.length;
            sum = new int[n];
            for (int i = 0; i < n; i++) {
                sum[i] = (i == 0 ? 0 : sum[i - 1]) + a[i];
            }
        }

        public int sumRange(int i, int j) {
            return i == 0 ? sum[j] : sum[j] - sum[i - 1];
        }
    }
}

