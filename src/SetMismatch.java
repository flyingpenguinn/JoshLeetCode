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

    // can reuse a as hashtable: set a[i] to negative if exist
    public int[] findErrorNums(int[] a) {
        int n = a.length;
        boolean[] v = new boolean[n + 1];
        int[] r = new int[2];
        for (int i = 0; i < n; i++) {
            if (v[a[i]]) {
                r[0] = a[i];
            }
            v[a[i]] = true;
        }
        for (int i = 1; i <= n; i++) {
            if (!v[i]) {
                r[1] = i;
                break;
            }
        }
        return r;
    }
}
