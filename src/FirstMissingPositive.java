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
        int i = 0;
        while (i < a.length) {
            int index = a[i] - 1;
            if (index >= 0 && index < a.length && a[index] != index + 1) {
                swap(a, i, index);
            } else {
                i++;
            }
        }
        for (i = 0; i < a.length; i++) {
            // first invalid pos
            if (a[i] != i + 1) {
                return i + 1;
            }
        }
        //otherwise the one after max
        return a.length + 1;
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
