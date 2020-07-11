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
        // a is guranteed non null and valid: all but one show up twice
        int l = 0;
        int u = a.length - 1;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if ((mid == 0 || mid == a.length - 1)) {
                // all others are excluded, must be them
                return a[mid];
            } else if (a[mid] != a[mid - 1] & a[mid] != a[mid + 1]) {
                return a[mid];
            } else {
                int first = a[mid - 1] == a[mid] ? mid - 1 : mid;
                if (first % 2 == 0) {
                    l = mid + 1;
                } else {
                    u = mid - 1;
                }
            }
        }
        return -1; // or throw
    }

    public static void main(String[] args) {
        int[] array = {3, 3, 7, 7, 10, 11, 11};
        System.out.println(new SingleElementInSortedArray().singleNonDuplicate(array));
    }
}
