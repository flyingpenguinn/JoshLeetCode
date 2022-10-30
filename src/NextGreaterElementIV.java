import java.util.*;

public class NextGreaterElementIV {
    public int[] secondGreaterElement(int[] a) {
        int n = a.length;
        Map<Integer, Set<Integer>> m = new HashMap<>();
        Deque<Integer> st = new ArrayDeque<>();
        for (int i = 0; i < n; ++i) {
            while (!st.isEmpty() && a[st.peek()] < a[i]) {
                m.computeIfAbsent(i, k -> new HashSet<>()).add(st.pop());
            }
            st.push(i);
        }
        st.clear();
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        int[] res = new int[n];
        Arrays.fill(res, -1);
        for (int i = n - 1; i >= 0; --i) {
            for (int pre : m.getOrDefault(i, new HashSet<>())) {
                Integer next = tm.higherKey(a[pre]);
                if (next != null) {
                    res[pre] = next;
                }
            }
            while (!st.isEmpty() && st.peek() <= a[i]) {
                int popped = st.pop();
                update(tm, popped, -1);
            }
            st.push(a[i]);
            update(tm, a[i], 1);
        }
        return res;
    }

    private void update(TreeMap<Integer, Integer> m, int k, int d) {
        int nv = m.getOrDefault(k, 0) + d;
        if (nv <= 0) {
            m.remove(k);
        } else {
            m.put(k, nv);
        }
    }
}
