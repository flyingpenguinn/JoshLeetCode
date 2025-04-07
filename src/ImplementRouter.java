import java.util.*;

public class ImplementRouter {
    // must binary search on getCount!
    class Packet {
        int s;
        int d;
        int t;
        int index;

        public Packet(int s, int d, int t, int index) {
            this.s = s;
            this.d = d;
            this.t = t;
            this.index = index;
        }
    }

    class Router {
        int limit = -1;
        private Map<Integer, Map<Integer, Set<Integer>>> pm = new HashMap<>();
        private Map<Integer, List<Integer>> dm = new HashMap<>();
        private Map<Integer, Integer> dindex = new HashMap<>();
        private PriorityQueue<Packet> pq = new PriorityQueue<>((x, y) -> {
            if (x.t != y.t) {
                return Integer.compare(x.t, y.t);
            } else {
                return Integer.compare(x.index, y.index);
            }
        });

        public Router(int memoryLimit) {
            this.limit = memoryLimit;
        }

        private int index = 0;

        public boolean addPacket(int s, int d, int t) {
            ++index;
            if (pm.containsKey(s) && pm.get(s).containsKey(d) && pm.get(s).get(d).contains(t)) {
                return false;
            }
            pm.computeIfAbsent(s, k -> new HashMap<>()).computeIfAbsent(d, p -> new HashSet<>()).add(t);
            dm.computeIfAbsent(d, k -> new ArrayList<>()).add(t);
            Packet cp = new Packet(s, d, t, index);
            pq.offer(cp);
            if (pq.size() > limit) {
                Packet top = pq.poll();
                pm.get(top.s).get(top.d).remove(top.t);
                dindex.put(top.d, dindex.getOrDefault(top.d, 0) + 1);
            }
            return true;
        }

        public int[] forwardPacket() {
            if (pq.isEmpty()) {
                return new int[0];
            }
            Packet top = pq.poll();
            pm.get(top.s).get(top.d).remove(top.t);
            dindex.put(top.d, dindex.getOrDefault(top.d, 0) + 1);
            return new int[]{top.s, top.d, top.t};
        }

        public int getCount(int d, int s, int t) {
            return binary(d, t) - binary(d, s - 1);
        }

        private int binary(int d, int t) {
            int l = dindex.getOrDefault(d, 0);
            final List<Integer> list = dm.getOrDefault(d, new ArrayList<>());
            int u = list.size() - 1;
            while (l <= u) {
                int mid = l + (u - l) / 2;
                if (list.get(mid) <= t) {
                    l = mid + 1;
                } else {
                    u = mid - 1;
                }
            }
            return u;
        }
    }
}
