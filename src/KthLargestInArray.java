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
    // in average O(n) if we random everything well.
    public int findKthLargest(int[] a, int k) {
        // assuming k valid
        return find(a, 0, a.length - 1, a.length - k + 1);
    }

    private int find(int[] a, int l, int u, int k) {
        if (l == u) {
            // k must be 1 here
            return a[l];
        }
        int pivot = partition(a, l, u);
        int leftLen = pivot - l + 1;
        if (leftLen == k) {
            return a[pivot];
        } else if (leftLen > k) {
            return find(a, l, pivot - 1, k);
        } else {
            return find(a, pivot + 1, u, k - leftLen);
        }
    }

    private Random rand = new Random();

    private int partition(int[] a, int l, int u) {
        // 0... i <=
        // i+1...j-1 >
        // j...onward under checking
        int picked = rand.nextInt(u - l + 1) + l;
        swap(a, picked, u);
        int t = a[u];
        int i = l - 1;
        int j = l;
        while (j <= u) {
            if (a[j] <= t) {
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


// nlog(number gap)
class KthLargestBinarySearchOnValue {
    // to be the kth number, there must be k numbers <= it.
    // note we can't do k-1 numbers < because there could be a bulk of equal numbers so we can never do </>
    public int findKthLargest(int[] a, int k) {
        k = a.length - k + 1; // turn to find smaller numbers
        long l = Integer.MAX_VALUE;
        long u = Integer.MIN_VALUE;
        for (int ai : a) {
            l = Math.min(l, ai);
            u = Math.max(u, ai);
        }
        while (l <= u) {
            long mid = l + (u - l) / 2;
            int nonBigger = countNonBigger(a, mid);
            if (nonBigger >= k) {
                // must do mid-1 when == because we want to get the first
                u = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        // l the first bth<=k-1
        return (int) l;
    }

    private int countNonBigger(int[] a, long mid) {
        int r = 0;
        for (int ai : a) {
            if (ai <= mid) {
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