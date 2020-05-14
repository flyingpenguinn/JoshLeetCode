/*
LC#1299
Given an array arr, replace every element in that array with the greatest element among the elements to its right, and replace the last element with -1.

After doing so, return the array.



Example 1:

Input: arr = [17,18,5,4,6,1]
Output: [18,6,6,6,1,-1]


Constraints:

1 <= arr.length <= 10^4
1 <= arr[i] <= 10^5
 */
public class ReplaceElementsGreatestOnRight {
    public int[] replaceElements(int[] a) {
        int n = a.length;
        int right = a[n - 1];
        a[n - 1] = -1;
        for (int i = n - 2; i >= 0; i--) {
            int tr = a[i];
            a[i] = Math.max(a[i + 1], right);
            right = tr;
        }
        return a;
    }
}
