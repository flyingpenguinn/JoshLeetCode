import base.ArrayUtils;

import java.util.Arrays;

public class MaxWallsDestroyedByRobot {
    class Rob {
        int pos;
        int dist;

        public Rob(int pos, int dist) {
            this.pos = pos;
            this.dist = dist;
        }
    }

    // dp on i and previous direction!
    public int maxWalls(int[] robots, int[] distance, int[] walls) {
        int n = robots.length;
        Rob[] a = new Rob[n];
        for (int i = 0; i < n; ++i) {
            a[i] = new Rob(robots[i],
                    distance[i]);
        }
        Arrays.sort(walls);
        Arrays.sort(a, (x, y) -> Integer.compare(x.pos, y.pos));
        dp = new int[n][3];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(dp[i], -1);
        }
        return solve(a, walls, 0, 2);
    }

    private int range(int l, int r, int[] walls) {
        if(l>r){
            return 0;
        }
        // num of <=r
        int binary1 = binary(walls, r);
        int binary2 = binary(walls, l - 1);
        return binary1 - binary2;
    }


    private int binary(int[] a, int t) {
        int l = 0;
        int u = a.length - 1;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (a[mid] <= t) {
                l = mid + 1;
            } else {
                u = mid - 1;
            }
        }
        return l;
    }

    private int[][] dp;

    // pre==1 means on right
    private int solve(Rob[] a, int[] walls, int i, int pre) {
        int n = a.length;
        if (i == n) {
            return 0;
        }
        if (dp[i][pre] != -1) {
            return dp[i][pre];
        }
        int left = Math.max(1, a[i].pos - a[i].dist);
        int right = a[i].pos + a[i].dist;
        if (i > 0) {
            left = Math.max(a[i - 1].pos +1, left);
            if (pre==1) {
                int last = a[i - 1].pos + a[i - 1].dist;
                left = Math.max(last + 1, left);
            }
        }
        if (i + 1 < n) {
            right = Math.min(right, a[i + 1].pos - 1);
        }
        int leftshot = range(left, a[i].pos, walls);
        int way1 = leftshot + solve(a, walls, i + 1, 0);
        int rightshot = range(a[i].pos, right, walls);
        int way2 = rightshot + solve(a, walls, i + 1, 1);
        int res = Math.max(way1, way2);
        dp[i][pre] = res;
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new MaxWallsDestroyedByRobot().maxWalls(ArrayUtils.read1d("4"), ArrayUtils.read1d("3"), ArrayUtils.read1d("1,0")));
    }
}
