import java.util.*;

public class StrangePrinterII {
    // if a color is contained in another then the former must print after the latter. we then topo sort the colors.
    // as long as we can sort the color, we can print them fine
    public boolean isPrintable(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        Map<Integer, Set<Integer>> g = new HashMap<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                g.putIfAbsent(a[i][j], new HashSet<>());
            }
        }
        for (int c : g.keySet()) {
            int mini = m;
            int minj = n;
            int maxi = -1;
            int maxj = -1;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (a[i][j] == c) {
                        mini = Math.min(mini, i);
                        minj = Math.min(minj, j);
                        maxi = Math.max(maxi, i);
                        maxj = Math.max(maxj, j);
                    }
                }
            }
            for (int i = mini; i <= maxi; i++) {
                for (int j = minj; j <= maxj; j++) {
                    if (a[i][j] != c) {
                        g.computeIfAbsent(a[i][j], k -> new HashSet<>()).add(c);
                    }
                }
            }
        }
        Map<Integer, Integer> st = new HashMap<>();
        List<Integer> topo = new ArrayList<>();
        for (int c : g.keySet()) {
            if (st.getOrDefault(c, 0) == 0) {
                if (!dfs(c, st, topo, g)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean dfs(int c, Map<Integer, Integer> st, List<Integer> topo, Map<Integer, Set<Integer>> g) {
        st.put(c, 1);
        for (int next : g.getOrDefault(c, new HashSet<>())) {
            int nst = st.getOrDefault(next, 0);
            if (nst == 1) {
                return false;
            } else if (nst == 0) {
                if (!dfs(next, st, topo, g)) {
                    return false;
                }
            }
        }
        st.put(c, 2);
        topo.add(c);
        return true;
    }
}
