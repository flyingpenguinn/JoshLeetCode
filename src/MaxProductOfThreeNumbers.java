import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

/*
LC#628
Given an integer array, find three numbers whose product is maximum and output the maximum product.

Example 1:

Input: [1,2,3]
Output: 6


Example 2:

Input: [1,2,3,4]
Output: 24


Note:

The length of the given array will be in range [3,104] and all elements are in the range [-1000, 1000].
Multiplication of any three numbers in the input won't exceed the range of 32-bit signed integer.
 */
public class MaxProductOfThreeNumbers {
    public int maximumProduct(int[] a) {
        Arrays.sort(a);
        int n = a.length;
        int v1 = a[n - 1] * a[n - 2] * a[n - 3];
        int v2 = a[0] * a[1] * a[n - 1];
        return Math.max(v1, v2);
    }

    public static void main(String[] args) {
        int[] array = {-1, -2, -3, 4};
        System.out.println(new MaxProductOfThreeNumbers().maximumProduct(array));
    }
}
