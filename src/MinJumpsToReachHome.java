import base.ArrayUtils;

import java.util.*;

public class MinJumpsToReachHome {
    public int minimumJumps(int[] fbd, int a, int b, int x) {
//TODO for limit we may need a mathematical proof
        int maxfbd = 0;
        for (int i = 0; i < fbd.length; i++) {
            maxfbd = Math.max(maxfbd, fbd[i]);
        }
        int limit = Math.max(x, maxfbd) + a + b;
        boolean[][] seen = new boolean[limit + 1][2];
        boolean[] fb = new boolean[limit + 1];
        for (int i = 0; i < fbd.length; i++) {
            fb[fbd[i]] = true;
        }
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0, 0, 0});

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
                if (valid(npos1, limit, seen, fb, 0)) {
                    seen[npos1][0] = true;
                    q.offer(new int[]{npos1, dist + 1, 0});
                }
                int npos2 = pos - b;
                if (valid(npos2, limit, seen, fb, 1) && back == 0) {
                    seen[npos2][1] = true;
                    q.offer(new int[]{npos2, dist + 1, 1});
                }
            }
        }
        return -1;
    }


    private boolean valid(int i, int limit, boolean[][] seen, boolean[] fb, int back) {
        return i >= 0 && i <= limit && !seen[i][back] && !fb[i];
    }

    private int lcm(int a, int b) {
        int gcd = gcd(a, b);
        return a * b / gcd;
    }

    private int gcd(int a, int b) {
        if (a < b) {
            return gcd(b, a);
        } else {
            return b == 0 ? a : gcd(b, a % b);
        }
    }
}
