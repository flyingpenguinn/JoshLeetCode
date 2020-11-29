import base.ArrayUtils;

import java.util.*;

public class MinJumpsToReachHome {
    // note the magic number 6000... similar to race car
    public int minimumJumps(int[] f, int a, int b, int x) {

        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0, 0, 0});
        int limit = 6000;
        // actually max(forbidden, x)+a+b
        boolean[][] seen = new boolean[limit + 1][2];
        for (int i = 0; i < f.length; i++) {
            if (f[i] < seen.length) {
                seen[f[i]][0] = true;
                seen[f[i]][1] = true;
            }
        }
        while (!q.isEmpty()) {
            int[] top = q.poll();
            int pos = top[0];
            int dist = top[1];
            int back = top[2];
            if (pos == x) {
                return dist;
            } else {
                int npos1 = pos + a;
                if (valid(npos1, limit, seen, 0)) {
                    seen[npos1][0] = true;
                    q.offer(new int[]{npos1, dist + 1, 0});
                }
                int npos2 = pos - b;
                if (valid(npos2, limit, seen, 1) && back == 0) {
                    seen[npos2][1] = true;
                    q.offer(new int[]{npos2, dist + 1, 1});
                }
            }
        }
        return -1;
    }


    private boolean valid(int i, int limit, boolean[][] seen, int back) {
        return i >= 0 && i <= limit && !seen[i][back];
    }

    public static void main(String[] args) {

        System.out.println(new MinJumpsToReachHome().minimumJumps(ArrayUtils.read1d("[549, 693, 456, 1814, 1609]"), 748, 889, 545));
    }
}

class JumpCorrect {
    public int minimumJumps(int[] forbidden, int a, int b, int x) {
        Deque<int[]> pq = new ArrayDeque<>();
        pq.offer(new int[]{0, 0, 0});//step, current index, direction(0 is back, 1 is forward)
        Set<Integer> forbit = new HashSet<>();
        Set<String> visited = new HashSet<>();
        int maxLimit = 2000 + 2 * b;
        for (int num : forbidden) {
            forbit.add(num);
            maxLimit = Math.max(maxLimit, num + 2 * b);
        }
        while (!pq.isEmpty()) {
            int[] node = pq.poll();
            System.out.println(Arrays.toString(node));

            int idx = node[0];
            int step = node[1];
            int dir = node[2];
            if (idx == x) return step;
            //try jump forward
            if (idx + a < maxLimit && !forbit.contains(idx + a) && !visited.contains(idx + a + "," + 0)) {
                visited.add(idx + a + "," + 0);
                pq.offer(new int[]{idx + a, step + 1, 0});
            }
            //try jump back
            if (idx - b >= 0 && !forbit.contains(idx - b) && !visited.contains(idx - b + "," + 1) && dir != 1) {
                visited.add(idx - b + "," + 1);
                pq.offer(new int[]{idx - b, step + 1, 1});
            }
        }
        return -1;
    }
}
