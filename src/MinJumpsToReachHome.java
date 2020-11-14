import java.util.ArrayDeque;
import java.util.Deque;

public class MinJumpsToReachHome {
    // note the magic number 4000... similar to race car
    public int minimumJumps(int[] f, int a, int b, int x) {

        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0, 0, 0});
        int limit = 4000;
        // 2* (given number limits) because if we go forward beyond 4000, we should just go back first since we will go back anyway later. note between 2000 and 4000 there is no forbidden...
        boolean[][] seen = new boolean[limit + 1][2];
        for (int i = 0; i < f.length; i++) {
            if (f[i] < seen.length) {
                seen[f[i]][0] = true;
                seen[f[i]][1] = true;
            }
        }
        while (!q.isEmpty()) {
            int[] top = q.poll();
            //  System.out.println(top[0]);
            int pos = top[0];
            int dist = top[1];
            int back = top[2];
            if (pos == x) {
                return dist;
            } else {
                int npos1 = pos + a;
                if (valid(npos1, limit, seen, back)) {
                    seen[npos1][back] = true;
                    q.offer(new int[]{npos1, dist + 1, 0});
                }
                int npos2 = pos - b;
                if (valid(npos2, limit, seen, back) && back == 0) {
                    seen[npos2][back] = true;
                    q.offer(new int[]{npos2, dist + 1, 1});
                }
            }
        }
        return -1;
    }


    private boolean valid(int i, int limit, boolean[][] seen, int back) {
        return i >= 0 && i <= limit && !seen[i][back];
    }
}
