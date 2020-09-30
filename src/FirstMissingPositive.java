/*
LC#41
Given an unsorted integer array, find the smallest missing positive integer.

Example 1:

Input: [1,2,0]
Output: 3
Example 2:

Input: [3,4,-1,1]
Output: 2
Example 3:

Input: [7,8,9,11,12]
Output: 1
Note:

Your algorithm should run in O(n) time and uses constant extra space.
 */

import base.ArrayUtils;

public class FirstMissingPositive {
    // use original array as hashmap...
    // put a[i] to the place it should be
    public int firstMissingPositive(int[] a) {
        // 1->0,  2->1....
        int n = a.length;
        for (int i = 0; i < n; i++) {
            while (a[i] - 1 >= 0 && a[i] - 1 < n && a[i] != a[a[i] - 1]) {
                swap(a, i, a[i] - 1);
            }
        }
        for (int i = 0; i < n; i++) {
            if (a[i] - 1 != i) {
                return i + 1;
            }
        }
        return n + 1;
    }

    private void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public static void main(String[] args) {
        System.out.println(new FirstMissingPositive().firstMissingPositive(ArrayUtils.read1d("7,8,9")));
    }
}
