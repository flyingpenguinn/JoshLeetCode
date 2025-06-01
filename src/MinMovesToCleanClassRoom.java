import java.util.*;

public class MinMovesToCleanClassRoom {
    static class Status {
        int i;
        int j;
        int lstatus;
        int energy;
        int moves;

        public Status(int i, int j, int lstatus, int energy, int moves) {
            this.i = i;
            this.j = j;
            this.lstatus = lstatus;
            this.energy = energy;
            this.moves = moves;
        }
    }

    private final int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int minMoves(String[] a, int energy) {
        int m = a.length;
        int n = a[0].length();
        int Max = (int) 1e9;
        Deque<Status> q = new ArrayDeque<>();
        int starti = -1;
        int startj = -1;
        Map<Integer, Map<Integer, Integer>> lm = new HashMap<>();
        int lindex = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                final char v = a[i].charAt(j);
                if (v == 'L') {
                    lm.computeIfAbsent(i, p -> new HashMap<>()).put(j, lindex);
                    lindex += 1;
                } else if (v == 'S') {
                    starti = i;
                    startj = j;
                }
            }
        }
        int[][][][] dist = new int[m][n][energy + 1][(1 << lindex)];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                for (int k = 0; k <= energy; ++k) {
                    Arrays.fill(dist[i][j][k], Max);
                }
            }
        }
        q.offerLast(new Status(starti, startj, 0, energy, 0));
        while (!q.isEmpty()) {
            Status top = q.pollFirst();
            int i = top.i;
            int j = top.j;

            int lstatus = top.lstatus;
            int cenergy = top.energy;

            int cmoves = top.moves;
            if (top.lstatus + 1 == (1 << lindex)) {
                return cmoves;
            }
            if (cenergy == 0 && a[i].charAt(j) != 'R') {
                continue;
            }
            int nmoves = cmoves + 1;
            for (int[] d : dirs) {
                int ni = i + d[0];
                int nj = j + d[1];
                if (ni >= 0 && ni < m && nj >= 0 && nj < n) {
                    char nv = a[ni].charAt(nj);
                    if (nv == 'X') {
                        continue;
                    }
                    int nenergy = cenergy - 1;
                    if (nv == 'R') {
                        nenergy = energy;
                    }

                    int nlstatus = lstatus;
                    if (nv == 'L') {
                        int nlindex = lm.get(ni).get(nj);
                        if ((((lstatus >> nlindex) & 1) == 0)) {
                            nlstatus |= (1 << nlindex);
                        }
                    }
                    if (dist[ni][nj][nenergy][nlstatus] > nmoves) {
                        dist[ni][nj][nenergy][nlstatus] = nmoves;
                        q.offerLast(new Status(ni, nj, nlstatus, nenergy, nmoves));
                    }

                }
            }
        }
        return -1;
    }
}
