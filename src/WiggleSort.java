/*
LC#280
Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....

Example:

Input: nums = [3,5,2,1,6,4]
Output: One possible answer is [3,5,1,6,2,4]
 */
public class WiggleSort {
    public void wiggleSort(int[] a) {
        int n = a.length;
        if (n <= 1) {
            return;
        }
        // 1,3,5...th must be the biggest
        for (int i = 1; i + 1 < n; i += 2) {
            int max = Math.max(a[i - 1], Math.max(a[i], a[i + 1]));
            if (a[i - 1] == max) {
                swap(a, i - 1, i);
            } else if (a[i + 1] == max) {
                swap(a, i + 1, i);
            }
        }
        // for 0,1,2,3
        if (n % 2 == 0 && a[n - 1] < a[n - 2]) {
            swap(a, n - 1, n - 2);
        }
    }

    void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

}
