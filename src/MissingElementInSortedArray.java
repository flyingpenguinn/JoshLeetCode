/*
LC#1060
Given a sorted array A of unique numbers, find the K-th missing number starting from the leftmost number of the array.



Example 1:

Input: A = [4,7,9,10], K = 1
Output: 5
Explanation:
The first missing number is 5.
Example 2:

Input: A = [4,7,9,10], K = 3
Output: 8
Explanation:
The missing numbers are [5,6,8,...], hence the third missing number is 8.
Example 3:

Input: A = [1,2,4], K = 3
Output: 6
Explanation:
The missing numbers are [3,5,6,7,...], hence the third missing number is 6.


Note:

1 <= A.length <= 50000
1 <= A[i] <= 1e7
1 <= K <= 1e8
 */
public class MissingElementInSortedArray {
    // find the first index that can be used as the "base"
    public int missingElement(int[] a, int k) {
        int n = a.length;
        int l = 0;
        int u = n - 1;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (count(a, mid) >= k) {
                u = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        // a[u] has less than k missing numbers
        int uth = count(a, u);
        return a[u] + k - uth;
    }

    // for given a[i] how many numbers missing
    private int count(int[] a, int i) {
        return a[i] - a[0] - i;
    }
}
