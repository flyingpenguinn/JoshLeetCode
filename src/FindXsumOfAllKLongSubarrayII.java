
import java.util.*;

public class FindXsumOfAllKLongSubarrayII {
    private static class RemovablePq {
        private int size = 0;
        private TreeMap<Occ, Long> tm;
        private long sum = 0;

        private RemovablePq(boolean smallTop) {
            if (smallTop) {
                tm = new TreeMap<>();
            } else {
                tm = new TreeMap<>(Collections.reverseOrder());
            }
        }

        public Occ poll() {
            --size;
            if (tm.isEmpty()) {
                throw new IllegalStateException("Empty pq");
            } else {
                Occ top = tm.firstKey();
                update(tm, top, -1);
                sum -= (long) top.cnt * top.v;
                return top;
            }
        }

        public void offer(Occ k) {
            ++size;
            update(tm, k, 1);
            sum += (long) k.v * k.cnt;
        }

        private void update(TreeMap<Occ, Long> tm, Occ k, long d) {
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

        public boolean contains(Occ k) {
            return tm.containsKey(k);
        }

        // must call contains before remove
        public void remove(Occ k) {
            if (!this.contains(k)) {
                return;
            }
            --size;
            update(tm, k, -1);
            sum -= (long) k.v * k.cnt;
        }

        public Occ top() {
            return tm.firstKey();
        }
    }

    class Occ implements Comparable<Occ> {
        final int v;
        final int cnt;

        public Occ(int v, int cnt) {
            this.v = v;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Occ o) {
            if (this.cnt != o.cnt) {
                return Integer.compare(this.cnt, o.cnt);
            } else {
                return Integer.compare(this.v, o.v);
            }
        }

        @Override
        public boolean equals(Object o) {
            return o instanceof Occ && compareTo((Occ) o) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(v, cnt);
        }
    }

    public long[] findXSum(int[] a, int k, int x) {
        int n = a.length;
        Map<Integer, Integer> m = new HashMap<>();
        long[] res = new long[n - k + 1];
        int ri = 0;
        RemovablePq pq = new RemovablePq(true);
        RemovablePq loser = new RemovablePq(false);
        for (int i = 0; i < n; ++i) {
            int v = a[i];
            int oldc = m.getOrDefault(v, 0);
            if (oldc > 0) {
                Occ oldcc = new Occ(v, oldc);
                removeFromPq(pq, oldcc, loser);
            }
            int newc = oldc + 1;
            Occ newcc = new Occ(v, newc);
            pq.offer(newcc);
            if (pq.size > x) {
                Occ lq = pq.poll();
                loser.offer(lq);
            }
            m.put(v, newc);
            if (i - k + 1 >= 0) {
                res[ri++] = pq.sum;
                int head = i - k + 1;
                int hv = a[head];
                int oldheadc = m.get(hv);
                Occ oldheadcc = new Occ(hv, oldheadc);
                removeFromPq(pq, oldheadcc, loser);
                int newheadc = oldheadc - 1;
                if (newheadc > 0) {
                    Occ newheadcc = new Occ(hv, newheadc);
                    pq.offer(newheadcc);
                }
                if (!loser.isEmpty()) {
                    pq.offer(loser.poll());
                }
                while (pq.size > x) {
                    loser.offer(pq.poll());
                }

                m.put(hv, newheadc);
            }
        }
        return res;
    }

    private static void removeFromPq(RemovablePq pq, Occ oldcc, RemovablePq loser) {
        if (pq.contains(oldcc)) {
            pq.remove(oldcc);
        } else {
            loser.remove(oldcc);
        }
    }

}
