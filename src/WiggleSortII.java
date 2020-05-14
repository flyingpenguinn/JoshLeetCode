import base.ArrayUtils;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Random;
/*
LC#324
Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....

Example 1:

Input: nums = [1, 5, 1, 1, 6, 4]
Output: One possible answer is [1, 4, 1, 5, 1, 6].
Example 2:

Input: nums = [1, 3, 2, 2, 3, 1]
Output: One possible answer is [2, 3, 1, 3, 1, 2].
Note:
You may assume all input has valid answer.

Follow Up:
Can you do it in O(n) time and/or in-place with O(1) extra space?
 */

public class WiggleSortII {
    // odd from tail to mid then even from mid to head
    // mid has at most n/2 like 1,2,2,5=> 2,5,1,2. we just want a[1] to be definitely > mid so that it can win over a[0] and a[2], and so forth
    public void wiggleSort(int[] a) {
        int n = a.length;
        int[] ca = Arrays.copyOf(a, n);
        Arrays.sort(ca);
        int i = n - 1;
        for (int j = 1; j < n; j += 2) {
            a[j] = ca[i--];
        }
        for (int j = 0; j < n; j += 2) {
            a[j] = ca[i--];
        }
    }

    public static void main(String[] args) {
        int[] a = ArrayUtils.read1d("1, 3,2,2,3,1");
        new WiggleSortIIOn().wiggleSort(a);
        System.out.println(Arrays.toString(a));
    }

}

class WiggleSortIIOn {
    // select median, do a three-way partition by median. then odd from tail, even from cutoff point
    public void wiggleSort(int[] a) {
        int n = a.length;
        int[] ca = Arrays.copyOf(a, n);
        double median = mid(a);
        partition3(ca, 0, n - 1, median);
        int j = n - 1;
        for (int i = 1; i < n; i += 2) {
            a[i] = ca[j--];
        }
        for (int i = 0; i < n; i += 2) {
            a[i] = ca[j--];
        }
    }

    // three way partition to make sure all things eq median is in the middle
    private void partition3(int[] a, int l, int u, double m) {
        int i = l - 1;
        int j = l;
        int k = u + 1;
        while (j < k) { // !
            if (a[j] == m) {
                j++;
            } else if (a[j] < m) {
                swap(a, ++i, j);
                j++;
            } else {
                swap(a, --k, j);
                // not ++j to evaluate again later
            }
        }
    }

    private double mid(int[] a) {
        int n = a.length;
        if (n % 2 == 0) {
            int v1 = sel(a, 0, n - 1, n / 2);
            int v2 = sel(a, 0, n - 1, n / 2 + 1);
            return (v1 + v2) / 2.0;
        } else {
            return sel(a, 0, n - 1, n / 2 + 1);
        }
    }

    Random rand = new Random();

    // k== 1...n
    private int sel(int[] a, int l, int u, int k) {
        if (l == u) {
            // k must be 1
            return a[l];
        }
        int index = rand.nextInt(u - l + 1);
        swap(a, l + index, u);
        int pivot = partition2(a, l, u);
        int ll = pivot - l + 1;
        if (ll == k) {
            return a[pivot];
        } else if (ll > k) {
            return sel(a, l, pivot - 1, k);
        } else {
            return sel(a, pivot + 1, u, k - ll);
        }
    }

    // normal two way partition
    private int partition2(int[] a, int l, int u) {
        int i = l - 1;
        int j = l;
        int t = a[u];
        while (j <= u) {
            if (a[j] >= t) {
                swap(a, ++i, j);
            }
            j++;
        }
        return i;
    }

    private void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}