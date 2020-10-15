/*
LC#189
Given an array, rotate the array to the right by k steps, where k is non-negative.

Example 1:

Input: [1,2,3,4,5,6,7] and k = 3
Output: [5,6,7,1,2,3,4]
Explanation:
rotate 1 steps to the right: [7,1,2,3,4,5,6]
rotate 2 steps to the right: [6,7,1,2,3,4,5]
rotate 3 steps to the right: [5,6,7,1,2,3,4]
Example 2:

Input: [-1,-100,3,99] and k = 2
Output: [3,99,-1,-100]
Explanation:
rotate 1 steps to the right: [99,-1,-100,3]
rotate 2 steps to the right: [3,99,-1,-100]
Note:

Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.
Could you do it in-place with O(1) extra space?
 */
public class RotateArray {
    public void rotate(int[] a, int k) {
        int n = a.length;
        k %= n;
        if (k == 0) {
            return;
        }
        reverse(a, 0, n - k - 1);
        reverse(a, n - k, n - 1);
        reverse(a, 0, n - 1);
    }

    private void reverse(int[] a, int i, int j) {
        while (i < j) {
            swap(a, i++, j--);
        }
    }

    private void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}

class RotateArraySwapBased {
    // put numbers to correct place one swap a time
    // we must put n numbers to correct place so done is counting from 0 to n, no need to maintain a done array
    public void rotate(int[] a, int k) {
        int n = a.length;
        k %= n;
        if (k == 0) {
            return;
        }
        int done = 0;
        for (int i = 0; i < n; i++) {

            int ci = i;
            while (true) {
                int mi = map(ci, n, k);
                if (mi == i) {
                    done++;
                    break;
                }
                swap(a, i, mi);
                done++;
                if (done == n) {
                    break;
                }
                ci = mi;
            }
            if (done == n) {
                break;
            }
        }
    }

    // 0--> k, 1--> k+1... n-k+1... n-1
    // n-k--->0, n-k+1--->1... n-1-->k-1
    private int map(int i, int n, int k) {
        if (i < n - k) {
            return i + k;
        } else {
            return i - (n - k);
        }
    }

    private void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}
