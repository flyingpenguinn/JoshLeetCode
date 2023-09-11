import java.util.*;

public class MinMovesSpreadStoneOverGrid {
    private int[][] target = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
    private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private class Status {
        int[][] grid = new int[3][3];
        int dist;

        public Status(int[][] grid, int dist) {
            this.grid = grid;
            this.dist = dist;
        }
    }

    private long getstatus(int[][] a) {
        long status = 0;
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                int dig = a[i][j];
                status = status * 10 + dig;
            }
        }
        return status;
    }

    private int[][] copy(int[][] a) {
        int[][] res = new int[3][3];
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                res[i][j] = a[i][j];
            }
        }
        return res;
    }

    public int minimumMoves(int[][] a) {

        Set<Long> seen = new HashSet<>();
        Deque<Status> q = new ArrayDeque<>();
        q.offer(new Status(a, 0));
        seen.add(getstatus(a));
        while (!q.isEmpty()) {
            Status top = q.poll();
            int[][] cgrid = top.grid;
            int step = top.dist;
            if (Arrays.deepEquals(cgrid, target)) {
                return step;
            }
            //  System.out.println(Arrays.deepToString(cgrid)+" "+step);
            if (step > 100) {
                continue;
            }
            for (int i = 0; i < 3; ++i) {
                for (int j = 0; j < 3; ++j) {
                    if (cgrid[i][j] > 1) {
                        for (int[] d : dirs) {
                            int ni = i + d[0];
                            int nj = j + d[1];
                            if (ni >= 0 && ni < 3 && nj >= 0 && nj < 3) {
                                int[][] ngrid = copy(cgrid);
                                --ngrid[i][j];
                                ++ngrid[ni][nj];
                                long nstatus = getstatus(ngrid);
                                if (!seen.contains(nstatus)) {
                                    seen.add(nstatus);
                                    q.offer(new Status(ngrid, step + 1));
                                }
                            }
                        }
                    }
                }
            }
        }
        return -1;
    }
}
