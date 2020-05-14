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
    public int missingNumber(int[] arr) {
        int n = arr.length;
        int d1 = arr[1] - arr[0];
        int d2 = arr[n - 1] - arr[n - 2];
        int d = arr[1] > arr[0] ? Math.min(d1, d2) : Math.max(d1, d2);
        for (int i = 1; i < n; i++) {
            if (arr[i] - arr[i - 1] != d) {
                return arr[i - 1] + d;
            }
        }
        return arr[n - 1] + d;
    }
}
