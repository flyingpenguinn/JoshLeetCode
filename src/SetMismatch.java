/*
LC#645
The set S originally contains numbers from 1 to n. But unfortunately, due to the data error, one of the numbers in the set got duplicated to another number in the set, which results in repetition of one number and loss of another number.

Given an array nums representing the data status of this set after the error. Your task is to firstly find the number occurs twice and then find the number that is missing. Return them in the form of an array.

Example 1:
Input: nums = [1,2,2,4]
Output: [2,3]
Note:
The given array size will in the range [2, 10000].
The given array's numbers won't have any order.
 */

public class SetMismatch {

    public int[] findErrorNums(int[] a) {
        int n = a.length;
        long sum = 0;
        long sqsum = 0;
        for (int i = 0; i < n; i++) {
            sqsum += a[i] * a[i];
            sum += a[i];
        }
        long diff = sum - n * (n + 1) / 2;
        long sqdiff = sqsum - 1L * n * (n + 1) * (2 * n + 1) / 6;
        long theirsum = sqdiff / diff;
        long v1 = (diff + theirsum) / 2;
        long v2 = (theirsum - diff) / 2;
        return new int[]{(int) v1, (int) v2};
    }
}

