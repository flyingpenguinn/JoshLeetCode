import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class EqualSumGridPartitionII {
    private void update(Map<Long, Long> m, long k, long d) {
        long nv = m.getOrDefault(k, 0L) + d;
        if (nv <= 0) {
            m.remove(k);
        } else {
            m.put(k, nv);
        }
    }


    public boolean canPartitionGrid(int[][] ia) {
        int m = ia.length;
        int n = ia[0].length;
        if (m == 1 && n == 1) {
            return false;
        }
        long[][] a = new long[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                a[i][j] = ia[i][j];
            }
        }

        long[] cols = new long[n];
        long allsum = 0;
        Map<Long, Long> allm = new HashMap<>();
        for (int j = 0; j < n; ++j) {
            long csum = 0;
            for (int i = 0; i < m; ++i) {
                csum += a[i][j];
                update(allm, a[i][j], 1);
            }
            cols[j] = csum;
            allsum += csum;

        }
        Map<Long, Long> rm = new HashMap<>(allm);
        long leftsum = 0;
        Set<Long> leftset = new HashSet<>();

        for (int j = 0; j < n; ++j) {
            long rightsum = allsum - leftsum;
            if (leftsum == rightsum) {
                return true;
            }
            long diff = leftsum - rightsum;
            if (leftset.contains(diff)) {
                return true;
            }
            long diff2 = rightsum - leftsum;
            if (rm.containsKey(diff2)) {
                return true;
            }
            if (j == 0) {
                leftset.add(a[0][0]);
                leftset.add(a[m - 1][0]);
            } else {
                if (j == 1) {
                    for (int i = 1; i < m - 1; ++i) {
                        leftset.add(a[i][0]);
                    }
                }
                if (m != 1) {
                    for (int i = 0; i < m; ++i) {
                        leftset.add(a[i][j]);
                    }
                }
            }
            if (j == n - 2) {
                for (int i = 1; i < m - 1; ++i) {
                    update(rm, a[i][n - 1], -1);
                }
            }
            for (int i = 0; i < m; ++i) {
                update(rm, a[i][j], -1);
            }
            leftsum += cols[j];
        }

        // rows
        long[] rows = new long[m];
        for (int i = 0; i < m; ++i) {
            long rsum = 0;
            for (int j = 0; j < n; ++j) {
                rsum += a[i][j];
            }
            rows[i] = rsum;
        }
        leftsum = 0;
        leftset = new HashSet<>();
        rm = new HashMap<>(allm);
        for (int i = 0; i < m; ++i) {
            long rightsum = allsum - leftsum;
            if (leftsum == rightsum) {
                return true;
            }
            long diff = leftsum - rightsum;
            if (leftset.contains(diff)) {
                return true;
            }
            long diff2 = rightsum - leftsum;
            if (rm.containsKey(diff2)) {
                return true;
            }
            if (i == 0) {
                leftset.add(a[0][0]);
                leftset.add(a[0][n - 1]);
            } else {
                if (i == 1) {
                    for (int j = 1; j < n - 1; ++j) {
                        leftset.add(a[0][j]);
                    }
                }
                if (n != 1) {
                    for (int j = 0; j < n; ++j) {
                        leftset.add(a[i][j]);
                    }
                }
            }
            if (i == m - 2) {
                for (int j = 1; j < n - 1; ++j) {
                    update(rm, a[m - 1][j], -1);
                }
            }
            for (int j = 0; j < n; ++j) {
                update(rm, a[i][j], -1);
            }
            leftsum += rows[i];
        }
        return false;
    }
}
