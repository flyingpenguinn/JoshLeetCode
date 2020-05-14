/*
LC#540
You are given a sorted array consisting of only integers where every element appears exactly twice, except for one element which appears exactly once. Find this single element that appears only once.



Example 1:

Input: [1,1,2,3,3,4,4,8,8]
Output: 2
Example 2:

Input: [3,3,7,7,10,11,11]
Output: 10


Note: Your solution should run in O(log n) time and O(1) space.
 */
public class SingleElementInSortedArray {

    // the first element must %2==0 if there is no single element mudding the water
    public int singleNonDuplicate(int[] a) {
        int n = a.length;
        int l = 0;
        int u = n - 1;
        while (l <= u) {
            int m = l + (u - l) / 2;
            if (m == 0 || m == n - 1) {
                return a[m];
            }
            if (a[m] != a[m - 1] && a[m] != a[m + 1]) {
                return a[m];
            }
            int first = a[m] == a[m - 1] ? m - 1 : m;
            if (first % 2 != 0) {
                u = m - 1;
            } else {
                l = m + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] array = {3, 3, 7, 7, 10, 11, 11};
        System.out.println(new SingleElementInSortedArray().singleNonDuplicate(array));
    }
}
