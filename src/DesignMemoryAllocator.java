import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DesignMemoryAllocator {
    // can also be treeset and internal based. when freed, merge with previous and next chunk. dont do next chunk only!
    class Allocator {
        private int[] mem;
        private Map<Integer, List<Integer>> idm = new HashMap<>();

        public Allocator(int n) {
            mem = new int[n];
        }

        public int allocate(int size, int mID) {
            int count = 0;
            int res = -1;
            for (int i = 0; i < mem.length; ++i) {
                if (mem[i] == 0) {
                    ++count;
                } else {
                    count = 0;
                }
                if (count == size) {
                    for (int j = i - count + 1; j <= i; ++j) {
                        mem[j] = 1;
                        idm.computeIfAbsent(mID, k -> new ArrayList<>()).add(j);
                    }
                    res = i - count + 1;
                    break;
                }
            }
            //  System.out.println(Arrays.toString(mem));
            return res;
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
}
