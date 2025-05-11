import java.util.*;

public class MinOperationsToConvertAllToZero {
    public int minOperations(int[] a) {
        int n = a.length;
        Map<Integer, List<Integer>> m = new HashMap<>();
        int maxv = 0;
        TreeSet<Integer> cur = new TreeSet<>();
        for (int i = 0; i < n; ++i) {
            int v = a[i];
            m.computeIfAbsent(v, k -> new ArrayList<>()).add(i);
            maxv = Math.max(v, maxv);
            if (v == 0) {
                cur.add(i);
            }
        }
        int res = 0;
        for (int v = 1; v <= maxv; ++v) {
            List<Integer> pos = m.getOrDefault(v, new ArrayList<>());
            Collections.sort(pos);
            if (pos.isEmpty()) {
                continue;
            }
            int i = 0;
            int pn = pos.size();
            int cres = 1;
            while (i < pn) {
                int j = i;
                Integer bar = cur.higher(pos.get(i));
                if (bar == null) {
                    break;
                }
                while (j < pn && pos.get(j) < bar) {
                    ++j;
                }
                if (j == pn) {
                    break;
                } else {
                    i = j;
                    ++cres;
                }
            }
            cur.addAll(pos);
            res += cres;
        }
        return res;
    }
}

class MinOperationsConvertAllStack {
    public int minOperations(int[] nums) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0);
        int res = 0;
        for (int a : nums) {
            while (!stack.isEmpty() && stack.peek() > a) {
                stack.pop();
            }
            if (stack.isEmpty() || stack.peek() < a) {
                res++;
                stack.push(a);
            }
        }
        return res;
    }
}
