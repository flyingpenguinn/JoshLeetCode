import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
LC#912
Given an array of integers nums, sort the array in ascending order.



Example 1:

Input: nums = [5,2,3,1]
Output: [1,2,3,5]
Example 2:

Input: nums = [5,1,1,2,0,0]
Output: [0,0,1,1,2,5]


Constraints:

1 <= nums.length <= 50000
-50000 <= nums[i] <= 50000
 */
public class SortAnArray {
    // quick sort. in average Onlogn time and Olgn space (recursions)
    public int[] sortArray(int[] a) {
        quicksort(a, 0, a.length - 1);
        return a;
    }

    private void quicksort(int[] a, int l, int u) {
        if (l >= u) {
            return;
        }
        int pvindex = partition(a, l, u);
        quicksort(a, pvindex + 1, u);
        quicksort(a, l, pvindex - 1);
    }

    private int partition(int[] a, int l, int u) {
        int i = l - 1;
        int j = l;
        int midindex = mid(a, l, u);
        swap(a, midindex, u);
        int pv = a[u];
        while (j <= u) {
            if (a[j] <= pv) {
                swap(a, ++i, j);
            }
            j++;
        }
        return i;
    }

    private int mid(int[] a, int l, int u) {
        int mid = l + (u - l) / 2;
        if (a[l] >= a[mid] && a[mid] <= a[u]) {
            return mid;
        }
        if (a[mid] >= a[l] && a[l] <= a[u]) {
            return l;
        }
        return u;
    }


    private void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}

class SortAnArrayHeapSort {
    // Onlgn time and O(1) space
    public int[] sortArray(int[] a) {
        int n = a.length;
        int last = n - 1;
        for (int i = (last - 1) / 2; i >= 0; i--) {
            siftdown(a, i, last);
        }
        for (int i = 0; i < n; i++) {
            swap(a, 0, last--);
            siftdown(a, 0, last);
        }
        return a;
    }


    // big heap
    private void siftdown(int[] a, int i, int last) {
        // i+1 to last already heap (could just be leaves)
        while (true) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            if (left > last && right > last) {
                break;
            }
            // use bigc to avoid complicated logics
            int bigc = left;
            if (right <= last && a[right] > a[left]) {
                bigc = right;
            }
            if (a[i] >= a[bigc]) {
                break;
            }
            swap(a, i, bigc);
            i = bigc;
        }
    }

    private void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}

class SortAnArrayMergeSort {
    // Onlgn time and space complexity
    public int[] sortArray(int[] a) {
        mergesort(a, 0, a.length - 1);
        return a;
    }

    private void mergesort(int[] a, int l, int u) {
        if (l >= u) {
            return;
        }
        int mid = l + (u - l) / 2;
        // can't be 0, mid-1 otherwise 0,1 will stack overflow on 0,1 forever
        mergesort(a, l, mid);
        mergesort(a, mid + 1, u);
        int[] tmp = new int[u - l + 1];
        int i1 = l;
        int i2 = mid + 1;
        int im = 0;
        while (i1 <= mid && i2 <= u) {
            if (a[i1] <= a[i2]) {
                tmp[im++] = a[i1++];
            } else {
                tmp[im++] = a[i2++];
            }
        }
        while (i1 <= mid) {
            tmp[im++] = a[i1++];
        }
        while (i2 <= u) {
            tmp[im++] = a[i2++];
        }
        for (int i = l; i <= u; i++) {
            a[i] = tmp[i - l];
        }
    }
}
