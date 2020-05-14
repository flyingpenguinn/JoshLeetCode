import java.util.Arrays;

/*
Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.

Example 1:

Input: [1,3,4,2,2]
Output: 2
Example 2:

Input: [3,1,3,4,2]
Output: 3
Note:

You must not modify the array (assume the array is read only).
You must use only constant, O(1) extra space.
Your runtime complexity should be less than O(n2).
There is only one duplicate number in the array, but it could be repeated more than once.
 */
public class FindDuplicateNumber {

    public static void main(String[] args) {
        int[] array = {3, 1, 3, 4, 2};
        System.out.println(new FindDuplicateNumber().findDuplicate(array));
    }

    // treat it as linked list cycle problem
    //dupe is start of circle
    public int findDuplicate(int[] a) {
        int n = a.length;
        int slow = a[0];
        int fast = a[a[0]];
        while (slow != fast) {
            slow = a[slow];
            fast = a[a[fast]];
        }
        fast = 0;
        while (slow != fast) {
            slow = a[slow];
            fast = a[fast];
        }
        return slow;
    }

}

class FindDuplicateBinarySearch {

    // don't really need to sort as we actually binary search number not index
    // programming perls...
    public int findDuplicate(int[] a) {
        int n = a.length - 1;
        int l = 1;
        int u = n;
        while (l <= u) {
            int m = l + (u - l) / 2;
            int nsm = nsm(a, m);
            int ensm = n - m + 1;
            if (nsm > ensm) {
                l = m + 1;
            } else {
                u = m - 1;
            }
        }
        // first >
        return u;
    }


    int nsm(int[] a, int m) {
        int r = 0;
        int n = a.length;
        for (int i = 0; i < n; i++) {
            if (a[i] >= m) {
                r++;
            }
        }
        return r;
    }
}
