import java.util.*;

/*
LC#480
Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.

Examples:
[2,3,4] , the median is 3

[2,3], the median is (2 + 3) / 2 = 2.5

Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position. Your job is to output the median array for each window in the original array.

For example,
Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.

Window position                Median
---------------               -----
[1  3  -1] -3  5  3  6  7       1
 1 [3  -1  -3] 5  3  6  7       -1
 1  3 [-1  -3  5] 3  6  7       -1
 1  3  -1 [-3  5  3] 6  7       3
 1  3  -1  -3 [5  3  6] 7       5
 1  3  -1  -3  5 [3  6  7]      6
Therefore, return the median sliding window as [1,-1,-1,3,5,6].

Note:
You may assume k is always valid, ie: k is always smaller than input array's size for non-empty array.
Answers within 10^-5 of the actual value will be accepted as correct.
 */
public class SlidingWindowMedian {
    // treemap serve as heap
    // can't use map size that's key value pair count not the heap count! need another counter if we want to treat maps as heaps
    // using tree map as heap for perf of removal
    // using tree map as heap for perf of removal
    private TreeMap<Integer, Integer> tm1 = new TreeMap<>();
    private int size1 = 0;
    private TreeMap<Integer, Integer> tm2 = new TreeMap<>();
    private int size2 = 0;

    public double[] medianSlidingWindow(int[] a, int k) {
        if (a == null || a.length == 0 || k > a.length || k <= 0) {
            return new double[0];
        }
        int n = a.length;
        for (int i = 0; i < k - 1; i++) {
            insert(a[i]);
        }
        double[] r = new double[n - k + 1];
        int ri = 0;
        for (int i = k - 1; i < n; i++) {
            insert(a[i]);
            r[ri++] = getMedian();
            int head = i - k + 1;
            remove(a[head]);
        }
        return r;
    }

    private void insert(int t) {
        // tm1 is a max heap with smaller values, tm2 is a min heap with bigger values. tm1 always bigger or equal to tm2
        if (tm1.isEmpty()) { // tm2 must be empty too
            update(tm1, t, 1);
        } else if (t <= tm1.lastKey()) {
            update(tm1, t, 1);
        } else {
            update(tm2, t, 1);
        }
        adjust();
    }

    private void remove(int t) {
        if (t <= tm1.lastKey()) {
            update(tm1, t, -1);
        } else {
            update(tm2, t, -1);
        }
        adjust();
    }

    private double getMedian() {
        if ((size1 + size2) % 2 == 0) {
            return (0.0 + tm1.lastKey() + tm2.firstKey()) / 2.0;
        } else {
            return tm1.lastKey();
        }
    }

    private void adjust() {
        // tm1==tm2, or tm1== tm2+1
        if (size1 > size2 + 1) {
            update(tm2, tm1.lastKey(), 1);
            update(tm1, tm1.lastKey(), -1);
        } else if (size2 > size1) {
            update(tm1, tm2.firstKey(), 1);
            update(tm2, tm2.firstKey(), -1);
        }
    }


    private void update(TreeMap<Integer, Integer> m, int k, int delta) {
        int nv = m.getOrDefault(k, 0) + delta;
        if (nv <= 0) {
            m.remove(k);
        } else {
            m.put(k, nv);
        }
        if (m == tm1) {
            size1 += delta;
        } else {
            size2 += delta;
        }
    }

    public static void main(String[] args) {
        SlidingWindowMedian swm = new SlidingWindowMedian();
        int[] nums = {9, 7, 0, 3, 9, 8, 6, 5, 7, 6};
        System.out.println(Arrays.toString(swm.medianSlidingWindow(nums, 2)));
    }
}