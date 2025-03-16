import java.util.*;

public class LongestSpecialPathII {
    // TODO
    private List<List<int[]>> adj;
    private int[] nums;
    private List<Integer> pre;
    private TreeMap<Integer, Integer> res;
    private Map<Integer, List<Integer>> occurrence;
    private Map<Integer, Integer> mpLockup;
    private TreeMap<Integer, Integer> lockups;
    private int n;

    private void addLockup(int x) {
        lockups.put(x, lockups.getOrDefault(x, 0) + 1);
    }

    private void removeLockup(int x) {
        if (!lockups.containsKey(x)) return;
        int c = lockups.get(x);
        if (c == 1) lockups.remove(x);
        else lockups.put(x, c - 1);
    }

    private Integer secondToLast() {
        if (lockups.isEmpty()) return null;
        Map.Entry<Integer, Integer> last = lockups.lastEntry();
        if (last.getValue() >= 2) return last.getKey();
        Map.Entry<Integer, Integer> lower = lockups.lowerEntry(last.getKey());
        return (lower == null) ? null : lower.getKey();
    }

    private void dfs(int v, int parent, int startDepth, int currDepth) {
        int val = nums[v];
        occurrence.putIfAbsent(val, new ArrayList<>());
        List<Integer> occList = occurrence.get(val);
        if (occList.size() > 1) {
            int l2 = occList.get(occList.size() - 2);
            startDepth = Math.max(startDepth, l2 + 1);
        }
        occList.add(currDepth);
        if (occList.size() > 1) {
            int newVal = occList.get(occList.size() - 2);
            if (mpLockup.containsKey(val)) removeLockup(mpLockup.get(val));
            mpLockup.put(val, newVal);
            addLockup(newVal);
        }
        Integer sec = secondToLast();
        int lockup = (sec == null) ? 0 : (sec + 1);
        int nodes = currDepth - Math.max(startDepth, lockup) + 1;
        int edges = nodes - 1;
        if (edges > 0) {
            int sz = pre.size();
            int length = pre.get(sz - 1);
            if (sz - edges - 1 >= 0) length -= pre.get(sz - edges - 1);
            Integer oldVal = res.get(length);
            if (oldVal == null) res.put(length, nodes);
            else res.put(length, Math.min(oldVal, nodes));
        }
        for (int[] e : adj.get(v)) {
            int nxt = e[0], dist = e[1];
            if (nxt != parent) {
                if (pre.isEmpty()) pre.add(dist);
                else pre.add(pre.get(pre.size() - 1) + dist);
                dfs(nxt, v, startDepth, currDepth + 1);
                pre.remove(pre.size() - 1);
            }
        }
        occList.remove(occList.size() - 1);
        if (mpLockup.containsKey(val)) removeLockup(mpLockup.get(val));
        if (occList.size() > 1) {
            int newVal = occList.get(occList.size() - 2);
            mpLockup.put(val, newVal);
            addLockup(newVal);
        } else {
            mpLockup.remove(val);
        }
    }

    public int[] longestSpecialPath(int[][] edges, int[] nums) {
        n = edges.length + 1;
        adj = new ArrayList<>(n);
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] e : edges) {
            adj.get(e[0]).add(new int[]{e[1], e[2]});
            adj.get(e[1]).add(new int[]{e[0], e[2]});
        }
        this.nums = nums;
        pre = new ArrayList<>();
        res = new TreeMap<>();
        occurrence = new HashMap<>();
        mpLockup = new HashMap<>();
        lockups = new TreeMap<>();
        res.put(0, 1);
        dfs(0, -1, 0, 0);
        int key = res.lastKey();
        return new int[]{key, res.get(key)};
    }
}
