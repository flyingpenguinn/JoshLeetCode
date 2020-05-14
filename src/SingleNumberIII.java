import base.ArrayUtils;

import java.util.Arrays;

/*
LC#260
Given an array of numbers nums, in which exactly two elements appear only once and all the other elements appear exactly twice. Find the two elements that appear only once.

Example:

Input:  [1,2,1,3,2,5]
Output: [3,5]
Note:

The order of the result is not important. So in the above example, [5, 3] is also correct.
Your algorithm should run in linear runtime complexity. Could you implement it using only constant space complexity?
 */
public class SingleNumberIII {
    // first find the different digit for the two number then split the numbers to two groups. for each group that number appears only once
    public int[] singleNumber(int[] a) {
        int dj = -1;
        for (int j = 0; j < 32; j++) {
            int jc = 0;
            for (int i = 0; i < a.length; i++) {
                jc += (a[i] >> j) & 1;

            }
            if (jc % 2 == 1) {
                // two nums differ
                dj = j;
                break;
            }
        }
        int r1 = 0;
        int r2 = 0;
        for (int i = 0; i < a.length; i++) {
            if (((a[i] >> dj) & 1) == 1) {
                r1 ^= a[i];
            } else {
                r2 ^= a[i];
            }
        }

        return new int[]{r1, r2};
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new SingleNumberIII().singleNumber(ArrayUtils.read1d("1,1,2,2,3,5"))));
    }
}
