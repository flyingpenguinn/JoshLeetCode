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
    TreeMap<Long, Integer> small = new TreeMap<>();
    TreeMap<Long, Integer> big = new TreeMap<>();
    int smallsize = 0;
    int bigsize = 0;

    public double[] medianSlidingWindow(int[] a, int k) {

        int n = a.length;
        double[] r = new double[n - k + 1];
        int ri = 0;
        for (int i = 0; i < n; i++) {
            if (small.isEmpty() || a[i] <= small.lastKey()) {
                updatemap(small, a[i], 1);
            } else {
                updatemap(big, a[i], 1);
            }
            adjust();
            if (i - k + 1 >= 0) {
                if (bigsize == smallsize) {
                    double median = (0.0 + big.firstKey() + small.lastKey()) / 2.0;
                    r[ri++] = median;
                } else {
                    r[ri++] = small.lastKey();
                }
                long head = a[i - k + 1];
                if (head <= small.lastKey()) {
                    updatemap(small, head, -1);
                } else {
                    updatemap(big, head, -1);
                }
                adjust();
            }

        }
        return r;
    }

    private void adjust() {
        if (smallsize > bigsize + 1) {
            // small size <= big size +1
            long key = small.lastKey();
            updatemap(small, key, -1);
            updatemap(big, key, 1);
        } else if (bigsize > smallsize) {
            // // small size >= big size
            long key = big.firstKey();
            updatemap(big, key, -1);
            updatemap(small, key, 1);

        }
    }

    private void updatemap(TreeMap<Long, Integer> map, long k, int d) {
        int v = map.getOrDefault(k, 0) + d;
        if (v <= 0) {
            map.remove(k);
        } else {
            map.put(k, v);
        }
        if (map == small) {
            smallsize += d;
        } else {
            bigsize += d;
        }
    }

    public static void main(String[] args) {
        SlidingWindowMedian swm = new SlidingWindowMedian();
        int[] nums = {9, 7, 0, 3, 9, 8, 6, 5, 7, 6};
        System.out.println(Arrays.toString(swm.medianSlidingWindow(nums, 2)));
    }
}

class SlidingWindowMedianMyOwnHeap {
    // or write heap by myself...

    class Heap {
        private Map<Integer, Integer> aToHeap = new HashMap<>();
        private int[] heap = new int[0];
        private int size = 0;
        private boolean isBig;
        private int[] a = null;

        public Heap(int[] a, int n, boolean isBig) {
            this.a = a;
            this.heap = new int[n];
            this.size = 0;
            this.isBig = isBig;
            aToHeap.clear();
        }

        public int size() {
            return this.size;
        }

        public void add(int aindex) {
            heap[size] = aindex;
            aToHeap.put(aindex, size);
            size += 1;
            siftUp(size - 1);
        }

        // 0... start-1 is heap. make 0...start heap
        private void siftUp(int start) {
            int i = start;
            while (i > 0) {
                int parent = (i - 1) / 2;
                if (shouldSwap(parent, i)) {
                    swap(heap, parent, i);
                    i = parent;
                } else {
                    break;
                }
            }
        }

        private boolean shouldSwap(int parent, int i) {
            int parentValue = a[heap[parent]];
            int ivalue = a[heap[i]];
            return isBig ? ivalue > parentValue : ivalue < parentValue;
        }

        public boolean contains(int aindex) {
            return aToHeap.containsKey(aindex);
        }

        public void remove(int aindex) {
            Integer heapIndex = aToHeap.get(aindex);
            if (heapIndex != null) {
                swap(heap, heapIndex, size - 1);
                size -= 1;
                // must do down and up because heap property is not guaranteed between this value and the last value
                siftDown(heapIndex);
                siftUp(heapIndex);
                aToHeap.remove(aindex);
            }
        }

        // 0... heapindex -1 is heap, heapIndex+1 ... size-1 has been heap, make heapIndex...size-1 heap too
        private void siftDown(int start) {
            int i = start;
            while (i < size) {
                int p = i;
                // must use p because we want to use left to compare with right
                if (2 * i + 1 < size && better(2 * i + 1, p, isBig)) {
                    p = 2 * i + 1;
                }
                if (2 * i + 2 < size && better(2 * i + 2, p, isBig)) {
                    p = 2 * i + 2;
                }
                if (p != i) {
                    swap(heap, p, i);
                    i = p;
                } else {
                    break;
                }
            }
        }

        boolean better(int v1, int v2, boolean isBig) {
            return isBig ? a[heap[v1]] > a[heap[v2]] : a[heap[v1]] < a[heap[v2]];
        }

        private void swap(int[] heap, int i, int j) {
            int iindex = heap[i];
            int jindex = heap[j];
            aToHeap.put(iindex, j);
            aToHeap.put(jindex, i);

            int tmp = heap[i];
            heap[i] = heap[j];
            heap[j] = tmp;
        }

        public int top() {
            return heap[0];
        }

        public int removeTop() {
            int rt = heap[0];
            swap(heap, 0, size - 1);
            // swap first then remove because swap will add to map again
            aToHeap.remove(rt);
            size -= 1;
            siftDown(0);
            return rt;
        }
    }

    class Heaps {
        private Heap bigHeap;
        private Heap smallHeap;
        private int[] a = null;

        public Heaps(int[] a) {
            this.a = a;
            int n = a.length;
            bigHeap = new Heap(a, n / 2 + 1, true);
            smallHeap = new Heap(a, n / 2 + 1, false);
        }

        public void addHeap(int aindex) {
            int value = a[aindex];
            if (bigHeap.size > 0) {
                int bigTop = a[bigHeap.top()];
                if (value >= bigTop) {
                    smallHeap.add(aindex);
                } else {
                    bigHeap.add(aindex);
                }
            } else if (smallHeap.size > 0) {
                int smallTop = a[smallHeap.top()];
                if (value <= smallTop) {
                    bigHeap.add(aindex);
                } else {
                    smallHeap.add(aindex);
                }
            } else {
                bigHeap.add(aindex);
            }
            rebalance();
        }

        private void rebalance() {
            if (bigHeap.size() > smallHeap.size() + 1) {
                int topIndex = bigHeap.removeTop();
                smallHeap.add(topIndex);
            } else if (smallHeap.size() > bigHeap.size() + 1) {
                int topIndex = smallHeap.removeTop();
                bigHeap.add(topIndex);
            }
        }

        public void remove(int aindex) {
            if (bigHeap.contains(aindex)) {
                bigHeap.remove(aindex);
            } else {
                smallHeap.remove(aindex);
            }
            // need a rebalance after removal
            rebalance();
        }

        public double getMedian() {
            if (bigHeap.size() == smallHeap.size() + 1) {
                return a[bigHeap.top()];
            } else if (smallHeap.size == bigHeap.size + 1) {
                return a[smallHeap.top()];
            } else if (bigHeap.size() == smallHeap.size()) {
                long m1 = a[bigHeap.top()];
                long m2 = a[smallHeap.top()];
                // trap for overflow! always use this format
                return m1 + (m2 - m1) / 2.0;
            } else {
                throw new IllegalStateException();
            }
        }
    }


    public double[] medianSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        Heaps heaps = new Heaps(nums);
        for (int i = 0; i < k - 1; i++) {
            heaps.addHeap(i);
        }
        double[] result = new double[n - k + 1];
        for (int i = k - 1; i < n; i++) {
            heaps.addHeap(i);
            double median = heaps.getMedian();
            result[i - k + 1] = median;
            heaps.remove(i - k + 1);
        }
        return result;
    }
}
