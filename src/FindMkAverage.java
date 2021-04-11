import java.util.ArrayDeque;
import java.util.Deque;
import java.util.TreeMap;

public class FindMkAverage {

    class MKAverage {


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

            public void pollMax() {
                if (isEmpty()) {
                    return;
                }
                remove(max());
            }


            public void pollMin() {
                if (isEmpty()) {
                    return;
                }
                remove(min());
            }
        }

        private RemovablePq small = new RemovablePq();
        private RemovablePq big = new RemovablePq();
        private RemovablePq mid = new RemovablePq();

        private Deque<Integer> dq = new ArrayDeque<>();
        private int m = 0;
        private int k = 0;
        private long sum = 0;


        public MKAverage(int m, int k) {
            this.m = m;
            this.k = k;
        }

        public void addElement(int num) {

            sum += num;
            dq.offerLast(num);
            if (dq.size() > m) {
                int top = dq.pollFirst();
                sum -= top;
                if (small.contains(top)) {
                    small.remove(top);

                } else if (big.contains(top)) {
                    big.remove(top);
                } else {
                    mid.remove(top);
                }
            }
            adjust();
            if (small.isEmpty() || num < small.max()) {
                small.offer(num);
            } else if (big.isEmpty() || num > big.min()) {
                big.offer(num);
            } else {
                mid.offer(num);
            }
            adjust();
        }

        private void adjust() {
            if (small.size > k) {
                mid.offer(small.max());
                small.pollMax();
            }
            if (big.size > k) {
                mid.offer(big.min());
                big.pollMin();
            }
            if (small.size < k && !mid.isEmpty()) {
                small.offer(mid.min());
                mid.pollMin();
            }
            if (big.size < k && !mid.isEmpty()) {
                big.offer((mid.max()));
                mid.pollMax();
            }
        }

        public int calculateMKAverage() {

            if (dq.size() < m) {
                return -1;
            }
            return (int) ((sum - big.sum() - small.sum()) / (m - 2 * k));
        }
    }

}
