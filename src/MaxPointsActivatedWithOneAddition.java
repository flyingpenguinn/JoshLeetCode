import java.util.*;

public class MaxPointsActivatedWithOneAddition {
    private static class UF {
        public int[] pa;
        public int[] psize;

        public UF(int size) {
            pa = new int[size];
            psize = new int[size];
            Arrays.fill(pa, -1);
            Arrays.fill(psize, 1);
        }

        public int find(int i) {
            if (pa[i] == -1) {
                return i;
            }
            int rt = find(pa[i]);
            pa[i] = rt;
            return rt;
        }

        public boolean union(int i, int j) {
            int ai = find(i);
            int aj = find(j);
            if (ai == aj) {
                return false;
            }
            if (psize[ai] < psize[aj]) {
                pa[ai] = aj;
                psize[aj] += psize[ai];
            } else {
                pa[aj] = ai;
                psize[ai] += psize[aj];
            }
            return true;
        }

    }

    public int maxActivated(int[][] ps) {
        int n = ps.length;
        UF uf = new UF(n);
        Map<Integer, List<Integer>> rm = new HashMap<>();
        Map<Integer, List<Integer>> cm = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            int[] p = ps[i];
            int r = p[0];
            int c = p[1];
            rm.computeIfAbsent(r, k -> new ArrayList<>()).add(i);
            cm.computeIfAbsent(c, k -> new ArrayList<>()).add(i);
        }
        for (int rk : rm.keySet()) {
            List<Integer> list = rm.get(rk);
            for (int j = 0; j + 1 < list.size(); ++j) {
                int cv = list.get(j);
                int cv2 = list.get(j + 1);
                uf.union(cv, cv2);
            }
        }
        for (int ck : cm.keySet()) {
            List<Integer> list = cm.get(ck);
            for (int j = 0; j + 1 < list.size(); ++j) {
                int cv = list.get(j);
                int cv2 = list.get(j + 1);
                uf.union(cv, cv2);
            }
        }
        List<Integer> sizes = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            if (uf.find(i) == i) {
                sizes.add(uf.psize[i]);
            }
        }
        Collections.sort(sizes, Collections.reverseOrder());
        if (sizes.size() == 1) {
            return sizes.get(0) + 1;
        } else {
            return sizes.get(0) + sizes.get(1) + 1;
        }
    }
}
