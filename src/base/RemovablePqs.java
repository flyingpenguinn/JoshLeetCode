package base;

import java.util.Collections;
import java.util.TreeMap;

public class RemovablePqs {
    private static class RemovablePq {
        private int size = 0;
        private TreeMap<Long, Long> tm;
        private long sum = 0;

        private RemovablePq(boolean minHeap) {
            if (minHeap) {
                tm = new TreeMap<>();
            } else {
                tm = new TreeMap<>(Collections.reverseOrder());
            }
        }

        public Long poll() {

            if (tm.isEmpty()) {
                throw new IllegalStateException("Empty pq");
            } else {
                --size;
                Long top = tm.firstKey();
                update(tm, top, -1);
                sum -= top;
                return top;

            }
        }

        public void offer(long k) {
            ++size;
            sum += k;
            update(tm, k, 1);
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
            return size() == 0;
        }

        public int size() {
            return size;
        }

        public boolean contains(long k) {
            return tm.containsKey(k);
        }

        // must call contains before remove
        public boolean remove(long k) {
            if (!this.contains(k)) {
                return false;
            }
            --size;
            sum -= k;
            update(tm, k, -1);
            return true;
        }

        public long top() {
            return tm.firstKey();
        }

        public long getSum() {
            return sum;
        }
    }
}
