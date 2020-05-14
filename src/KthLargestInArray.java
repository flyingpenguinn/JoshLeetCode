import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

/*
LC#215
Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.

Example 1:

Input: [3,2,1,5,6,4] and k = 2
Output: 5
Example 2:

Input: [3,2,3,1,2,4,5,5,6] and k = 4
Output: 4
Note:
You may assume k is always valid, 1 ≤ k ≤ array's length.
 */
public class KthLargestInArray {
    // in average O(n) if we random everything well. can swap u with a middle random one
    public int findKthLargest(int[] a, int k) {
        return dof(a, 0, a.length - 1, a.length - k + 1);
    }

    int dof(int[] a, int l, int u, int k) {
        if (l == u) {
            // cant be k==1.l...u not ordered
            return a[l];
        }
        int pivot = partition(a, l, u);
        int th = pivot - l + 1;
        if (th == k) {
            return a[pivot];
        } else if (th > k) {
            return dof(a, l, pivot - 1, k);
        } else {
            return dof(a, pivot + 1, u, k - th);
        }

    }

    int partition(int[] a, int l, int u) {
        int p = a[u];
        int i = l - 1;
        int j = l;
        while (j <= u) {
            if (a[j] <= p) {
                swap(a, ++i, j);
            }
            j++;
        }
        return i;
    }

    void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}


// nlog(MaxValue)
class KthLargestBinarySearchOnValue {
    public int findKthLargest(int[] a, int k) {
        long min = Integer.MAX_VALUE;
        long max = Integer.MIN_VALUE;
        for (int ai : a) {
            min = Math.min(min, ai);
            max = Math.max(max, ai);
        }
        long l = 0;
        long u = max - min;
        while (l <= u) {
            long mid = l + (u - l) / 2;
            int th = bth(a, mid, min);
            if (th <= k - 1) {
                u = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        // l the first bth<=k-1
        return (int) (min + l);
    }

    int bth(int[] a, long m, long min) {
        int r = 0;
        for (int ai : a) {
            if (ai > m + min) {
                r++;
            }
        }
        return r;
    }
}

// n logk as the heap is always <=k in size
class KthLargestHeap {
    public int findKthLargest(int[] a, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int n = a.length;
        for (int i = 0; i < n; i++) {
            pq.offer(a[i]);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        int rt = pq.isEmpty() ? -1 : pq.poll();
        pq.clear();
        return rt;
    }
}

// nlogn
class KthLargestSorting {
    public int findKthLargest(int[] a, int k) {
        Arrays.sort(a);
        return a[a.length - k];
    }
}