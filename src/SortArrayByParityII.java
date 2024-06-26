import base.ArrayUtils;

import java.util.ArrayDeque;
import java.util.Deque;

/*
LC#922
Given an array A of non-negative integers, half of the integers in A are odd, and half of the integers are even.

Sort the array so that whenever A[i] is odd, i is odd; and whenever A[i] is even, i is even.

You may return any answer array that satisfies this condition.



Example 1:

Input: [4,2,5,7]
Output: [4,5,2,7]
Explanation: [4,7,2,5], [2,5,4,7], [2,7,4,5] would also have been accepted.


Note:

2 <= A.length <= 20000
A.length % 2 == 0
0 <= A[i] <= 1000
 */
public class SortArrayByParityII {
    // two pointers dont necessarily need to start from 0 and n-1
    public int[] sortArrayByParityII(int[] a) {
        int n = a.length;
        int i = 0;
        int j = 1;
        while (i < n && j < n) {
            while (i < n && a[i] % 2 == 0) {
                i += 2;
            }
            while (j < n && a[j] % 2 == 1) {
                j += 2;
            }
            if (i < n && j < n) {
                swap(a, i, j);
            }
            i += 2;
            j += 2;
        }
        return a;
    }

    private void swap(int[] a, int i, int j) {
        //  System.out.println("swapping "+i+" "+j);
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public static void main(String[] args) {
        System.out.println(new SortArrayByParityII().sortArrayByParityII(ArrayUtils.read1d("[648,831,560,986,192,424,997,829,897,843]")));
    }
}
