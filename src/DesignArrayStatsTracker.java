import java.util.*;

public class DesignArrayStatsTracker {
    // priority template that supports removal

    public static class StatisticsTracker {
        private static class MyPq<K> {
            private int size = 0;
            private TreeMap<K, Long> tm;

            private MyPq(boolean smallTop) {
                if (smallTop) {
                    tm = new TreeMap<>();
                } else {
                    tm = new TreeMap<>(Collections.reverseOrder());
                }
            }

            public K poll() {
                --size;
                if (tm.isEmpty()) {
                    throw new IllegalStateException("Empty pq");
                } else {
                    K top = tm.firstKey();
                    update(tm, top, -1);
                    return top;
                }
            }

            public void offer(K k) {
                ++size;
                update(tm, k, 1);
            }

            private void update(TreeMap<K, Long> tm, K k, long d) {
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

            public boolean contains(K k) {
                return tm.containsKey(k);
            }

            public void remove(K k) {
                --size;
                update(tm, k, -1);
            }

            public K top() {
                return tm.firstKey();
            }
        }

        private Deque<Integer> dq = new ArrayDeque<>();
        private long sum = 0;
        private MyPq<Integer> pq1 = new MyPq<Integer>(false);
        private MyPq<Integer> pq2 = new MyPq<Integer>(true);
        private Map<Integer, Integer> freq = new HashMap<>();
        private TreeMap<Integer, TreeSet<Integer>> fm = new TreeMap<>();

        private void update(Map<Integer, Integer> m, Integer k, Integer d) {
            Integer nv = m.getOrDefault(k, 0) + d;
            if (nv <= 0) {
                m.remove(k);
            } else {
                m.put(k, nv);
            }
        }

        private void addFm(int freq, int v) {
            fm.computeIfAbsent(freq, p -> new TreeSet<>()).add(v);
        }

        private void removeFm(int freq, int v) {
            TreeSet<Integer> cur = fm.getOrDefault(freq, new TreeSet<>());
            if (cur.contains(v)) {
                cur.remove(v);
            }
            if (cur.isEmpty()) {
                fm.remove(freq);
            } else {
                fm.put(freq, cur);
            }
        }

        public StatisticsTracker() {

        }

        public void addNumber(int v) {
            sum += v;
            dq.offerLast(v);
            int oldfreq = freq.getOrDefault(v, 0);
            update(freq, v, 1);
            removeFm(oldfreq, v);
            addFm(oldfreq + 1, v);
            if (!pq1.isEmpty() && v > pq1.top()) {
                pq2.offer(v);
            } else {
                pq1.offer(v);
            }
            adjustMyPq();
        }

        protected void adjustMyPq() {
            if (pq1.size() > pq2.size() + 1) {
                pq2.offer(pq1.poll());
            } else if (pq1.size() < pq2.size()) {
                pq1.offer(pq2.poll());
            }
        }

        public void removeFirstAddedNumber() {
            if (dq.isEmpty()) {
                return;
            }
            int first = dq.pollFirst();
            removeNumber(first);
        }

        protected void removeNumber(int v) {
            sum -= v;
            int oldfreq = freq.getOrDefault(v, 0);
            update(freq, v, -1);
            removeFm(oldfreq, v);
            addFm(oldfreq - 1, v);
            if (pq1.contains(v)) {
                pq1.remove(v);
            } else if (pq2.contains(v)) {
                pq2.remove(v);
            } else {
                return;
            }
            adjustMyPq();
        }

        public int getMean() {
            if (dq.isEmpty()) {
                return -1;
            }
            return (int) (sum / dq.size());
        }

        public int getMedian() {
            if (pq1.isEmpty()) {
                return -1;
            }
            if (pq1.size() == pq2.size() + 1) {
                return pq1.top();
            } else {
                return pq2.top();
            }
        }

        public int getMode() {
            if (fm.isEmpty()) {
                return -1;
            }
            return fm.get(fm.lastKey()).iterator().next();
        }
    }


    public static void main(String[] args) {
        StatisticsTracker statisticsTracker = new StatisticsTracker();
        statisticsTracker.addNumber(9); // The data structure now contains [9]
        statisticsTracker.addNumber(5); // The data structure now contains [9, 5]
        statisticsTracker.getMean(); // return 7
        statisticsTracker.removeFirstAddedNumber(); // The data structure now contains [5]
        statisticsTracker.addNumber(5); // The data structure now contains [5, 5]
        statisticsTracker.addNumber(6); // The data structure now contains [5, 5, 6]
        statisticsTracker.removeFirstAddedNumber(); // The data structure now contains [5, 6]
        statisticsTracker.getMedian(); // return 6
        statisticsTracker.addNumber(8); // The data structure now contains [5, 6, 8]
        statisticsTracker.getMode(); // return 5

        /*
        statisticsTracker.addNumber(4); // The data structure now contains [4, 4]
        statisticsTracker.addNumber(2); // The data structure now contains [4, 4, 2]
        statisticsTracker.addNumber(3); // The data structure now contains [4, 4, 2, 3]
        System.out.println(statisticsTracker.getMean()); // return 3
        System.out.println(statisticsTracker.getMedian()); // return 4
        System.out.println(statisticsTracker.getMode()); // return 4
        statisticsTracker.removeFirstAddedNumber(); // The data structure now contains [4, 2, 3]
        System.out.println(statisticsTracker.getMode()); // return 2
        */

    }
}
