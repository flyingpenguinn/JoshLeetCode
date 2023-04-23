import java.util.TreeMap;

public class SlidingSubarrayBeauty {
    // the restraint might allow a counter based solution, but we use a pq with removal regardless
    class PQ {
        private TreeMap<Integer, Integer> tm = new TreeMap<>();
        private int tc = 0;

        private boolean isEmpty() {
            return tc == 0;
        }

        private void offer(int v) {
            if (v > 0) {
                return;
            }
            update(v, 1);
        }

        private Integer top() {
            return tm.lastKey();
        }

        private Integer bottom() {
            return tm.firstKey();
        }

        private Integer poll() {
            Integer top = tm.lastKey();
            remove(top);
            return top;
        }

        private Integer pollBottom() {
            Integer bt = tm.firstKey();
            remove(bt);
            return bt;
        }

        private void remove(int v) {
            update(v, -1);
        }


        private void update(int k, int d) {
            if (d > 0) {
                int nv = tm.getOrDefault(k, 0) + d;
                tm.put(k, nv);
                ++tc;
            } else {
                if (!tm.containsKey(k)) {
                    return;
                }
                --tc;
                int nv = tm.getOrDefault(k, 0) + d;
                if (nv <= 0) {
                    tm.remove(k);
                } else {
                    tm.put(k, nv);
                }
            }
        }
    }


    private PQ pq1 = new PQ();
    private PQ pq2 = new PQ();

    private int x;

    public int[] getSubarrayBeauty(int[] a, int k, int x) {
        this.x = x;
        int n = a.length;
        for (int i = 0; i < k - 1; ++i) {
            addv(a[i]);
        }
        int[] res = new int[n - k + 1];
        int ri = 0;
        for (int i = k - 1; i < n; ++i) {
            addv(a[i]);
            if (pq1.tc < x) {
                res[ri++] = 0;
            } else {
                res[ri++] = pq1.top();
            }
            int head = i - k + 1;
            remove(a[head]);
        }
        return res;
    }

    private void addv(int i) {
        pq1.offer(i);
        if (pq1.tc > x) {
            Integer largest = pq1.poll();
            pq2.offer(largest);
        }
    }

    private void remove(int i) {
        if (pq1.isEmpty()) {
            pq2.remove(i);
        } else {
            if (i > pq1.top()) {
                pq2.remove(i);
            } else {
                pq1.remove(i);
            }
        }
        if (pq1.tc < x) {
            if (pq2.isEmpty()) {
                return;
            } else {
                pq1.offer(pq2.pollBottom());
            }
        }
    }
}
