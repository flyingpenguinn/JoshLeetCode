import java.util.*;

public class FindXsumOfAllKLongSubarrayII {
    // two tree sets mimic priority queue for sliding window top freq algo
    private class Item implements Comparable<Item> {
        long v;
        long cnt;

        Item(long v, long c) {
            this.v = v;
            this.cnt = c;
        }

        @Override
        public int compareTo(Item other) {
            if (this.cnt != other.cnt) {
                return Long.compare(this.cnt, other.cnt);
            }
            return Long.compare(this.v, other.v);
        }
    }

    public long[] findXSum(int[] a, int k, int x) {
        int n = a.length;
        long[] res = new long[n - k + 1];
        int ri = 0;
        TreeSet<Item> heap1 = new TreeSet<>(); // small heap for curren tops in sliding window
        TreeSet<Item> heap2 = new TreeSet<>(Collections.reverseOrder());  // a candidate pool a big heap
        long sum = 0;
        Map<Long, Long> cnt = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            long v = a[i];
            long oldc = cnt.getOrDefault(v, 0L);
            long newc = oldc + 1;
            cnt.put(v, newc);
            Item oldi = new Item(v, oldc);
            Item newi = new Item(v, newc);
            if (heap2.contains(oldi)) {
                heap2.remove(oldi);
                addHeap(heap2, newi);
            } else {
                heap1.remove(oldi);
                addHeap(heap1, newi);
                sum += v;
            }
            // in case another item in heap2 can now connect with this number
            if(!heap2.isEmpty()){
                Item top = heap2.pollFirst();
                addHeap(heap1, top);
                sum += top.v * top.cnt;
            }
            // at most 2 operations as we added at most 2 elements: one from heap2 the other from current v
            while (heap1.size() > x) {
                Item top = heap1.pollFirst();
                sum -= top.v * top.cnt;
                addHeap(heap2, top);
            }
            int head = i - k + 1;
            if (head >= 0) {
                res[ri++] = sum;
                long hv = a[head];
                long oldhv = cnt.getOrDefault(hv, 0L);
                long newhv = oldhv - 1;
                cnt.put(hv, newhv);

                Item oldhi = new Item(hv, oldhv);
                Item newhi = new Item(hv, newhv);
                if (heap2.contains(oldhi)) {
                    heap2.remove(oldhi);
                    addHeap((TreeSet<Item>) heap2, newhi);
                } else {
                    heap1.remove(oldhi);
                    addHeap(heap1, newhi);
                    sum -= hv;
                }

            }
        }
        return res;
    }

    protected void addHeap(TreeSet<Item> heap, Item item) {
        if(item.cnt>0) {
            heap.add(item);
        }
    }

}
