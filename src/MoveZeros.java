import java.util.Arrays;

/*
LC#283
Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

Example:

Input: [0,1,0,3,12]
Output: [1,3,12,0,0]
Note:

You must do this in-place without making a copy of the array.
Minimize the total number of operations.
 */
public class MoveZeros {

    public void moveZeroes(int[] a) {
        int j = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] != 0) {
                a[j++] = a[i];
            }
        }
        while (j < a.length) {
            a[j++] = 0;
        }
    }

    public static void main(String[] args) {
        MoveZerosLikeQuickSort mz = new MoveZerosLikeQuickSort();
        int[] moved = {0, 1, 0, 3, 12};
        mz.moveZeroes(moved);
        System.out.println(Arrays.toString(moved));
    }
}

class MoveZerosLikeQuickSort {
    // during partition order of <= is kept.order of > may not be
    public void moveZeroes(int[] a) {
        int i = -1;
        int j = 0;
        while (j < a.length) {
            if (a[j] != 0) {
                swap(a, ++i, j);
            }
            j++;
        }
    }

    void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}
