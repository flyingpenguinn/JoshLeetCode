import base.ArrayUtils;

import java.util.TreeMap;

public class MinOperationsToMakeSubarrayEqual {
    // priorityqueue priority queue that is removable
    class RemovablePq {
        private TreeMap<Long, Long> tm = new TreeMap<>();
        private long sum = 0;
        private long size = 0;

        public void offer(long x) {
            update(tm, x, 1);
            sum += x;
            size++;
        }

        public void remove(long x) {
            if (tm.containsKey(x)) {
                update(tm, x, -1);
                sum -= x;
                size--;
            }
        }

        public boolean contains(long x) {
            return tm.containsKey(x);
        }

        public long size() {
            return size;
        }

        public long sum() {
            return sum;
        }

        public Long min() {
            return tm.isEmpty() ? null : tm.firstKey();
        }

        public Long max() {
            return tm.isEmpty() ? null : tm.lastKey();
        }

        private void update(TreeMap<Long, Long> tm, long k, long d) {
            long nv = tm.getOrDefault(k, 0L) + d;
            if (nv <= 0) {
                tm.remove(k);
            } else {
                tm.put(k, nv);
            }
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public long pollMax() {
            if (isEmpty()) {
                throw new IllegalStateException();
            }
            long rt = max();
            remove(rt);
            return rt;
        }


        public long pollMin() {
            if (isEmpty()) {
                throw new IllegalStateException();
            }
            long rt = min();
            remove(rt);
            return rt;
        }
    }

    void adjust() {
        if (pq1.size() > pq2.size() + 1) {
            pq2.offer(pq1.pollMax());
        } else if (pq2.size() > pq1.size()) {
            pq1.offer(pq2.pollMin());
        }
    }

    private RemovablePq pq1 = new RemovablePq();
    private RemovablePq pq2 = new RemovablePq();

    public long minOperations(int[] a, int k) {
        int n = a.length;

        long res = Long.MAX_VALUE;
        for (int i = 0; i < n; ++i) {
            int v = a[i];
            addToPq(v);
            int head = i - k + 1;
            if (head >= 0) {
                long mid = pq1.max();
                long bigger = pq2.sum();
                long smaller = pq1.sum();
                long biggersize = pq2.size();
                long smallersize = pq1.size();
                long diff1 = smallersize * mid - smaller;
                long diff2 = bigger - biggersize * mid;
                long diff = diff1 + diff2;
                res = Math.min(res, diff);
                removeFromPq(a[head]);
            }
        }
        return res;
    }

    protected void addToPq(int v) {
        if (!pq1.isEmpty() && v > pq1.max()) {
            pq2.offer(v);
        } else {
            pq1.offer(v);
        }
        adjust();
    }

    protected void removeFromPq(int headelement1) {
        int headelement = headelement1;
        if (pq1.contains(headelement)) {
            pq1.remove(headelement);
        } else {
            pq2.remove(headelement);
        }
        adjust();
    }

    public static void main(String[] args) {
        System.out.println(new MinOperationsToMakeSubarrayEqual().minOperations(ArrayUtils.read1d("[4,-3,2,1,-4,6]"), 3));
    }
}
