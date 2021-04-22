/*
In some array arr, the values were in arithmetic progression: the values arr[i+1] - arr[i] are all equal for every 0 <= i < arr.length - 1.

Then, a value from arr was removed that was not the first or last value in the array.

Return the removed value.



Example 1:

Input: arr = [5,7,11,13]
Output: 9
Explanation: The previous array was [5,7,9,11,13].
Example 2:

Input: arr = [15,13,12]
Output: 14
Explanation: The previous array was [15,14,13,12].
 */

public class MissingNumberInArithmeticProgression {
    public int missingNumber(int[] a) {
        int n = a.length;
        int diff1 = a[1] - a[0];
        int diff2 = a[n - 1] - a[n - 2];
        int diff = 0;
        if (diff1 == diff2) {
            diff = diff1;
        } else {
            diff = diff1 == diff2 * 2 ? diff2 : diff1;
        }
        for (int i = 1; i < n; i++) {
            if (a[i] - a[i - 1] != diff) {
                return a[i - 1] + diff;
            }
        }
        return a[0]; // all eq, the first is fine. note there must be one number missing
    }
}
