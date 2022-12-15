import java.util.*;

public class DesignMemoryAllocator {
    // can also be treeset and internal based. when freed, merge with previous and next chunk. dont do next chunk only!
    class Allocator {
        private int[] mem;
        private Map<Integer, List<Integer>> idm = new HashMap<>();

        public Allocator(int n) {
            mem = new int[n];
        }

        public int allocate(int size, int mID) {
            int n = mem.length;
            int i = 0;
            while (i < n) {
                if (mem[i] == 1) {
                    ++i;
                    continue;
                }
                int j = i + 1;
                while (j < n && mem[j] == 0) {
                    ++j;
                }
                /// i... j-1
                if (j - i >= size) {
                    for (int k = i; k < i + size; ++k) {
                        idm.computeIfAbsent(mID, p -> new ArrayList<>()).add(k);
                        mem[k] = 1;
                    }
                    return i;
                } else {
                    i = j;
                }
            }
            return -1;
        }

        public int free(int mID) {
            if (!idm.containsKey(mID)) {
                return 0;
            }
            int res = 0;
            for (int fi : idm.get(mID)) {
                mem[fi] = 0;
                ++res;
            }
            idm.remove(mID);
            return res;
        }
    }

    class Allocator2 {
        // solution 2 is to use the intervals. note we must merge with prev and next alike
        private TreeMap<Integer, Integer> ts = new TreeMap<>();
        private HashMap<Integer, Map<Integer, Integer>> mm = new HashMap<>();

        public Allocator2(int n) {
            ts.put(0, n - 1);
        }

        public int allocate(int size, int mID) {
            int res = -1;
            for (int s : ts.keySet()) {
                int e = ts.get(s);
                int clen = e - s + 1;
                if (clen >= size) {
                    res = s;
                    ts.remove(s);
                    if (clen > size) {
                        ts.put(s + size, e);
                    }
                    Map<Integer, Integer> cm = mm.getOrDefault(mID, new HashMap<>());
                    cm.put(s, s + size - 1);
                    mm.put(mID, cm);
                    break;
                }
            }
            return res;
        }

        public int free(int mID) {
            if (!mm.containsKey(mID)) {
                return 0;
            }
            Map<Integer, Integer> freed = mm.getOrDefault(mID, new HashMap<>());
            int res = 0;
            for (int s : freed.keySet()) {
                int e = freed.get(s);
                int nnext = e;
                int nstart = s;
                res += e-s+1;
                Integer next = ts.higherKey(e);
                if(next != null && next == e+1){
                    nnext = ts.get(next);
                    ts.remove(next);
                }
                Integer prev = ts.lowerKey(s);
                if(prev != null && ts.get(prev)+1 == s){
                    nstart = prev;
                    ts.remove(prev);
                }
                ts.put(nstart, nnext);
            }
            mm.remove(mID);
            return res;
        }
    }
}
