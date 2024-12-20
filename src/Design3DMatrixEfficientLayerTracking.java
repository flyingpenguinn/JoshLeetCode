import java.util.TreeMap;
import java.util.TreeSet;

public class Design3DMatrixEfficientLayerTracking {
    class matrix3D {
        private int[][][] cnt;
        private int[] rcnt;
        private TreeMap<Integer, TreeSet<Integer>> m = new TreeMap<>();

        public matrix3D(int n) {
            cnt = new int[n][n][n];
            rcnt = new int[n];
            for (int i = 0; i < n; ++i) {
                m.computeIfAbsent(0, k -> new TreeSet<>()).add(i);
            }
        }

        public void setCell(int x, int y, int z) {
            if (cnt[x][y][z] == 1) {
                return;
            }
            cnt[x][y][z] = 1;
            int ov = rcnt[x];
            int nv = ov + 1;
            update(ov, nv, x);

            //  System.out.println(m);
            rcnt[x] = nv;
        }

        private void update(int ov, int nv, int i) {
            TreeSet<Integer> os = m.getOrDefault(ov, new TreeSet<>());
            os.remove(i);
            if (os.isEmpty()) {
                m.remove(ov);
            } else {
                m.put(ov, os);
            }

            TreeSet<Integer> ns = m.getOrDefault(nv, new TreeSet<>());
            ns.add(i);

            m.put(nv, ns);

        }

        public void unsetCell(int x, int y, int z) {
            if (cnt[x][y][z] == 0) {
                return;
            }
            cnt[x][y][z]--;
            int ov = rcnt[x];

            int nv = ov - 1;
            update(ov, nv, x);

            //  System.out.println(m);
            rcnt[x] = nv;
        }

        public int largestMatrix() {
            if (m.isEmpty()) {
                return -1;
            }
            int maxk = m.lastKey();
            //    System.out.println("maxk is "+maxk);
            return m.get(maxk).last();
        }
    }
}
